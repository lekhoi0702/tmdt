package com.quancafehighland.model;

import spring.entity.UserTBEntity;


public class UserModel extends UserTBEntity{

	
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Long ID;
	
	private Long roleID;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		this.ID = iD;
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}


	
	
}
