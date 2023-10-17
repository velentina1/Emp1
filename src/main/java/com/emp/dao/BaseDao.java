package com.emp.dao;

import com.emp.bean.Emp;
import com.emp.util.JDBCUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    public boolean update(String sql,Object...params)  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        connection = JDBCUtil.getConnection();
        preparedStatement = JDBCUtil.getPreparedStatement(sql,connection);
        JDBCUtil.bindPstmt(preparedStatement,params);
        int i = preparedStatement.executeUpdate();
        return i > 0 ? true :false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,preparedStatement,connection);
        }

        return false;
    }

    public Emp QueryOne(String sql,Object...params){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Emp emp = null;
        try{
        conn = JDBCUtil.getConnection();
        pstmt = JDBCUtil.getPreparedStatement(sql , conn);
        JDBCUtil.bindPstmt(pstmt,params);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            emp = new Emp();
            emp.setEmpno(rs.getInt("empno"));
            emp.setEname(rs.getString("ename"));
            emp.setJob(rs.getString("job"));
            emp.setMgr(rs.getInt("mgr"));
            emp.setHiredate(rs.getString("hiredate"));
            emp.setSal(rs.getDouble("sal"));
            emp.setComm(rs.getDouble("comm"));
            emp.setDeptno(rs.getInt("deptno"));
        }

        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }

        return emp;

    }

    public List<Emp> QueryAll (String sql, Object...params){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Emp> list = null;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = JDBCUtil.getPreparedStatement(sql , conn);
            JDBCUtil.bindPstmt(pstmt,params);
            rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();;

            Emp emp = null;
            list = new ArrayList<>();
            while (rs.next()){
                emp = new Emp();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    BeanUtils.setProperty(emp,
                            metaData.getColumnLabel(i+1),
                            rs.getObject(i+1));
                }
                list.add(emp);
//                for (Emp item : list) {
//                    System.out.println(item);
//                }
//                emp.setEmpno(rs.getInt("empno"));
//                emp.setEname(rs.getString("ename"));
//                emp.setJob(rs.getString("job"));
//                emp.setMgr(rs.getInt("mgr"));
//                emp.setHiredate(rs.getString("hiredate"));
//                emp.setSal(rs.getDouble("sal"));
//                emp.setComm(rs.getDouble("comm"));
//                emp.setDeptno(rs.getInt("deptno"));

            }

        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }

        return list;
    }
}
