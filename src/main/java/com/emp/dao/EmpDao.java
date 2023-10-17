package com.emp.dao;

import com.emp.bean.Emp;

import java.util.List;

public interface EmpDao {
    boolean addEmp(Emp emp);
    boolean deleteEmp(int empno);
    boolean updateSal(int empno, double sal);

    Emp selectByeno(int empno);

    List<Emp> selectAll();

    List<Emp> selectByPage (int pageNo, int pageSize);

    List<Emp> selectByLike (String keyword);


    boolean updateEmpByEmpno(Emp emp);

    List<Emp> selectByEname(String ename);

    int getTotal();
}
