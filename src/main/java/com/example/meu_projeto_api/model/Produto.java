package com.example.meu_projeto_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

// A anotação @Entity diz ao Spring/JPA que esta classe será uma tabela no banco
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Chave primária gerada automaticamente

    @NotBlank(message = "O nome não pode estar vazio") // Validação do Validation Starter
    private String nome;

    @Positive(message = "O preço deve ser maior que zero")
    private Double preco;

    // Construtores, Getters e Setters (Obrigatórios para o Spring e JPA)
    public Produto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}