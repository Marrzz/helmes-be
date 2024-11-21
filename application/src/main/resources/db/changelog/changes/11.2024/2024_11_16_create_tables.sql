--liquibase formatted sql
--changeset marek:create_tables splitStatements:false

CREATE TABLE sector
(
    id        BIGINT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    parent_id BIGINT NULL,
    CONSTRAINT fk_parent FOREIGN KEY (parent_id) REFERENCES sector (id) ON DELETE CASCADE
);

CREATE TABLE person
(
    id           VARCHAR(64) PRIMARY KEY NOT NULL,
    name         VARCHAR(255)            NOT NULL,
    agreed_terms BOOLEAN                 NOT NULL
);

CREATE TABLE person_sector
(
    id        SERIAL PRIMARY KEY,
    person_id VARCHAR(64) NOT NULL,
    sector_id BIGINT      NOT NULL,
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE,
    CONSTRAINT fk_sector FOREIGN KEY (sector_id) REFERENCES sector (id) ON DELETE CASCADE
);