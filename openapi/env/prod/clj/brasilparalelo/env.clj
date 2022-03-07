(ns brasilparalelo.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[brasilparalelo started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[brasilparalelo has shut down successfully]=-"))
   :middleware identity})
