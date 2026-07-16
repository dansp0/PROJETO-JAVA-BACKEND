package br.saasmania.economizae.transcription.domain;

public interface ITranscritorDeAudioPort {
    TextoTranscrito transcrever(AudioOriginal audio);
}
