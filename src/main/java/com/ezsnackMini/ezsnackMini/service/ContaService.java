package com.ezsnackMini.ezsnackMini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezsnackMini.ezsnackMini.model.Conta;
import com.ezsnackMini.ezsnackMini.repository.ContaRepository;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Optional<Conta> buscarContaPorId(Long id) {
        return contaRepository.findById(id);
    }

    public Conta atualizarConta(Long id, Conta contaAtualizada) {
        return contaRepository.findById(id).map(conta -> {
            conta.setSaldo(contaAtualizada.getSaldo());
            return contaRepository.save(conta);
        }).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public void deletarConta(Long id) {
        contaRepository.deleteById(id);
    }
}
