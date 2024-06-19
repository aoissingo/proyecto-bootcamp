package com.banco.client.controller;

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

import com.banco.client.model.Client;
import com.banco.client.service.ClientServiceLocal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
    private ClientServiceLocal clientService;

    @GetMapping
    public Flux<Client> getAllClient() {
    	logger.info("Ingresando a ClientController.getAllClient");
        return clientService.getAllClient();
    }

    @GetMapping("/{id}")
    public Mono<Client> getClientById(@PathVariable String id) {
    	logger.info("Ingresando a ClientController.getClientById");
        return clientService.getClientById(id);
    }

    @PostMapping
    public Mono<Client> createClient(@RequestBody Client client) {
    	logger.info("Ingresando a ClientController.createClient");
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public Mono<Client> updateClient(@PathVariable String id, @RequestBody Client client) {
    	logger.info("Ingresando a ClientController.updateClient");
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteClient(@PathVariable String id) {
    	logger.info("Ingresando a ClientController.deleteClient");
        return clientService.deleteClient(id);
    }


}
