package com.ezsnackMini.ezsnackMini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezsnackMini.ezsnackMini.model.Escola;
import com.ezsnackMini.ezsnackMini.repository.EscolaRepository;

@Service
public class EscolaService {

    private final EscolaRepository escolaRepository;

    public EscolaService(EscolaRepository escolaRepository) {
        this.escolaRepository = escolaRepository;
    }

    public Escola salvar(Escola escola) {
        return escolaRepository.save(escola);
    }

    public List<Escola> listarTodas() {
        return escolaRepository.findAll();
    }

    public Optional<Escola> buscarPorId(Long id) {
        return escolaRepository.findById(id);
    }

    public void deletar(Long id) {
        escolaRepository.deleteById(id);
    }

    public Escola atualizar(Escola escola) {
        return escolaRepository.save(escola);
    }
}
