package br.saasmania.economizae.transaction.application;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.saasmania.economizae.transaction.application.dto.ConsultaTransacoesResult;
import br.saasmania.economizae.transaction.domain.CalculadoraDeTransacoes;
import br.saasmania.economizae.transaction.domain.ITransacaoRepository;
import br.saasmania.economizae.transaction.domain.Transacao;

@Service
public class ConsultarTransacoesPorPeriodoUseCase {
    private final ITransacaoRepository repositorio;
    private final CalculadoraDeTransacoes calculadora;

    public ConsultarTransacoesPorPeriodoUseCase(ITransacaoRepository repositorio,
                                                 CalculadoraDeTransacoes calculadora) {
        this.repositorio = repositorio;
        this.calculadora = calculadora;
    }

    public ConsultaTransacoesResult executar(LocalDate inicio, LocalDate fim) {
        List<Transacao> transacoes = repositorio.buscarPorPeriodo(inicio, fim);
        return new ConsultaTransacoesResult(transacoes, calculadora.somar(transacoes));
    }
}