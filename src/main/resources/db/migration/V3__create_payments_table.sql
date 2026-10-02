CREATE TABLE payments(
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    payment_reference VARCHAR(100) NOT NULL UNIQUE,
    amount NUMERIC(19, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    status VARCHAR(20) NOT NULL,
    payment_method VARCHAR(30),
    gateway_name VARCHAR(50),
    failure_reason VARCHAR(255),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,

    CONSTRAINT fk_payments_order
                    FOREIGN KEY (order_id)
                     REFERENCES orders(id)
);