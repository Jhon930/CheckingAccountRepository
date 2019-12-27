package com.project.checkingaccount.models;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="checkingaccounts")
public class CheckingAccount {
	
	@Id
	private String id;
	private String number;
	private BigDecimal balance;
	private String status;
	
	@Valid
	@NotNull
	private List<Client> clients;
	
	public CheckingAccount() {
		
	}

	public CheckingAccount(String id, String number, BigDecimal balance,
			String status) {
		this.id = id;
		this.number = number;
		this.balance = balance;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	
}
