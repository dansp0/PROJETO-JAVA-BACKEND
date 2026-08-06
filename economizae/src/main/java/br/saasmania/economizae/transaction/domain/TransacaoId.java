package br.saasmania.economizae.transaction.domain;

import java.util.UUID;

public record TransacaoId(UUID valor) {
    public static TransacaoId novo() {
        return new TransacaoId(UUID.randomUUID());
    }

    public String asString(){
        return valor.toString();
    }
}
