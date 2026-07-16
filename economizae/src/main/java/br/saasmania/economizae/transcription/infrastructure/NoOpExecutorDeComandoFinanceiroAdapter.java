package br.saasmania.economizae.transcription.infrastructure;

import br.saasmania.economizae.transcription.domain.IntencaoComando;
import br.saasmania.economizae.transcription.domain.RespostaFinal;
import br.saasmania.economizae.transcription.domain.IExecutorDeComandoFinanceiroPort;
import org.springframework.stereotype.Component;

@Component
public class NoOpExecutorDeComandoFinanceiroAdapter implements IExecutorDeComandoFinanceiroPort {

    @Override
    public RespostaFinal executar(IntencaoComando intencao) {
        return new RespostaFinal("Intenção identificada: " + intencao.tipo() + " " + intencao.parametros());
    }
}