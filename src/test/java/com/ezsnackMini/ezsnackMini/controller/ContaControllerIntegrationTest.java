package com.ezsnackMini.ezsnackMini.controller;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.ezsnackMini.ezsnackMini.model.Conta;
import com.ezsnackMini.ezsnackMini.repository.ContaRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContaRepository contaRepository;

    @BeforeEach
    void limparBase() {
        contaRepository.deleteAll();
    }

    @Test
    void deveCriarContaPeloEndpoint() {
        Conta conta = new Conta();
        conta.setSaldo(new BigDecimal("200.0"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Conta> request = new HttpEntity<>(conta, headers);

        ResponseEntity<Conta> response = restTemplate.postForEntity("/contas", request, Conta.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getSaldo()).isEqualByComparingTo(new BigDecimal("200.0"));
    }
}
