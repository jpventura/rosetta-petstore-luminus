CREATE TABLE IF NOT EXISTS users
(
    id UUID PRIMARY KEY,
    first_name VARCHAR(32) NOT NULL,
    last_name  VARCHAR(32) NOT NULL,
    email      VARCHAR(32) NOT NULL,
    admin      BOOLEAN     NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL,
    active     BOOLEAN     NOT NULL,
    password   VARCHAR(64) NOT NULL,
    token      VARCHAR(32),

    CONSTRAINT unique_user UNIQUE (id, email)
);
