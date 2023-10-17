package com.emp.controller;

import com.emp.bean.Emp;
import com.emp.service.EmpService;
import com.emp.service.EmpServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/findEmpServlet")
public class ServletFindEmp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String empno = request.getParameter("empno");
        EmpService empService = new EmpServiceImpl();
        Emp emp = empService.getEmp(Integer.parseInt(empno));
        request.setAttribute("emp",emp);
        request.getRequestDispatcher("/updateEmp.jsp").forward(request,response);
    }
}
