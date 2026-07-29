package br.saasmania.economizae.transcription.infrastructure;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.TipoMovimento;
import br.saasmania.economizae.transcription.domain.IntencaoComando;
import br.saasmania.economizae.transcription.domain.TipoIntencao;

public class IntentToolHolder {

    private IntencaoComando resultado;

    @Tool(description = "Registra uma transação financeira (despesa ou receita) relatada pelo usuário")
    public String criarTransacao(
            @ToolParam(description = "Valor da transação em reais, ex: 45.90") BigDecimal valor,
            @ToolParam(description = "Se é uma despesa (gasto/pagamento) ou uma receita (ganho/recebimento)")
                    TipoMovimento tipoMovimento,
            @ToolParam(description = "Categoria da transação", required = false) CategoriaTransacao categoria,
            @ToolParam(description = "Estabelecimento ou local mencionado", required = false) String estabelecimento,
            @ToolParam(description = "Período mencionado (hoje, ontem, etc.)", required = false) String periodo) {

        Map<String, String> parametros = new HashMap<>();
        parametros.put("valor", valor.toString());
        parametros.put("tipo", tipoMovimento.name());
        if (categoria != null) parametros.put("categoria", categoria.name());
        if (estabelecimento != null) parametros.put("estabelecimento", estabelecimento);
        if (periodo != null) parametros.put("periodo", periodo);

        this.resultado = new IntencaoComando(TipoIntencao.CRIAR_TRANSACAO, parametros);
        return "registrado";
    }

    @Tool(description = "Consulta transações financeiras já registradas por período e/ou categoria")
    public String consultarTransacao(
            @ToolParam(description = "Período consultado (hoje, essa semana, esse mes)", required = false) String periodo,
            @ToolParam(description = "Categoria a filtrar", required = false) CategoriaTransacao categoria) {

        Map<String, String> parametros = new HashMap<>();
        if (periodo != null) parametros.put("periodo", periodo);
        if (categoria != null) parametros.put("categoria", categoria.name());

        this.resultado = new IntencaoComando(TipoIntencao.CONSULTAR_TRANSACAO, parametros);
        return "consultado";
    }

    public IntencaoComando getResultado() {
        return resultado;
    }
}