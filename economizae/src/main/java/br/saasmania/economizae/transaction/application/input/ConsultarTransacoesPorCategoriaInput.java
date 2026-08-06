package br.saasmania.economizae.transaction.application.input;

import br.saasmania.economizae.transaction.domain.CategoriaTransacao;

public record ConsultarTransacoesPorCategoriaInput(CategoriaTransacao categoria) {
}