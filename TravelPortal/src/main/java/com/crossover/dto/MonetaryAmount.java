package com.crossover.dto;

import java.io.Serializable;

public class MonetaryAmount  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1615786143805688750L;
	private Number amount;
	private String currency;
	public MonetaryAmount(){}
	public MonetaryAmount(Number amount, String currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}
	public Number getAmount() {
		return amount;
	}
	public void setAmount(Number amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("\n");
		builder.append(" Amount: "+this.amount);
		builder.append(" Currency: "+this.currency);
		return builder.toString();
	}
}
