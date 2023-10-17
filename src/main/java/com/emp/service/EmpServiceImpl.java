package com.emp.service;

import com.emp.bean.Emp;
import com.emp.dao.EmpDao;
import com.emp.dao.EmpDaoImpl;

import java.util.List;

public class EmpServiceImpl implements EmpService{
    EmpDao empDao = new EmpDaoImpl();
    @Override
    public List<Emp> getAllEmp() {
       return empDao.selectAll();
    }

    @Override
    public Emp getEmp(int empno) {
        return empDao.selectByeno(empno);
    }

    @Override
    public boolean updateEmpByEmpno(Emp emp) {
        return empDao.updateEmpByEmpno(emp);
    }

    @Override
    public boolean deleteEmpByEmpno(int empno) {
        return empDao.deleteEmp(empno);
    }

    @Override
    public boolean addEmp(Emp emp) {
        return empDao.addEmp(emp);
    }

    @Override
    public List<Emp> selectEmpByEname(String ename) {
        return empDao.selectByEname(ename);
    }

    @Override
    public List<Emp> selectByPage(int pageNo, int pageSize) {
        return empDao.selectByPage(pageNo,pageSize);
    }

    @Override
    public int getTotalRecords() {
        return empDao.getTotal();
    }
}
