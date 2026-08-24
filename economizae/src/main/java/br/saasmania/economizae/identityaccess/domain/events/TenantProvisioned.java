package br.saasmania.economizae.identityaccess.domain.events;

import br.saasmania.economizae.identityaccess.domain.valueobjects.TenantId;

import java.time.Instant;
import java.util.Objects;

public class TenantProvisioned extends DomainEvent {
    private final TenantId tenantId;
    private final String name;
    private final String plan;

    public TenantProvisioned(TenantId tenantId, String name, String plan, Instant occurredOn) {
        super(occurredOn);
        this.tenantId = Objects.requireNonNull(tenantId, "TenantId cannot be null");
        this.name = name;
        this.plan = plan;
    }

    public TenantProvisioned(TenantId tenantId, String name, String plan) {
        this(tenantId, name, plan, Instant.now());
    }

    @Override
    public String eventName() {
        return "identityaccess.tenant.provisioned";
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public String getName() {
        return name;
    }

    public String getPlan() {
        return plan;
    }
}