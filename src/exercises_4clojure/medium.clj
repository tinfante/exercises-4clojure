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


;; problem 54
(defn v54
  [x]
  (every? identity
    [
     (= (x 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8))) 
     (= (x 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
     (= (x 3 (range 8)) '((0 1 2) (3 4 5)))
     ]))

(def s54-a
  (fn my-partition
    ([x s]
     (my-partition x (rest s) [[(first s)]])
     )
    ([x s z]
     (if (empty? s)
       (if (not= (count (last z)) x)
         (map #(apply list %) (butlast z))
         (map #(apply list %) z)
         )
       (if (= (count (last z)) x)
         (my-partition x (rest s) (conj z [(first s)]))
         (my-partition x (rest s) (update-in z [(dec (count z))] conj (first s)))
         ))))
  )

(def s54-b
  (fn my-partition
    ([x s]
     (my-partition x (drop x s) [(take x s)])
     )
    ([x s z]
     (if (empty? s)
       (if (not= (count (last z)) x)
         (apply list (butlast z))
         (apply list z)
         )
       (my-partition x (drop x s) (conj z (take x s)))
       )))
  )

(defn p54
  []
  (println (str "problem 54: " (v54 s54-b)))
  )
