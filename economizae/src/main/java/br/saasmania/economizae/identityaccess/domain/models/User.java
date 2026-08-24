package br.saasmania.economizae.identityaccess.domain.models;

import br.saasmania.economizae.identityaccess.domain.valueobjects.ContactInformation;
import br.saasmania.economizae.identityaccess.domain.valueobjects.Name;
import br.saasmania.economizae.identityaccess.domain.valueobjects.TenantId;
import br.saasmania.economizae.identityaccess.domain.valueobjects.UserId;

import java.util.Objects;

public class User {
    private final UserId id;
    private final TenantId tenantId;
    private String username;
    private Person person;
    private String passwordHash;
    private String role;
    private boolean signMessages;
    private int tokenVersion;
    private boolean isSuper;
    private boolean online;
    private Long whatsappId;

    public User(
            UserId id,
            TenantId tenantId,
            String username,
            Person person,
            String passwordHash,
            String role,
            boolean signMessages,
            int tokenVersion,
            boolean isSuper,
            boolean online,
            Long whatsappId
    ) {
        this.id = id;
        this.tenantId = Objects.requireNonNull(tenantId, "TenantId cannot be null");
        this.username = username;
        this.person = Objects.requireNonNull(person, "Person cannot be null");
        this.passwordHash = passwordHash;
        this.role = role != null ? role : "admin";
        this.signMessages = signMessages;
        this.tokenVersion = tokenVersion;
        this.isSuper = isSuper;
        this.online = online;
        this.whatsappId = whatsappId;
    }

    public User(UserId id, TenantId tenantId, String username, Person person, String passwordHash) {
        this(id, tenantId, username, person, passwordHash, "admin", true, 0, false, false, null);
    }

    public void changePassword(String newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }

    public void changePersonalName(Name name) {
        this.person.setName(name);
    }

    public void changePersonalContactInformation(ContactInformation contactInformation) {
        this.person = new Person(
                this.person.getTenantId(),
                contactInformation,
                this.person.getName()
        );
    }

    public UserId getId() {
        return id;
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isSignMessages() {
        return signMessages;
    }

    public void setSignMessages(boolean signMessages) {
        this.signMessages = signMessages;
    }

    public int getTokenVersion() {
        return tokenVersion;
    }

    public void setTokenVersion(int tokenVersion) {
        this.tokenVersion = tokenVersion;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Long getWhatsappId() {
        return whatsappId;
    }

    public void setWhatsappId(Long whatsappId) {
        this.whatsappId = whatsappId;
    }
}