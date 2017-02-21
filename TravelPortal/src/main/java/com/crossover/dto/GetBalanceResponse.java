package com.crossover.dto;

public class GetBalanceResponse {
	private String accountName;
	private long balance;
	private String currency;
	public GetBalanceResponse(String accountName, long balance, String currency) {
		super();
		this.accountName = accountName;
		this.balance = balance;
		this.currency = currency;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
