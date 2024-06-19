package com.banco.credit.service;

import com.banco.credit.model.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditServiceLocal {
	
	Flux<Credit> findAll();

    Mono<Credit> findById(String id);

    Mono<Credit> save(Credit credit);

    Mono<Credit> updateAccount(String id, Credit credit);

    Mono<Void> deleteById(String id);

}
