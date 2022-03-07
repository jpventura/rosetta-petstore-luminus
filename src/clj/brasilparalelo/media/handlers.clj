(ns brasilparalelo.media.handlers
  (:require
   [brasilparalelo.db.core :as db]
   [brasilparalelo.config :refer [env]]))

(defn create-all
  [{body :body}]
  {:status 201 :body body})

(defn create-one
  [{body :body}]
  {:status 201 :body body})

(defn find-patriot
  [request])

(defn find-premium
  [request])

(defn find-mecenas
  [request])

(defn find-all
  [request]
  {:status 200 :body (db/get-all-media)})

(defn find-one
  [{{{media_id :id} :path} :parameters}]
  {:status 200 :body {:id media_id}})
