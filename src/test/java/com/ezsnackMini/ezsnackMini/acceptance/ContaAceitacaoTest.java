package com.ezsnackMini.ezsnackMini.acceptance;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.ezsnackMini.ezsnackMini.model.Conta;
import com.ezsnackMini.ezsnackMini.repository.ContaRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaAceitacaoTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContaRepository contaRepository;

    @BeforeEach
    void setup() {
        contaRepository.deleteAll();
    }

    @Test
    void fluxoCompletoDeCriacaoEAtualizacaoDeConta() {
        // Criar conta
        Conta conta = new Conta();
        conta.setSaldo(new BigDecimal("50.00"));

        ResponseEntity<Conta> postResponse = restTemplate.postForEntity("/contas", conta, Conta.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Conta contaCriada = postResponse.getBody();
        assertThat(contaCriada).isNotNull();
        Long id = contaCriada.getId();

        // Buscar conta criada
        ResponseEntity<Conta> getResponse = restTemplate.getForEntity("/contas/" + id, Conta.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getSaldo()).isEqualByComparingTo(new BigDecimal("50.00"));

        // Atualizar conta
        contaCriada.setSaldo(new BigDecimal("100.00"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Conta> putRequest = new HttpEntity<>(contaCriada, headers);

        ResponseEntity<Conta> putResponse = restTemplate.exchange("/contas/" + id, HttpMethod.PUT, putRequest, Conta.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(putResponse.getBody().getSaldo()).isEqualByComparingTo(new BigDecimal("100.00"));
    }
}
