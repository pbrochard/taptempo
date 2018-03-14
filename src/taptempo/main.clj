(ns taptempo.main
  (:require [gettext.core :refer [_]]
            [taptempo.utils :refer [now read-char]]))

(defn calc-tempo
  "Compute tempo in bpm from samples"
  [samples]
  (let [cnt (count samples)]
    [(when (> cnt 1)
       (* 60 1000 (/ (dec cnt)
                     (reduce +
                             (map (fn [[end start]] (- end start))
                                  (partition 2 1 samples))))))
     cnt]))

(defn print-tempo [[tempo cnt] prec]
  (println (format (str "Tempo: %." prec "f bpm (%d " (_ "samples") ")") (float (or tempo 0)) cnt)))

(defn compute-next
  "Append now to samples. Keep at most sample-size.
   Reset samples if more than reset-time has elapsed since last call"
  [now samples {:keys [sample-size reset-time]}]
  (if (< (- now (first samples)) (* 1000 reset-time))
    (conj (take (dec sample-size) samples) now)
    [now]))

(defn do-loop [{:keys [precision sample-size reset-time] :as options}]
  (println (_ "Press the enter key in cadence (q to quit)"))
  (loop [samples [(now)]]
    (when-not (= (read-char) \q)
      (print-tempo (calc-tempo samples) precision)
      (recur (compute-next (now) samples options))))
  (println (_ "Bye!")))
