package com.crossover.dto;

import java.io.Serializable;

public class AirlineTicket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4841783744432073961L;
	private Integer amount;
	private AirlineOffer details;
	public AirlineTicket() {
		super();
	}
	public AirlineTicket(Integer amount, AirlineOffer details) {
		super();
		this.amount = amount;
		this.details = details;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public AirlineOffer getDetails() {
		return details;
	}
	public void setDetails(AirlineOffer details) {
		this.details = details;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("\n Ticket \n");
		builder.append(" Amount: " + this.amount);
		builder.append(" Details: " + this.details);
		return builder.toString();
	}
	
}
