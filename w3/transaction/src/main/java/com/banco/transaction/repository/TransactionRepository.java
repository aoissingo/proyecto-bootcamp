package com.banco.transaction.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.banco.transaction.model.Transaction;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String>{

}
