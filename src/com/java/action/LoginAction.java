package com.java.action;

import java.sql.Connection;
import java.sql.SQLException;

import com.java.dao.OperatorDao;
import com.java.model.Operator;
import com.java.util.DbUtil;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OperatorDao optDao=new OperatorDao();
	DbUtil dbUtil=new DbUtil();
	private Operator opt;

	public Operator getOpt() {
		return opt;
	}

	public void setOpt(Operator opt) {
		this.opt = opt;
	}
	
	public String login(){
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			Operator resultOpt=optDao.query(conn, opt);
			if(resultOpt!=null){
				return SUCCESS;
			}else{
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
		

	}
}
