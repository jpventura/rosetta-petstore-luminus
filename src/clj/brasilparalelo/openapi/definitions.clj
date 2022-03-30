(ns brasilparalelo.openapi.definitions)

(def Media
  {})

(def Subscription
  {
   :id         str
   :user_id    str
   :active     str
   :created_at str
   :type       str
   :updated_at str
   })

(def CreateUser
  {
   :first_name str
   :last_name  str
   :email      str
   :password   str
   })

(def User
  {
   :id            str
   :active        boolean
   :admin         boolean
   :created_at    str
   :email         str
   :subscriptions [str]
   :updated_at    str
   })