package com.crossover.dto;

import java.io.Serializable;

public class DepositWithDrawRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5656332461182324937L;
	private String accountId;
	private MonetaryAmount monetaryAmount;
	public DepositWithDrawRequest() {
		super();
	}
	public DepositWithDrawRequest(String accountId, MonetaryAmount monetaryAmount) {
		super();
		this.accountId = accountId;
		this.monetaryAmount = monetaryAmount;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public MonetaryAmount getMonetaryAmount() {
		return monetaryAmount;
	}
	public void setMonetaryAmount(MonetaryAmount monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}
	
}
