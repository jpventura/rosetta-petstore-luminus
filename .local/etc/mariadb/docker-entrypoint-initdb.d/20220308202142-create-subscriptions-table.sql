CREATE TABLE IF NOT EXISTS subscriptions
(
    id         UUID PRIMARY KEY,
    user_id    UUID,
    active     BOOLEAN,
    type       VARCHAR(16),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT subscriptions_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

