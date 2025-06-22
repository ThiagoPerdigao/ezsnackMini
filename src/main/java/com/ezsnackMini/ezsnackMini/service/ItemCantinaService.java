package com.ezsnackMini.ezsnackMini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezsnackMini.ezsnackMini.model.ItemCantina;
import com.ezsnackMini.ezsnackMini.repository.ItemCantinaRepository;

@Service
public class ItemCantinaService {

    private final ItemCantinaRepository itemCantinaRepository;

    public ItemCantinaService(ItemCantinaRepository itemCantinaRepository) {
        this.itemCantinaRepository = itemCantinaRepository;
    }

    public ItemCantina salvar(ItemCantina item) {
        return itemCantinaRepository.save(item);
    }

    public List<ItemCantina> listarTodos() {
        return itemCantinaRepository.findAll();
    }

    public Optional<ItemCantina> buscarPorId(Long id) {
        return itemCantinaRepository.findById(id);
    }

    public void deletar(Long id) {
        itemCantinaRepository.deleteById(id);
    }

    public ItemCantina atualizar(ItemCantina item) {
        return itemCantinaRepository.save(item);
    }
}
