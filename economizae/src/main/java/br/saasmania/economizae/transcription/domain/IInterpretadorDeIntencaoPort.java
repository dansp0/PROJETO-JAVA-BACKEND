package br.saasmania.economizae.transcription.domain;

public interface IInterpretadorDeIntencaoPort {
    IntencaoComando interpretar(TextoTranscrito texto);
}