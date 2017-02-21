package com.crossover.dto;

public class AirlineTickets {
	private AirlineTicket[] tickets;

	public AirlineTickets(AirlineTicket[] tickets) {
		super();
		this.tickets = tickets;
	}
	public AirlineTickets() {
		// TODO Auto-generated constructor stub
	}
	public AirlineTicket[] getTickets() {
		return tickets;
	}
	public void setTickets(AirlineTicket[] tickets) {
		this.tickets = tickets;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Tickets: \n");
		for(AirlineTicket ticket: this.tickets){
			builder.append(" \n Ticket: \n"+ticket.toString());
		}
		return builder.toString();
	}
}
