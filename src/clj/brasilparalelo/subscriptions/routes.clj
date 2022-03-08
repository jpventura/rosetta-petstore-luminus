(ns brasilparalelo.subscriptions.routes
  (:require
    [brasilparalelo.subscriptions.handlers :as subscriptions]
    [buddy.auth :refer [authenticated?]]
    [brasilparalelo.auth.handlers :as auth]
    [buddy.auth.backends.token :refer [jws-backend jwe-backend]]
    [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]))

(defn routes []
  [""

   ["/" {
         :swagger {:security {:apiAuth []}}
         :get     {
                   :summary    "List all subscription collection"
                   :parameters {:header {:authorization str}}
                   :responses
                   {
                    200 {:body [{:id str}]}
                    401 {:body {:error str}}}
                   :handler    subscriptions/find}
         }
    ]

   ["/:id" {
            :patch {:summary    "Patch user subscription collection"
                    :parameters {:body {:id str :type str}}

                    :responses  {200 {:body {:user_id str}}
                                 401 {:body {:error str}}
                                 404 {:body {:error str}}
                                 412 {:body {:error str}}}
                    :handler    subscriptions/patch-one}
            }
    ]
   ])