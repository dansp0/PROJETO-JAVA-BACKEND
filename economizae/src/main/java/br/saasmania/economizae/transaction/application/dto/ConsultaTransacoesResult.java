package br.saasmania.economizae.transaction.application.dto;

import java.math.BigDecimal;
import java.util.List;

import br.saasmania.economizae.transaction.domain.Transacao;

public record ConsultaTransacoesResult(List<Transacao> transacoes, BigDecimal total) {
}