package br.saasmania.economizae.transaction.application;

import java.util.List;

import org.springframework.stereotype.Service;

import br.saasmania.economizae.transaction.application.dto.ConsultaTransacoesResult;
import br.saasmania.economizae.transaction.domain.CalculadoraDeTransacoes;
import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.ITransacaoRepository;
import br.saasmania.economizae.transaction.domain.Transacao;

@Service
public class ConsultarTransacoesPorCategoriaUseCase {
    private final ITransacaoRepository repositorio;
    private final CalculadoraDeTransacoes calculadora;

    public ConsultarTransacoesPorCategoriaUseCase(ITransacaoRepository repositorio,
                                                   CalculadoraDeTransacoes calculadora) {
        this.repositorio = repositorio;
        this.calculadora = calculadora;
    }

    public ConsultaTransacoesResult executar(CategoriaTransacao categoria) {
        List<Transacao> transacoes = repositorio.buscarPorCategoria(categoria);
        return new ConsultaTransacoesResult(transacoes, calculadora.somar(transacoes));
    }
}