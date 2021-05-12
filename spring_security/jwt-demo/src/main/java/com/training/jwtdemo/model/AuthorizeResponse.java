package com.training.jwtdemo.model;

public class AuthorizeResponse {

	private String jwt;

	public AuthorizeResponse() {
		super();
	}

	public AuthorizeResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}
