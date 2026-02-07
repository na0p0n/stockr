CREATE TABLE user_role(
    id SERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE rental_status(
    id SERIAL PRIMARY KEY,
    status VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO user_role(id, role) VALUES
    (1, 'ADMIN'),
    (2, 'USER'),
    (3, 'STAFF');

INSERT INTO rental_status(id, status) VALUES
    (1, 'RENTING'),
    (2, 'RETURNED'),
    (3, 'OVERDUE');
