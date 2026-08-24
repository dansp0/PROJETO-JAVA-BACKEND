package br.saasmania.economizae.identityaccess.application.usecases;

import br.saasmania.economizae.identityaccess.domain.models.Tenant;
import br.saasmania.economizae.identityaccess.domain.repositories.ITenantRepository;
import br.saasmania.economizae.shared.events.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTenantUseCase {
    private final ITenantRepository tenantRepository;
    private final DomainEventPublisher domainEventPublisher;

    public CreateTenantUseCase(ITenantRepository tenantRepository, DomainEventPublisher domainEventPublisher) {
        this.tenantRepository = tenantRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Transactional
    public Tenant execute(String name, String plan) {
        Tenant tenant = Tenant.provision(name, plan);
        Tenant saved = tenantRepository.create(tenant);
        domainEventPublisher.publishAll(tenant.pullDomainEvents());
        return saved;
    }
}