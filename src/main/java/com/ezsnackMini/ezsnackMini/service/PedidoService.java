package com.ezsnackMini.ezsnackMini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezsnackMini.ezsnackMini.model.Pedido;
import com.ezsnackMini.ezsnackMini.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido atualizar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
