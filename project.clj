(defproject everything-everywhere-all-at-once "1000-SNAPSHOT"
  :description "everything-everywhere-all-at-once: everything-everywhere-all-at-once"
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.cli "1.0.214"]
                 [org.clojure/tools.logging "1.2.4"]
                 ]

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources" "src/test/resources"]
  :target-path "target/%s"
  :jar-name "everything-everywhere-all-at-once-lein.jar"
  :uberjar-name "everything-everywhere-all-at-once-lein-uberjar.jar"
  :main pl.tomaszgigiel.everythingeverywhereallatonce.core
  :aot [pl.tomaszgigiel.everythingeverywhereallatonce.core]

  :profiles {:test {:resource-paths ["src/test/resources"]}
             :main-core {:main pl.tomaszgigiel.everythingeverywhereallatonce.core}}

  :aliases {"run-main-core" ["with-profile" "main-core" "run"]}
)
