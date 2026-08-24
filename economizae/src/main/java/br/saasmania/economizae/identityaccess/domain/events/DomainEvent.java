package br.saasmania.economizae.identityaccess.domain.events;

import java.time.Instant;

public abstract class DomainEvent {
    private final Instant occurredOn;

    protected DomainEvent(Instant occurredOn) {
        this.occurredOn = occurredOn != null ? occurredOn : Instant.now();
    }

    protected DomainEvent() {
        this(Instant.now());
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }

    public abstract String eventName();
}