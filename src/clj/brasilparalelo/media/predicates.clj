(ns brasilparalelo.media.predicates
  (:require
    [brasilparalelo.config :refer [env]]
    [brasilparalelo.util :as util]
    [clj-time.core :as time]))

(defonce MEDIA_BASIC '("series"))
(defonce MEDIA_PATRIOT '("debate" "interview" "podcast" "series"))
(defonce MEDIA_PREMIUM '("course" "debate" "interview" "podcast" "series"))
(defonce MEDIA_PATRON '("course" "debate" "interview" "podcast" "patron" "series"))

(defn- media-by-subscription
  [{subscription_type :type}]

  (cond
    (= subscription_type "patriot") MEDIA_PATRIOT
    (= subscription_type "premium") MEDIA_PREMIUM
    (= subscription_type "patron") MEDIA_PATRON
    :else MEDIA_BASIC))

(defn- authorized-due-subscription-type
  [medium subscription]

  (let [{media_type :type} medium allowed-media (media-by-subscription subscription)]
    (.contains allowed-media media_type)))

(defn- subscription-expires-at
  [{created_at :created_at}]
  (time/plus (util/tomorrow (util/midnight created_at)) (time/months 12)))

(defn- subscription-valid [subscription]
  (time/before? (.toLocalDateTime (time/now)) (subscription-expires-at subscription)))

(defn- released-during-subscription-period
  [medium subscription]

  (let [{released_at :released_at} medium]
    (time/before?
      (subscription-expires-at subscription)
      (util/to-local-datetime released_at))))

(defn- access-authorized [medium subscription]
  (let [{medium_type :type} medium]
    (if (= medium_type "series")
      true
      (and (authorized-due-subscription-type medium subscription)
           (subscription-valid subscription)
           ;; FIXME: It's not clear if media is restricted to new and old users
           ;; (released-during-subscription-period medium subscription)
           ))))

(defn rbac
  [subscription]
  (fn [medium]
    (access-authorized medium subscription)))
