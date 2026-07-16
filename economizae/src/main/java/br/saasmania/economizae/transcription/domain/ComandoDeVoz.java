package br.saasmania.economizae.transcription.domain;

import java.time.Instant;

public class ComandoDeVoz {
    private final ComandoDeVozId id;
    private final AudioOriginal audioOriginal;
    private final Instant recebidoEm;
    private TextoTranscrito textoTranscrito;
    private IntencaoComando intencao;
    private RespostaFinal respostaFinal;
    private StatusComando status;
    private String motivoFalha;

    private ComandoDeVoz(ComandoDeVozId id, AudioOriginal audioOriginal, Instant recebidoEm) {
        this.id = id;
        this.audioOriginal = audioOriginal;
        this.recebidoEm = recebidoEm;
        this.status = StatusComando.RECEBIDO;
    }

    public static ComandoDeVoz receber(AudioOriginal audioOriginal) {
        return new ComandoDeVoz(ComandoDeVozId.novo(), audioOriginal, Instant.now());
    }

    public static ComandoDeVoz reconstruir(
            ComandoDeVozId id,
            AudioOriginal audioOriginal,
            Instant recebidoEm,
            StatusComando status,
            TextoTranscrito textoTranscrito,
            IntencaoComando intencao,
            RespostaFinal respostaFinal,
            String motivoFalha) {
        var comando = new ComandoDeVoz(id, audioOriginal, recebidoEm);
        comando.status = status;
        comando.textoTranscrito = textoTranscrito;
        comando.intencao = intencao;
        comando.respostaFinal = respostaFinal;
        comando.motivoFalha = motivoFalha;
        return comando;
    }

    public void marcarComoTranscrito(TextoTranscrito textoTranscrito) {
        exigirStatus(StatusComando.RECEBIDO);
        this.textoTranscrito = textoTranscrito;
        this.status = StatusComando.TRANSCRITO;
    }

    public void marcarIntencaoIdentificada(IntencaoComando intencao) {
        exigirStatus(StatusComando.TRANSCRITO);
        this.intencao = intencao;
        this.status = StatusComando.INTENCAO_IDENTIFICADA;
    }

    public void marcarComoExecutado(RespostaFinal respostaFinal) {
        exigirStatus(StatusComando.INTENCAO_IDENTIFICADA);
        this.respostaFinal = respostaFinal;
        this.status = StatusComando.EXECUTADO;
    }

    public void marcarComoFalho(String motivo) {
        this.motivoFalha = motivo;
        this.status = StatusComando.FALHOU;
    }

    private void exigirStatus(StatusComando esperado) {
        if (this.status != esperado) {
            throw new IllegalStateException(
                    "Transição inválida: esperado status %s, atual %s".formatted(esperado, this.status));
        }
    }

    public ComandoDeVozId getId() {
        return id;
    }

    public AudioOriginal getAudioOriginal() {
        return audioOriginal;
    }

    public Instant getRecebidoEm() {
        return recebidoEm;
    }

    public TextoTranscrito getTextoTranscrito() {
        return textoTranscrito;
    }

    public IntencaoComando getIntencao() {
        return intencao;
    }

    public RespostaFinal getRespostaFinal() {
        return respostaFinal;
    }

    public StatusComando getStatus() {
        return status;
    }

    public String getMotivoFalha() {
        return motivoFalha;
    }
}