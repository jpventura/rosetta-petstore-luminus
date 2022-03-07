(ns brasilparalelo.util
  (:require
    [brasilparalelo.db.core :as db]
    [brasilparalelo.config :refer [env]]
    [clj-time.core :as time]
    [clj-time.format :as format]))

(defn to-local-datetime
  [dt]
  (let [year (.getYear dt)
        month (.getMonthValue dt)
        day (.getDayOfMonth dt)
        hour (.getHour dt)
        minutes (.getMinute dt)
        seconds (.getSecond dt)
        ]
    (time/date-time year month day hour minutes seconds)))

(defn midnight [dt]
  (let [year (.getYear dt)
        month (.getMonthValue dt)
        day (.getDayOfMonth dt)]
    (time/date-time year month day)))

(defn tomorrow [dt]
  (.toLocalDateTime (time/plus dt (time/days 1))))
