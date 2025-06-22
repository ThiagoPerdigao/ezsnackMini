package com.ezsnackMini.ezsnackMini.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezsnackMini.ezsnackMini.model.Escola;
import com.ezsnackMini.ezsnackMini.service.EscolaService;

@RestController
@RequestMapping("/escolas")
public class EscolaController {

    private final EscolaService escolaService;

    public EscolaController(EscolaService escolaService) {
        this.escolaService = escolaService;
    }

    @PostMapping
    public ResponseEntity<Escola> criarEscola(@RequestBody Escola escola) {
        Escola criada = escolaService.salvar(escola);
        return ResponseEntity.ok(criada);
    }

    @GetMapping
    public ResponseEntity<List<Escola>> listarEscolas() {
        return ResponseEntity.ok(escolaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escola> buscarPorId(@PathVariable Long id) {
        return escolaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escola> atualizar(@PathVariable Long id, @RequestBody Escola escola) {
        return escolaService.buscarPorId(id)
                .map(e -> {
                    escola.setId(id);
                    return ResponseEntity.ok(escolaService.atualizar(escola));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        escolaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
