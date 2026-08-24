package br.saasmania.economizae.identityaccess.infrastructure.persistence.entities;

import br.saasmania.economizae.identityaccess.domain.models.Tenant;
import br.saasmania.economizae.identityaccess.domain.valueobjects.TenantId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tenants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantEntity {
    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String plan;

    @Column(nullable = false)
    private boolean isActive;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    public static TenantEntity from(Tenant tenant) {
        return new TenantEntity(
                UUID.fromString(tenant.tenantId().id()),
                tenant.getName(),
                tenant.getPlan(),
                tenant.isActive(),
                tenant.getCreatedAt(),
                Instant.now());
    }

    public Tenant toDomain() {
        return new Tenant(
                TenantId.from(this.id.toString()),
                this.name,
                this.plan,
                this.isActive,
                this.createdAt);
    }
}
