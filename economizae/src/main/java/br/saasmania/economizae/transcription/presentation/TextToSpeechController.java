package br.saasmania.economizae.transcription.presentation;

import br.saasmania.economizae.transcription.service.TextToSpeechService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de sintetização de voz
 * Faz a chamada ao serviço de sintetização de voz e retorna o áudio gerado como resposta para o Postman
 */

@RestController
@RequestMapping("/api/audio")
public class TextToSpeechController {

    private final TextToSpeechService ttsService;

    public TextToSpeechController(TextToSpeechService ttsService) {
        this.ttsService = ttsService;
    }

    // Define que a saída será um arquivo de áudio MPEG (MP3)
    @PostMapping(value = "/sintetizar", produces = "audio/mpeg")
    public ResponseEntity<byte[]> sintetizarVoz(@RequestBody String textoDaIa) {
        System.out.println("Texto recebido:>>>>>>>>>>" + textoDaIa);
        // Chama a camada de serviço para bater na OpenAI e gerar o áudio
        byte[] audioGerado = ttsService.converterTextoParaAudio(textoDaIa);
        
        // Prepara os cabeçalhos para o Postman/Navegador entenderem que é um arquivo
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "assistente_voz.mp3");

        return new ResponseEntity<>(audioGerado, headers, HttpStatus.OK);
    }
}