CREATE TABLE users (
    id             UUID         NOT NULL,
    name           VARCHAR(255) NOT NULL,
    email          VARCHAR(255) NOT NULL,
    password_hash  VARCHAR(255) NOT NULL,
    sign_messages  BOOLEAN      NOT NULL DEFAULT TRUE,
    token_version  INTEGER      NOT NULL DEFAULT 0,
    profile        VARCHAR(255) NOT NULL DEFAULT 'admin',
    is_super       BOOLEAN      NOT NULL DEFAULT FALSE,
    online         BOOLEAN      NOT NULL DEFAULT FALSE,
    is_doctor      BOOLEAN      NOT NULL DEFAULT FALSE,
    company_id     INTEGER,
    whatsapp_id    BIGINT,
    created_at     TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at     TIMESTAMP    NOT NULL DEFAULT now(),
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_users_email UNIQUE (email)
);