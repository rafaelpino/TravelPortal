package com.crossover.dto;

import java.io.Serializable;

public class BuyTicketRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2658689892735219057L;
	private String accountId;
	private Integer amount;
	private AirlineRoute route;
	public BuyTicketRequest() {
		super();
	}
	public BuyTicketRequest(String accountId, Integer amount, AirlineRoute route) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.route = route;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public AirlineRoute getRoute() {
		return route;
	}
	public void setRoute(AirlineRoute route) {
		this.route = route;
	}
	
}
