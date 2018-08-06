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
  (fn [x] (sort-by s/lower-case (re-seq #"\w+" x)))
  )

(defn p70
  []
  (println (str "problem 70: " (v70 s70)))
  )


;; problem 74
(defn v74
  [x]
  (every? identity
    [
     (= (x "4,5,6,7,8,9") "4,9")
     (= (x "15,16,25,36,37") "16,25,36")
     ]))

(def s74
  (fn [s] (s/join "," (filter #(zero? (mod (Math/sqrt %) 1))
                              (map #(Integer. %) (re-seq #"\d+" s)))))
  )

(defn p74
  []
  (println (str "problem 74: " (v74 s74)))
  )


;; problem 75
(defn v75
  [x]
  (every? identity
    [
     (= (x 1) 1)
     (= (x 10) (count '(1 3 7 9)) 4)
     (= (x 40) 16)
     (= (x 99) 60)
     ]))

(def s75
  (fn [n]
    ; greatest common divisor code is from exercise easy 66.
    (letfn [(gcd [n1 n2]
              (if (zero? n2)
                n1
                (recur n2 (rem n1 n2))))]
      (count (filter #(= 1 %) (map (partial gcd n) (range 1 (inc n)))))))
  )

(defn p75
  []
  (println (str "problem 75: " (v75 s75))))


;; problem 76
(defn v76
  [x]
  (= x (letfn
         [(foo [x y] #(bar (conj x y) y))
          (bar [x y] (if (> (last x) 10)
                       x
                       #(foo x (+ 2 y))))]
         (trampoline foo [] 1))))

(def s76
  [1 3 5 7 9 11]
  )

(defn p76
  []
  (println (str "problem 76: " (v76 s76))))


;; problem 77
(defn v77
  [x]
  (every? identity
    [
     (= (x ["meat" "mat" "team" "mate" "eat"])
        #{#{"meat" "team" "mate"}})
     (= (x ["veer" "lake" "item" "kale" "mite" "ever"])
        #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})
     ]))

(def s77
  (fn af
    ([v]
     (af v
         (keys (filter #(> (val %) 1) (frequencies (map frequencies v))))
         #{}))
    ([v f r]
     (if (empty? f)
       r
       (af v
           (rest f)
           (conj r (set (filter #(= (first f) (frequencies %)) v)))))))
  )

(defn p77
  []
  (println (str "problem 77: " (v77 s77))))


;; problem 78
(defn v78
  [z]
  (every? identity
    [
     (= (letfn [(triple [x] #(sub-two (* 3 x)))
                (sub-two [x] #(stop?(- x 2)))
                (stop? [x] (if (> x 50) x #(triple x)))]
          (z triple 2))
        82)
     (= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
                (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
          (map (partial z my-even?) (range 6)))
        [true false true false true false])
     ]))

(def s78
  (fn [f & a]
    (let [fa (apply f a)]
      (if (fn? fa)
        (recur fa nil)
        fa
        )))
  )

(defn p78
  []
  (println (str "problem 78: " (v78 s78))))


;; problem 80
(defn v80
  [x]
  (every? identity
    [
     (= (x 6) true)
     (= (x 7) false)
     (= (x 496) true)
     (= (x 500) false)
     (= (x 8128) true)
     ]))

(def s80
  (fn [n]
    (if (= n (apply + (filter identity
                              (for [i (range 2 (inc n))]
                                (if (zero? (mod n i))
                                  (/ n i))))))
      true
      false))
  )

(defn p80
  []
  (println (str "problem 80: " (v80 s80))))


;; problem 102
(defn v102
  [x]
  (every? identity
    [
     (= (x "something") "something")
     (= (x "multi-word-key") "multiWordKey")
     (= (x "leaveMeAlone") "leaveMeAlone")
     ]))

(def s102
  (fn [x]
    (let [p (s/split x #"-")]
      (s/join "" (into (vector (first p)) (map s/capitalize (rest p))))
      ))
  )

(defn p102
  []
  (println (str "problem 102: " (v102 s102))))


; problem 105
(defn v105
  [x]
  (every? identity
    [
     (= {} (x []))
     (= {:a [1]} (x [:a 1]))
     (= {:a [1], :b [2]} (x [:a 1, :b 2]))
     (= {:a [1 2 3], :b [], :c [4]} (x [:a 1 2 3 :b :c 4]))
     ]))

(def s105
  (fn f
    ([v] (if (not (empty? v))
           (f (rest v) (first v) {} [])
           {}))
    ([v k m a]
     (if (empty? v)
       (assoc m k a)
       (if (keyword? (first v))
         (recur (rest v) (first v) (assoc m k a) [])
         (recur (rest v) k m (conj a (first v)))))))
  )

(defn p105
  []
  (println (str "problem 105: " (v105 s105))))


; problem 115
(defn v115
  [x]
  (every? identity
    [
     (= true (x 11))
     (= true (x 121))
     (= false (x 123))
     (= true (x 0))
     (= false (x 88099))
     (= true (x 89098))
     (= true (x 89089))
     (= (take 20 (filter x (range)))
        [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101]) 
     ]))

(def s115
  (fn [n]
    (let [s (str n)
          h (int (/ (count s) 2))]
      (= (reduce + (map read-string (map str (take h s))))
         (reduce + (map read-string (map str (take-last h s)))))))
  )

(defn p115
  []
  (println (str "problem 115: " (v115 s115))))

