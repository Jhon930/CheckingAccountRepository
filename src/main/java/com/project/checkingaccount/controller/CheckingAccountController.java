package com.project.checkingaccount.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.checkingaccount.models.CheckingAccount;
import com.project.checkingaccount.repository.CheckingAccountRepository;
import com.project.checkingaccount.services.CheckingAccountService;

import java.net.URI;
import java.time.Duration;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/checkaccounts")
public class CheckingAccountController {
	
	@Autowired
	private CheckingAccountRepository chkRepository;
	
	@Autowired
	private CheckingAccountService service;
	
	@PostMapping
	public Mono<ResponseEntity<CheckingAccount>> create(@RequestBody CheckingAccount chkAccount){
		     return chkRepository.save(chkAccount)
				           .map(savedAccount -> ResponseEntity.ok(savedAccount));
	}
	
	@GetMapping
	public Flux<CheckingAccount> listAccounts(){
		return service.findAll();
	}
	
	@PostMapping("/insert")
	public Mono<CheckingAccount> insertCheckingAccount(@RequestBody CheckingAccount savingAccount){
			
		return service.saveCheckingAccount(savingAccount);
		
	}
	
	@PutMapping("/{chAccountId}")
	public Mono<ResponseEntity<CheckingAccount>> update(@RequestBody CheckingAccount checkingAccount, @PathVariable String chkId){
		return chkRepository.findById(chkId)
				.flatMap(c -> {
						c.setBalance(checkingAccount.getBalance());
						c.setStatus(checkingAccount.getStatus());
				        return chkRepository.save(c);
				})
						.map(c->ResponseEntity.created(URI.create("/api/checkaccount/".concat(c.getId())))
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("/{chAccountId}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String chkId){
		return chkRepository.findById(chkId).flatMap(c -> {
					return chkRepository.delete(c).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				}).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	
}
