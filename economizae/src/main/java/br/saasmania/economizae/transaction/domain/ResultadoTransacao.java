package br.saasmania.economizae.transaction.domain;

import java.math.BigDecimal;

public record ResultadoTransacao(String descricao, BigDecimal valor, String estabelecimento) {
}