package com.emp.service;

import com.emp.bean.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> getAllEmp();
    Emp getEmp(int empno);



    boolean updateEmpByEmpno(Emp emp);

    boolean deleteEmpByEmpno(int i);

    boolean addEmp(Emp emp);

    List<Emp> selectEmpByEname(String ename);
}
