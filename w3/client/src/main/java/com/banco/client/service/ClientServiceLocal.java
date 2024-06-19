package com.banco.client.service;

import com.banco.client.model.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientServiceLocal {

	Flux<Client> getAllClient();

    Mono<Client> getClientById(String id);

    Mono<Client> createClient(Client client);

    Mono<Client> updateClient(String id, Client customer);

    Mono<Void> deleteClient(String id);
}
