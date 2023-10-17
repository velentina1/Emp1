package com.emp.controller;

import com.emp.bean.Emp;
import com.emp.service.EmpService;
import com.emp.service.EmpServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "selectEmpServlet", value = "/selectEmpServlet")
public class ServletSelectEmp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String ename = request.getParameter("ename");
        if (ename == null || ename == "") {
            response.sendRedirect(request.getContextPath() + "/empListServlet");
        }else {
            EmpService empService = new EmpServiceImpl();
            List<Emp> empList = empService.selectEmpByEname(ename);
            request.setAttribute("empList",empList);
            request.getRequestDispatcher("/showEmp.jsp").forward(request,response);
        }
    }
}
