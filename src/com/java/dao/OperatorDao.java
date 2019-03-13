package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.model.Operator;

public class OperatorDao {
	public Operator query(Connection conn,Operator opt) throws SQLException{
		Operator resultOpt=null;
		String sql="SELECT * FROM operator WHERE username=? AND PASSWORD=?;";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, opt.getUsername());
		stat.setString(2, opt.getPassword());
		ResultSet rs=stat.executeQuery();
		if(rs.next()){
			resultOpt=new Operator();
			resultOpt.setUsername(rs.getString("userName"));
			resultOpt.setUsername(rs.getString("password"));
		}
		return resultOpt;
		
	}
}
