package com.banco.account.controller;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.banco.account.model.Account;
import com.banco.account.service.AccountServiceLocal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = AccountController.class)
public class AccountControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AccountServiceLocal accountService;

    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account();
        testAccount.setId("acc001");
        testAccount.setType("savings");
        testAccount.setBalance(1000.0);
        testAccount.setMaintenanceFee(10.0);
        testAccount.setMaxTransactions(20);
        testAccount.setCustomerId("cust001");
        testAccount.setOwners(Arrays.asList("owner001", "owner002"));
        testAccount.setAuthorizedSigners(Arrays.asList("signer001", "signer002"));
    }

    @Test
    void testGetAllAccounts() {
        Mockito.when(accountService.findAll()).thenReturn(Flux.just(testAccount));

        webTestClient.get().uri("/accounts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Account.class)
                .hasSize(1)
                .contains(testAccount)
                .consumeWith(response -> {
                    System.out.println("Response body listar: " + response.getResponseBody());
                });
    }

    @Test
    void testGetAccountById() {
        Mockito.when(accountService.findById("acc001")).thenReturn(Mono.just(testAccount));

        webTestClient.get().uri("/accounts/acc001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Account.class)
                .isEqualTo(testAccount)
                .consumeWith(response -> {
                    System.out.println("Response body search account: " + response.getResponseBody());
                });
    }

    @Test
    void testCreateAccount() {
        Mockito.when(accountService.save(Mockito.any(Account.class))).thenReturn(Mono.just(testAccount));

        webTestClient.post().uri("/accounts")
                .bodyValue(testAccount)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Account.class)
                .isEqualTo(testAccount)
                .consumeWith(response -> {
                    System.out.println("Response body save: " + response.getResponseBody());
                });
    }

    @Test
    void testUpdateAccount() {
        Account updatedAccount = new Account();
        updatedAccount.setId("acc001");
        updatedAccount.setType("savings");
        updatedAccount.setBalance(1500.0);
        updatedAccount.setMaintenanceFee(15.0);
        updatedAccount.setMaxTransactions(25);
        updatedAccount.setCustomerId("cust001");
        updatedAccount.setOwners(Arrays.asList("owner001", "owner002"));
        updatedAccount.setAuthorizedSigners(Arrays.asList("signer001", "signer002"));

        Mockito.when(accountService.updateAccount(Mockito.eq("acc001"), Mockito.any(Account.class))).thenReturn(Mono.just(updatedAccount));

        webTestClient.put().uri("/accounts/acc001")
                .bodyValue(updatedAccount)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Account.class)
                .isEqualTo(updatedAccount)
                .consumeWith(response -> {
                    System.out.println("Response body update: " + response.getResponseBody());
                });
    }

    @Test
    void testDeleteAccount() {
        Mockito.when(accountService.deleteById("acc001")).thenReturn(Mono.empty());

        webTestClient.delete().uri("/accounts/acc001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class)
                .consumeWith(response -> {
                    System.out.println("Account deleted successfully.");
                });
    }
}
