CREATE TABLE orders(
    id UUID PRIMARY KEY ,
    merchant_id UUID NOT NULL,
    order_number VARCHAR(50) NOT NULL UNIQUE,
    amount NUMERIC(19, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    status VARCHAR(20) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,

    CONSTRAINT fk_orders_merchant
                   FOREIGN KEY (merchant_id)
                   REFERENCES merchants(id)
);