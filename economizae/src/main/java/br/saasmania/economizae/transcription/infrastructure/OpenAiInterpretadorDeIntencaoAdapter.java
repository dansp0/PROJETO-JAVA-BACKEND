package br.saasmania.economizae.transcription.infrastructure;

import br.saasmania.economizae.transcription.domain.IntencaoComando;
import br.saasmania.economizae.transcription.domain.IInterpretadorDeIntencaoPort;
import br.saasmania.economizae.transcription.domain.TextoTranscrito;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class OpenAiInterpretadorDeIntencaoAdapter implements IInterpretadorDeIntencaoPort {
    private static final String PROMPT_TEMPLATE = """
            Você interpreta comandos financeiros ditados em português brasileiro.

            Frase transcrita: "%s"

            Se a frase relatar um gasto, pagamento ou recebimento já realizado, chame a função criarTransacao.
            Se a frase perguntar sobre gastos, saldo ou histórico, chame a função consultarTransacao.
            Se não for possível identificar com segurança, não chame nenhuma função.
            """;

    private final ChatClient chatClient;

    public OpenAiInterpretadorDeIntencaoAdapter(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public IntencaoComando interpretar(TextoTranscrito texto) {
        var holder = new IntentToolHolder();

        chatClient.prompt()
                .user(PROMPT_TEMPLATE.formatted(texto.valor()))
                .tools(holder)
                .call()
                .content();

        return holder.getResultado() != null
                ? holder.getResultado()
                : IntencaoComando.desconhecida();
    }
}