package com.emp.dao;

import com.emp.bean.Emp;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        String sql = "select * from emp where empno = ?";
//        BaseDao baseDao = new BaseDao();
//        Emp a = baseDao.QueryOne(sql,7369);
//        System.out.println(a);


        EmpDao empDao = new EmpDaoImpl();

//        System.out.println(empDao.selectByPage(2,10));
//        System.out.println(empDao.selectByLike("E"));
        System.out.println(empDao.selectByEname("SMITH"));
    }
}
