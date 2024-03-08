package com.example.banking.controller;

public class LoginRequest {
	private int customerId;
	private String password;

	public LoginRequest() {

	}

	public LoginRequest(int customerId, String password) {
		super();
		this.customerId = customerId;
		this.password = password;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
