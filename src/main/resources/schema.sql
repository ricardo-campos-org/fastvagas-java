CREATE TABLE contacts (
    contact_id SERIAL PRIMARY KEY,
    name       VARCHAR(30) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    subject    VARCHAR(20) NOT NULL,
    message    VARCHAR(2000) NOT NULL,
    sent_at    TIMESTAMP NOT NULL
);

CREATE TABLE states (
    state_id SERIAL PRIMARY KEY,
    name     VARCHAR(30) NOT NULL,
    sigla_uf CHAR(2) NOT NULL
);

CREATE TABLE cities (
    city_id  SERIAL PRIMARY KEY,
    name     VARCHAR(30) NOT NULL,
    state_id INTEGER NOT NULL REFERENCES states (state_id)
);

CREATE TABLE portals (
    portal_id  SERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    url        VARCHAR(300) NOT NULL,
    city_id    INTEGER NOT NULL REFERENCES cities (city_id)
);

CREATE TABLE portal_jobs (
    portal_job_id  SERIAL PRIMARY KEY,
    name           VARCHAR(600) NOT NULL,
    company_name   VARCHAR(600) NOT NULL,
    job_type       VARCHAR(30) NULL,
    description    VARCHAR(600) NOT NULL,
    published_at   TIMESTAMP NULL, -- e.g. 1999-01-08
    url            VARCHAR(300) NOT NULL,
    seen           TIMESTAMP NULL, -- e.g. 1999-01-08 04:05:06
    portal_id      INTEGER NOT NULL REFERENCES portals (portal_id)
);

CREATE INDEX idx_portal_jobs_url ON portal_jobs (url);

CREATE TABLE users (
    user_id        SERIAL PRIMARY KEY,
    first_name     VARCHAR(20) NOT NULL,
    last_name      VARCHAR(30) NOT NULL,
    email          VARCHAR(100) UNIQUE NOT NULL,
    password       VARCHAR(255) NOT NULL,
    city_id        INTEGER NOT NULL REFERENCES cities (city_id),
    enabled        INT NOT NULL DEFAULT 1,
    last_login     TIMESTAMP NULL,
    created_at     TIMESTAMP NOT NULL,
    disabled_at    TIMESTAMP NULL
);

CREATE INDEX idx_users_email ON users (email);

CREATE TABLE authorities (
    email  VARCHAR(100) NOT NULL REFERENCES users (email),
    authority VARCHAR(50) NOT NULL
);

CREATE TABLE payments (
    payment_id     SERIAL PRIMARY KEY,
    user_id        INTEGER NOT NULL REFERENCES users (user_id),
    amount         DECIMAL(6, 2) NOT NULL,
    due_date       DATE NOT NULL,
    payday         TIMESTAMP NULL
);

CREATE TABLE plans (
    plan_id     SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(300) NOT NULL,
    amount      DECIMAL(6, 2) NOT NULL,
    plan_type   CHAR(1) NOT NULL, -- 1-semanal, 2-mensal, 3-anual
    created_at  TIMESTAMP NOT NULL,
    disabled_at TIMESTAMP NULL
);

CREATE TABLE user_jobs (
    user_id        INTEGER NOT NULL REFERENCES users (user_id),
    portal_job_id  INTEGER NOT NULL REFERENCES portal_jobs (portal_job_id),
    PRIMARY KEY (user_id, portal_job_id)
);

CREATE TABLE user_terms (
    user_id INTEGER NOT NULL REFERENCES users (user_id),
    terms   VARCHAR(300) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE crowler_log (
    created_at TIMESTAMP NOT NULL,
    sequence   INTEGER NOT NULL,
    portal_id  INTEGER NOT NULL REFERENCES portals (portal_id),
    text       VARCHAR(2000) NOT NULL,
    PRIMARY KEY (created_at, sequence)
);