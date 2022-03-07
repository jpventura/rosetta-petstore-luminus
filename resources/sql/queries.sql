-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
    (id, first_name, last_name, email, password)
VALUES (:id, :first_name, :last_name, :email, :pass)

-- :name update-user! :! :n
-- :doc updates an existing user record
UPDATE users
SET first_name = :first_name,
    last_name  = :last_name,
    email      = :email
WHERE id = :id

-- :name get-user :? :1
-- :doc retrieves a user record given the id
SELECT *
FROM users
WHERE id = :id

-- :name get-user-by-email :? :1
-- :doc retrieves a user record given the email
SELECT users.id,
       users.admin,
       users.password,
       subscriptions.type
FROM users
         INNER JOIN subscriptions ON users.id = subscriptions.user_id
WHERE users.email = :email
-- SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1

-- :name get-all-users :? :*
-- :doc retrieve all users.
SELECT *
FROM users

-- :name delete-user! :! :n
-- :doc deletes a user record given the id
DELETE
FROM users
WHERE id = :id

-- :name get-all-media :? :*
-- :doc retrieve all media.
SELECT *
FROM media;


-- :name update-user! :? :1
-- :doc updates an existing user record
UPDATE users
SET
    token :token,
    updated_at :updated_at
WHERE id = :id