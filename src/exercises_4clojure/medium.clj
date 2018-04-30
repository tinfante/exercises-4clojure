(ns exercises-4clojure.medium)


;; problem 50
(defn v50
  [x]
  (every? identity
    [
     (= (set (x [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
     (= (set (x [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})
     (= (set (x [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})
     ]))

(def s50
  (fn
    [x]
    (loop [t [clojure.lang.PersistentVector java.lang.Long
              clojure.lang.Keyword java.lang.String]
           s x
           n #{}]
      (if (empty? t)
        n
        (let [f (vec (filter #(instance? (first t) %) s))]
          (if (empty? f)
            (recur (rest t) s n)
            (recur (rest t) s (conj n (vec (filter #(instance? (first t) %) s))))
            )))))
  )

(defn p50
  []
  (println (str "problem 50: " (v50 s50)))
  )
