package com.banco.account.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.banco.account.model.Account;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account,String>{

}
