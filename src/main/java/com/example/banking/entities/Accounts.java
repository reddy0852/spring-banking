package com.example.banking.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Accounts {
	@Id
	private int customerId;
	private String name;
	private String password;
	@Column(unique = true)
	private int accountNo;
	private String type;
	private String mobile;
	private double balance;
	private String lastPassword;
	private int ifsc;

	public Accounts() {

	}

	public Accounts(int customerId, String name, int accountNo, String type, String mobile, double balance, int ifsc,
			String password) {
		this.customerId = customerId;
		this.name = name;
		this.accountNo = accountNo;
		this.type = type;
		this.mobile = mobile;
		this.balance = balance;
		this.ifsc = ifsc;
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

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getLastPassword() {
		return lastPassword;
	}

	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIfsc() {
		return ifsc;
	}

	public void setIfsc(int ifsc) {
		this.ifsc = ifsc;
	}

	@Override
	public String toString() {
		return "Accounts [customerId=" + customerId + ", name=" + name + ", accountNo=" + accountNo + ", type=" + type
				+ ", mobile=" + mobile + ", balance=" + balance + ", ifsc=" + ifsc + "]";
	}

}
