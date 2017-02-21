package com.crossover.dto;

public class AirlineOfferResponse {
	private String from;
	private String to;
	private Number price;
	private String currency;
	public AirlineOfferResponse( String from, String to, Number price, String currency) {
		super();
		this.from = from;
		this.to = to;
		this.price = price;
		this.currency = currency;
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
	public Number getPrice() {
		return price;
	}
	public void setPrice(Number price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
