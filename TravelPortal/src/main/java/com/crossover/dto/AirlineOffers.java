package com.crossover.dto;

import java.io.Serializable;

public class AirlineOffers  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5651591279973263582L;
	private AirlineOffer[] offers;
	public AirlineOffers() {
		super();
	}
	public AirlineOffers(AirlineOffer[] offers) {
		super();
		this.offers = offers;
	}
	public AirlineOffer[] getOffers() {
		return offers;
	}
	public void setOffers(AirlineOffer[] offers) {
		this.offers = offers;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Offers: \n");
		for(AirlineOffer offer: this.offers){
			builder.append(" \n Offer: \n"+offer.toString());
		}
		return builder.toString();
	}

}
