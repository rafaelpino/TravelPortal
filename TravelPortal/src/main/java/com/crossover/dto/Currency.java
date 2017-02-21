package com.crossover.dto;

public enum Currency {
	USD("USD"),EUR("EUR");
	private String currency;
	private Currency(String currency){
		this.currency = currency;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
