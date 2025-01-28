package com.skully.vinconomy.model.dto;

import java.io.Serializable;

public class UserRegistration implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String name;
	private String uuid;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
