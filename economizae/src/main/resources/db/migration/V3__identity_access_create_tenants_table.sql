CREATE TABLE tenants (
    id         UUID         NOT NULL,
    name       VARCHAR(255) NOT NULL,
    plan       VARCHAR(255) NOT NULL DEFAULT 'free',
    is_active  BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at TIMESTAMP    NOT NULL DEFAULT now(),
    CONSTRAINT pk_tenants PRIMARY KEY (id),
    CONSTRAINT uq_tenants_name UNIQUE (name)
);

ALTER TABLE users DROP COLUMN profile;
ALTER TABLE users DROP COLUMN is_doctor;
ALTER TABLE users DROP COLUMN company_id;

ALTER TABLE users ADD COLUMN tenant_id UUID;
ALTER TABLE users ADD COLUMN username VARCHAR(255);
ALTER TABLE users ADD COLUMN role VARCHAR(255) NOT NULL DEFAULT 'admin';

-- id was already UUID here (unlike the TypeORM version, which had to migrate
-- from SERIAL to UUID). If your table still has an int id, drop/recreate it
-- the same way the TypeORM migration does:
-- ALTER TABLE users DROP CONSTRAINT pk_users;
-- ALTER TABLE users DROP COLUMN id;
-- ALTER TABLE users ADD COLUMN id UUID NOT NULL;
-- ALTER TABLE users ADD CONSTRAINT pk_users PRIMARY KEY (id);

ALTER TABLE users ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE users ALTER COLUMN username SET NOT NULL;

ALTER TABLE users
    ADD CONSTRAINT fk_users_tenant FOREIGN KEY (tenant_id) REFERENCES tenants (id);