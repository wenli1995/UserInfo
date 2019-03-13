package com.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbUtil {
	//定义获取数据库连接的方法
	private String dburl="jdbc:mysql://127.0.0.1:3306/db_user";
	private String username="root";
	private String password="root";
	private String driver="com.mysql.jdbc.Driver";   //mysql-connector-java-8.0.12.jar包需要放在WEB/INF/lib下
	public Connection getConnection() throws Exception{
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(dburl,username,password);
		return  conn;
	}
	//定义断开连接的方法
	public void closeConnection(Connection conn) throws SQLException{
		if(conn!=null){
			conn.close();
		}
	}
	
	public static void main(String[] args){
	//用于单元测试；
		DbUtil dbutil=new DbUtil();
		Connection conn=null;
		try {
			conn = dbutil.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("连接数据库失败");
		}
		if(conn!=null){
			System.out.println("连接数据库成功");
			try {
				dbutil.closeConnection(conn);
				System.out.println("关闭数据库连接成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("关闭数据库连接失败");
			}

		}else{
			System.out.println("数据库数据失败");
		}
	}
}
