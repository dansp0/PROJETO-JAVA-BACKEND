package com.example.meu_projeto_api.repository;

import com.example.meu_projeto_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// O JpaRepository já traz métodos prontos como save(), findAll(), findById(), deleteById()
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}