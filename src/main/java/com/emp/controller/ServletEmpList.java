package com.emp.controller;

import com.emp.bean.Emp;
import com.emp.service.EmpService;
import com.emp.service.EmpServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/empListServlet")
public class ServletEmpList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
        //以下是全部显示的代码
//        EmpService empService = new EmpServiceImpl();
//        List<Emp> empList = empService.getAllEmp();
//        request.setAttribute("empList",empList);
//        request.getRequestDispatcher("/showEmp.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageNo = 1; // 默认页码


        int pageSize = 3; // 默认每页记录数

        // 从请求参数中获取页码和每页记录数
        String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
        try {
            pageNo = Integer.parseInt(pageNoStr);
            pageSize = Integer.parseInt(pageSizeStr);
            // 使用 pageSize 进行操作
        } catch (NumberFormatException e) {
            // 处理无效的 pageSize 输入，可以给用户一个错误消息或使用默认值
            e.printStackTrace();
        } finally {
            if (pageNoStr != null && pageSizeStr != null) {
                pageNo = Integer.parseInt(pageNoStr);
                pageSize = Integer.parseInt(pageSizeStr);
            }

            EmpService empService = new EmpServiceImpl();
            int totalRecords = empService.getTotalRecords();
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            // 确保页码不会超过总页数
            if (pageNo < 1) {
                pageNo = 1;
            } else if (pageNo > totalPages) {
                pageNo = totalPages;
            }
//            request.setAttribute("pageNo", pageNo);
//
//            request.setAttribute("pageSize", pageSize);


            List<Emp> empList = empService.selectByPage(pageNo, pageSize);
            request.setAttribute("empList", empList);

// 传递分页参数到JSP页面
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("pageSize", pageSize);


            request.getRequestDispatcher("/showEmp.jsp").forward(request, response);
        }



    }
}
