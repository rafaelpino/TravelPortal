package com.crossover.dto;

import java.io.Serializable;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1649453135130039824L;
	private MonetaryAmount balance;
	private String id;
	public Account() {
		super();
	}
	public Account(MonetaryAmount balance, String id) {
		super();
		this.balance = balance;
		this.id = id;
	}
	public MonetaryAmount getBalance() {
		return balance;
	}
	public void setBalance(MonetaryAmount balance) {
		this.balance = balance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("\n Account \n");
		builder.append(" Id: " + this.id);
		builder.append(" Balance: " + this.balance);
		return builder.toString();
	}
	
}
