package br.saasmania.economizae.transcription.domain;

import java.util.Map;

public record IntencaoComando(TipoIntencao tipo, Map<String, String> parametros) {
    public IntencaoComando {
        if(tipo == null){
            throw new IllegalArgumentException("Tipo de intenção é obrigatório");
        }
    }

    public static IntencaoComando desconhecida(){
        return new IntencaoComando(TipoIntencao.DESCONHECIDA, Map.of());
    }
}
