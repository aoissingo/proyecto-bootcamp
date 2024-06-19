package com.banco.credit.controller;

import com.banco.credit.model.Credit;
import com.banco.credit.service.CreditServiceLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = CreditController.class)
public class CreditControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CreditServiceLocal creditService;

    private Credit testCredit;

    @BeforeEach
    void setUp() {
        testCredit = new Credit();
        testCredit.setId("cred001");
        testCredit.setType("personal");
        testCredit.setAmount(5000.0);
        testCredit.setInterestRate(0.05);
        testCredit.setTerm(12);
        testCredit.setCustomerId("cust001");
    }

    @Test
    void testFindAllCredits() {
        Mockito.when(creditService.findAll()).thenReturn(Flux.just(testCredit));

        webTestClient.get().uri("/credits")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Credit.class)
                .hasSize(1)
                .contains(testCredit)
                .consumeWith(response -> {
                    System.out.println("Response body: " + response.getResponseBody());
                });
    }

    @Test
    void testFindCreditById() {
        Mockito.when(creditService.findById("cred001")).thenReturn(Mono.just(testCredit));

        webTestClient.get().uri("/credits/cred001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Credit.class)
                .isEqualTo(testCredit)
                .consumeWith(response -> {
                    System.out.println("Response body: " + response.getResponseBody());
                });
    }

    @Test
    void testSaveCredit() {
        Mockito.when(creditService.save(Mockito.any(Credit.class))).thenReturn(Mono.just(testCredit));

        webTestClient.post().uri("/credits")
                .bodyValue(testCredit)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Credit.class)
                .isEqualTo(testCredit)
                .consumeWith(response -> {
                    System.out.println("Response body: " + response.getResponseBody());
                });
    }

    @Test
    void testUpdateCredit() {
        Credit updatedCredit = new Credit();
        updatedCredit.setId("cred001");
        updatedCredit.setType("personal");
        updatedCredit.setAmount(6000.0);
        updatedCredit.setInterestRate(0.06);
        updatedCredit.setTerm(12);
        updatedCredit.setCustomerId("cust001");

        Mockito.when(creditService.updateAccount(Mockito.eq("cred001"), Mockito.any(Credit.class))).thenReturn(Mono.just(updatedCredit));

        webTestClient.put().uri("/credits/cred001")
                .bodyValue(updatedCredit)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Credit.class)
                .isEqualTo(updatedCredit)
                .consumeWith(response -> {
                    System.out.println("Response body: " + response.getResponseBody());
                });
    }

    @Test
    void testDeleteCredit() {
        Mockito.when(creditService.deleteById("cred001")).thenReturn(Mono.empty());

        webTestClient.delete().uri("/credits/cred001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class)
                .consumeWith(response -> {
                    System.out.println("Credit deleted successfully.");
                });
    }
}
