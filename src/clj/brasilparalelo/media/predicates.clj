(ns brasilparalelo.media.predicates
  (:require
    [brasilparalelo.config :refer [env]]
    [brasilparalelo.util :as util]
    [clj-time.core :as time]))

(defonce MEDIA_BASIC '("series"))
(defonce MEDIA_PATRIOT '("debate" "interview" "podcast" "series"))
(defonce MEDIA_PREMIUM '("course" "debate" "interview" "podcast" "series"))
(defonce MEDIA_PATRON '("course" "debate" "interview" "podcast" "patron" "series"))

(defn- subscription-expires-at [subscription]
  (time/plus (util/tomorrow (util/midnight (:created_at subscription))) (time/months 12)))

(defn- subscription-not-expired [subscription]
  (time/before? (time/today-at-midnight) (subscription-expires-at subscription)))

(defn- media-by-subscription
  [{subscription_type :type}]
  (cond
    (= subscription_type "patriot") MEDIA_PATRIOT
    (= subscription_type "premium") MEDIA_PREMIUM
    (= subscription_type "patron") MEDIA_PATRON
    :else MEDIA_BASIC)
  )

(defn- media-access-allowed [subscription media]
  (let [{type :type} media]
    (if (= type "series")
      true
      (and
        (subscription-not-expired subscription)
        (.contains (media-by-subscription subscription) type))
      )))

(defn rbac
  [subscription]

  (fn [media]
    (media-access-allowed subscription media)))
