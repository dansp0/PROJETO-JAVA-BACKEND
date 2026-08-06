package br.saasmania.economizae.transaction.application;
import java.util.List;

import org.springframework.stereotype.Service;
import br.saasmania.economizae.transaction.application.input.ConsultarTransacoesPorPeriodoInput;
import br.saasmania.economizae.transaction.application.output.ConsultaTransacoesOutput;
import br.saasmania.economizae.transaction.application.output.TransactionOutput;
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

        public ConsultaTransacoesOutput executar(ConsultarTransacoesPorPeriodoInput input) {
        List<Transacao> transacoes = repositorio.buscarPorPeriodo(input.inicio(), input.fim());

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