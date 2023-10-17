package com.emp.util;

import com.emp.bean.Emp;
import com.emp.dao.EmpDaoImpl;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        EmpDaoImpl empDao = new EmpDaoImpl();
        List<Emp> emps = empDao.selectAll();
        System.out.println(emps);

    }
}
