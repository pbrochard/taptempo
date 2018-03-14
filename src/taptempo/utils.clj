(ns taptempo.utils
  (:import jline.console.ConsoleReader))

(defn now []
  (System/currentTimeMillis))

(defn read-char []
  (->> (ConsoleReader.) (.readCharacter) char))

(defn parse-int [x]
  (Integer/parseInt x))
