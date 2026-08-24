package br.saasmania.economizae.identityaccess.domain.valueobjects;

import java.util.regex.Pattern;

public record EmailAddress(String address) {

    private static final Pattern EMAIL_REGEX = 
            Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

    public EmailAddress {
        if (!isValid(address)) {
            throw new IllegalArgumentException("Invalid EmailAddress: " + address);
        }
    }

    public static EmailAddress from(String address) {
        return new EmailAddress(address);
    }

    public static boolean isValid(String address) {
        if (address == null || address.isEmpty() || address.length() > 100) {
            return false;
        }
        return EMAIL_REGEX.matcher(address).matches();
    }

    @Override
    public String toString() {
        return this.address;
    }
}
