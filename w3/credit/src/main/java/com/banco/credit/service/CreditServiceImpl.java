package com.banco.credit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.credit.model.Credit;
import com.banco.credit.repository.CreditRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditServiceLocal{
	
	@Autowired
    private CreditRepository creditRepository;

	@Override
	public Flux<Credit> findAll() {
		return creditRepository.findAll();
	}

	@Override
	public Mono<Credit> findById(String id) {
		return creditRepository.findById(id);
	}

	@Override
	public Mono<Credit> save(Credit credit) {
		return creditRepository.save(credit);
	}

	@Override
	public Mono<Credit> updateAccount(String id, Credit credit) {
		return creditRepository.findById(id)
                .flatMap(existingCustomer -> {
//                    existingCustomer.setName(account.getName());
//                    existingCustomer.setType(account.getType());
//                    existingCustomer.setEmail(account.getEmail());
////                    existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                    return creditRepository.save(existingCustomer);
                });
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return creditRepository.deleteById(id);
	}

}
