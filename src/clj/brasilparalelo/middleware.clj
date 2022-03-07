(ns brasilparalelo.middleware
  (:require
    [brasilparalelo.env :refer [defaults]]
    [brasilparalelo.auth.handlers :as auth]
    [ring.middleware.flash :refer [wrap-flash]]
    [ring.adapter.undertow.middleware.session :refer [wrap-session]]
    [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
    [buddy.auth.middleware :refer [wrap-authentication]]))

(defn wrap-auth [handler]
  (-> handler
      (wrap-authentication auth/backend)))

(defn wrap-base [handler]
  (-> ((:middleware defaults) handler)
      wrap-auth
      wrap-flash
      (wrap-session {:cookie-attrs {:http-only true}})
      (wrap-defaults
        (-> site-defaults
            (assoc-in [:security :anti-forgery] false)
            (dissoc :session)))))
