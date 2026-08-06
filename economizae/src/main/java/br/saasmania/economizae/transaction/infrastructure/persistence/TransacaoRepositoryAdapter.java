package br.saasmania.economizae.transaction.infrastructure.persistence;

import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.ITransacaoRepository;
import br.saasmania.economizae.transaction.domain.Transacao;
import br.saasmania.economizae.transaction.infrastructure.persistence.entity.TransacaoEntity;
import br.saasmania.economizae.transaction.infrastructure.persistence.repository.TransacaoJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TransacaoRepositoryAdapter implements ITransacaoRepository {

    private final TransacaoJpaRepository jpaRepository;

    public TransacaoRepositoryAdapter(TransacaoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void salvar(Transacao transacao) {
        jpaRepository.save(TransacaoEntity.from(transacao));
    }

    @Override
    public List<Transacao> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return jpaRepository.findByDataBetween(inicio, fim)
                .stream()
                .map(TransacaoEntity::toDomain)
                .toList();
    }

    @Override
    public List<Transacao> buscarPorCategoria(CategoriaTransacao categoria) {
        return jpaRepository.findByCategoria(categoria)
                .stream()
                .map(TransacaoEntity::toDomain)
                .toList();
    }
}