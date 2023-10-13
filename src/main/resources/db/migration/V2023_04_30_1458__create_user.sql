CREATE TABLE IF NOT EXISTS fjobs.user (
  id          SERIAL,
  first_name  VARCHAR(15) NOT NULL,
  last_name   VARCHAR(30) NOT NULL,
  email       VARCHAR(100) NOT NULL,
  city        VARCHAR(30) NOT NULL,
  state       VARCHAR(30) NOT NULL,
  created_at  TIMESTAMP NOT NULL,
  disabled_at TIMESTAMP,
  terms       VARCHAR(200) NOT NULL,
  last_search TIMESTAMP,
  CONSTRAINT user_pk
    PRIMARY KEY(id)
);
