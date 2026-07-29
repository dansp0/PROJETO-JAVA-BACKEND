package br.saasmania.economizae.transcription.domain;

import java.io.Serializable;
import java.util.Date;

public abstract class IdentifiedDomainObject implements Serializable {
    private long id = -1;
    private final Date creationDate = new Date();

    protected long id() {
        return this.id;
    }

    protected void setId(long anId) {
        this.id = anId;
    }

    protected Date creationDate() {
        return this.creationDate;
    }
}