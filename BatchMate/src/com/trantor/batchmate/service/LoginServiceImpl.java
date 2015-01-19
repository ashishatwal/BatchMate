package com.trantor.batchmate.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.trantor.batchmate.dao.UserDAOImpl;
import com.trantor.batchmate.model.UserBean;

public class LoginServiceImpl implements LoginService, SessionAware{

	private Map<String, Object> sessionMap= new HashMap<String, Object>();
	
	UserDAOImpl userDao;

	public UserDAOImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAOImpl userDao) {
		this.userDao = userDao;
	}

	/*
	 * @Override public void createUser(UserBean user) {
	 * 
	 * getUserDao().insertUser(user); System.out.println("user inserted"); }
	 */

	/*
	 * @Override public void updateUser(UserBean user) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void deleteUser(UserBean user) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */

	@Override
	public String verifyUser(UserBean user) {
		String result;
		String loggedUserName = null;
		String username = user.getUserName();
		UserBean dbUser = getUserDao().getUserInfo(username);
				
		if (dbUser.getUserPassword().equals(user.getUserPassword())
				&& dbUser.getUserType().equalsIgnoreCase(user.getUserType())) {
			 //sessionMap.put("userName", dbUser.getUserName());
			sessionMap.put("username", username);
			//System.out.println(dbUser.getUserType());
			
			
			if (sessionMap.containsKey("userName")) {
	            System.out.println("Value is here"+sessionMap.get("userName"));
	        }
			if (dbUser.getUserType() .equals("operator")) {
				result = "operator";
			} else {
				result = "admin";
			}
		} else {
			result = "error";
		}
		return result;
	}

	@Override
	public void setSession(Map<String, Object>map) {
		sessionMap = (SessionMap<String, Object>)map;
		// TODO Auto-generated method stub
		
	}

}
