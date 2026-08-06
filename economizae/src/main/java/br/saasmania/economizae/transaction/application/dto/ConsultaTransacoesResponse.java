package br.saasmania.economizae.transaction.application.dto;

import java.util.List;
import java.math.BigDecimal;

import br.saasmania.economizae.transaction.application.output.TransactionOutput;

public record ConsultaTransacoesResponse(List<TransactionOutput> transacoes, BigDecimal total) {
}