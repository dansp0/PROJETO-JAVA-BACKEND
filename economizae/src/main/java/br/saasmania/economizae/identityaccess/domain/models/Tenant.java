package br.saasmania.economizae.identityaccess.domain.models;

import br.saasmania.economizae.identityaccess.domain.events.DomainEvent;
import br.saasmania.economizae.identityaccess.domain.events.TenantProvisioned;
import br.saasmania.economizae.identityaccess.domain.valueobjects.TenantId;
import br.saasmania.economizae.identityaccess.domain.valueobjects.UserId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tenant {
    private final TenantId id;
    private String name;
    private String plan;
    private boolean active;
    private final Instant createdAt;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Tenant(TenantId id, String name, String plan, boolean active, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.plan = plan != null ? plan : "free";
        this.active = active;
        this.createdAt = createdAt != null ? createdAt : Instant.now();
    }

    public Tenant(TenantId id, String name, String plan) {
        this(id, name, plan, true, Instant.now());
    }

    public Tenant(TenantId id, String name) {
        this(id, name, "free", true, Instant.now());
    }

    public User registerUser(String username, String passwordHash, Person person) {
        TenantId currentTenantId = tenantId();
        person.setTenantId(currentTenantId);

        return new User(UserId.create(), currentTenantId, username, person, passwordHash);
    }

    public TenantId tenantId() {
        return this.id != null ? this.id : TenantId.create();
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return this.active;
    }

    public static Tenant provision(String name, String plan) {
        Tenant tenant = new Tenant(TenantId.create(), name, plan);
        tenant.domainEvents.add(new TenantProvisioned(tenant.tenantId(), tenant.getName(), tenant.getPlan()));
        return tenant;
    }

    public static Tenant provision(String name) {
        return provision(name, "free");
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(this.domainEvents);
        this.domainEvents.clear();
        return Collections.unmodifiableList(events);
    }

    public TenantId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}