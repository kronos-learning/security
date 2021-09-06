package com.example.security.entity;

public class User {
	
	private int id;
	private String loginId;
	private String password;
	private int admin;
	
	public User(int id, String loginId, int admin) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.admin = admin;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	public String getAdminName() {
		if (this.getAdmin() == 1) {
			return "管理者";
		}
		return "";
	}
	
	public boolean isAdministrator() {
		return this.getAdmin() == 1;
	}
}
