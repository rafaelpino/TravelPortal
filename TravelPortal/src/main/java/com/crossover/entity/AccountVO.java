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
@Table(name = "accounts", schema="crossover")
public class AccountVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="accountId",nullable=false,length=50)
	private long Id;
	@Column(name="accountName",nullable=false,length=50)
	private String accountName;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="accountxuser")
	private UserVO accountxuser;
	
	public AccountVO() {
		super();
	}
	public AccountVO(long id, String accountName) {
		super();
		Id = id;
		this.accountName = accountName;
	}
	public AccountVO(String accountName) {
		super();
		this.accountName = accountName;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public UserVO getAccountxuser() {
		return accountxuser;
	}
	public void setAccountxuser(UserVO accountxuser) {
		this.accountxuser = accountxuser;
	}
	
}
