package com.banco.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.transaction.model.Transaction;
import com.banco.transaction.repository.TransactionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionServiceLocal{
	
	@Autowired
    private TransactionRepository transactionRepository;

	@Override
	public Flux<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	@Override
	public Mono<Transaction> findById(String id) {
		return transactionRepository.findById(id);
	}

	@Override
	public Mono<Transaction> save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Mono<Transaction> updateTransaction(String id, Transaction transaction) {
		return transactionRepository.findById(id)
                .flatMap(existingCustomer -> {
//                    existingCustomer.setName(account.getName());
//                    existingCustomer.setType(account.getType());
//                    existingCustomer.setEmail(account.getEmail());
////                    existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                    return transactionRepository.save(existingCustomer);
                });
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return transactionRepository.deleteById(id);
	}

}
