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


;; problem 55
(defn v55
  [x]
  (every? identity
    [
     (= (x [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})
     (= (x [:b :a :b :a :b]) {:a 2, :b 3})
     (= (x '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})
     ]))

(def s55-a
  (fn [x]
    (loop [s x m {}]
      (if (empty? s)
        m
        (if (contains? m (first s))
          (recur (rest s) (update-in m [(first s)] inc))
          (recur (rest s) (conj m {(first s) 1}))
          ))))
  )

(def s55-b
  (fn [s] (reduce (fn [x y] (update-in x [y] #(inc (or % 0)))) {} s))
  )

(defn p55
  []
  (println (str "problem 55: " (v55 s55-b)))
  )


;; problem 56
(defn v56
  [x]
  (every? identity
    [
     (= (x [1 2 1 3 1 2 4]) [1 2 3 4])
     (= (x [:a :a :b :b :c :c]) [:a :b :c])
     (= (x '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
     (= (x (range 50)) (range 50))
     ]))

(def s56
  ;(fn [s] (reduce (fn [x y] (if (some #(= % y) x) x (conj x y))) [] s))
  (fn [s] (reduce (fn [x y] (if ((set x) y) x (conj x y))) [] s))
  )

(defn p56
  []
  (println (str "problem 56: " (v56 s56)))
  )


;; problem 58
(defn v58
  [x]
  (every? identity
    [
     (= [3 2 1] ((x rest reverse) [1 2 3 4]))
     (= 5 ((x (partial + 3) second) [1 2 3 4]))
     (= true ((x zero? #(mod % 8) +) 3 5 7 9))
     (= "HELLO" ((x #(.toUpperCase %) #(apply str %) take) 5 "hello world"))
     ]))

(def s58
  (fn [& funcs]
    (fn [& args]
      (loop [rfns (rest (reverse funcs))
             outp (apply (last funcs) args)]
        (if (empty? rfns)
          outp
          (recur (rest rfns) ((first rfns) outp))
          ))))
  )

(defn p58
  []
  (println (str "problem 58: " (v58 s58)))
  )


;; problem 59
(defn v59
  [x]
  (every? identity
    [
     (= [21 6 1] ((x + max min) 2 3 5 1 6 4))
     (= ["HELLO" 5] ((x #(.toUpperCase %) count) "hello"))
     (= [2 6 4] ((x :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))
     ]))

(def s59
  (fn [& funcs]
    (fn [& args]
      (loop [fns funcs
             outp []]
        (if (empty? fns)
          outp
          (recur (rest fns) (conj outp (apply (first fns) args))
          )))))
  )

(defn p59
  []
  (println (str "problem 59: " (v59 s59)))
  )


;; problem 60
(defn v60
  [x]
  (every? identity
    [
     (= (take 5 (x + (range))) [0 1 3 6 10])
     (= (x conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
     (= (last (x * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)
     ]))

(def s60
  (fn my-reduce 
    ([func sqnc] (my-reduce func (first sqnc) (rest sqnc)))
    ([func value sqnc]
     (lazy-seq
       (if (empty? sqnc)
         (list value)
         (cons value
               (my-reduce func (func value (first sqnc)) (rest sqnc))
               )))))
  )

(defn p60
  []
  (println (str "problem 60: " (v60 s60)))
  )
