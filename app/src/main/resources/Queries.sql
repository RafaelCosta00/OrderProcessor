CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    order_number VARCHAR(100) NOT NULL,
    items JSONB NOT NULL,
    username VARCHAR(100) NOT NULL,
    shipment_option VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);