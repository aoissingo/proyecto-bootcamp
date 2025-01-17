package com.banco.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.account.model.Account;
import com.banco.account.repository.AccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountServiceLocal{
	
	@Autowired
    private AccountRepository accountRepository;

	@Override
	public Flux<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Mono<Account> findById(String id) {
		return accountRepository.findById(id);
	}

	@Override
	public Mono<Account> save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Mono<Account> updateAccount(String id, Account account) {
		return accountRepository.findById(id)
                .flatMap(existingCustomer -> {
//                    existingCustomer.setName(account.getName());
//                    existingCustomer.setType(account.getType());
//                    existingCustomer.setEmail(account.getEmail());
////                    existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                    return accountRepository.save(existingCustomer);
                });
	}

	@Override
	public Mono<Void> deleteById(String id) {
		 return accountRepository.deleteById(id);
	}

}
