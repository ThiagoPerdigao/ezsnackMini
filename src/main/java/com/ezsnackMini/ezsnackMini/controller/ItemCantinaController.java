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

import com.ezsnackMini.ezsnackMini.model.ItemCantina;
import com.ezsnackMini.ezsnackMini.service.ItemCantinaService;

@RestController
@RequestMapping("/itens")
public class ItemCantinaController {

    private final ItemCantinaService itemCantinaService;

    public ItemCantinaController(ItemCantinaService itemCantinaService) {
        this.itemCantinaService = itemCantinaService;
    }

    @PostMapping
    public ResponseEntity<ItemCantina> criarItem(@RequestBody ItemCantina item) {
        ItemCantina criado = itemCantinaService.salvar(item);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<ItemCantina>> listarItens() {
        return ResponseEntity.ok(itemCantinaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCantina> buscarPorId(@PathVariable Long id) {
        return itemCantinaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCantina> atualizar(@PathVariable Long id, @RequestBody ItemCantina item) {
        return itemCantinaService.buscarPorId(id)
                .map(i -> {
                    item.setId(id);
                    return ResponseEntity.ok(itemCantinaService.atualizar(item));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemCantinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
