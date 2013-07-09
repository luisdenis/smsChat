package com.blve.smschat.domain;

import com.pranical.basicmodeldomain.domain.User;


public class UserData extends User implements java.io.Serializable{
	
	private static final long serialVersionUID = -3111212197206777273L;
	
	private String userName;
	
   
	public UserData() {
		
	}
        
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
		

}
