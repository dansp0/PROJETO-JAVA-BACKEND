package br.saasmania.economizae.transcription.infrastructure;

import br.saasmania.economizae.transcription.domain.AudioOriginal;
import br.saasmania.economizae.transcription.domain.TextoTranscrito;
import br.saasmania.economizae.transcription.domain.ITranscritorDeAudioPort;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

@Component
public class SpringAiTranscritorDeAudioAdapter implements ITranscritorDeAudioPort {

    private final TranscriptionModel transcriptionModel;

    public SpringAiTranscritorDeAudioAdapter(TranscriptionModel transcriptionModel) {
        this.transcriptionModel = transcriptionModel;
    }

    @Override
    public TextoTranscrito transcrever(AudioOriginal audio) {
        var resource = new ByteArrayResource(audio.conteudo()) {
            @Override
            public String getFilename() {
                return audio.nomeArquivo();
            }
        };

        var prompt = new AudioTranscriptionPrompt(resource);
        var response = transcriptionModel.call(prompt);
        String texto = response.getResult().getOutput();

        return new TextoTranscrito(texto);
    }
}