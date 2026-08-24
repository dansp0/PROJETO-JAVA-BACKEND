package br.saasmania.economizae.identityaccess.infrastructure.persistence.entities;

import br.saasmania.economizae.identityaccess.domain.models.Person;
import br.saasmania.economizae.identityaccess.domain.models.User;
import br.saasmania.economizae.identityaccess.domain.valueobjects.ContactInformation;
import br.saasmania.economizae.identityaccess.domain.valueobjects.Name;
import br.saasmania.economizae.identityaccess.domain.valueobjects.TenantId;
import br.saasmania.economizae.identityaccess.domain.valueobjects.UserId;
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
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID tenantId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean signMessages;

    @Column(nullable = false)
    private int tokenVersion;

    @Column(nullable = false)
    private boolean isSuper;

    @Column(nullable = false)
    private boolean online;

    @Column(name = "whatsapp_id")
    private Long whatsappId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    public static UserEntity from(User user) {
        return new UserEntity(
                user.getId() != null ? UUID.fromString(user.getId().value()) : null,
                UUID.fromString(user.getTenantId().id()),
                user.getUsername(),
                user.getPerson().getName().value(),
                user.getPerson().getContactInformation() != null
                        ? user.getPerson().getContactInformation().email().address()
                        : null,
                user.getPasswordHash(),
                user.getRole(),
                user.isSignMessages(),
                user.getTokenVersion(),
                user.isSuper(),
                user.isOnline(),
                user.getWhatsappId(),
                Instant.now(),
                Instant.now());
    }

    public User toDomain() {
        TenantId tenantIdVo = TenantId.from(this.tenantId.toString());
        ContactInformation contactInfo = this.email != null ? ContactInformation.from(this.email) : null;
        Person person = new Person(tenantIdVo, contactInfo, Name.from(this.name));

        return new User(
                this.id != null ? UserId.from(this.id.toString()) : null,
                tenantIdVo,
                this.username,
                person,
                this.passwordHash,
                this.role,
                this.signMessages,
                this.tokenVersion,
                this.isSuper,
                this.online,
                this.whatsappId);
    }
}
