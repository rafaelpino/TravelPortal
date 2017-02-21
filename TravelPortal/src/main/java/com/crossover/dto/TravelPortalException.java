package com.crossover.dto;

public class TravelPortalException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 432816901977517633L;
	private String message;

	public TravelPortalException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
