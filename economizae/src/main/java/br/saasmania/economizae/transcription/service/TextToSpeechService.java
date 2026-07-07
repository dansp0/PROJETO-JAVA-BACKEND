package br.saasmania.economizae.transcription.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.stereotype.Service;

@Service
public class TextToSpeechService {
    
    private static final Logger log = LoggerFactory.getLogger(TextToSpeechService.class);
    private final OpenAiAudioSpeechModel speechModel;

    // A injeção de dependência do Spring Boot cuida de instanciar o modelo via application.properties
    public TextToSpeechService(OpenAiAudioSpeechModel speechModel) {
        this.speechModel = speechModel;
    }

    /**
     * Converte um texto limpo em um array de bytes representando um arquivo MP3.
     */
    public byte[] converterTextoParaAudio(String texto) {
        log.info("Sintetizando áudio para o texto: {}", texto);
        
        // O Spring AI abstrai a chamada para a API TTS da OpenAI.
        // O retorno padrão é um byte[] contendo o áudio binário.
        return speechModel.call(texto);
    }
}