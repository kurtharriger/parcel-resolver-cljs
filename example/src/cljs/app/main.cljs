
(ns app.main
  (:require [app.lib :as lib]
            ["project/ts/util.ts" :as ts]
            [reagent.core :as r]
            [reagent.dom :as rd]))



(defonce click-count (r/atom 0))
(defn counting-component []
  [:div
   "The atom " [:code "click-count"] " has value: "
   @click-count ". "
   [:input {:type "button" :value "Click me!"
            :on-click #(swap! click-count inc)}]])

(defn app []
  [:div
   [:h2 "ClojureScript Component"]
   [:p "I am a clojuresript component!"]
   [:h3 "REPL Support"]
   [:p "You can start a clojurescript repl to interact with me live"]
   [:p "Run the following in your terminal to start a repl"]
   [:pre "$ yarn shadow-cljs cljs-repl app
shadow-cljs - config: /Users/kurt.harriger/projects/seedlings/clojure/parcel-cljs/shadow-cljs.edn
shadow-cljs - connected to server
cljs.user=> (.log js/console \"hello from cljs repl\")
cljs.user=> (in-ns 'app.main)
app.main=> (greeting \"World\")
\"Hello, World.  Clojurescript generated this message\""]
   [:h3 "From VS Code"]
   [:p "Now try to connect to the repl from your editor"]
   [:p "For VS Code try " [:a {:href "https://calva.io/"} "Calva"]]
   [:ol
    [:li "Calva: Start or Connect to a ClojureScript REPL"]
    [:li "Connect to a running REPL in your project"]
    [:li "shadow-cljs"]
    [:li "localhost:<port> should already be populated from .shadow-cljs/nrepl.por"]
    [:li ":app"]]
   [:p "You should now have a repl runinng in VS Code"]

   [:h3 "Counter"]
   [:p "try changing the counter state from the repl"]
   [:pre
    "(swap! click-count inc)"]
   [:p "then alter this message in the source to see that counter state is not lost on reload"]
   [counting-component]]

  )


(defn ^:dev/after-load reload! []
  (println "[main] reloaded:"))


(defn ^:export mount! []
  (rd/render [app]
             (.getElementById js/document "app")))


(def ^:export App (r/reactify-component app))


;(mount!)
(ts/sayHi)
