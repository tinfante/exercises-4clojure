(ns exercises-4clojure.medium
  (:require [clojure.string :as s])
  )


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


;; problem 65
(defn v65
  [x]
  (every? identity
    [
     (= :map (x {:a 1, :b 2}))
     (= :list (x (range (rand-int 20))))
     (= :vector (x [1 2 3 4 5 6]))
     (= :set (x #{10 (rand-int 5)}))
     (= [:map :set :vector :list] (map x [{} #{} [] ()]))
     ]))

(def s65
  (fn [x]
    (let [e (empty x)]
      (cond
        (= e {}) :map
        (= e #{}) :set
        (reversible? e) :vector
        :else :list
        )))
  )

(defn p65
  []
  (println (str "problem 65: " (v65 s65)))
  )


;; problem 67
(defn v67
  [x]
  (every? identity
    [
     (= (x 2) [2 3])
     (= (x 5) [2 3 5 7 11])
     (= (last (x 100)) 541)
     ]))

(def s67-a
  (fn [n]
    (loop [i 2 l []]
      (if (= (count l) n)
        l
        (if (
             ; below, trial division function.
             ; returns true if x is composite, nil otherwise.
             (fn [x] (some
                       #(== % 0)
                       (map #(mod x %) (range 2 (inc (int (Math/sqrt x)))))
                       ))
             i)
          (recur (inc i) l)
          (recur (inc i) (conj l i))
          ))))
  )

(defn soe
  "Sieve of Eratosthenes.
  Doesn't return a list of primes up to the -nth prime. Instead,
  generates a range up to n and applies the sieve to it. Faster than
  previous function, but generates stack overflow for large n. For an
  interesting discussion about generating primes in clojure see:
      https://yonatankoren.com/post/3-lazy-prime-sequence
  "
  ([n]
  (soe n 2 (vec (range 2 (inc n))))
  )
  ([n p s]
  (if (= p n)
    (filter #(boolean %) s)
    (if (s (- p 2))  ; Check if vector index for p is not false.
      (soe n (inc p)
            (reduce (fn [x y] (assoc x (- y 2) false)) s (range (* p 2) (inc n) p))
            )
      (soe n (inc p) s)
      ))))

(defn p67
  []
  (println (str "problem 67: " (v67 s67-a)))
  )


;; problem 69
(defn v69
  [x]
  (every? identity
    [
     (= (x * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
        {:a 4, :b 6, :c 20})
     (= (x - {1 10, 2 20} {1 3, 2 10, 3 15})
        {1 7, 2 10, 3 15})
     (= (x concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
        {:a [3 4 5], :b [6 7], :c [8 9]})
     ]))

(def s69
  (fn [f & ms]
    (reduce
      (fn [a b]
        (if (empty? b)
          a
          (let [[k v] (first b)
                r (rest b)]
            (recur (assoc a k (if (a k)
                                (f (a k) v)
                                v
                                ))
                   (rest b)))))
      ms))
  )

(defn p69
  []
  (println (str "problem 69: " (v69 s69)))
  )


;; problem 70
(defn v70
  [x]
  (every? identity
          [
           (= (x  "Have a nice day.")
              ["a" "day" "Have" "nice"])
           (= (x  "Clojure is a fun language!")
              ["a" "Clojure" "fun" "is" "language"])
           (= (x  "Fools fall for foolish follies.")
              ["fall" "follies" "foolish" "Fools" "for"])
     ]))

(def s70
  (fn
    [string]
    (re-seq #"\w+" string)
    )
  )

(def compare-words
  ; This was harder than it should have been. (clojure.string/lower-case \char)
  ; returns a one element String, and the int function (to get the ascii value)
  ; doesn't accept Strings, only instanecs of java.lang.Character. In
  ; frustration I looked up how to transform a one character String to a
  ; Character (wihout splitting, re-seq, seq, etc), and finally, searched for
  ; other solutions. All of them use sort-by, which feels like it should be a
  ; restriction for the exercise. Anyway, java.lang.Character's static
  ; method toLowerCase returns a Character, so here is a solution without
  ; sort-by.
  (fn cw
    ([word1 word2]
     (cw (seq word1) (seq word2) [] [])
     )
    ([w1 w2 p1 p2]
     (let [f1 (int (Character/toLowerCase (first w1)))
           f2 (int (Character/toLowerCase (first w2)))
           j (partial s/join "")
           ]
       (cond
         (empty? w1) (j p1)
         (empty? w2) (j p2)
         (< f1 f2) (j (apply conj p1 w1))
         (> f1 f2) (j (apply conj p2 w2))
         (= f1 f2) (recur (rest w1)
                          (rest w2)
                          (conj p1 (first w1))
                          (conj p2 (first w2)))
         )
       )
     )
    )
  )

(defn p70
  []
  (println (str "problem 70: " (v70 s70)))
  )
