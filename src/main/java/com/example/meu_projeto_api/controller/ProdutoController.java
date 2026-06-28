package com.example.meu_projeto_api.controller;

import com.example.meu_projeto_api.model.Produto;
import com.example.meu_projeto_api.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe recebe requisições HTTP (REST)
@RequestMapping("/produtos") // Define a URL base para este controller
public class ProdutoController {

    @Autowired // Injeta a dependência do repositório automaticamente
    private ProdutoRepository repository;

    // Método para listar todos os produtos (GET)
    @GetMapping
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    // Método para criar um novo produto (POST) com validação (@Valid)
    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto) {
        Produto novoProduto = repository.save(produto);
        return ResponseEntity.status(201).body(novoProduto); // 201 = Created
    }
}