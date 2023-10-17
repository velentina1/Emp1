package com.emp.controller;

import com.emp.service.EmpService;
import com.emp.service.EmpServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/deleteEmpServlet")
public class ServerDeleteEmp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String empno = request.getParameter("empno");
        EmpService empService = new EmpServiceImpl();
        boolean result = empService.deleteEmpByEmpno(Integer.parseInt(empno));
        if (result) {
            String successScript = "<script>alert('删除成功');</script>";
            response.getWriter().write(successScript);
            response.setHeader("Refresh", "0;url=" + request.getContextPath() + "/empListServlet");
        }else {
            String failScript = "<script>alert('删除失败');</script>";
            response.getWriter().write(failScript);
        }
    }
}
