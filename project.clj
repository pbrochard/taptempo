(defproject taptempo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot taptempo.core
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [cider/cider-nrepl "0.17.0-SNAPSHOT"]
                 [org.clojars.trptcolin/jline "2.10.3"]
                 [org.clojure/tools.cli "0.3.5"]
                 [gettext "0.1.1"]
                 [trptcolin/versioneer "0.2.0"]]
  :profiles {:uberjar {:aot :all}})
