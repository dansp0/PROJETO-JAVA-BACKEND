CREATE TABLE transacao_entity (
    id              UUID            PRIMARY KEY,
    valor           NUMERIC(19,2)   NOT NULL,
    categoria       VARCHAR(50)     NOT NULL,
    estabelecimento VARCHAR(255),
    data            DATE            NOT NULL,
    tipo            VARCHAR(20)     NOT NULL
);