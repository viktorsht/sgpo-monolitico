CREATE TABLE IF NOT EXISTS roles (
    role_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT IGNORE INTO roles (role_id, name) VALUES (1, 'Admin');
INSERT IGNORE INTO roles (role_id, name) VALUES (2, 'Client');

CREATE TABLE IF NOT EXISTS travel_routes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    route_name VARCHAR(255) NOT NULL UNIQUE,
    departure_location VARCHAR(255) NOT NULL,
    arrival_location VARCHAR(255) NOT NULL,
    departure_time TIME NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    seats INT NOT NULL DEFAULT 50
);

INSERT IGNORE INTO travel_routes (route_name, departure_location, arrival_location, departure_time, price, seats)
VALUES
    ('Rota Salvador - Aracaju', 'Salvador', 'Aracaju', '07:30:00', 120.00, 50),
    ('Rota Recife - João Pessoa', 'Recife', 'João Pessoa', '08:00:00', 80.00, 50),
    ('Rota Fortaleza - Natal', 'Fortaleza', 'Natal', '10:00:00', 100.00, 50),
    ('Rota Teresina - São Luís', 'Teresina', 'São Luís', '06:00:00', 150.00, 50),
    ('Rota São Luís - Parnaíba', 'São Luís', 'Parnaíba', '07:30:00', 130.00, 50),
    ('Rota Parnaíba - Caxias', 'Parnaíba', 'Caxias', '09:00:00', 110.00, 50),
    ('Rota Caxias - Timon', 'Caxias', 'Timon', '12:00:00', 90.00, 50),
    ('Rota Timon - Teresina', 'Timon', 'Teresina', '14:30:00', 80.00, 50);

