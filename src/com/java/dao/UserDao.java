package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.model.User;
import com.java.util.StringUtil;

public class UserDao {
	public List<User> queryUser(Connection conn,User user) throws SQLException{
		ArrayList<User> userList=new ArrayList<>();
		String sql="select * from user";
		StringBuffer sb=new StringBuffer(sql);
		if(user!=null){
			if(user.getId()!=-1){
				sb.append(" and id="+user.getId());
			}
			if(StringUtil.isNotEmpty(user.getName())){
				sb.append(" and name like '%"+user.getName()+"%'");
			}
			if (StringUtil.isNotEmpty(user.getSex())){
				sb.append(" and sex='"+user.getSex()+"'");
			}
			
			sql=sb.toString().replaceFirst("and", "where");
		}
		PreparedStatement stat=conn.prepareStatement(sql);
		ResultSet rs=stat.executeQuery();
		while(rs.next()){
			User resultUser=new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setName(rs.getString("name"));
			resultUser.setAge(rs.getInt("age"));
			resultUser.setSex(rs.getString("sex"));
			resultUser.setBirthPlace(rs.getString("birthPlace"));
			resultUser.setPhone(rs.getString("phone"));
			resultUser.setEmail(rs.getString("email"));
			userList.add(resultUser);
		}
		return userList;
	}
	
	public User getUserById(Connection conn,int id) throws SQLException{
		User resultUser=null;
		String sql="select * from user where id=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1, id);
		ResultSet rs=stat.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setName(rs.getString("name"));
			resultUser.setAge(rs.getInt("age"));
			resultUser.setSex(rs.getString("sex"));
			resultUser.setBirthPlace(rs.getString("birthPlace"));
			resultUser.setPhone(rs.getString("phone"));
			resultUser.setEmail(rs.getString("email"));		
		}
		return resultUser;
		
		
	}
	
	public int insertUser(Connection conn,User user) throws SQLException{
		String sql="insert into user value(null,?,?,?,?,?,?)";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, user.getName());
		stat.setInt(2,user.getAge());
		stat.setString(3,user.getSex());
		stat.setString(4, user.getProvince()+user.getCity());
		stat.setString(5, user.getPhone());
		stat.setString(6,user.getEmail());
		return stat.executeUpdate();
	}
	
	public int updateUser(Connection conn,User user) throws SQLException{
		String sql="update user set name=?,age=?,sex=?,birthPlace=?,phone=?,email=? where id=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, user.getName());
		stat.setInt(2,user.getAge());
		stat.setString(3,user.getSex());
		stat.setString(4, user.getProvince()+user.getCity());
		stat.setString(5, user.getPhone());
		stat.setString(6,user.getEmail());
		stat.setInt(7,user.getId());
		return stat.executeUpdate();
	}
	
	public int deleteUser(Connection conn,int id) throws SQLException{
		String sql="delete from user where id=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1, id);
		return stat.executeUpdate();
	}
}
