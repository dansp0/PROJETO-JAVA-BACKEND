package br.saasmania.economizae.transcription.domain;

public record AudioOriginal(byte[] conteudo, String nomeArquivo, String contentType) {
    public AudioOriginal {
        if(conteudo == null || conteudo.length == 0){
            throw new IllegalArgumentException("Nome do arquivo é obrigatório");
        }
        if(nomeArquivo == null || nomeArquivo.isBlank()){
            throw new IllegalArgumentException("Nome do arquivop é obrigatório");
        }
        if(contentType == null || contentType.isBlank()){
            throw new IllegalArgumentException("Content-Type é obrigatório");
        }
    }
}
