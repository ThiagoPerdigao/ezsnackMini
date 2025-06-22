package com.ezsnackMini.ezsnackMini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezsnackMini.ezsnackMini.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
