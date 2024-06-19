package com.banco.credit.controller;

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

import com.banco.credit.model.Credit;
import com.banco.credit.service.CreditServiceLocal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credits")
public class CreditController {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
	
	@Autowired
    private CreditServiceLocal creditService;
	
	@GetMapping
    public Flux<Credit> findAll() {
		logger.info("Ingresando a CreditController.findAll");
        return creditService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Credit> findById(@PathVariable String id) {
    	logger.info("Ingresando a CreditController.findById");
        return creditService.findById(id);
    }

    @PostMapping
    public Mono<Credit> save(@RequestBody Credit Credit) {
    	logger.info("Ingresando a CreditController.save");
        return creditService.save(Credit);
    }

    @PutMapping("/{id}")
    public Mono<Credit> updateCredit(@PathVariable String id, @RequestBody Credit Credit) {
    	logger.info("Ingresando a CreditController.updateCredit");
        return creditService.updateAccount(id, Credit);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable String id) {
    	logger.info("Ingresando a CreditController.deleteById");
        return creditService.deleteById(id);
    }

}
