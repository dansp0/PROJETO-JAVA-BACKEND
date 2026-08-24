package br.saasmania.economizae.identityaccess.infrastructure.persistence.repositories;

import br.saasmania.economizae.identityaccess.domain.models.Tenant;
import br.saasmania.economizae.identityaccess.domain.repositories.ITenantRepository;
import br.saasmania.economizae.identityaccess.domain.valueobjects.TenantId;
import br.saasmania.economizae.identityaccess.infrastructure.persistence.entities.TenantEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TenantRepositoryImpl implements ITenantRepository {

    private final SpringDataTenantRepository springDataRepository;

    public TenantRepositoryImpl(SpringDataTenantRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Tenant create(Tenant tenant) {
        TenantEntity entity = TenantEntity.from(tenant);
        TenantEntity saved = springDataRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Tenant save(Tenant tenant) {
        UUID id = UUID.fromString(tenant.tenantId().id());
        TenantEntity entity = springDataRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));

        entity.setName(tenant.getName());
        entity.setPlan(tenant.getPlan());
        entity.setActive(tenant.isActive());

        TenantEntity saved = springDataRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<Tenant> findById(TenantId id) {
        return springDataRepository.findById(UUID.fromString(id.id()))
                .map(TenantEntity::toDomain);
    }

    @Override
    public Optional<Tenant> findByName(String name) {
        return springDataRepository.findByName(name)
                .map(TenantEntity::toDomain);
    }
}
