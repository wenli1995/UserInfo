package com.java.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.java.dao.DictDao;
import com.java.dao.UserDao;
import com.java.model.Dictionary;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private User user;  //添加或修改时，将【请求参数】封装成User对象,并将User对象设置为请求属性。request.setAtrribute()
	private User selectUser;  //查询时
	private String id;  //删除或修改时前端传入id作为请求参数
	private List<Dictionary> selectSexDict;  //查询时
	private List<Dictionary> sexDict;
	private List<Dictionary> provDict;
	private List<Dictionary> cityDict;
	private List<User> userList;
	private String mainPage;
	DbUtil dbUtil=new DbUtil();
	DictDao dictDao=new DictDao();
	UserDao userDao=new UserDao();
	
	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getSelectUser() {
		return selectUser;
	}

	public void setSelectUser(User selectUser) {
		this.selectUser = selectUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public List<Dictionary> getSexDict() {
		return sexDict;
	}

	public void setSexDict(List<Dictionary> sexDict) {
		this.sexDict = sexDict;
	}
	
	
	public List<Dictionary> getSelectSexDict() {
		return selectSexDict;
	}

	public void setSelectSexDict(List<Dictionary> selectSexDict) {
		this.selectSexDict = selectSexDict;
	}

	public List<Dictionary> getProvDict() {
		return provDict;
	}

	public void setProvDcit(List<Dictionary> provDict) {
		this.provDict = provDict;
	}

	public List<Dictionary> getCityDict() {
		return cityDict;
	}

	public void setCityDict(List<Dictionary> cityDict) {
		this.cityDict = cityDict;
	}

	public String  userQuery(){
		mainPage="users/userList.jsp";
		Connection conn=null;
		HttpSession session=request.getSession();
		try {
			conn=dbUtil.getConnection();
			Dictionary dict=new Dictionary();
			dict.setItemname("SEX");
			selectSexDict=dictDao.query(conn, dict);  //这里设置了值，前端可获取
			
			if(selectUser!=null){
				String isex="";
				if(selectUser.getSex()!="-1"){
					isex=dictDao.queryItemDesc(conn,new Dictionary("SEX",selectUser.getSex()));
				}
				selectUser.setSex(isex);	 //其他项在请求时直接赋值给selectUser
				/*
				if(selectUser.getName()!=""){
					//String iname=new String(selectUser.getName().getBytes("ISO-8859-1"), "UTF-8");//中文编码问题
					System.out.println(selectUser.getName());
					
				}//框架自动处理中文
				*/
				session.setAttribute("selectUser", selectUser);
			}else{
				selectUser=(User)session.getAttribute("selectUser");
			}

			userList=userDao.queryUser(conn, selectUser);
			
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
		
	public String userSave(){
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			String isex=dictDao.queryItemDesc(conn,new Dictionary("SEX",user.getSex()));
			String iprovince=dictDao.queryItemDesc(conn,new Dictionary("PROVINCE",user.getProvince()));
			String icity=dictDao.queryItemDesc(conn,new Dictionary("CITY",user.getCity()));
			user.setSex(isex);
			user.setProvince(iprovince);
			user.setCity(icity);
			System.out.println(id);
			if(StringUtil.isNotEmpty(id)){
				user.setId(Integer.parseInt(id));  //这步真正赋值
				int num1=userDao.updateUser(conn, user);
				System.out.println("更新"+num1);
			}else{
				int num2=userDao.insertUser(conn, user);
				System.out.println("插入"+num2);
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
		
		return "save";
		
	}
	
	public String  userPreSave(){
		mainPage="users/userSave.jsp";
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			if(StringUtil.isNotEmpty(id)){
				user=userDao.getUserById(conn,Integer.parseInt(id));
			}
			Dictionary dict=new Dictionary();
			dict.setItemname("SEX");
			sexDict=dictDao.query(conn, dict);  //这里设置了值，前端可获取
			
			dict.setItemname("PROVINCE");
			provDict=dictDao.query(conn, dict);  //这里设置了值，前端可获取
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally{
			try {
				dbUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public void userDelete(){
		Connection conn=null;
		int num;
		try {
			conn=dbUtil.getConnection();
			num = userDao.deleteUser(conn, Integer.parseInt(id));
			JSONObject resultJson=new JSONObject();
			if(num==0){
				resultJson.put("error", "删除失败");
			}
			resultJson.put("success", "删除成功");
			ResponseUtil.write(resultJson, ServletActionContext.getResponse());
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
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
}
