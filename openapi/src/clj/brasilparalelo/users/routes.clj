(ns brasilparalelo.users.routes
  (:require
    [brasilparalelo.users.handlers :as users]
    [brasilparalelo.subscriptions.handlers :as subscriptions]
    [brasilparalelo.openapi.definitions :refer [CreateUser Subscription User]]
    [buddy.auth :refer [authenticated?]]
    [buddy.auth.backends.token :refer [jws-backend jwe-backend]]
    [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
    [ring.util.http-response :refer :all]))

(defn routes []
  ""
  [
   ["/" {
         :post {
                :summary    "Create user"
                :parameters {:body CreateUser}
                :responses  {201 {
                                  :status 201
                                  :body   {:id str :token str}}
                             412 {
                                  :status 412
                                  :error  str}}
                :handler    users/create-one
                :tags       ["users"]
                }

         }
    ]
   ["/:id" {
            :swagger {:security {:apiAuth []}}

            :get     {:summary    "Get user details"
                      :parameters {:header {:authorization str}
                                   :path   {:id str}}
                      :responses  {200 {:status 200 :body User}
                                   401 {:status 403 :body {:error str}}
                                   403 {:status 403 :body {:error str}}}
                      :handler    users/find-one
                      :tags       ["users"]
                      }
            }
    ]
   [
    "/:user_id/subscriptions"
    {:get  {
            :summary    "Get user subscriptions history"
            :parameters {
                         :header {:authorization str}
                         :path   {:user_id str}}
            :responses  {200 {:body [Subscription]}}
            :handler    subscriptions/find
            :tags       ["subscriptions", "users"]
            }
     :post {
            :summary    "Create user subscription"
            :parameters {
                         :header {:authorization str}
                         :path   {:user_id str}
                         :body   {:type str}}
            :responses  {201 {:body [Subscription]}}
            :handler    subscriptions/create-one
            :tags       ["subscriptions", "users"]
            }

     }
    ]
   [
    "/:user_id/subscriptions/:subscription_id"
    {:get  {
            :summary    "Get user subscriptions history"
            :parameters {
                         :header {:authorization str}
                         :path   {:user_id str :subscription_id str}}
            :responses  {200 {:body Subscription}}
            :handler    subscriptions/find-one
            :tags       ["subscriptions", "users"]
            }
     :patch  {
            :summary    "Patch user subscription"
            :parameters {
                         :header {:authorization str}
                         :body {:active boolean :type str}
                         :path   {:user_id str :subscription_id str}}
            :responses  {200 {:body Subscription}}
            :handler    subscriptions/patch-one
            :tags       ["subscriptions", "users"]
            }
     }
    ]
   ])
