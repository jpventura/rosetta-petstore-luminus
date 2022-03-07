(ns brasilparalelo.media.test_handlers\
  (:require
    [brasilparalelo.config :refer [env]]
    [brasilparalelo.db.core :as db]
    [brasilparalelo.handler :refer :all]
    [brasilparalelo.middleware.formats :as formats]
    [clj-time.core :as time]
    [clojure.java.io :as io]
    [clojure.string :as string]
    [clojure.test :refer :all]
    [mount.core :as mount]
    [muuntaja.core :as m]
    [org.clojure/data.csv :as csv]
    [ring.mock.request :refer :all]))


(let [pathname (string/join "" [(:luminus-resources env), "media.tsv"])]
  (println pathname)
  (println ".")
  (println ".")
  (println ".")
  (println ".")
  (println ".")
  (println ".")
  (println ".")
  (println ".")
  )
;
;(with-open [rd (io/reader (io/file "/path/to/file"))]
;  (->> (line-seq rd)
;       (map #(.split ^String % "\t"))
;       (mapv vec)))
;
;
;
;(with-open [reader (io/reader "res")])
