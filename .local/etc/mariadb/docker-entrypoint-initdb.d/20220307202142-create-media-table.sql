CREATE TABLE IF NOT EXISTS media
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(100),
    released_at DATETIME,
    type        VARCHAR(16)
);
