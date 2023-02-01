-- psql --host=localhost --username=postgres --dbname=fastservice --password
-- password: pwd

CREATE TABLE fjobs.user (
    id          SERIAL,
    first_name  VARCHAR(20) NOT NULL,
    last_name   VARCHAR(30) NOT NULL,
    email       VARCHAR(100) UNIQUE NOT NULL,
    city        VARCHAR(50) NOT NULL,
    state       CHAR(2) NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    disabled_at TIMESTAMP NULL,
    terms       VARCHAR(300) NULL,
    last_search TIMESTAMP NULL,
    CONSTRAINT user_pk
      PRIMARY KEY(id)
);

CREATE TABLE fjobs.portal (
    id         SERIAL,
    name       VARCHAR(100) NOT NUll,
    search_url VARCHAR(300) NOT NULL,
    city       VARCHAR(50) NOT NULL,
    state      CHAR(2) NOT NULL,
    enabled    BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT user_pk
      PRIMARY KEY(id)
);

CREATE TABLE fjobs.job (
    id              SERIAL,
    job_title       VARCHAR(600) NOT NULL,
    company_name    VARCHAR(600) NOT NULL,
    job_type        VARCHAR(30) NULL DEFAULT NULL,
    job_description VARCHAR(600) NOT NULL,
    published_at    VARCHAR(30) NULL DEFAULT NULL,
    job_url         VARCHAR(1000) NOT NULL,
    portal_id       INTEGER NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT job_pk
      PRIMARY KEY(id),
    CONSTRAINT job_portal_fk
      FOREIGN KEY(portal_id) REFERENCES fjobs.portal (id)
);

CREATE INDEX fjobs.idx_job_job_url ON job (job_url);

CREATE TABLE fjobs.user_job (
    user_id    INTEGER NOT NULL,
    job_id     INTEGER NOT NULL REFERENCES portal_job (id),
    email_sent TIMESTAMP NULL,
    term       VARCHAR(30) NOT NULL,
    CONSTRAINT user_job_pk
      PRIMARY KEY(user_id, job_id),
    CONSTRAINT user_job_user_fk
      FOREIGN KEY(user_id) REFERENCES fjobs.user (id),
    CONSTRAINT user_job_job_fk
      FOREIGN KEY(job_id) REFERENCES fjobs.job (id)
);
