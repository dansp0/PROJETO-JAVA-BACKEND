package br.saasmania.economizae.identityaccess.domain.valueobjects;

public record Name(String value) {

    public Name {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Name: " + value);
        }
        value = value.trim();
    }

    public static Name from(String value) {
        return new Name(value);
    }

    public static boolean isValid(String value) {
        if (value == null)
            return false;
        String trimmed = value.trim();
        return !trimmed.isEmpty() && trimmed.length() <= 100;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
