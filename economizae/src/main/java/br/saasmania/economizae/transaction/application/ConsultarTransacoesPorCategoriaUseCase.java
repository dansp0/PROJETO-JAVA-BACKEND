package br.saasmania.economizae.transaction.application;

import java.util.List;

import org.springframework.stereotype.Service;

import br.saasmania.economizae.transaction.application.input.ConsultarTransacoesPorCategoriaInput;
import br.saasmania.economizae.transaction.application.output.ConsultaTransacoesOutput;
import br.saasmania.economizae.transaction.application.output.TransactionOutput;
import br.saasmania.economizae.transaction.domain.CalculadoraDeTransacoes;
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

    public ConsultaTransacoesOutput executar(ConsultarTransacoesPorCategoriaInput input) {
        List<Transacao> transacoes = repositorio.buscarPorCategoria(input.categoria());

        List<TransactionOutput> saida = transacoes.stream()
                .map(t -> new TransactionOutput(
                        t.getId().asString(),
                        t.getValor(),
                        t.getCategoria(),
                        t.getEstabelecimento(),
                        t.getData(),
                        t.getTipo()))
                .toList();

        return new ConsultaTransacoesOutput(saida, calculadora.somar(transacoes));
    }
}