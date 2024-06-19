package com.banco.client.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.banco.client.model.AuthorizedSigner;
import com.banco.client.model.Client;
import com.banco.client.model.Owner;
import com.banco.client.service.ClientServiceLocal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = ClientController.class)
public class ClientControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ClientServiceLocal clientService;

    private Client detailedClient;

    @BeforeEach
    void setUp() {
        Owner owner1 = new Owner();
        owner1.setOwnerId("owner003");
        owner1.setName("Emily Davis");
        owner1.setRole("CEO");

        Owner owner2 = new Owner();
        owner2.setOwnerId("owner004");
        owner2.setName("Frank Miller");
        owner2.setRole("CFO");

        AuthorizedSigner signer1 = new AuthorizedSigner();
        signer1.setSignerId("signer003");
        signer1.setName("Grace Lee");
        signer1.setRole("Manager");

        AuthorizedSigner signer2 = new AuthorizedSigner();
        signer2.setSignerId("signer004");
        signer2.setName("Henry Thompson");
        signer2.setRole("Assistant Manager");

        detailedClient = new Client();
        detailedClient.setId("cust002");
        detailedClient.setName("producciones maria");
        detailedClient.setType("business");
        detailedClient.setAddress("123 Pine St");
        detailedClient.setPhone("555-2345");
        detailedClient.setEmail("info@betaindustries.com");
        detailedClient.setOwners(Arrays.asList(owner1, owner2));
        detailedClient.setAuthorizedSigners(Arrays.asList(signer1, signer2));
    }

    @Test
    void testGetAllClient() {
        Mockito.when(clientService.getAllClient()).thenReturn(Flux.just(detailedClient));

        webTestClient.get().uri("/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Client.class)
                .hasSize(1)
                .consumeWith(response -> {
                    List<Client> clients = response.getResponseBody();
                    System.out.println("Response body: " + clients);
                    assertThat(clients).isNotNull();
                    assertThat(clients).hasSize(1);
                    assertThat(clients.get(0).getId()).isEqualTo("cust002");
                    assertThat(clients.get(0).getName()).isEqualTo("producciones maria");
                    assertThat(clients.get(0).getType()).isEqualTo("business");
                    assertThat(clients.get(0).getAddress()).isEqualTo("123 Pine St");
                    assertThat(clients.get(0).getPhone()).isEqualTo("555-2345");
                    assertThat(clients.get(0).getEmail()).isEqualTo("info@betaindustries.com");
                    assertThat(clients.get(0).getOwners()).hasSize(2);
                    assertThat(clients.get(0).getOwners().get(0).getName()).isEqualTo("Emily Davis");
                    assertThat(clients.get(0).getOwners().get(1).getName()).isEqualTo("Frank Miller");
                    assertThat(clients.get(0).getAuthorizedSigners()).hasSize(2);
                    assertThat(clients.get(0).getAuthorizedSigners().get(0).getName()).isEqualTo("Grace Lee");
                    assertThat(clients.get(0).getAuthorizedSigners().get(1).getName()).isEqualTo("Henry Thompson");
                });
    }

    @Test
    void testGetClientById() {
        Mockito.when(clientService.getClientById("cust002")).thenReturn(Mono.just(detailedClient));

        webTestClient.get().uri("/clients/cust002")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Client.class)
                .consumeWith(response -> {
                    Client responseBody = response.getResponseBody();
                    System.out.println("Response body: " + responseBody);
                    assertThat(responseBody).isNotNull();
                    assertThat(responseBody.getId()).isEqualTo("cust002");
                    assertThat(responseBody.getName()).isEqualTo("producciones maria");
                    assertThat(responseBody.getType()).isEqualTo("business");
                    assertThat(responseBody.getAddress()).isEqualTo("123 Pine St");
                    assertThat(responseBody.getPhone()).isEqualTo("555-2345");
                    assertThat(responseBody.getEmail()).isEqualTo("info@betaindustries.com");
                    assertThat(responseBody.getOwners()).hasSize(2);
                    assertThat(responseBody.getOwners().get(0).getName()).isEqualTo("Emily Davis");
                    assertThat(responseBody.getOwners().get(1).getName()).isEqualTo("Frank Miller");
                    assertThat(responseBody.getAuthorizedSigners()).hasSize(2);
                    assertThat(responseBody.getAuthorizedSigners().get(0).getName()).isEqualTo("Grace Lee");
                    assertThat(responseBody.getAuthorizedSigners().get(1).getName()).isEqualTo("Henry Thompson");
                });
    }

    @Test
    void testCreateClient() {
        Mockito.when(clientService.createClient(Mockito.any(Client.class))).thenReturn(Mono.just(detailedClient));

        webTestClient.post().uri("/clients")
                .bodyValue(detailedClient)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Client.class)
                .consumeWith(response -> {
                    Client responseBody = response.getResponseBody();
                    System.out.println("Response body: " + responseBody);
                    assertThat(responseBody).isNotNull();
                    assertThat(responseBody.getId()).isEqualTo("cust002");
                    assertThat(responseBody.getName()).isEqualTo("producciones maria");
                    assertThat(responseBody.getType()).isEqualTo("business");
                    assertThat(responseBody.getAddress()).isEqualTo("123 Pine St");
                    assertThat(responseBody.getPhone()).isEqualTo("555-2345");
                    assertThat(responseBody.getEmail()).isEqualTo("info@betaindustries.com");
                    assertThat(responseBody.getOwners()).hasSize(2);
                    assertThat(responseBody.getOwners().get(0).getName()).isEqualTo("Emily Davis");
                    assertThat(responseBody.getOwners().get(1).getName()).isEqualTo("Frank Miller");
                    assertThat(responseBody.getAuthorizedSigners()).hasSize(2);
                    assertThat(responseBody.getAuthorizedSigners().get(0).getName()).isEqualTo("Grace Lee");
                    assertThat(responseBody.getAuthorizedSigners().get(1).getName()).isEqualTo("Henry Thompson");
                });
    }

    @Test
    void testUpdateClient() {
        Client updatedClient = new Client();
        updatedClient.setId("cust002");
        updatedClient.setName("producciones maria updated");
        updatedClient.setType("business");
        updatedClient.setAddress("123 Pine St");
        updatedClient.setPhone("555-2345");
        updatedClient.setEmail("info@betaindustries.com");
        updatedClient.setOwners(detailedClient.getOwners());
        updatedClient.setAuthorizedSigners(detailedClient.getAuthorizedSigners());

        Mockito.when(clientService.updateClient(Mockito.eq("cust002"), Mockito.any(Client.class))).thenReturn(Mono.just(updatedClient));

        webTestClient.put().uri("/clients/cust002")
                .bodyValue(updatedClient)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Client.class)
                .consumeWith(response -> {
                    Client responseBody = response.getResponseBody();
                    System.out.println("Response body: " + responseBody);
                    assertThat(responseBody).isNotNull();
                    assertThat(responseBody.getId()).isEqualTo("cust002");
                    assertThat(responseBody.getName()).isEqualTo("producciones maria updated");
                    assertThat(responseBody.getType()).isEqualTo("business");
                    assertThat(responseBody.getAddress()).isEqualTo("123 Pine St");
                    assertThat(responseBody.getPhone()).isEqualTo("555-2345");
                    assertThat(responseBody.getEmail()).isEqualTo("info@betaindustries.com");
                    assertThat(responseBody.getOwners()).hasSize(2);
                    assertThat(responseBody.getOwners().get(0).getName()).isEqualTo("Emily Davis");
                    assertThat(responseBody.getOwners().get(1).getName()).isEqualTo("Frank Miller");
                    assertThat(responseBody.getAuthorizedSigners()).hasSize(2);
                    assertThat(responseBody.getAuthorizedSigners().get(0).getName()).isEqualTo("Grace Lee");
                    assertThat(responseBody.getAuthorizedSigners().get(1).getName()).isEqualTo("Henry Thompson");
                });
    }

    @Test
    void testDeleteClient() {
        Mockito.when(clientService.deleteClient("1")).thenReturn(Mono.empty());

        webTestClient.delete().uri("/clients/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class)
                .consumeWith(response -> {
                    System.out.println("Delete response: " + response);
                    assertThat(response.getResponseBody()).isNull();
                });
    }
}
