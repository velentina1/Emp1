<%--
  Created by IntelliJ IDEA.
  User: wxl
  Date: 2023/10/17
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>首页</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="./css/bootstrap.min.css" >
</head>
<body>
<div class="container">
    <h1>修改员工信息页面</h1>
    <form action="${pageContext.request.contextPath}/updateEmpServlet" method="post">
        <form>
            <div class="form-group">
                <label for="empno">员工编号</label>
                <input type="text" name="empno" class="form-control" id="empno" value="${emp.empno}" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="ename">员工姓名</label>
                <input type="text" name="ename" class="form-control" id="ename" value="${emp.ename}">
            </div>
            <div class="form-group">
                <label for="job">员工岗位</label>
                <input type="text" name="job" class="form-control" id="job" value="${emp.job}">
            </div>
            <div class="form-group">
                <label for="mgr">领导编号</label>
                <input type="text" name="mgr" class="form-control" id="mgr" value="${emp.mgr}">
            </div>
            <div class="form-group">
                <label for="hiredate">入职日期</label>
                <input type="text" name="hiredate" class="form-control" id="hiredate" value="${emp.hiredate}">
            </div>
            <div class="form-group">
                <label for="sal">员工工资</label>
                <input type="text" name="sal" class="form-control" id="sal" value="${emp.sal}">
            </div>
            <div class="form-group">
                <label for="comm">员工奖金</label>
                <input type="text" name="comm" class="form-control" id="comm" value="${emp.comm}">
            </div>
            <div class="form-group">
                <label for="deptno">部门编号</label>
                <input type="text" name="deptno" class="form-control" id="deptno" value="${emp.deptno}">
            </div>

            <button type="submit" class="btn btn-default">修改</button>
        </form>
    </form>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="./js/jquery-3.3.1.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="./js/bootstrap.min.js"></script>
</body>
</html>