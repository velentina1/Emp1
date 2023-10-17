<%@ page import="com.emp.service.EmpService" %>
<%@ page import="com.emp.service.EmpServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: wxl
  Date: 2023/10/16
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    int totalPages = calculateTotalPages(request); // 根据总记录数和每页记录数计算总页数
    int currentPage = getCurrentPage(request); // 从请求中获取当前页码

    // 限制 currentPage 在合理范围内
    if (currentPage < 1) {
        currentPage = 1;
    } else if (currentPage > totalPages) {
        currentPage = totalPages;
    }
%>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>查看全部员工信息列表</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="./css/bootstrap.min.css" >
    <style>
        a{
            font-size: 30px;
        }
        th,td{
            text-align: center;
        }
    </style>
</head>
<body>
<%--展示所有员工列表--%>
<div class="container">
    <h1 align="center">员工信息列表</h1>
    <a class="btn-default btn btn-success" href="addEmp.jsp">添加员工</a>
    <br>
    <br>

    <form class="form-inline" action="${pageContext.request.contextPath}/selectEmpServlet">
        <div class="form-group">
            <input type="text" class="form-control" name="ename" placeholder="请输入要查询的员工名">
        </div>
        <button type="submit" class="btn btn-default">查询</button>
    </form>
    <table class="table table-hover" border="1px">
        <tr class="success">
            <th>员工编号</th>
            <th>员工姓名</th>
            <th>员工岗位</th>
            <th>领导编号</th>
            <th>入职日期</th>
            <th>员工工资</th>
            <th>员工奖金</th>
            <th>部门编号</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${empList}" var="emp">
            <tr>
                <td>${emp.empno}</td>
                <td>${emp.ename}</td>
                <td>${emp.job}</td>
                <td>${emp.mgr}</td>
                <td>${emp.hiredate}</td>
                <td>${emp.sal}</td>
                <td>${emp.comm}</td>
                <td>${emp.deptno}</td>
                <td>
                    <a  class="btn btn-primary" href="${pageContext.request.contextPath}/findEmpServlet?empno=${emp.empno}">修改</a>
                    <a  class="btn btn-danger" href="javascript:deleteEmp(${emp.empno})">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <!-- 输入框用于设置pageSize -->
    <form action="${pageContext.request.contextPath}/empListServlet" method="post">
        <h6>每页显示记录数</h6>
        <input type="number" class="form-control" placeholder="每页显示记录数" name="pageSize" value="${pageSize}" style="width: 100px;"  />
        <h6>跳转页码</h6>
        <input type="number" class="form-control" placeholder="跳转页码" name="pageNo" value="${pageNo}" style="width: 100px;"  />
        <input type="submit" class="btn btn-default" value="设置" />
    <!-- 分页控件 -->
    <nav aria-label="Page navigation" align="center">
        <ul class="pagination justify-content-center pagination pagination-sm" >
            <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                <a class="page-link" href="${pageContext.request.contextPath}/empListServlet?pageNo=${pageNo-1}&pageSize=${pageSize}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <% for (int i = 1; i <= totalPages; i++) { %>
            <li class="page-item <%= currentPage == i ? "active" : "" %>">
                <a class="page-link" href="${pageContext.request.contextPath}/empListServlet?pageNo=<%= i %>&pageSize=${pageSize}">
                    <%= i %>
                </a>
            </li>
            <% } %>
            <li class="page-item <%= currentPage >= totalPages ? "disabled" : "" %>">
            <a class="page-link" href="${pageContext.request.contextPath}/empListServlet?pageNo=${pageNo+1}&pageSize=${pageSize}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
            </li>
        </ul>
    </nav>
    </form>



</div>
<script>
    function deleteEmp(empno){
        if (confirm("你确定删除这条记录吗？"))
        {
        location.href = "${pageContext.request.contextPath}/deleteEmpServlet?empno=" + empno;
        }
    }
</script>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="./js/jquery-3.3.1.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="./js/bootstrap.min.js"></script>
</body>
</html>
<%!
    // 计算总页数
    public int calculateTotalPages(HttpServletRequest request) {
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = 3;
        int TotalPages = 0;

        try {
            pageSize = Integer.parseInt(pageSizeStr);
            // 使用 pageSize 进行操作
        } catch (NumberFormatException e) {
            // 处理无效的 pageSize 输入，可以给用户一个错误消息或使用默认值
            e.printStackTrace();
        } finally {

            EmpService empService = new EmpServiceImpl();
            int totalRecords = empService.getTotalRecords();

            TotalPages =  (int) Math.ceil((double) totalRecords / pageSize);
        }
        return TotalPages;
    }

    // 获取当前页码
    public int getCurrentPage(HttpServletRequest request) {
        int pageNo = 1;
        String pageNoParam = request.getParameter("pageNo");

        if (pageNoParam != null && !pageNoParam.isEmpty()) {
            try {
                pageNo =  Integer.parseInt(pageNoParam);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // 打印异常信息
//                System.out.println("Error parsing pageNo: " + pageNoParam);
            }
        }

//        System.out.println("Current Page: " + pageNo); // 打印当前页码
        return pageNo;
    }
%>