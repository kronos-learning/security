package com.example.security.form;

public class LoginForm {
	
	private String loginId;
	private String password;	
	private String csrf;
        
	public String getCsrf() {
			return csrf;
	}

	public void setCsrf(String csrf) {
			this.csrf = csrf;
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
}
