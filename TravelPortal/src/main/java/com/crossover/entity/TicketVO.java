package com.crossover.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets", schema="crossover")
public class TicketVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ticketId",nullable=false,length=50)
	private long Id;
	@Column(name="origin",nullable=false,length=50)
	private String origin;
	@Column(name="destiny",nullable=false,length=50)
	private String destiny;
	@Column(name="amount",nullable=false,length=50)
	private Integer amount;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ticketxuser")
	private UserVO ticketxuser;
	
	public TicketVO() {
		super();
	}

	public TicketVO(String origin, String destiny, Integer amount, UserVO ticketxuser) {
		super();
		this.origin = origin;
		this.destiny = destiny;
		this.amount = amount;
		this.ticketxuser = ticketxuser;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public UserVO getTicketxuser() {
		return ticketxuser;
	}

	public void setTicketxuser(UserVO ticketxuser) {
		this.ticketxuser = ticketxuser;
	}
	
	
}
