package br.saasmania.economizae.transcription.domain;

import java.io.Serializable;

public abstract class IdentifiedDomainObject implements Serializable {
    private long id = -1;

    public IdentifiedDomainObject(){
        super();
    }

    protected long id(){
        return this.id;
    }

    protected void setId(long anId){
        this.id = anId;
    }
}
