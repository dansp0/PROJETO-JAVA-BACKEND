package br.saasmania.economizae.identityaccess.domain.models;

import br.saasmania.economizae.identityaccess.domain.valueobjects.ContactInformation;
import br.saasmania.economizae.identityaccess.domain.valueobjects.Name;
import br.saasmania.economizae.identityaccess.domain.valueobjects.TenantId;

import java.util.Objects;

public class Person {
    private TenantId tenantId;
    private final ContactInformation contactInformation;
    private Name name;

    public Person(TenantId tenantId, ContactInformation contactInformation, Name name) {
        this.tenantId = tenantId;
        this.contactInformation = contactInformation;
        this.name = Objects.requireNonNull(name, "Name cannot be null");
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public void setTenantId(TenantId tenantId) {
        this.tenantId = tenantId;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
    }
}