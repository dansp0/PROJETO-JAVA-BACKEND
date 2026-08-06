package br.saasmania.economizae.transaction.application.output;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.TipoMovimento;

public record TransactionOutput(
        String id,
        BigDecimal valor,
        CategoriaTransacao categoria,
        String estabelecimento,
        LocalDate data,
        TipoMovimento tipo) {
}