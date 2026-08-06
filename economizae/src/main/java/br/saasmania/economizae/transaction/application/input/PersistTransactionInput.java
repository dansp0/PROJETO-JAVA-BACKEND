package br.saasmania.economizae.transaction.application.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.TipoMovimento;

public record PersistTransactionInput(
        BigDecimal valor,
        CategoriaTransacao categoria,
        String estabelecimento,
        LocalDate data,
        TipoMovimento tipo) {
}
