package com.ezsnackMini.ezsnackMini.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ezsnackMini.ezsnackMini.model.Escola;
import com.ezsnackMini.ezsnackMini.repository.EscolaRepository;

public class EscolaServiceTest {

    private EscolaRepository escolaRepository;
    private EscolaService escolaService;

    @BeforeEach
    public void setUp() {
        escolaRepository = Mockito.mock(EscolaRepository.class);
        escolaService = new EscolaService(escolaRepository);
    }

    @Test
    public void deveSalvarEscola() {
        Escola escola = new Escola();
        escola.setId(1L);
        escola.setNome("Escola Teste");

        when(escolaRepository.save(any(Escola.class))).thenReturn(escola);

        Escola salva = escolaService.salvar(escola);

        assertNotNull(salva);
        assertEquals("Escola Teste", salva.getNome());
        verify(escolaRepository, times(1)).save(escola);
    }

    @Test
    public void deveListarTodasAsEscolas() {
        Escola escola1 = new Escola();
        escola1.setId(1L);
        escola1.setNome("Escola A");

        Escola escola2 = new Escola();
        escola2.setId(2L);
        escola2.setNome("Escola B");

        when(escolaRepository.findAll()).thenReturn(Arrays.asList(escola1, escola2));

        List<Escola> escolas = escolaService.listarTodas();

        assertNotNull(escolas);
        assertEquals(2, escolas.size());
        verify(escolaRepository, times(1)).findAll();
    }

    @Test
    public void deveBuscarEscolaPorId() {
        Escola escola = new Escola();
        escola.setId(1L);
        escola.setNome("Escola Teste");

        when(escolaRepository.findById(1L)).thenReturn(Optional.of(escola));

        Optional<Escola> encontrada = escolaService.buscarPorId(1L);

        assertTrue(encontrada.isPresent());
        assertEquals("Escola Teste", encontrada.get().getNome());
        verify(escolaRepository, times(1)).findById(1L);
    }

    @Test
    public void deveRetornarVazioQuandoEscolaNaoEncontrada() {
        when(escolaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Escola> encontrada = escolaService.buscarPorId(1L);

        assertFalse(encontrada.isPresent());
        verify(escolaRepository, times(1)).findById(1L);
    }

    @Test
    public void deveDeletarEscolaPorId() {
        doNothing().when(escolaRepository).deleteById(1L);

        escolaService.deletar(1L);

        verify(escolaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deveAtualizarEscola() {
        Escola escola = new Escola();
        escola.setId(1L);
        escola.setNome("Escola Atualizada");

        when(escolaRepository.save(any(Escola.class))).thenReturn(escola);

        Escola atualizada = escolaService.atualizar(escola);

        assertNotNull(atualizada);
        assertEquals("Escola Atualizada", atualizada.getNome());
        verify(escolaRepository, times(1)).save(escola);
    }
}
