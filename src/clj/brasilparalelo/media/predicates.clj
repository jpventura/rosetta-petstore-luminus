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

  ;; (let [{{medium_type :type} medium {subscription_type :type} subscription}]
  ;;   (.contains allowed-media medium_type))
  ;; )

  (println medium)
  (println subscription)
  true)


  ;; (let [allowed-media (media-by-subscription {:type subscription_type})]
  
  ;; )


;; (defn- subscription-expires-at
;;   [{created_at :created_at}]

;;   (println {:created_at created_at
;;             :tomorrow (util/tomorrow (util/midnight created_at))
;;             :expires_in (time/plus (util/tomorrow (util/midnight created_at)) (time/months 12))})

;;   (time/plus (util/tomorrow (util/midnight created_at)) (time/months 12)))

;; (defn- subscription-valid [subscription]
;;   ;; (time/before? (time/today-at-midnight) (subscription-expires-at subscription))

;;   ;; (time/before?
;;   ;;  (time/today-at-midnight)
;;   ;;  (subscription-expires-at subscription))
;;   true
;;   )

;; (defn- released-during-subscription-period
;;   [medium subscription]

;;   ;; (println {:released_at (:released_at medium)
;;   ;;           :expires_in (subscription-expires-at subscription)})

;;   ;; (time/before? (:released_at medium) (subscription-expires-at subscription))

;;   true
;;   )

(defn- access-authorized [medium subscription]

  (let [{medium_type :type} medium {subscription_type :type} subscription]
    (if (= medium_type "series")
      true

      (authorized-due-subscription-type medium subscription)
      )
    )
  )


(defn rbac
  [subscription]

  (fn [medium]
    (access-authorized medium subscription)))
