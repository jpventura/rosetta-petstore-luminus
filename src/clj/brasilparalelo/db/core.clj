(ns brasilparalelo.db.core
  (:require
    [next.jdbc.date-time]
    [next.jdbc.result-set]
    [conman.core :as conman]
    [mount.core :refer [defstate]]
    [clojure.java.io :as io]
    [clojure.string :as string]
    [brasilparalelo.config :refer [env]]))

(defonce RESOURCE (:luminus-resources env "/home/ventura/internal_git/brasil-paralelo/resources"))

(defstate ^:dynamic *db*
          :start (conman/connect! {:jdbc-url (env :database-url)})
          :stop (conman/disconnect! *db*))

(conman/bind-connection *db* "sql/queries.sql")

(extend-protocol next.jdbc.result-set/ReadableColumn
  java.sql.Timestamp
  (read-column-by-label [^java.sql.Timestamp v _]
    (.toLocalDateTime v))
  (read-column-by-index [^java.sql.Timestamp v _2 _3]
    (.toLocalDateTime v))
  java.sql.Date
  (read-column-by-label [^java.sql.Date v _]
    (.toLocalDate v))
  (read-column-by-index [^java.sql.Date v _2 _3]
    (.toLocalDate v))
  java.sql.Time
  (read-column-by-label [^java.sql.Time v _]
    (.toLocalTime v))
  (read-column-by-index [^java.sql.Time v _2 _3]
    (.toLocalTime v)))

(defn find-one
  [id]
  (get-user id))

;
(defn sign-in-with-password
  [{username :username password :password}]
  (get-user {:username username :password password}))

(defn tsv
  [{filename :filename dname :dirname}]

  (let [dirname (or dname (string/join "/" [RESOURCE "db"]))
        pathname (string/join "/" [dirname filename])]

  (with-open [rd (io/reader (io/file pathname))]
    (->> (line-seq rd)
         (map #(.split ^String % "\t"))
         (mapv seq)))))
