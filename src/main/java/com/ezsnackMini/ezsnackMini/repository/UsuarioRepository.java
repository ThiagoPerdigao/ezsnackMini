package com.ezsnackMini.ezsnackMini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezsnackMini.ezsnackMini.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
