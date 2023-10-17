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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpService empService = new EmpServiceImpl();
        List<Emp> empList = empService.getAllEmp();
        request.setAttribute("empList",empList);
        request.getRequestDispatcher("/showEmp.jsp").forward(request,response);
    }
}
