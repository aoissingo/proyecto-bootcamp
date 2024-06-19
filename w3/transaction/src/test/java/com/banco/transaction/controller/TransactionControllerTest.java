package com.banco.transaction.controller;

import com.banco.transaction.model.Transaction;
import com.banco.transaction.service.TransactionServiceLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@WebFluxTest(controllers = TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TransactionServiceLocal transactionService;

    private Transaction testTransaction;

    @BeforeEach
    void setUp() {
        testTransaction = new Transaction();
        testTransaction.setId("trans001");
        testTransaction.setType("deposit");
        testTransaction.setAmount(1000.0);
        testTransaction.setDate(LocalDate.now());
        testTransaction.setAccountId("acc001");
        testTransaction.setCreditId("cred001");
    }

    @Test
    void testFindAllTransactions() {
        Mockito.when(transactionService.findAll()).thenReturn(Flux.just(testTransaction));

        webTestClient.get().uri("/transactions")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Transaction.class)
                .hasSize(1)
                .contains(testTransaction)
                .consumeWith(response -> {
                    System.out.println("Response body: " + response.getResponseBody());
                });
    }

    @Test
    void testFindTransactionById() {
        Mockito.when(transactionService.findById("trans001")).thenReturn(Mono.just(testTransaction));

        webTestClient.get().uri("/transactions/trans001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Transaction.class)
                .isEqualTo(testTransaction)
                .consumeWith(response -> {
                    System.out.println("Response body: " + response.getResponseBody());
                });
    }

    @Test
    void testSaveTransaction() {
        Mockito.when(transactionService.save(Mockito.any(Transaction.class))).thenReturn(Mono.just(testTransaction));

        webTestClient.post().uri("/transactions")
                .bodyValue(testTransaction)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Transaction.class)
                .isEqualTo(testTransaction)
                .consumeWith(response -> {
                    System.out.println("Response body: " + response.getResponseBody());
                });
    }

    @Test
    void testUpdateTransaction() {
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setId("trans001");
        updatedTransaction.setType("withdrawal");
        updatedTransaction.setAmount(800.0);
        updatedTransaction.setDate(LocalDate.now());
        updatedTransaction.setAccountId("acc002");
        updatedTransaction.setCreditId("cred001");

        Mockito.when(transactionService.updateTransaction(Mockito.eq("trans001"), Mockito.any(Transaction.class)))
                .thenReturn(Mono.just(updatedTransaction));

        webTestClient.put().uri("/transactions/trans001")
                .bodyValue(updatedTransaction)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Transaction.class)
                .isEqualTo(updatedTransaction)
                .consumeWith(response -> {
                    System.out.println("Response body: " + response.getResponseBody());
                });
    }

    @Test
    void testDeleteTransaction() {
        Mockito.when(transactionService.deleteById("trans001")).thenReturn(Mono.empty());

        webTestClient.delete().uri("/transactions/trans001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class)
                .consumeWith(response -> {
                    System.out.println("Transaction deleted successfully.");
                });
    }
}
