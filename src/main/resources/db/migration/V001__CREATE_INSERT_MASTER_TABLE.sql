CREATE SCHEMA stockr_dev;

CREATE TABLE stockr_dev.user_role(
    id SERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE stockr_dev.rental_status(
    id SERIAL PRIMARY KEY,
    status VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO stockr_dev.user_role(id, role) VALUES
    (1, 'USER'),
    (2, 'ADMIN'),
    (3, 'STAFF');

INSERT INTO stockr_dev.rental_status(id, status) VALUES
    (1, 'RENTING'),
    (2, 'RETURNED'),
    (3, 'OVERDUE');
