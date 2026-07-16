package br.saasmania.economizae.transcription.domain;

import java.util.Optional;

public interface IComandoDeVozRepository {
    ComandoDeVoz salvar(ComandoDeVoz comandoDeVoz);
    Optional<ComandoDeVoz> buscarPorId(ComandoDeVozId id);
}
