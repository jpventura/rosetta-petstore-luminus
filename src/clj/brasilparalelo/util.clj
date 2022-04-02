(ns brasilparalelo.util
  (:require
    [brasilparalelo.db.core :as db]
    [brasilparalelo.config :refer [env]]
    [clj-time.core :as time]
    [clj-time.format :as format]))

(defn midnight [dt]
  (let [year (.getYear dt)
        month (.getMonthValue dt)
        day (.getDayOfMonth dt)]
    (time/date-time year month day)))

(defn tomorrow [dt]
  (.toLocalDateTime (time/plus dt (time/days 1))))
