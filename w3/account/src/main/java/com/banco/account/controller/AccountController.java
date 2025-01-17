package com.banco.account.controller;

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

import com.banco.account.model.Account;
import com.banco.account.service.AccountServiceLocal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
    private AccountServiceLocal accountService;
	
	@GetMapping
    public Flux<Account> getAllClient() {
		logger.info("Ingresando a AccountController.getAllClient");
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Account> getClientById(@PathVariable String id) {
    	logger.info("Ingresando a AccountController.getClientById");
        return accountService.findById(id);
    }

    @PostMapping
    public Mono<Account> createClient(@RequestBody Account account) {
    	logger.info("Ingresando a AccountController.createClient");
        return accountService.save(account);
    }

    @PutMapping("/{id}")
    public Mono<Account> updateClient(@PathVariable String id, @RequestBody Account account) {
    	logger.info("Ingresando a AccountController.updateClient");
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteClient(@PathVariable String id) {
    	logger.info("Ingresando a AccountController.deleteClient");
        return accountService.deleteById(id);
    }

}
