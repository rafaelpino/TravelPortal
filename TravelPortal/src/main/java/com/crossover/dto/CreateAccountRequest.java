package com.crossover.dto;

import java.io.Serializable;

public class CreateAccountRequest implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1310548827788951308L;
		private String currency;
		public CreateAccountRequest() {
			super();
		}
		public CreateAccountRequest(String currency) {
			super();
			this.currency = currency;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
}
