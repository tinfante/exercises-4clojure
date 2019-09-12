(ns exercises-4clojure.easy
  (:require [clojure.set])
  )


;; problem 19
(defn v19
  [x]
  (every? identity
    [
     (= (x [1 2 3 4 5]) 5)
     (= (x '(5 4 3)) 3)
     (= (x ["b" "c" "d"]) "d")
     ]))

(def s19
  (fn [[f & r]]
    (if r
      (recur r)
      f))
  )

(defn p19
  []
  (println (str "problem 19: " (v19 s19)))
  )


;; problem 20
(defn v20
  [x]
  (every? identity
    [
     (= (x (list 1 2 3 4 5)) 4)
     (= (x ["a" "b" "c"]) "b")
     (= (x [[1 2] [3 4]]) [1 2])
     ]))

(def s20
  (fn [x] (nth x (- (count x) 2)))
  )

(defn p20
  []
  (println (str "problem 20: " (v20 s20)))
  )


;; problem 21
(defn v21
  [x]
  (every? identity
    [
     (= (x '(4 5 6 7) 2) 6)
     (= (x [:a :b :c] 0) :a)
     (= (x [1 2 3 4] 1) 2)
     (= (x '([1 2] [3 4] [5 6]) 2) [5 6])
     ]))

(def s21
  (fn [s i]
    (loop [c i t s]
      (if (zero? c)
        (first t)
        (recur (dec c) (rest t))
        )))
  )

(defn p21
  []
  (println (str "problem 21: " (v21 s21)))
  )


;; problem 22
(defn v22
  [x]
  (every? identity
    [
     (= (x '(1 2 3 3 1)) 5)
     (= (x "Hello World") 11)
     (= (x [[1 2] [3 4] [5 6]]) 3)
     (= (x '(13)) 1)
     (= (x '(:a :b :c)) 3)
     ]))

(def s22
  (fn n [s]
    (loop [t s c 0]
      (if (empty? t)
        c
        (recur (rest t) (inc c))
        )))
  )

(defn p22
  []
  (println (str "problem 22: " (v22 s22)))
  )


;; problem 23
(defn v23
  [x]
  (every? identity
    [
     (= (x [1 2 3 4 5]) [5 4 3 2 1])
     (= (x (sorted-set 5 7 2 7)) '(7 5 2))
     (= (x  [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]])
     ]))

(def s23
  (fn n [s]
    (loop [t s o '()]
      (if (empty? t)
        o
        (recur (rest t) (conj o (first t)))
        )))
  )

(defn p23
  []
  (println (str "problem 23: " (v23 s23)))
  )

;; problem 24
(defn v24
  [x]
  (every? identity
    [
     (= (x [1 2 3]) 6)
     (= (x (list 0 -2 5 5)) 8)
     (= (x #{4 2 1}) 7)
     (= (x '(0 0 -1)) -1)
     (= (x '(1 10 3)) 14)
     ]))

(def s24
  (fn [s] (reduce + s))
  )

(defn p24
  []
  (println (str "problem 24: " (v24 s24)))
  )


;; problem 25
(defn v25
  [x]
  (every? identity
    [
     (= (x #{1 2 3 4 5}) '(1 3 5))
     (= (x [4 2 1 6]) '(1))
     (= (x [2 2 4 6]) '())
     (= (x [1 1 1 3]) '(1 1 1 3))
     ]))

(def s25
  (fn filter-odd [x] (filter #(odd? %) x))
  )

(defn p25
  []
  (println (str "problem 25: " (v25 s25)))
  )


;; problem 26
(defn v26
  [x]
  (every? identity
    [
     (= (x 3) '(1 1 2))
     (= (x 6) '(1 1 2 3 5 8))
     (= (x 8) '(1 1 2 3 5 8 13 21))
     ]))

(def s26
  (fn [x]
    (loop [c 0 l [0 1]]
      (if (< c (- x 1))
        (recur (inc c) (conj l (apply + (take-last 2 l))) )
        (rest l)
        )))
  )

(defn p26
  []
  (println (str "problem 26: " (v26 s26)))
  )


;; problem 27
(defn v27
  [x]
  (every? identity
    [
     (false? (x '(1 2 3 4 5)))
     (true? (x "racecar"))
     (true? (x [:foo :bar :foo]))
     (true? (x '(1 1 3 3 1 1)))
     (false? (x '(:a :b :c)))
     ]))

(def s27
  (fn is-palindrome [x]
    (let [l (int (Math/floor (/ (count x) 2)))]
      (= (take l x) (reverse (take-last l x)))
      ))
  )

(defn p27
  []
  (println (str "problem 27: " (v27 s27)))
  )


;; problem 28
(defn v28
  [x]
  (every? identity
    [
     (= (x '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
     (= (x ["a" ["b"] "c"]) '("a" "b" "c"))
     (= (x '((((:a))))) '(:a))
     ]))

(def s28
  (fn recur-flatten [z]
    (if (some sequential? z)
      (recur-flatten ((fn simple-flatten
                        [x]
                        (loop [y x f []]
                          (if (empty? y)
                            (apply list f)
                            (recur
                              (rest y)
                              (if (sequential? (first y))
                                (concat f (first y))
                                (conj (vec f) (first y))
                                )))))
                      z))
      z))
  )

(defn p28
  []
  (println (str "problem 28: " (v28 s28)))
  )


;; problem 29
(defn v29
  [x]
  (every? identity
    [
     (= (x "HeLlO, WoRlD!") "HLOWRD")
     (empty? (x "nothing"))
     (= (x "$#A(*&987Zf") "AZ")
     ]))

(import 'java.lang.Character)
(require 'clojure.string)
(def s29
  (fn
    [x]
    (clojure.string/join "" (filter #(Character/isUpperCase %) x))
  )
  )

(defn p29
  []
  (println (str "problem 29: " (v29 s29)))
  )


;; problem 30
(defn v30
  [x]
  (every? identity
    [
     (= (apply str (x "Leeeeeerrroyyy")) "Leroy")
     (= (x [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
     (= (x [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))
     ]))

(def s30
  (fn compress
    ([x]
     (compress (rest x) (first x) (conj '() (first x)))
     )
    ([x last-item compressed]
     (if (empty? x)
       compressed
       (if (= (first x) last-item)
         (compress (rest x) last-item compressed)
         (compress (rest x) (first x) (conj (vec compressed) (first x)))
         ))))
  )

(defn p30
  []
  (println (str "problem 30: " (v30 s30)))
  )


;; problem 31
(defn v31
  [x]
  (every? identity
    [
     (= (x [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
     (= (x [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
     (= (x [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))
     ]))

(def s31
  (fn pack
    ([x]
     (pack (rest x) (first x) [(list (first x))])
     )
    ([x last-item packed]
     (if (empty? x)
       packed
       (if (= (first x) last-item)
         (pack (rest x)
               last-item
               (update-in packed [(dec (count packed))] conj last-item)
               )
         (pack (rest x) (first x) (conj packed (list (first x))))
         ))))
  )

(defn p31
  []
  (println (str "problem 31: " (v31 s31)))
  )


;; problem 32
(defn v32
  [x]
  (every? identity
    [
     (= (x [1 2 3]) '(1 1 2 2 3 3))
     (= (x [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
     (= (x [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
     (= (x [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
     ]))

(def s32
  (fn [x] (apply list (reduce #(conj %1 %2 %2) [] x)))
  )

(defn p32
  []
  (println (str "problem 32: " (v32 s32)))
  )


;; problem 33
(defn v33
  [x]
  (every? identity
    [
     (= (x [1 2 3] 2) '(1 1 2 2 3 3))
     (= (x [:a :b] 4) '(:a :a :a :a :b :b :b :b))
     (= (x [4 5 6] 1) '(4 5 6))
     (= (x [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
     (= (x [44 33] 2) [44 44 33 33])
     ]))

(def s33
  (fn [x y]
    (apply list (reduce (fn [a b] (apply conj a (take y (repeat b)))) [] x)))
  )

(defn p33
  []
  (println (str "problem 33: " (v33 s33)))
  )


;; problem 34
(defn v34
  [x]
  (every? identity
    [
     (= (x 1 4) '(1 2 3))
     (= (x -2 2) '(-2 -1 0 1))
     (= (x 5 8) '(5 6 7))
     ]))

(def s34-a
  (fn [x y]
    (if (> x y)
      nil
      (loop [i x r []]
        (if (= i y)
          r
          (recur (inc i) (conj r i))
          ))))
  )

(def s34-b
  (fn [x y] (take (- y x) (iterate inc x)))
  )

(defn p34
  []
  (println (str "problem 34: " (v34 s34-b)))
  )


;; problem 38
(defn v38
  [x]
  (every? identity
    [
     (= (x 1 8 3 4) 8)
     (= (x 30 20) 30)
     (= (x 45 67 11) 67)
     ]))

(def s38-a
  (fn [& x]
    ((fn my-max [s m]
       (if (empty? s)
         m
         (if (> (first s) m)
           (my-max (rest s) (first s))
           (my-max (rest s) m)
           )))
     (rest x)
     (first x)
     ))
  )

; feels like cheating
(def s38-b
  (fn [& x] (last (sort x)))
  )

; with reduce
(def s38-c
  (fn [& x] (reduce (fn [a b] (if (> a b) a b)) 0 x))
  )

(defn p38
  []
  (println (str "problem 38: " (v38 s38-c)))
  )


;; problem 39
(defn v39
  [x]
  (every? identity
    [
     (= (x [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
     (= (x [1 2] [3 4 5 6]) '(1 3 2 4))
     (= (x [1 2 3 4] [5]) [1 5])
     (= (x [30 20] [25 15]) [30 25 20 15])
     ]))

(def s39
  (fn [x y]
    (loop [f x s y i []]
      (if (or (empty? f) (empty? s))
        (apply list i)
        (recur (rest f) (rest s) (conj i (first f) (first s)))
        )))
  )

(defn p39
  []
  (println (str "problem 39: " (v39 s39)))
  )


;; problem 40
(defn v40
  [x]
  (every? identity
    [
     (= (x 0 [1 2 3]) [1 0 2 0 3])
     (= (apply str (x ", " ["one" "two" "three"])) "one, two, three")
     (= (x :z [:a :b :c :d]) [:a :z :b :z :c :z :d])
     ]))

(def s40
  (fn [x y] (vec (butlast (reduce (fn [a b] (conj a b x)) [] y))))
  )

(defn p40
  []
  (println (str "problem 40: " (v40 s40)))
  )


;; problem 41
(defn v41
  [x]
  (every? identity
    [
     (= (x [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
     (= (x [:a :b :c :d :e :f] 2) [:a :c :e])
     (= (x [1 2 3 4 5 6] 4) [1 2 3 5 6])
     ]))

(def s41
  (fn drop-nth
    ([x n]
     (drop-nth x n 1 [])
     )
    ([x n i z]
     (if (empty? x)
       z
       (if (= 0 (mod i n))
         (drop-nth (rest x) n (inc i) z)
         (drop-nth (rest x) n (inc i) (conj z (first x)))
         ))))
  )

(defn p41
  []
  (println (str "problem 41: " (v41 s41)))
  )


;; problem 42
(defn v42
  [x]
  (every? identity
    [
     (= (x 1) 1)
     (= (x 3) 6)
     (= (x 5) 120)
     (= (x 8) 40320)
     ]))

(def s42-a
 (fn [n]
   (loop [i 0 f 1]
     (if (< i n)
       (recur (inc i) (* f (inc i)))
       f
       )))
  )

(def s42-b
  (fn [n]
    (if (= n 1)
      1
      (* n (s42-b (dec n)))
      ))
  )

(defn p42
  []
  (println (str "problem 42: " (v42 s42-b)))
  )


;; problem 43
(defn v43
  [x]
  (every? identity
    [
     (= (x [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))
     (= (x (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))
     (= (x (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))
     ]))

(def s43
  (fn reverse-interleave
    ([sqnc num-bins]
     (reverse-interleave sqnc num-bins 0 (map (fn [_] []) (range num-bins)))
     )
    ([sqnc num-bins index result]
     (if (empty? sqnc)
       (map #(apply list %) result)
       (reverse-interleave (rest sqnc) num-bins (inc index) (update-in (vec result) [(mod index num-bins)] conj (first sqnc)))
       )))
  )

(defn p43
  []
  (println (str "problem 43: " (v43 s43)))
  )


;; problem 44
(defn v44
  [x]
  (every? identity
    [
     (= (x 2 [1 2 3 4 5]) '(3 4 5 1 2))
     (= (x -2 [1 2 3 4 5]) '(4 5 1 2 3))
     (= (x 6 [1 2 3 4 5]) '(2 3 4 5 1))
     (= (x 1 '(:a :b :c)) '(:b :c :a))
     (= (x -4 '(:a :b :c)) '(:c :a :b))
     ]))

(def s44
  (fn [y x]
    (apply list
           (apply conj
                  (vec (drop (mod y (count x)) x))
                  (take (mod y (count x)) x)
                  )))
  )

(defn p44
  []
  (println (str "problem 44: " (v44 s44)))
  )


;; problem 45
(defn v45
  [x]
  (= x (take 5 (iterate #(+ 3 %) 1)))
  )

(def s45
  '(1 4 7 10 13)
  )

(defn p45
  []
  (println (str "problem 45: " (v45 s45)))
  )


;; problem 46
(defn v46
  [x]
  (every? identity
    [
     (= 3 ((x nth) 2 [1 2 3 4 5]))
     (= true ((x >) 7 8))
     (= 4 ((x quot) 2 8))
     (= [1 2 3] ((x take) [1 2 3 4 5] 3))
     ]))

(def s46
  (fn [f] (fn [x y] (f y x)))
  )

(defn p46
  []
  (println (str "problem 46: " (v46 s46)))
  )


;; problem 47
(defn v47
  [x]
  (every? identity
    [
     (contains? #{4 5 6} x)
     (contains? [1 1 1 1 1] x)
     (contains? {4 :a 2 :b} x)
     (not (contains? [1 2 4] x))
     ]))

(def s47
  4
  )

(defn p47
  []
  (println (str "problem 47: " (v47 s47)))
  )


;; problem 48
(defn v48
  [x]
  (every? identity
    [
     (= x (some #{2 7 6} [5 6 7 8]))
     (= x (some #(when (even? %) %) [5 6 7 8]))
     ]))

(def s48
  6
  )

(defn p48
  []
  (println (str "problem 48: " (v48 s48)))
  )


;; problem 49
(defn v49
  [x]
  (every? identity
    [
     (= (x 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
     (= (x 1 [:a :b :c :d]) [[:a] [:b :c :d]])
     (= (x 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])
     ]))

(def s49
  (fn [n s] [(take n s) (drop n s)])
  )

(defn p49
  []
  (println (str "problem 49: " (v49 s49)))
  )


;; problem 51
(defn v51
  [x]
  (= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] x] [a b c d]))
  )

(def s51
  [1 2 3 4 5]
  )

(defn p51
  []
  (println (str "problem 51: " (v51 s51)))
  )


;; problem 61
(defn v61
  [x]
  (every? identity
    [
     (= (x [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
     (= (x [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})
     (= (x [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})
     ]))

(def s61
  (fn my-zipmap
    ([x y] (my-zipmap x y {}))
    ([x y m]
     (if (or (empty? x) (empty? y))
       m
       (my-zipmap (rest x) (rest y) (assoc m (first x) (first y)))
       )))
  )

(defn p61
  []
  (println (str "problem 61: " (v61 s61)))
  )


;; problem 62
(defn v62
  [x]
  (every? identity
    [
     (= (take 5 (x #(* 2 %) 1)) [1 2 4 8 16])
     (= (take 100 (x inc 0)) (take 100 (range)))
     (= (take 9 (x #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3])))
     ]))

(def s62
  (fn my-iterate
    [f x]
    (lazy-seq (cons x (my-iterate f (f x))))
    )
  )

(defn p62
  []
  (println (str "problem 62: " (v62 s62)))
  )


;; problem 63
(defn v63
  [x]
  (every? identity
    [
     (= (x #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
     (= (x #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
        {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})
     (= (x count [[1] [1 2] [3] [1 2 3] [2 3]])
        {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})
     ]))

(def s63
  (fn my-group-by
    ([f s] (my-group-by f s {}))
    ([f s m]
     (if (empty? s)
       m
       (let [r (f (first s))]
         (if (contains? m r)
           (my-group-by f (rest s) (assoc m r (conj (m r) (first s))))
           (my-group-by f (rest s) (assoc m r [(first s)]))
           )))))
  )

(defn p63
  []
  (println (str "problem 63: " (v63 s63)))
  )


;; problem 66
(defn v66
  [x]
  (every? identity
    [
     (= (x 2 4) 2)
     (= (x 10 5) 5)
     (= (x 5 7) 1)
     (= (x 1023 858) 33)
     ]))

(def s66-a
  ; Euclid's algorithm
  (fn gcd
    ([n1 n2]
     (gcd n2 (quot n1 n2) (rem n1 n2))  ; rem or mod? mod only returns positives.
     )
    ([prev-r q r]
     (if (zero? r)
       prev-r
       (gcd r (quot prev-r r) (rem prev-r r))
       )))
  )

(def s66-b
  ; Euclid's algorithm simplified.
  (fn
    [n1 n2]
    (if (zero? n2)
      n1
      (recur n2 (rem n1 n2))))
  )

(defn p66
  []
  (println (str "problem 66: " (v66 s66-b)))
  )


;; problem 81
(defn v81
  [x]
  (every? identity
    [
     (= (x #{0 1 2 3} #{2 3 4 5}) #{2 3})
     (= (x #{0 1 2} #{3 4 5}) #{})
     (= (x #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})
     ]))

(def s81-a
  ;; This is actually more general than required, because the function
  ;; can receive any number of sets, not just two.
  (fn [& x]
    (set (filter identity (apply (fn [& sl]
                                   (for [i (first sl)]
                                     (if (every? #(contains? % i) (rest sl))
                                       i)))
                                 x))))
  )

(def s81-b
  ;; Cryptic but succint.
  ;(fn [s1 s2] (set (filter s1 s2)))

  ;; Even shorter.
  (comp set filter)
  )

(def s81-c
  ;; Another alternative.
  (fn [s1 s2] (set (filter #(contains? s1 %) s2)))
  )

(def s81-d
  ;; Yet another possibility.
  #(set (filter identity (map %1 %2)))
  )

(defn p81
  []
  (println (str "problem 81: " (v81 s81-a))))


;; problem 83
(defn v83
  [x]
  (every? identity
    [
     (= false (x false false))
     (= true (x true false))
     (= false (x true))
     (= true (x false true false))
     (= false (x true true true))
     (= true (x true true true false))
     ]))

(def s83
  #(if (= 1 (count (set %&))) false true)
  )

(defn p83
  []
  (println (str "problem 83: " (v83 s83))))


;; problem 88
(defn v88
  [x]
  (every? identity
    [
     (= (x #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})
     (= (x #{:a :b :c} #{}) #{:a :b :c})
     (= (x #{} #{4 5 6}) #{4 5 6})
     (= (x #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})
     ]))

(def s88-a
  (fn [s1 s2]
    (into (set (filter #(not (contains? s2 %)) s1))
          (filter #(not (contains? s1 %)) s2)))
  )

(def s88-b
  (fn [s1 s2]
    ; using built-in set functions
    (clojure.set/difference
      (clojure.set/union s1 s2)
      (clojure.set/intersection s1 s2))
    ; alternatively
    ;(into (clojure.set/difference s1 s2) (clojure.set/difference s2 s1))
    )
  )

(defn p88
  []
  (println (str "problem 88: " (v88 s88-a))))


;; problem 90
(defn v90
  [x]
  (every? identity
    [
     (= (x #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
        #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
          ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
          ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})
     (= (x #{1 2 3} #{4 5})
        #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})
     (= 300 (count (x (into #{} (range 10))
                      (into #{} (range 30)))))
     ]))

(def s90
  #(set (for [x %1 y %2] [x y]))
  )

(defn p90
  []
  (println (str "problem 90: " (v90 s90))))


;; problem 95
(defn v95
  [x]
  (every? identity
    [
     (= (x '(:a (:b nil nil) nil))
        true)
     (= (x '(:a (:b nil nil)))
        false)
     (= (x [1 nil [2 [3 nil nil] [4 nil nil]]])
        true)
     (= (x [1 [2 nil nil] [3 nil nil] [4 nil nil]])
        false)
     (= (x [1 [2 [3 [4 nil nil] nil] nil] nil])
        true)
     (= (x [1 [2 [3 [4 false nil] nil] nil] nil])
        false)
     (= (x '(:a nil ()))
        false)
     ]))

(def s95
  (fn bt? [n]
    (if (not= 3 (count n))
      false
      (every?
        #(identity %)
        (for [i n]
          (if (contains?
                #{java.lang.Long java.lang.String clojure.lang.Keyword nil}
                (type i))
            true
            (if (coll? i)
              (bt? i)))))))
  )

(defn p95
  []
  (println (str "problem 95: " (v95 s95))))


;; problem 97
(defn v97
  [x]
  (every? identity
    [
     (= (x 1) [1])
     (= (map x (range 1 6))
        [     [1]
             [1 1]
            [1 2 1]
           [1 3 3 1]
          [1 4 6 4 1]])
     (= (x 11)
        [1 10 45 120 210 252 210 120 45 10 1])
     ]))

(def s97
  (fn [n]
    (loop [v [1]
           i 1]
      (if (= n i)
        v
        (recur
          (apply vector
                 (map #(apply + %) (partition 2 1(into [0] (conj v 0)))))
          (inc i)))))
  )

(defn p97
  []
  (println (str "problem 97: " (v97 s97))))


;; problem 99
(defn v99
  [x]
  (every? identity
    [
     (= (x 1 1) [1])
     (= (x 99 9) [8 9 1])
     (= (x 999 99) [9 8 9 0 1])
     ]))

(def s99
  (fn [x y] (for [l (str (* x y))] (Integer. (str l))))
  )

(defn p99
  []
  (println (str "problem 99: " (v99 s99))))


;; problem 107
(defn v107
  [x]
  (every? identity
    [
     (= 256 ((x 2) 16)
            ((x 8) 2))
     (= [1 8 27 64] (map (x 3) [1 2 3 4]))
     (= [1 2 4 8 16] (map #((x %) 2) [0 1 2 3 4]))
     ]))

(def s107
  (fn [x]
    (fn [b]
      (loop [a 1 i x]
        (if (zero? i)
          a
          (recur (* a b) (dec i))
          ))))
  )

(defn p107
  []
  (println (str "problem 107: " (v107 s107))))


;; problem 118
(defn v118
  [x]
  (every? identity
    [
     (= [3 4 5 6 7]
        (x inc [2 3 4 5 6]))
     (= (repeat 10 nil)
        (x (fn [_] nil) (range 10)))
     (= [1000000 1000001]
        (->> (x inc (range))
             (drop (dec 1000000))
             (take 2)))
     ]))

(def s118
  (fn my-map [f s]
    (if (empty? s)
      '()  ; or (list) or nil
      (lazy-seq (cons (f (first s)) (my-map f (rest s))))
      ))
  )

(defn p118
  []
  (println (str "problem 118: " (v118 s118))))


;; problem 120
(defn v120
  [x]
  (every? identity
    [
     (= 8 (x (range 10)))
     (= 19 (x (range 30)))
     (= 50 (x (range 100)))
     (= 50 (x (range 1000)))
     ]))

(def s120
  (fn [s]
    (loop [z s
           a []]
      (if (empty? z)
        (count a)
        (let [d (map #(read-string (str %)) (str (first z)))]
          (recur (rest z) (if (< (first z)
                                 (apply + (map #(* % %) d)))
                            (conj a (first z))
                            a))))))
  )

(defn p120
  []
  (println (str "problem 120: " (v120 s120))))


;; problem 122
(defn v122
  [x]
  (every? identity
    [
     (= 0     (x "0"))
     (= 7     (x "111"))
     (= 8     (x "1000"))
     (= 9     (x "1001"))
     (= 255   (x "11111111"))
     (= 1365  (x "10101010101"))
     (= 1365  (x "10101010101"))
     ]))

(def s122
  #(Integer/parseInt % 2)
  )

(defn p122
  []
  (println (str "problem 122: " (v122 s122))))


;; problem 126
(defn v126
  [y]
  (boolean
    (let [x y]
      (and (= (class x) x) x))
    ))

(def s126
  Class
  )

(defn p126
  []
  (println (str "problem 126: " (v126 s126))))


;; problem 128
(defn v128
  [x]
  (every? identity
    [
     (= {:suit :diamond :rank 10} (x "DQ"))
     (= {:suit :heart :rank 3} (x "H5"))
     (= {:suit :club :rank 12} (x "CA"))
     (= (range 13) (map (comp :rank x str)
                        '[S2 S3 S4 S5 S6 S7
                          S8 S9 ST SJ SQ SK SA]))
     ]))

(def s128
  (fn [c]
    (let [s (seq c)
          m (apply hash-map (interleave [\2 \3 \4 \5 \6 \7 \8 \9 \T \J \Q \K \A
                                         \C    \D       \H     \S]
                                        [0  1  2  3  4  5  6  7  8  9  10 11 12
                                         :club :diamond :heart :spade]))]
      {:suit (m (first s)) :rank (m (second s))}))
  )

(defn p128
  []
  (println (str "problem 128: " (v128 s128))))


;; problem 135
(defn v135
  [x]
  (every? identity
    [
     (= 7  (x 2 + 5))
     (= 42 (x 38 + 48 - 2 / 2))
     (= 8  (x 10 / 2 - 1 * 2))
     (= 72 (x 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))
     ]))

(def s135
  (fn [& args]
    (letfn [(c [l a] (if (empty? l)
                       a
                       (recur (rest (rest l)) ((first l) a (second l)))))]
      (c (rest args) (first args))))
  )

(defn p135
  []
  (println (str "problem 135: " (v135 s135))))


;; problem 143
(defn v143
  [x]
  (every? identity
    [
     (= 0 (x [0 1 0] [1 0 0]))
     (= 3 (x [1 1 1] [1 1 1]))
     (= 32 (x [1 2 3] [4 5 6]))
     (= 256 (x [2 5 6] [100 10 1]))
     ]))

(def s143-a
  (fn [v1 v2]
    (reduce
      #(+ %1 (* (first %2) (second %2))) 0 (partition 2 (interleave v1 v2))))
  )

; shorter and clearer. map accepts more than 1 seq, and goes over the first
; item of both, the second item of both, and so on. Stops when there are no
; more items in either of the seqs.
(def s143-b
  #(reduce + (map * %1 %2))
  )

(defn p143
  []
  (println (str "problem 143: " (v143 s143-b))))


;; problem 147
(defn v147
  [x]
  (every? identity
    [
     (= (second (x [2 3 2])) [2 5 5 2])
     (= (take 5 (x [1])) [[1] [1 1] [1 2 1] [1 3 3 1] [1 4 6 4 1]])
     (= (take 2 (x [3 1 2])) [[3 1 2] [3 4 3 2]])
     (= (take 100 (x [2 4 2])) (rest (take 101 (x [2 2]))))
     ]))

(def s147
  (fn pt [v]
    (lazy-seq (cons v (pt (mapv +' (conj v 0) (cons 0 v)))))
    )
  )

(defn p147
  []
  (println (str "problem 147: " (v147 s147))))


;; problem 153
(defn v153
  [x]
  (every? identity
    [
     (= (x #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})
        true)
     (= (x #{#{:a :b :c :d :e}
             #{:a :b :c :d}
             #{:a :b :c}
             #{:a :b}
             #{:a}})
        false)
     (= (x #{#{[1 2 3] [4 5]}
             #{[1 2] [3 4 5]}
             #{[1] [2] 3 4 5}
             #{1 2 [3 4] [5]}})
        true)
     (= (x #{#{'a 'b}
           #{'c 'd 'e}
           #{'f 'g 'h 'i}
           #{''a ''c ''f}})
        true)
     (= (x #{#{'(:x :y :z) '(:x :y) '(:z) '()}
             #{#{:x :y :z} #{:x :y} #{:z} #{}}
             #{'[:x :y :z] [:x :y] [:z] [] {}}})
        false)
     (= (x #{#{(= "true") false}
             #{:yes :no}
             #{(class 1) 0}
             #{(symbol "true") 'false}
             #{(keyword "yes") ::no}
             #{(class '1) (int \0)}})
        false)
     (= (x #{#{distinct?}
             #{#(-> %) #(-> %)}
             #{#(-> %) #(-> %) #(-> %)}
             #{#(-> %) #(-> %) #(-> %)}})
        true)
     (= (x #{#{(#(-> *)) + (quote mapcat) #_ nil}
             #{'+ '* mapcat (comment mapcat)}
             #{(do) set contains? nil?}
             #{, , , #_, , empty?}})
        false)
     ]))

(def s153
  (fn [s]
    (let [a (apply concat s)]
      (= (count a) (count (distinct a)))))
  )

(defn p153
  []
  (println (str "problem 153: " (v153 s153))))


;; problem 157
(defn v157
  [x]
  (every? identity
    [
     (= (x [:a :b :c]) [[:a 0] [:b 1] [:c 2]])
     (= (x [0 1 3]) '((0 0) (1 1) (3 2)))
     (= (x [[:foo] {:bar :baz}]) [[[:foo] 0] [{:bar :baz} 1]])
     ]))

(def s157
  (fn [s] (map-indexed #(vector %2 %1) s))
  )

(defn p157
  []
  (println (str "problem 157: " (v157 s157))))


;; problem 161
(defn v161
  [x]
  (every? identity
    [
     (clojure.set/superset? x #{2})
     (clojure.set/subset? #{1} x)
     (clojure.set/superset? x #{1 2})
     (clojure.set/subset? #{1 2} x)
     ]))

(def s161
  #{1 2 3}
  )

(defn p161
  []
  (println (str "problem 161: " (v161 s161)))
  )


;; problem 166
(defn v166
  [x]
  (every? identity
    [
     (= :gt (x < 5 1))
     (= :eq (x (fn [x y] (< (count x) (count y))) "pear" "plum"))
     (= :lt (x (fn [x y] (< (mod x 5) (mod y 5))) 21 3))
     (= :gt (x > 0 2))
     ]))

(def s166
  (fn [c x y]
    (cond
      (c x y) :lt
      (c y x) :gt
      :default :eq))
  )

(defn p166
  []
  (println (str "problem 166: " (v166 s166))))


;; problem 173
(defn v173
  []
  (= 3
     (let [[x y] [+ (range 3)]] (apply x y))
     (let [[[x y] b] [[+ 1] 2]] (x y b))
     (let [[x y] [inc 2]] (x y)))
  )

(defn p173
  []
  (println (str "problem 173: " (v173))))
