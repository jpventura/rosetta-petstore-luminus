-- :name create-useSr! :! :n
-- :doc creates a new user record
INSERT INTO users
    (id, active, admin, email, first_name, last_name, password, token)
VALUES ('9617143c-a561-4c15-b911-2f72bfd24bb1', true, false, :email, :first_name, :last_name, :password, '')

-- :name create-user :? :1
-- :doc creates a new user record
INSERT INTO users
(id, active, admin, created_at, email, first_name, last_name, password, token, updated_at)
VALUES (:id, :active, :admin, :created_at, :email, :first_name, :last_name, :password, :token, :updated_at)

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

-- :name get-profile :? :1
-- :doc retrieves a user record given the id
SELECT id,
       email,
       first_name,
       last_name,
       active,
       created_at,
       updated_at
FROM users
WHERE email = :email

-- :name create-subscription ?: 1
-- :doc create subscriptions for existing user
INSERT INTO subscriptions(id, user_id, active, type, created_at, updated_at)
VALUES (:id, :user_id, :active, :type, :created_at, :updated_at);


-- :name get-user-subscription :! :1
-- :doc get an existing active user subscription
SELECT *
FROM subscriptions
WHERE id = :subscription_id
  AND user_id = :user_id;



-- :name get-all-subscriptions :? :*
-- :doc retrieve all subscriptions.
SELECT *
FROM subscriptions;

-- :name get-user-subscriptions :? :*
-- :doc get user subscriptions
SELECT *
FROM subscriptions
WHERE user_id = :user_id;

-- :name get-user-subscriptions-ids :? :*
-- :doc get user subscriptions IDs
SELECT id
FROM subscriptions
WHERE user_id = :user_id;


-- :name update-user-subscription :! :1
-- :doc updates an existing user subscription
UPDATE subscriptions
SET active     = :active,
    updated_at = :updated_at
WHERE id = :subscription_id
  AND user_id = :user_id;

-- :name get-user-active-subscription :! :1
-- :doc get an existing active user subscription
SELECT *
FROM subscriptions
WHERE user_id = :user_id
  AND active = 1
ORDER BY created_at DESC;

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
SET token :token,
    updated_at :updated_at
WHERE id = : id
