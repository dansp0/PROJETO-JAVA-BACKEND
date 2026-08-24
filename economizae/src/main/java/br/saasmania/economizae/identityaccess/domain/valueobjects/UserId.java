package br.saasmania.economizae.identityaccess.domain.valueobjects;

import java.util.UUID;
import java.util.Objects;

public record UserId(String value) {
    public UserId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("UserId cannot be empty");
        }
    }

    public static UserId create() {
        return new UserId(UUID.randomUUID().toString());
    }

    public static UserId from(String value) {
        return new UserId(value);
    }

    public static UserId from(UUID value) {
        Objects.requireNonNull(value, "UserId cannot be null");
        return new UserId(value.toString());
    }

    @Override
    public String toString() {
        return this.value;
    }
}