package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.model.Dictionary;

public class DictDao {
	public List<Dictionary> query(Connection conn,Dictionary dict) throws SQLException{
		ArrayList<Dictionary> dictList=new ArrayList<>();
		String sql="SELECT * FROM dictionary WHERE itemname=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, dict.getItemname());
		ResultSet rs=stat.executeQuery();
		while(rs.next()){
			Dictionary resultDict=new Dictionary();
			resultDict.setItemid(rs.getInt("itemid"));
			resultDict.setItemname(rs.getString("itemname"));
			resultDict.setItemcode(rs.getString("itemcode"));
			resultDict.setItemdesc(rs.getString("itemdesc"));
			dictList.add(resultDict);
		}
		return dictList;
		
	}
	public String queryItemDesc(Connection conn,Dictionary dict) throws SQLException{
		String sql="SELECT itemdesc FROM dictionary WHERE itemname=? and itemcode=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, dict.getItemname());
		stat.setString(2, dict.getItemcode());
		ResultSet rs=stat.executeQuery();
		if(rs.next()){
			return  rs.getString("itemdesc");
		}
		return "";
	}
	public List<Dictionary> queryCitiesByProvince(Connection conn,Dictionary provDict) throws SQLException{
		ArrayList<Dictionary> dictList=new ArrayList<>();
		String sql="select *  from dictionary where itemname='CITY' and itemcode like '"+provDict.getItemcode()+"%'";
		PreparedStatement stat=conn.prepareStatement(sql);
		ResultSet rs=stat.executeQuery();
		while(rs.next()){
			Dictionary resultDict=new Dictionary();
			resultDict.setItemid(rs.getInt("itemid"));
			resultDict.setItemname(rs.getString("itemname"));
			resultDict.setItemcode(rs.getString("itemcode"));
			resultDict.setItemdesc(rs.getString("itemdesc"));
			dictList.add(resultDict);
		}
		System.out.println(dictList.toString());
		return dictList;
		
	}

}
