package com.project.checkingaccount.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.project.checkingaccount.models.CheckingAccount;

import reactor.core.publisher.Mono;

public interface CheckingAccountRepository extends ReactiveMongoRepository<CheckingAccount,String> {
	
    @Query(value="{'clients.dni': ?0}", fields="{'clients.dni':1}")	
	Mono<CheckingAccount> findByClientDni(String dni); 
	    
	@Query(value="{'number': ?0}", fields="{'number':1}")	
	Mono<CheckingAccount> findByNumberAccount(String number);

}
