package br.saasmania.economizae.transcription.domain;

import java.util.UUID;

public record ComandoDeVozId(UUID valor){
    public ComandoDeVozId{
        if(valor == null){
            throw new IllegalArgumentException("ComandoDeVozId não pode ser nulo");
        }
    }
    public static ComandoDeVozId novo(){
        return new ComandoDeVozId(UUID.randomUUID());
    }
}
