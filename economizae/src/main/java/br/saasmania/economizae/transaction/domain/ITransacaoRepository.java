package br.saasmania.economizae.transaction.domain;

import java.time.LocalDate;
import java.util.List;

public interface ITransacaoRepository {
    void salvar(Transacao transacao);
    List<Transacao> buscarPorPeriodo(LocalDate inicio, LocalDate fim);
    List<Transacao> buscarPorCategoria(CategoriaTransacao categoria);
}