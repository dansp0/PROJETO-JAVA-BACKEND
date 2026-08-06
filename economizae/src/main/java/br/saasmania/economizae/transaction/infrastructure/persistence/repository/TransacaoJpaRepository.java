package br.saasmania.economizae.transaction.infrastructure.persistence.repository;

import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.infrastructure.persistence.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransacaoJpaRepository extends JpaRepository<TransacaoEntity, UUID> {
    List<TransacaoEntity> findByDataBetween(LocalDate inicio, LocalDate fim);
    List<TransacaoEntity> findByCategoria(CategoriaTransacao categoria);
}