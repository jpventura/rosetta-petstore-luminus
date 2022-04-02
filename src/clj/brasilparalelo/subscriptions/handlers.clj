(ns brasilparalelo.subscriptions.handlers
  (:require
    [brasilparalelo.db.core :as db]
    [buddy.auth :refer [authenticated? throw-unauthorized]]
    [clj-time.core :as time]
    [ring.util.http-response :refer :all]))

(defstruct Subscription :id :user_id :type :created_at :updated_at)

(defn create
  [request])

(defn find
  [request]

  (if-not (authenticated? request)
    (unauthorized {:error "Authentication required"})

    (let [{{admin :admin sub :sub} :identity
           {user_id :user_id}      :path-params} request]

      (if (and admin (nil? user_id))
        (ok (db/get-all-subscriptions))

        (if (or (nil? user_id) (= user_id sub))
          (ok (db/get-user-subscriptions {:user_id sub}))
          (forbidden {:error "Insufficient permissions"}))))))

(defn find-active
  [request]

  (if-not (authenticated? request)
    (unauthorized {:error "Authentication required"})

    (let [{{admin :admin sub :sub} :identity
           {user_id :user_id}      :path-params} request]

      (if (and admin (nil? user_id))
        (ok (db/get-all-subscriptions))

        (if (or (nil? user_id) (= user_id sub))
          (ok (filter
                (fn [subscription] (:active subscription))
                (db/get-user-subscriptions {:user_id sub}))))))))

(defn create-one
  [request]


  (println (find-active request))

  {:status 201
   :body   (struct Subscription
                   "6054e7b9-245b-4572-b4ce-af1a425fbb24"
                   "0386a23b-da8d-4654-9dbd-9bbe2bb2e66c"
                   "patron"
                   "2021-11-01T07:30:11"
                   "2021-11-01T07:30:11")})

(defn find-one
  [request]

  (let [{{user_id :user_id subscription_id :subscription_id} :path-params} request]
    (let [subscription (db/get-user-subscription {:user_id user_id :subscription_id subscription_id})]
      (if-not subscription
        (not-found {:user_id user_id :subscription_id subscription_id})
        (ok subscription)))
    )
  )

(defn update
  [request])

(defn update-one
  [request])

;; FIXME: This serializers should be already provided by Luminus
(defn- string-to-int
  [active]
  (if (= (type active) java.lang.String)
    (if (= active "false") 0 1)
    (if (= (type active) java.lang.Boolean)
      (if active 1 0)
      )))


(defn cancel-subscription
  [request]

  (let [{{user_id :user_id subscription_id :subscription_id} :path-params} request]

    (let [subscriptions (db/get-user-subscriptions {:user_id user_id})]
      (let [active_subscriptions (filter (fn [{active :active}] active) subscriptions)]
        (map (fn [{user_id :user_id}]
               (db/update-user-subscription {:subscription_id subscription_id :user_id user_id :active 0})
               active_subscriptions))))))

(defn patch-one
  [request]

  (cancel-subscription request)

  (let [{{user_id :user_id subscription_id :subscription_id} :path-params {active :active type :type} :body-params} request]

    (let [{count :next.jdbc/update-count}
          (db/update-user-subscription
            {
             :user_id         user_id
             :subscription_id subscription_id
             :active          (string-to-int active)
             :updated_at      (str (.toLocalDateTime (time/now)))
             })]

      (ok (db/get-user-subscription {:user_id user_id :subscription_id subscription_id})))))
