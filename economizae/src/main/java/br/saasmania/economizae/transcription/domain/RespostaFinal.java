package br.saasmania.economizae.transcription.domain;

public record RespostaFinal(String mensagem) {
    public RespostaFinal {
        if(mensagem == null || mensagem.isBlank()){
            throw new IllegalArgumentException("Resposta final não pode ser vazia");
        }
    }
}
