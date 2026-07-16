package br.saasmania.economizae.transcription.presentation;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.saasmania.economizae.transcription.application.ReceberAudioETransformarEmTextoUseCase;
import br.saasmania.economizae.transcription.domain.AudioOriginal;
import br.saasmania.economizae.transcription.domain.RespostaFinal;

@RestController
@RequestMapping("/api")
public class TranscriptionModelController {
    private final ReceberAudioETransformarEmTextoUseCase useCase;

    public TranscriptionModelController(ReceberAudioETransformarEmTextoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(value = "/transcribe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String transcribe(@RequestParam("file") MultipartFile file) {
        AudioOriginal audio = paraAudioOriginal(file);
        RespostaFinal resposta = useCase.executar(audio);
        return resposta.mensagem();
    }


    private AudioOriginal paraAudioOriginal(MultipartFile file) {
        try {
            return new AudioOriginal(
                    file.getBytes(),
                    file.getOriginalFilename(),
                    file.getContentType()
            );
        } catch (IOException ex) {
            throw new UncheckedIOException("Falha ao ler o arquivo de áudio enviado", ex);
        }
    }
}
