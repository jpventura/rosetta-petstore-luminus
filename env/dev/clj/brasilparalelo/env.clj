(ns brasilparalelo.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [brasilparalelo.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[brasilparalelo started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[brasilparalelo has shut down successfully]=-"))
   :middleware wrap-dev})
