package com.project.checkingaccount.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.project.checkingaccount.models.CheckingAccount;
import com.project.checkingaccount.repository.CheckingAccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {

	@Autowired
	private CheckingAccountRepository repository;
	
	@Autowired
	private WebClient client;
	
	@Override
	public Flux<CheckingAccount> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<CheckingAccount> saveCheckingAccount(CheckingAccount savingAccount) {
		// TODO Auto-generated method stub
	
		return client.post()
					.uri("/insert")
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.body(BodyInserters.fromObject(savingAccount))
					.retrieve()
					.bodyToMono(CheckingAccount.class);
		
	}

	@Override
	public Mono<CheckingAccount> findByAccountId(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Mono<CheckingAccount> findByDni(String dni) {
		// TODO Auto-generated method stub
		return repository.findByClientDni(dni);
	}

	@Override
	public Mono<CheckingAccount> findByNumberAccount(String number) {
		// TODO Auto-generated method stub
		return repository.findByNumberAccount(number);
	}
}
