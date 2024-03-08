package com.example.banking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transactions {
	@Id
	@GeneratedValue
	private Long transactionId;
	private int fromAccountNo;
	private int toAccountNo;
	private int ifsc;

	public Transactions() {
	
	}

	public Transactions( int fromAccountNo, int toAccountNo, int ifsc) {
		
//		this.transactionId = transactionId;
		this.fromAccountNo = fromAccountNo;
		this.toAccountNo = toAccountNo;
		this.ifsc = ifsc;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public int getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(int fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public int getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(int toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public int getIfsc() {
		return ifsc;
	}

	public void setIfsc(int ifsc) {
		this.ifsc = ifsc;
	}

	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", fromAccountNo=" + fromAccountNo + ", toAccountNo="
				+ toAccountNo + ", ifsc=" + ifsc + "]";
	}
	
}
