package com.project.checkingaccount.services;

import com.project.checkingaccount.models.CheckingAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CheckingAccountService {
	
	 public Flux<CheckingAccount> findAll();
		
	 public Mono<CheckingAccount> saveCheckingAccount(CheckingAccount savingAccount);
	 
	 public Mono<CheckingAccount> findByAccountId(String id);
	 
	 public Mono<CheckingAccount> findByDni(String dni);
	 
	 public Mono<CheckingAccount> findByNumberAccount(String number);
	 

}
