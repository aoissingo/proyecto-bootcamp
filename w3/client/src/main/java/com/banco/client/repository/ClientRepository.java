package com.banco.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.banco.client.model.Client;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client,String> {

}
