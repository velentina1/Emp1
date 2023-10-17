package com.emp.dao;

import com.emp.bean.Emp;

import java.util.List;



public class EmpDaoImpl extends BaseDao implements EmpDao{



    @Override
    public boolean addEmp(Emp emp) {
        String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
        return update(sql,emp.getEmpno(),emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getComm(),emp.getDeptno());
    }

    @Override
    public boolean deleteEmp(int empno) {
        String sql = "delete from emp where empno = ?";
        return update(sql,empno);

    }

    @Override
    public boolean updateSal(int empno, double sal) {
        String sql = "update emp set sal = ? where empno = ?";
        return update(sql,sal,empno);
    }

    @Override
    public Emp selectByeno(int empno) {
        String sql = "select * from emp where empno = ?";
        return QueryOne(sql,empno);
    }

    @Override
    public List<Emp> selectAll() {
        String sql = "select * from emp";
        return QueryAll(sql);
    }

    @Override
    public List<Emp> selectByPage(int pageNo, int pageSize) {
        String sql = "select * from emp limit ? offset ?";
        int offset = (pageNo - 1) * pageSize;
        return QueryAll(sql, pageSize, offset);
    }


    @Override
    public List<Emp> selectByLike(String keyword) {
        String sql = "SELECT * FROM emp WHERE ename LIKE ?";
        String likeKeyword = "%" + keyword + "%";
        return QueryAll(sql, likeKeyword);
    }

    @Override
    public boolean updateEmpByEmpno(Emp emp) {
        String sql = "update emp set ename = ? ,job = ?, mgr = ? , hiredate = ? , sal = ? , comm = ?, deptno = ? where empno = ?";
        return update(sql,emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getComm(),emp.getDeptno(),emp.getEmpno());
    }

    @Override
    public List<Emp> selectByEname(String ename) {
        String sql = "select * from emp where ename = ?";
        return QueryAll(sql,ename);
    }

    @Override
    public int getTotal() {
        String sql = "SELECT COUNT(*) FROM emp";
        return Count(sql);
    }


}
