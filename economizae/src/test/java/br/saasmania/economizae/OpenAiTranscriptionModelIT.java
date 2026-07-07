package br.saasmania.economizae;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiTranscriptionModelIT {
    @Autowired
    OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    @ParameterizedTest
    @CsvSource({
        "recording-1.mp3, 80 reais",
        "recording-2.mp3, 40 reais",
        "recording-3.mp3, 120 reais",
        "recording-4.mp3, 90 reais",
        "recording-5.mp3, 200 reais",
        "recording-6.mp3, 60 reais",
        "recording-7.mp3, 20 reais",
        "recording-8.mp3, 24.75 reais",
    })
    public void should_containExpectedKeywords_when_audioFilesAreProcessed(String fileName, String expectedKeyword){
        var recording = new ClassPathResource("audio/" + fileName);

        var response = openAiAudioTranscriptionModel.call(recording);

        assertThat(response).contains(expectedKeyword);

        System.out.println(response);
    }
}
