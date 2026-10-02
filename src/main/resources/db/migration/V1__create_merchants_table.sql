CREATE TABLE merchants (
                           id UUID PRIMARY KEY,
                           merchant_code VARCHAR(50) NOT NULL UNIQUE,
                           name VARCHAR(150) NOT NULL,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           status VARCHAR(20) NOT NULL,
                           created_at TIMESTAMPTZ NOT NULL,
                           updated_at TIMESTAMPTZ NOT NULL
);