package br.saasmania.economizae.transcription.domain;

public record TextoTranscrito(String valor) {
    public TextoTranscrito {
        if(valor == null || valor.isBlank()){
            throw new IllegalArgumentException("Texto transcrito não pode ser vazio");
        }
    }
}
