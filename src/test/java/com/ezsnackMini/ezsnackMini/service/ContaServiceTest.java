package com.ezsnackMini.ezsnackMini.service;

import com.ezsnackMini.ezsnackMini.model.Conta;
import com.ezsnackMini.ezsnackMini.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContaServiceTest {

    private ContaRepository contaRepository;
    private ContaService contaService;

    @BeforeEach
    public void setUp() {
        contaRepository = Mockito.mock(ContaRepository.class);
        contaService = new ContaService(contaRepository);
    }

    @Test
    public void deveSalvarConta() {
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSaldo(BigDecimal.valueOf(100.0));

        when(contaRepository.save(any(Conta.class))).thenReturn(conta);

        Conta salva = contaService.criarConta(conta);

        assertNotNull(salva);
        assertEquals(BigDecimal.valueOf(100.0), salva.getSaldo()); // comparação correta
        verify(contaRepository, times(1)).save(conta);
    }
}
