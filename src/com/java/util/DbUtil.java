package com.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbUtil {
	//�����ȡ���ݿ����ӵķ���
	private String dburl="jdbc:mysql://127.0.0.1:3306/db_user";
	private String username="root";
	private String password="root";
	private String driver="com.mysql.jdbc.Driver";   //mysql-connector-java-8.0.12.jar����Ҫ����WEB/INF/lib��
	public Connection getConnection() throws Exception{
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(dburl,username,password);
		return  conn;
	}
	//����Ͽ����ӵķ���
	public void closeConnection(Connection conn) throws SQLException{
		if(conn!=null){
			conn.close();
		}
	}
	
	public static void main(String[] args){
	//���ڵ�Ԫ���ԣ�
		DbUtil dbutil=new DbUtil();
		Connection conn=null;
		try {
			conn = dbutil.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("�������ݿ�ʧ��");
		}
		if(conn!=null){
			System.out.println("�������ݿ�ɹ�");
			try {
				dbutil.closeConnection(conn);
				System.out.println("�ر����ݿ����ӳɹ�");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("�ر����ݿ�����ʧ��");
			}

		}else{
			System.out.println("���ݿ�����ʧ��");
		}
	}
}
