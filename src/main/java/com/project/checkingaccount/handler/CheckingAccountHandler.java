package com.project.checkingaccount.handler;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.http.MediaType.*;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.project.checkingaccount.models.CheckingAccount;
import com.project.checkingaccount.repository.CheckingAccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CheckingAccountHandler {
	
	private CheckingAccountRepository checkingAccountRepository;

    public CheckingAccountHandler(CheckingAccountRepository checkingAccountRepository){
        this.checkingAccountRepository = checkingAccountRepository;
    }

    public Mono<ServerResponse> createCheckingAccount(ServerRequest request) {
        Mono<CheckingAccount> userMono = request.bodyToMono(CheckingAccount.class).flatMap(user -> checkingAccountRepository.save(user));
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(userMono, CheckingAccount.class);
    }

    public Mono<ServerResponse> listCheckingAccount(ServerRequest request) {
        Flux<CheckingAccount> user = checkingAccountRepository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(user, CheckingAccount.class);
    }

    public Mono<ServerResponse> getCheckingAccountById(ServerRequest request) {
        String chkId = request.pathVariable("chkId");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<CheckingAccount> chkMono = checkingAccountRepository.findById(chkId);
        return chkMono.flatMap(chk -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(chk)))
                .switchIfEmpty(notFound);
    }
    
    public Mono<ServerResponse> deleteCheckingAccount(ServerRequest request) {
        String chkId = request.pathVariable("chkId");
        checkingAccountRepository.deleteById(chkId);
        return ServerResponse.ok().build();
    }


}
