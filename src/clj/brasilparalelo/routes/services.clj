(ns brasilparalelo.routes.services
  (:require
    [brasilparalelo.auth.routes :as auth]
    [brasilparalelo.media.routes :as media]
    [brasilparalelo.middleware.formats :as formats]
    [reitit.coercion.spec :as spec-coercion]
    [reitit.ring.coercion :as coercion]
    [reitit.ring.middleware.multipart :as multipart]
    [reitit.ring.middleware.muuntaja :as muuntaja]
    [reitit.ring.middleware.parameters :as parameters]
    [reitit.swagger :as swagger]
    [reitit.swagger-ui :as swagger-ui]
    [ring.util.http-response :refer :all]))

(defn service-routes []
  ["/api"
   {:coercion   spec-coercion/coercion
    :muuntaja   formats/instance
    :swagger    {:id ::api}
    :middleware [
                 parameters/parameters-middleware           ;; query-params & form-params
                 muuntaja/format-negotiate-middleware       ;; content-negotiation
                 muuntaja/format-response-middleware        ;; encoding response body
                 coercion/coerce-exceptions-middleware      ;; exception handling
                 muuntaja/format-request-middleware         ;; decoding request body
                 coercion/coerce-response-middleware        ;; coercing response bodys
                 coercion/coerce-request-middleware         ;; coercing request parameters
                 multipart/multipart-middleware             ;; multipart
                 ]
    }

   ;; swagger documentation
   ["" {:no-doc  true
        :swagger {:info {:title       "Brasil Paralelo OpenAPI"
                         :description "https://cljdoc.org/d/metosin/reitit"}}}

    ["/swagger.json"
     {:get (swagger/create-swagger-handler)}]

    ["/api-docs/*"
     {:get (swagger-ui/create-swagger-ui-handler
             {:url    "/api/swagger.json"
              :config {:validator-url nil}})}]]

   ["/status" {:get (constantly (ok {:message "pong"}))}]

   ["/media"
    {:swagger {:security {:apiAuth []} :tags ["media"]}}
    (media/routes)]

   ["" (auth/routes)]])
