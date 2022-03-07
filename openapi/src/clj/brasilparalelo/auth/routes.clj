(ns brasilparalelo.auth.routes
  (:require [brasilparalelo.auth.handlers :as auth]))

(defn routes []
  [""
   {:swagger {:tags ["auth"]}}

   ["/authorize"

    {
     :post {:summary    "plus with spec body parameters"
            :parameters {:body {:email str :password str}}
            :responses  {200 {:body {:token str}}
                         401 {:body {:error str}}}
            :handler    auth/sign-in-with-password}
     }
    ]
   ])
