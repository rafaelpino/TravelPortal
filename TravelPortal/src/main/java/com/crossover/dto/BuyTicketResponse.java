package com.crossover.dto;

public class BuyTicketResponse {
	private String from;
	private String to;
	private Integer amount;
	public BuyTicketResponse(String from, String to, Integer amount) {
		super();
		this.from = from;
		this.to = to;
		this.amount = amount;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}	
