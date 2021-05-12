package com.training.jwtdemo.model;

public class AuthorizeRequest {

	private String username;
	private String password;

	public AuthorizeRequest() {
		super();
	}

	public AuthorizeRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
