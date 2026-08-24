package br.saasmania.economizae.identityaccess.infrastructure.persistence.repositories;

import br.saasmania.economizae.identityaccess.infrastructure.persistence.entities.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataTenantRepository extends JpaRepository<TenantEntity, UUID> {
    Optional<TenantEntity> findByName(String name);
}