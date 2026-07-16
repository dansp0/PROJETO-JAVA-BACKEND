package br.saasmania.economizae.transcription.infrastructure;

import br.saasmania.economizae.transcription.domain.ComandoDeVoz;
import br.saasmania.economizae.transcription.domain.ComandoDeVozId;
import br.saasmania.economizae.transcription.domain.IComandoDeVozRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryComandoDeVozRepository implements IComandoDeVozRepository {

    private final Map<ComandoDeVozId, ComandoDeVoz> armazenamento = new ConcurrentHashMap<>();

    @Override
    public ComandoDeVoz salvar(ComandoDeVoz comandoDeVoz) {
        armazenamento.put(comandoDeVoz.getId(), comandoDeVoz);
        return comandoDeVoz;
    }

    @Override
    public Optional<ComandoDeVoz> buscarPorId(ComandoDeVozId id) {
        return Optional.ofNullable(armazenamento.get(id));
    }
}