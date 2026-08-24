package br.saasmania.economizae.identityaccess.domain.repositories;

import br.saasmania.economizae.identityaccess.domain.models.Tenant;
import br.saasmania.economizae.identityaccess.domain.valueobjects.TenantId;

import java.util.Optional;

public interface ITenantRepository {
    Tenant create(Tenant tenant);
    Tenant save(Tenant tenant);
    Optional<Tenant> findById(TenantId id);
    Optional<Tenant> findByName(String name);
}
