package com.crossover.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users", schema="crossover")
public class UserVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userId")
	private long id;
	@Column(name="mail",nullable=false,length=50)
	private String mail;
	@Column(name="password",nullable=false,length=50)
	private String password;
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="accountxuser")
	private List<AccountVO> userAccounts = new ArrayList<>();
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="ticketxuser")
	private List<TicketVO> userTickets = new ArrayList<>();
	public UserVO(){}
	public UserVO(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AccountVO> getUserAccounts() {
		return userAccounts;
	}
	public void setUserAccounts(List<AccountVO> userAccounts) {
		this.userAccounts = userAccounts;
	}
	public List<TicketVO> getUserTickets() {
		return userTickets;
	}
	public void setUserTickets(List<TicketVO> userTickets) {
		this.userTickets = userTickets;
	}
	
	
	
}
