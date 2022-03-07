(ns brasilparalelo.media.routes
  (:require
   [brasilparalelo.media.handlers :as handlers]
   [buddy.auth :refer [authenticated?]]
   [brasilparalelo.auth.handlers :as auth]
   [buddy.auth.backends.token :refer [jws-backend jwe-backend]]
   [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]))

(defstruct Media {:id str})

(def auth-backend (jws-backend {:secret "2b2254f0-c972-48e9-8299-4c152de96aa4" :options {:alg :hs512}}))


(defn wrap-nocache [handler]
  (fn [request]
    (println ".")
    (println ".")
    (println ".")
    (println ".")
    (println (keys(:headers request)))
    (println ".")
    (println ".")    (println ".")    (println ".")    (println ".")
    (println ".")
    (if (authenticated? request)
      (println "authenticado")
      (println "porra"))

    (let [response (handler request)]
      (assoc-in response [:headers  "X-Team"] "no-cache"))))


(defn routes []
  [""
   ["/" {:get  {:summary    "List all media collection"
                :parameters {:header {:authorization str}}
                :responses  {200 {:body [{:id          str
                                          :name        str
                                          :released_at str
                                          :type        str}]}
                             401 {:body {:status_code int, :error str}}}
                :handler    handlers/find-all}

         :post {:summary   "Create media collection"
                :responses {200 {:body str}
                            401 {:body {:status_code int, :error str}}
                            409 {:body {:status_code int, :error str}}}
                :handler   handlers/create-all}}]
   ["/:id" {:get {:summary    "Get media by ID"
                  :parameters {:path {:id str}}
                  :responses  {200 {:body {:id          str
                                           :name        str
                                           :released_at str
                                           :type        str}}
                               401 {:body {:status_code int, :error str}}
                               409 {:body {:status_code int, :error str}}}
                  :handler    handlers/find-one}}]])
