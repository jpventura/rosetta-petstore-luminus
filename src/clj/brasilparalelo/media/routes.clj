(ns brasilparalelo.media.routes
  (:require
    [brasilparalelo.media.handlers :as handlers]
    [buddy.auth :refer [authenticated?]]
    [brasilparalelo.auth.handlers :as auth]
    [buddy.auth.backends.token :refer [jws-backend jwe-backend]]
    [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]))

(defn routes []
  [""
   ["/" {:get  {:swagger    {:security {:apiAuth []}}
                :summary    "List all media collection"
                :parameters {:header {:authorization str}}
                :responses  {200 {:body [{:id          str
                                          :name        str
                                          :released_at str
                                          :type        str}]}
                             401 {:body {:status_code int, :error str}}}
                :handler    handlers/find-all}

         :post {:swagger   {:security {:apiAuth []}}
                :summary   "Create media collection"
                :responses {200 {:body str}
                            401 {:body {:status_code int, :error str}}
                            409 {:body {:status_code int, :error str}}}
                :handler   handlers/create-all}}]
   ["/:id" {:get {:swagger    {:security {:apiAuth []}}
                  :summary    "Get media by ID"
                  :parameters {:path {:id str}}
                  :responses  {200 {:body {:id          str
                                           :name        str
                                           :released_at str
                                           :type        str}}
                               401 {:body {:status_code int, :error str}}
                               409 {:body {:status_code int, :error str}}}
                  :handler    handlers/find-one}}]])
