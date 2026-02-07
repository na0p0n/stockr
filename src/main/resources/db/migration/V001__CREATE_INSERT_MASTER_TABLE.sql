CREATE TABLE user_role(
    id SERIAL PRIMARY KEY,
    role VARCHAR(20)
)

CREATE TABLE rental_status(
    id SERIAL PRIMARY KEY,
    status VARCHAR(20)
)

INSERT INTO user_role VALUES
    (1, 'ADMIN'),
    (2, 'USER'),
    (3, 'STAFF');

INSERT INTO rental_status VALUES
    (1, 'RENTING'),
    (2, 'RETURNED'),
    (3, 'OVERDUE');