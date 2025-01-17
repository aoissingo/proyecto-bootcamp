package com.banco.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.client.model.Client;
import com.banco.client.repository.ClientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientServiceLocal{

	@Autowired
    private ClientRepository clientRepository;

    public Flux<Client> getAllClient() {
        return clientRepository.findAll();
    }

    public Mono<Client> getClientById(String id) {
        return clientRepository.findById(id);
    }

    public Mono<Client> createClient(Client client) {
        return clientRepository.save(client);
    }

    public Mono<Client> updateClient(String id, Client customer) {
        return clientRepository.findById(id)
                .flatMap(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setType(customer.getType());
                    existingCustomer.setEmail(customer.getEmail());
//                    existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                    return clientRepository.save(existingCustomer);
                });
    }

    public Mono<Void> deleteClient(String id) {
        return clientRepository.deleteById(id);
    }
}
