package com.emp.controller;

import com.emp.bean.Emp;
import com.emp.service.EmpService;
import com.emp.service.EmpServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addEmpServlet")
public class ServletAddEmp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            Map<String, String[]> parameterMap = request.getParameterMap();
            Emp emp = new Emp();

            BeanUtils.populate(emp,parameterMap);

            EmpService empService = new EmpServiceImpl();
            boolean result = empService.addEmp(emp);
            if (result){
                String successScript = "<script>alert('添加成功');</script>";
                response.getWriter().write(successScript);
//                response.sendRedirect(request.getContextPath() + "/empListServlet");
                response.setHeader("Refresh", "0;url=" + request.getContextPath() + "/empListServlet");
            }else {
                String failScript = "<script>alert('添加失败');</script>";
                response.getWriter().write(failScript);
            }
            } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
