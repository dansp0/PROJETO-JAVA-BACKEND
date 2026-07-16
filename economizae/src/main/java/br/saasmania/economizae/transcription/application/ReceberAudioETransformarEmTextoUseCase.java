package br.saasmania.economizae.transcription.application;

import br.saasmania.economizae.transcription.domain.AudioOriginal;
import br.saasmania.economizae.transcription.domain.ComandoDeVoz;
import br.saasmania.economizae.transcription.domain.IExecutorDeComandoFinanceiroPort;
import br.saasmania.economizae.transcription.domain.IComandoDeVozRepository;
import br.saasmania.economizae.transcription.domain.IntencaoComando;
import br.saasmania.economizae.transcription.domain.IInterpretadorDeIntencaoPort;
import br.saasmania.economizae.transcription.domain.RespostaFinal;
import br.saasmania.economizae.transcription.domain.TextoTranscrito;
import br.saasmania.economizae.transcription.domain.ITranscritorDeAudioPort;
import org.springframework.stereotype.Service;

@Service
public class ReceberAudioETransformarEmTextoUseCase {
    private final ITranscritorDeAudioPort transcritor;
    private final IInterpretadorDeIntencaoPort interpretador;
    private final IExecutorDeComandoFinanceiroPort executor;
    private final IComandoDeVozRepository repositorio;

    public ReceberAudioETransformarEmTextoUseCase(
            ITranscritorDeAudioPort transcritor,
            IInterpretadorDeIntencaoPort interpretador,
            IExecutorDeComandoFinanceiroPort executor,
            IComandoDeVozRepository repositorio){
        this.transcritor = transcritor;
        this.interpretador = interpretador;
        this.executor = executor;
        this.repositorio = repositorio;
    }

    public RespostaFinal executar(AudioOriginal audioOriginal) {
        ComandoDeVoz comando = ComandoDeVoz.receber(audioOriginal);
        repositorio.salvar(comando);

        try {
            TextoTranscrito texto = transcritor.transcrever(audioOriginal);
            comando.marcarComoTranscrito(texto);
            repositorio.salvar(comando);

            IntencaoComando intencao = interpretador.interpretar(texto);
            comando.marcarIntencaoIdentificada(intencao);
            repositorio.salvar(comando);

            RespostaFinal resposta = executor.executar(intencao);
            comando.marcarComoExecutado(resposta);
            repositorio.salvar(comando);

            return resposta;

        } catch (Exception ex) {
            comando.marcarComoFalho(ex.getMessage());
            repositorio.salvar(comando);
            throw ex;
        }
    }
}
