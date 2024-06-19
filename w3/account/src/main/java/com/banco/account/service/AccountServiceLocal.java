package com.banco.account.service;


import com.banco.account.model.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountServiceLocal {
	
	Flux<Account> findAll();

    Mono<Account> findById(String id);

    Mono<Account> save(Account account);

    Mono<Account> updateAccount(String id, Account customer);

    Mono<Void> deleteById(String id);

}
