package com.java.action;

import com.java.util.ResponseUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.java.dao.DictDao;
import com.java.model.Dictionary;
import com.java.util.DbUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class DictAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DictDao dictDao=new DictDao();
	DbUtil dbUtil=new DbUtil();
	
	private String provCode;
	
	
	public String getProvCode() {
		return provCode;
	}


	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}


	public String getCityByProvince(){
		Connection conn=null;
		Dictionary provDict=new Dictionary();
		provDict.setItemcode(provCode);
		try {
			conn=dbUtil.getConnection();
			List<Dictionary> cityDict=dictDao.queryCitiesByProvince(conn,provDict);
			JSONArray jsonArray=JSONArray.fromObject(cityDict);
			System.out.println(jsonArray);
			ResponseUtil.write(jsonArray, ServletActionContext.getResponse());
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
