package com.banco.transaction.service;

import com.banco.transaction.model.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionServiceLocal {
	
	Flux<Transaction> findAll();

    Mono<Transaction> findById(String id);

    Mono<Transaction> save(Transaction transaction);

    Mono<Transaction> updateTransaction(String id, Transaction transaction);

    Mono<Void> deleteById(String id);

}
