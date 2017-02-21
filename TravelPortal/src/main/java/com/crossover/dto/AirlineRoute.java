package com.crossover.dto;

import java.io.Serializable;

public class AirlineRoute  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6220473835157079592L;
	private String from;
	private String to;
	public AirlineRoute(){}
	public AirlineRoute(String from, String to) {
		super();
		this.from = from;
		this.to = to;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("\n");
		builder.append(" From: "+this.from);
		builder.append(" To: "+this.to);
		return builder.toString();
	}
	
}
