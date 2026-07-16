package br.saasmania.economizae.transcription.infrastructure;

import br.saasmania.economizae.transcription.domain.IntencaoComando;
import br.saasmania.economizae.transcription.domain.IInterpretadorDeIntencaoPort;
import br.saasmania.economizae.transcription.domain.TextoTranscrito;
import br.saasmania.economizae.transcription.domain.TipoIntencao;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OpenAiInterpretadorDeIntencaoAdapter implements IInterpretadorDeIntencaoPort {

    private static final String PROMPT_TEMPLATE = """
            Você interpreta comandos financeiros ditados em português brasileiro.

            Frase transcrita: "%s"

            Classifique a intenção como uma destas:
            - CRIAR_TRANSACAO: quando a pessoa relata um gasto ou pagamento já realizado
            - CONSULTAR_TRANSACAO: quando a pessoa pergunta sobre gastos, saldo ou histórico
            - DESCONHECIDA: quando não for possível identificar com segurança

            Se for CRIAR_TRANSACAO, extraia também, quando presentes:
            - valor (número, sem "reais", usando ponto decimal)
            - estabelecimento (local ou loja mencionada)
            - categoria (ex: alimentação, transporte, saúde, lazer)

            Se for CONSULTAR_TRANSACAO, extraia quando presentes:
            - periodo (ex: hoje, essa semana, esse mes)
            - categoria
            """;

    private final ChatClient chatClient;

    public OpenAiInterpretadorDeIntencaoAdapter(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public IntencaoComando interpretar(TextoTranscrito texto) {
        var resultado = chatClient.prompt()
                .user(PROMPT_TEMPLATE.formatted(texto.valor()))
                .call()
                .entity(IntencaoDetectadaDTO.class);

        if (resultado == null) {
            return IntencaoComando.desconhecida();
        }

        return mapearParaIntencaoComando(resultado);
    }

    private IntencaoComando mapearParaIntencaoComando(IntencaoDetectadaDTO dto) {
        TipoIntencao tipo;
        try {
            tipo = TipoIntencao.valueOf(dto.tipo());
        } catch (IllegalArgumentException | NullPointerException ex) {
            return IntencaoComando.desconhecida();
        }

        Map<String, String> parametros = new HashMap<>();
        if (dto.valor() != null) parametros.put("valor", dto.valor());
        if (dto.estabelecimento() != null) parametros.put("estabelecimento", dto.estabelecimento());
        if (dto.categoria() != null) parametros.put("categoria", dto.categoria());
        if (dto.periodo() != null) parametros.put("periodo", dto.periodo());

        return new IntencaoComando(tipo, parametros);
    }

    private record IntencaoDetectadaDTO(
            String tipo,
            String valor,
            String estabelecimento,
            String categoria,
            String periodo
    ) {}
}