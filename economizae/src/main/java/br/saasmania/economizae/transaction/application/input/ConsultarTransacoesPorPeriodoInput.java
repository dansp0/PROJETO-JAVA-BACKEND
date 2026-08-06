package br.saasmania.economizae.transaction.application.input;

import java.time.LocalDate;

public record ConsultarTransacoesPorPeriodoInput(LocalDate inicio, LocalDate fim) {
}
