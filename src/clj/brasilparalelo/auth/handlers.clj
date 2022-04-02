(ns brasilparalelo.auth.handlers
  (:require
    [brasilparalelo.config :refer [env]]
    [brasilparalelo.db.core :as db]
    [buddy.auth :refer [authenticated? throw-unauthorized]]
    [buddy.auth.backends.token :refer [jwe-backend]]
    [buddy.auth.backends.token :refer [token-backend]]
    [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
    [buddy.core.codecs :as codecs]
    [buddy.core.nonce :as nonce]
    [buddy.hashers :as hashers]
    [buddy.sign.jwt :as jwt]
    [clojure.string :as string]
    [clj-time.core :as time]
    [ring.util.http-response :refer :all]))

(defonce random-secret
         (let [randomdata (nonce/random-bytes 16)]
           (codecs/bytes->hex randomdata)))

(defonce SECRET (:luminus-secret env random-secret))

;;; This should be obtained by environment definitions
(defonce RESOURCES "/home/ventura/internal_git/brasil-paralelo/resources/")

(defonce backend
         (jwe-backend {:secret  SECRET
                       :options {:alg :a256kw :enc :a128gcm}}))

(defn obfuscate-password
  [{password :password}]
  (hashers/derive password {:alg :pbkdf2+sha256 :iterations 1000 :salt SECRET}))


(defn create-token
  [{{{:keys [id admin type]} :body} :parameters}]

  (let
    [claims
     {
      :admin admin
      :exp   (time/plus (time/now) (time/seconds 3600))
      :iat   (time/now)
      :sub   id
      :type  type
      }]

    (let [token (jwt/encrypt claims SECRET {:alg :a256kw :enc :a128gcm})]
      (println claims)
      (println (jwt/sign claims SECRET {:alg :a256kw :enc :a128gcm}))
      {:token token})
    ))

(defn refresh-token
  [{{{:keys [id admin type]} :body} :parameters}]


  (println {:id id :admin admin :type type})
  (println ".")
  (println ".")
  (println ".")
  (println ".")
  (println ".")
  (println ".")
  (println ".")

  (let
    [claims
     {
      :admin admin
      :exp   (time/plus (time/now) (time/seconds 3600))
      :iat   (time/now)
      :type  (or type "basic")
      :sub   id
      }]

    (let [token (jwt/encrypt claims SECRET {:alg :a256kw :enc :a128gcm})]

      (println claims)
      (println (jwt/sign claims SECRET {:alg :hs512}))
      {:token token})
    ))

(defn- check-authentication-basic
  [request]

  (let [{{:keys [body]} :parameters} request]

    (let [user (db/get-user-by-email body)]
      (if-not user
        (throw-unauthorized))

      ;; FIXME: Store password hash only
      (if-not (= (:password user) (:password body))
        (throw-unauthorized))

      (let [{:keys [id admin type]} user]
        (into request {:parameters {:body {:id id :admin admin :type type}}})))))

(defn sign-in-with-password
  [request]
  (->
    (check-authentication-basic request)
    (refresh-token)
    (ok)))

(defn check-authenticated
  [request]

  )