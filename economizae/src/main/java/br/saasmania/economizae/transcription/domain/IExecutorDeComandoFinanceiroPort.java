package br.saasmania.economizae.transcription.domain;

public interface IExecutorDeComandoFinanceiroPort {
    RespostaFinal executar(IntencaoComando intencao);
}
