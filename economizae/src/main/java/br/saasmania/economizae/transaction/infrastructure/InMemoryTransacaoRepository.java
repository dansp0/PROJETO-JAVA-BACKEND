package br.saasmania.economizae.transaction.infrastructure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.context.annotation.Profile;

import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.ITransacaoRepository;
import br.saasmania.economizae.transaction.domain.Transacao;

@Profile("test")
public class InMemoryTransacaoRepository implements ITransacaoRepository {
    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    @Override
    public void salvar(Transacao transacao) {
        transacoes.add(transacao);
    }

    @Override
    public List<Transacao> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        List<Transacao> resultado = new ArrayList<>();
        for (Transacao t : transacoes) {
            if (!t.getData().isBefore(inicio) && !t.getData().isAfter(fim)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    @Override
    public List<Transacao> buscarPorCategoria(CategoriaTransacao categoria) {
        List<Transacao> resultado = new ArrayList<>();
        for (Transacao t : transacoes) {
            if (t.getCategoria() == categoria) {
                resultado.add(t);
            }
        }
        return resultado;
    }
}