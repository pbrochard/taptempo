(ns taptempo.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as str]
            [gettext.core :refer [_]]
            [trptcolin.versioneer.core :as version]
            [taptempo.utils :refer [parse-int]]
            [taptempo.main :as main])
  (:gen-class))

(def cli-options
  [["-p", "--precision PREC" (_ "change the number of decimal for the tempo. The default is 0 decimal places, the max is 5 decimals")
    :default 0
    :parse-fn parse-int
    :validate [#(<= 0 % 5) (_ "Must be a number between 0 and 5")]]
   ["-r", "--reset-time T" (_ "change the time in seconds to reset the calculation. The default is 5 seconds")
    :default 5
    :parse-fn parse-int
    :validate [pos? (_ "Must be a positive number")]]
   ["-s", "--sample-size N" (_ "change the number of samples needed to calculate the tempo. The default is 5 samples")
    :default 5
    :parse-fn parse-int
    :validate [#(<= 2 %) (_ "Must be a number greater than 1")]]
   ["-v", "--version" (_ "print the version number")]
   ["-h", "--help"]])

(defn usage [options-summary]
  (->> ["Usage: TapTempo [options]"
        ""
        "Options:"
        options-summary]
       (str/join \newline)))

(defn version []
  (version/get-version "taptempo" "taptempo"))

(defn error-msg [errors]
  (str (_ "The following errors occurred while parsing your command") ":\n\n"
       (str/join \newline errors)))

(defn validate-args [args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options)    {:exit-message (usage summary) :ok? true}
      (:version options) {:exit-message (version) :ok? true}
      errors             {:exit-message (error-msg errors)}
      :else              {:options options})))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn -main [& args]
  (let [{:keys [options exit-message ok?]} (validate-args args)]
    (if exit-message
      (exit (if ok? 0 1) exit-message)
      (main/do-loop options))))
