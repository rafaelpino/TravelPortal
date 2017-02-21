package com.crossover.dto;

import java.io.Serializable;

public class AirlineOffer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4871941560285967084L;
	private MonetaryAmount price;
	private AirlineRoute route;
	public AirlineOffer(){}
	public AirlineOffer(MonetaryAmount price, AirlineRoute route) {
		super();
		this.price = price;
		this.route = route;
	}
	public MonetaryAmount getPrice() {
		return price;
	}
	public void setPrice(MonetaryAmount price) {
		this.price = price;
	}
	public AirlineRoute getRoute() {
		return route;
	}
	public void setRoute(AirlineRoute route) {
		this.route = route;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" Price: " + price.toString() + "\n");
		builder.append(" Route" + route.toString()+ "\n");
		return builder.toString();
	}
	
}
