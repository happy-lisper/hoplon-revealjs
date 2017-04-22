(set-env!
 :dependencies '[[org.clojure/clojure       "1.7.0"]
                 [hoplon/boot-hoplon        "0.2.1"];;TODO 0.2.2+ versions fail to compile missing due to `(ns)` in deps.cljc https://github.com/hoplon/boot-hoplon/commit/1a114460b9d37146f0fe9621d8591f93695f1c15
                 [org.clojure/clojurescript "1.9.518"]
                 [adzerk/bootlaces "0.1.13"]]
 :resource-paths #{"src"}
 :asset-paths  #{"resources/assets"})

;;TODO deps.cljs with fake "provides": {:foreign-libs [{:file "reveal.inc.js" :provides ["foo.bar2"]} {:file "head.inc.js" :provides ["foo.bar3"]}]}

(require
 '[hoplon.boot-hoplon :refer [hoplon]]
 '[adzerk.bootlaces :refer :all])

(def +version+ "0.1.6-SNAPSHOT")

(task-options!
 pom {:project     'org.clojars.happy-lisper/hoplon-revealjs
      :version +version+
      :description "a simple Hoplon wrapper over Reveal.js"
      :url "https://github.com/happy-lisper/hoplon-revealjs"
      :scm {:url "https://github.com/happy-lisper/hoplon-revealjs"}
      :license {"MIT" "https://opensource.org/licenses/MIT"}}
 hoplon {:manifest true})

(deftask build []
  (comp
   (hoplon)
   (pom)
   (jar)
   (install)))
