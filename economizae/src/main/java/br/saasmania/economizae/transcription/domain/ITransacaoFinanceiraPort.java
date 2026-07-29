package br.saasmania.economizae.transcription.domain;

import java.util.List;
import java.util.Map;

import br.saasmania.economizae.transaction.domain.ResultadoTransacao; 

public interface ITransacaoFinanceiraPort {
    ResultadoTransacao criarTransacao(Map<String, String> parametros);
    List<ResultadoTransacao> consultarTransacoes(Map<String, String> parametros);
}
