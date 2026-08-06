package br.saasmania.economizae.transaction.application.output;

import java.math.BigDecimal;
import java.util.List;

public record ConsultaTransacoesOutput(List<TransactionOutput> transacoes, BigDecimal total) {
}