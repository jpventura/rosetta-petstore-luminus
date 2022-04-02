(ns brasilparalelo.users.handlers
  (:require
    [brasilparalelo.auth.handlers :as auth]
    [brasilparalelo.subscriptions.handlers :as subscriptions]
    [brasilparalelo.db.core :as db]
    [clj-time.core :as time]
    [clojure.string :as string]
    [ring.util.http-response :refer :all]))

(defn- create-one-unsafe
  [{:keys [email first_name password last_name]}]

  (let [id (str (java.util.UUID/randomUUID))
        user {
              :id         id
              :active     true
              :admin      false
              :created_at (str (.toLocalDateTime (time/now)))
              :email      email
              :first_name first_name
              :last_name  last_name
              :password   password
              :token      ""
              :updated_at (str (.toLocalDateTime (time/now)))
              }]

    (if (db/create-user user)
      (let [subscription (db/create-subscription
                           {
                            :id         (str (java.util.UUID/randomUUID))
                            :user_id    id
                            :active     true
                            :type       "basic"
                            :created_at (str (.toLocalDateTime (time/now)))
                            :updated_at (str (.toLocalDateTime (time/now)))
                            })]
        (if subscription
          (auth/refresh-token {:id id :admin false :type "basic"}))))))

(defn create-one [{body :body-params}]
  (if (db/get-profile body)
    (precondition-failed {:error "user already exists"})
    (ok (create-one-unsafe body))))

(defn find-one
  [request]

  (let [{{sub :sub} :identity {id :id} :path-params} request]

    (if-not (= sub id)
      (forbidden {:error "Invalid user ID"}))

    (let [subscriptions (db/get-user-subscriptions {:user_id id})
          user (db/get-user {:id id})]

      (if-not user
        (not-found)

        (let [{:keys [id active admin created_at email updated_at]} user]

          (ok {:id         id
               :active     active
               :admin      admin
               :created_at created_at
               :email      email
               :subscriptions (map (fn [{id :id}] id) subscriptions)
               :updated_at updated_at
               }))))))
