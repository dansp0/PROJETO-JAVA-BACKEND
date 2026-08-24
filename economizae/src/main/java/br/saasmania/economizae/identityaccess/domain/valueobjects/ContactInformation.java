package br.saasmania.economizae.identityaccess.domain.valueobjects;

import java.util.Objects;

public record ContactInformation(EmailAddress email, String phone) {

    public ContactInformation {
        Objects.requireNonNull(email, "EmailAddress cannot be null");
    }

    public static ContactInformation from(String email, String phone) {
        return new ContactInformation(EmailAddress.from(email), phone);
    }

    public static ContactInformation from(String email) {
        return new ContactInformation(EmailAddress.from(email), null);
    }

    @Override
    public String toString() {
        return phone != null ? email.toString() + " (" + phone + ")" : email.toString();
    }
}
