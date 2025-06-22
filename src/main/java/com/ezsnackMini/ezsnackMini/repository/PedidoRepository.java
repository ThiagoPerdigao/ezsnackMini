package com.ezsnackMini.ezsnackMini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezsnackMini.ezsnackMini.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
