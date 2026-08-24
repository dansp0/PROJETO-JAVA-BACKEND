package br.saasmania.economizae.identityaccess.domain.valueobjects;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public record TenantId(String id) {

    private static final Pattern UUID_V4_REGEX =
            Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", Pattern.CASE_INSENSITIVE);

    public TenantId {
        if (!isValid(id)) {
            throw new IllegalArgumentException("Invalid TenantId: " + id);
        }
    }

    public static TenantId create() {
        return new TenantId(UUID.randomUUID().toString());
    }

    public static TenantId from(String id) {
        return new TenantId(id);
    }

    public static TenantId from(UUID id) {
        Objects.requireNonNull(id, "TenantId cannot be null");
        return new TenantId(id.toString());
    }

    public static boolean isValid(String id) {
        return id != null && UUID_V4_REGEX.matcher(id).matches();
    }

    @Override
    public String toString() {
        return this.id;
    }
}
