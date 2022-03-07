(ns brasilparalelo.media.handlers
  (:require
    [brasilparalelo.config :refer [env]]
    [brasilparalelo.db.core :as db]
    [brasilparalelo.media.predicates :as predicates]
    [buddy.auth :refer [authenticated?]]
    [clj-time.core :as time]
    [clojure.string :as string]
    [ring.util.http-response :refer :all]))

(defn create-all
  [{body :body}]
  {:status 201 :body body})

(defn create-one
  [{body :body}]
  {:status 201 :body body})

(defn find-all
  [request]

  (if-not (authenticated? request)
    (unauthorized {:error "Authentication token expired"})

    (let [{{admin :admin sub :sub type :type} :identity} request
          subscription (db/get-user-active-subscription {:user_id sub})
          media (db/get-all-media)
          predicate (predicates/rbac subscription)]
      (if admin
        (ok media)
        (ok (filterv predicate media))))))

(defn find-one
  [{{{media_id :id} :path} :parameters}]
  {:status 200 :body {:id media_id}})
