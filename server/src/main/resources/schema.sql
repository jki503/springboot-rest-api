CREATE TABLE customers
(
    customer_id BINARY(16) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    create_at DATETIME(6) NOT NULL,
    last_login_at DATETIME(6) NOT NULL,
    CONSTRAINT unique_customer_email UNIQUE (email)
);