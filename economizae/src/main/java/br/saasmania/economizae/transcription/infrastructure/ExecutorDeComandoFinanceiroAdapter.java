package br.saasmania.economizae.transcription.infrastructure;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.saasmania.economizae.transaction.application.ConsultarTransacoesPorPeriodoUseCase;
import br.saasmania.economizae.transaction.application.CriarTransacaoUseCase;
import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.TipoMovimento;
import br.saasmania.economizae.transcription.domain.IExecutorDeComandoFinanceiroPort;
import br.saasmania.economizae.transcription.domain.IntencaoComando;
import br.saasmania.economizae.transcription.domain.RespostaFinal;
import br.saasmania.economizae.transaction.application.input.ConsultarTransacoesPorPeriodoInput;
import br.saasmania.economizae.transaction.application.input.PersistTransactionInput;
import br.saasmania.economizae.transaction.application.output.ConsultaTransacoesOutput;
import br.saasmania.economizae.transaction.application.output.TransactionOutput;

@Component
public class ExecutorDeComandoFinanceiroAdapter implements IExecutorDeComandoFinanceiroPort {

    private final CriarTransacaoUseCase criarTransacao;
    private final ConsultarTransacoesPorPeriodoUseCase consultarTransacoes;

    public ExecutorDeComandoFinanceiroAdapter(
            CriarTransacaoUseCase criarTransacao,
            ConsultarTransacoesPorPeriodoUseCase consultarTransacoes) {
        this.criarTransacao = criarTransacao;
        this.consultarTransacoes = consultarTransacoes;
    }

    @Override
    public RespostaFinal executar(IntencaoComando intencao) {
        return switch (intencao.tipo()) {
            case CRIAR_TRANSACAO -> criar(intencao.parametros());
            case CONSULTAR_TRANSACAO -> consultar(intencao.parametros());
            case DESCONHECIDA -> new RespostaFinal(
                    "Não consegui entender o comando. Pode repetir de outra forma?");
        };
    }

    private TipoMovimento extrairTipo(String tipoBruto) {
        if (tipoBruto == null || tipoBruto.isBlank()) {
            return TipoMovimento.DESPESA; // default explícito, não acidental
        }
        try {
            return TipoMovimento.valueOf(tipoBruto.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            return TipoMovimento.DESPESA;
        }
    }

    private RespostaFinal criar(Map<String, String> parametros) {
        PersistTransactionInput input = new PersistTransactionInput(
                extrairValor(parametros.get("valor")),
                extrairCategoria(parametros.get("categoria")),
                parametros.getOrDefault("estabelecimento", "não informado"),
                extrairData(parametros.get("periodo")),
                extrairTipo(parametros.get("tipo")));

        TransactionOutput resultado = criarTransacao.executar(input);

        return new RespostaFinal(
                "Transação registrada: R$ %.2f em %s (%s) no dia %s".formatted(
                        resultado.valor(), resultado.estabelecimento(),
                        resultado.categoria(), resultado.data()));
    }

    private RespostaFinal consultar(Map<String, String> parametros) {
        LocalDate data = extrairData(parametros.get("periodo"));
        ConsultaTransacoesOutput resultado = consultarTransacoes.executar(
                new ConsultarTransacoesPorPeriodoInput(data, data));

        return new RespostaFinal(
                "Você tem %d transação(ões) em %s, totalizando R$ %.2f".formatted(
                        resultado.transacoes().size(), data, resultado.total()));
    }

    private BigDecimal extrairValor(String valorBruto) {
        if (valorBruto == null || valorBruto.isBlank()) {
            throw new IllegalArgumentException("Valor da transação não informado pela intenção");
        }
        return new BigDecimal(valorBruto.replace(",", "."));
    }

    private CategoriaTransacao extrairCategoria(String categoriaBruta) {
        if (categoriaBruta == null || categoriaBruta.isBlank()) {
            return CategoriaTransacao.OUTROS;
        }
        try {
            return CategoriaTransacao.valueOf(categoriaBruta.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            return CategoriaTransacao.OUTROS;
        }
    }

    private LocalDate extrairData(String periodo) {
        if (periodo == null)
            return LocalDate.now();
        return switch (periodo.toLowerCase(Locale.ROOT)) {
            case "hoje" -> LocalDate.now();
            case "ontem" -> LocalDate.now().minusDays(1);
            default -> LocalDate.now(); // refinar depois: parsing de datas explícitas
        };
    }
}