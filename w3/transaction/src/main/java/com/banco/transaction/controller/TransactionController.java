package com.banco.transaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.transaction.model.Transaction;
import com.banco.transaction.service.TransactionServiceLocal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
    private TransactionServiceLocal transactionService;
	
	@GetMapping
    public Flux<Transaction> findAll() {
		logger.info("Ingresando a TransactionController.findAll");
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Transaction> findById(@PathVariable String id) {
    	logger.info("Ingresando a TransactionController.findById");
    	return transactionService.findById(id);
    }

    @PostMapping
    public Mono<Transaction> save(@RequestBody Transaction transaction) {
    	logger.info("Ingresando a TransactionController.save");
        return transactionService.save(transaction);
    }

    @PutMapping("/{id}")
    public Mono<Transaction> updateCredit(@PathVariable String id, @RequestBody Transaction transaction) {
    	logger.info("Ingresando a TransactionController.updateCredit");
    	return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable String id) {
    	logger.info("Ingresando a TransactionController.deleteById");
        return transactionService.deleteById(id);
    }
}
