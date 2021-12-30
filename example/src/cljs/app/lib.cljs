
(ns app.lib
    (:require ["dayjs"   :as dayjs]
              ["shortid" :as shortid]
              ["lodash"  :as lodash]
              ["lodash"  :refer [isString]]))

(defn use-npm-modules []
  (.log js/console "dayjs" (dayjs))
  (.log js/console "shortid" (.generate shortid))
  (.log js/console "lodash" (isString :a-keyword) (lodash/isObject {})))

(defn ^:export greeting [user]
  (use-npm-modules)
  (str "Hello, " user ".  Clojurescript generated this message"))