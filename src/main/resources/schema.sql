-- psql --host=localhost --username=retsuko --dbname=ondetemvagas --password
-- password: retsuko123

CREATE TABLE contact (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(30) NOT NULL,
    email   VARCHAR(100) NOT NULL,
    subject VARCHAR(20) NOT NULL,
    message VARCHAR(2000) NOT NULL,
    sent_at TIMESTAMP NOT NULL
);

CREATE TABLE state (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(30) NOT NULL,
    acronym CHAR(2) NOT NULL
);

CREATE TABLE city (
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(30) NOT NULL,
    state_id INTEGER NOT NULL REFERENCES state (id)
);

CREATE TABLE portal (
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50) NOT NULL,
    jobs_uri VARCHAR(300) NOT NULL,
    city_id  INTEGER NOT NULL REFERENCES city (id),
    enabled  BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE portal_jobs (
    id              SERIAL PRIMARY KEY,
    job_title       VARCHAR(600) NOT NULL,
    company_name    VARCHAR(600) NOT NULL,
    job_type        VARCHAR(30) NULL DEFAULT NULL,
    job_description VARCHAR(600) NOT NULL,
    published_at    VARCHAR(30) NULL DEFAULT NULL,
    job_uri         VARCHAR(1000) NOT NULL,
    portal_id       INTEGER NOT NULL REFERENCES portal (id),
    created_at      TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_portal_jobs_uri ON portal_jobs (job_uri);

CREATE TABLE person (
    id          SERIAL PRIMARY KEY,
    first_name  VARCHAR(20) NOT NULL,
    last_name   VARCHAR(30) NOT NULL,
    email       VARCHAR(100) UNIQUE NOT NULL,
    password    VARCHAR(60) NOT NULL,
    city_id     INTEGER NOT NULL REFERENCES city (id),
    enabled     BOOLEAN NOT NULL DEFAULT TRUE,
    last_login  TIMESTAMP NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    disabled_at TIMESTAMP NULL
);

CREATE INDEX idx_person_email ON person (email);

CREATE TABLE authorities (
    email  VARCHAR(100) NOT NULL REFERENCES person (email),
    authority VARCHAR(50) NOT NULL
);

CREATE TABLE payments (
    id        SERIAL PRIMARY KEY,
    person_id INTEGER NOT NULL REFERENCES person (id),
    amount    DECIMAL(6, 2) NOT NULL,
    due_date  DATE NOT NULL,
    payday    TIMESTAMP NULL
);

CREATE TABLE plans (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(300) NOT NULL,
    amount      DECIMAL(6, 2) NOT NULL,
    plan_type   CHAR(1) NOT NULL, -- 1-semanal, 2-mensal, 3-anual
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    disabled_at TIMESTAMP NULL
);

CREATE TABLE person_jobs (
    id             SERIAL PRIMARY KEY,
    person_id      INTEGER NOT NULL REFERENCES person (id),
    portal_job_id  INTEGER NOT NULL REFERENCES portal_jobs (id),
    seen           TIMESTAMP NULL
);

CREATE TABLE person_terms (
    id        SERIAL PRIMARY KEY,
    person_id INTEGER NOT NULL REFERENCES person (id),
    terms     VARCHAR(300) NOT NULL
);

CREATE TABLE crowler_log (
    id         SERIAL PRIMARY KEY,
    portal_id  INTEGER NOT NULL REFERENCES portal (id),
    text       VARCHAR(2000) NOT NULL,
    created_at TIMESTAMP NOT NULL
);