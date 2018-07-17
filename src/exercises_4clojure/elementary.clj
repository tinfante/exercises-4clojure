(ns exercises-4clojure.elementary)


;; problem 1
(defn v1
  [x]
  (= x true)
  )

(def s1 true)

(defn p1
  []
  (println (str "problem 1: " (v1 s1)))
  )


;; problem 2
(defn v2
  [x]
  (= (- 10 (* 2 3)) x)
  )

(def s2 4)

(defn p2
  []
  (println (str "problem 2: " (v2 s2)))
  )


;; problem 3
(defn v3
  [x]
  (=  x (.toUpperCase "hello world"))
  )

(def s3 "HELLO WORLD")

(defn p3
  []
  (println (str "problem 3: " (v3 s3)))
  )


;; problem 4
(defn v4
  [x]
  (= (apply list x) '(:a :b :c))
  )

(def s4 '(:a :b :c))

(defn p4
  []
  (println (str "problem 4: " (v4 s4)))
  )


;; problem 5
(defn v5
  [x]
  (every? identity
    [
     (= x (conj '(2 3 4) 1))
     (= x (conj '(3 4) 2 1))
     ]))

(def s5 '(1 2 3 4))

(defn p5
  []
  (println (str "problem 5: " (v5 s5)))
  )


;; problem 6
(defn v6
  [x]
  (= x (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))
  )

(def s6 [:a :b :c])

(defn p6
  []
  (println (str "problem 6: " (v6 s6)))
  )


;; problem 7
(defn v7
  [x]
  (every? identity
    [
     (= x (conj [1 2] 3 4))
     (= x (conj [1 2 3] 4))
     ]))

(def s7 [1 2 3 4])

(defn p7
  []
  (println (str "problem 7: " (v7 s7)))
  )


;; problem 8
(defn v8
  [x]
  (every? identity
    [
     (= x (set '(:a :a :b :c :c :c :c :d :d)))
     ;; Below causes error: clojure.set not found. Probably have to import lib.
     ;(= x (clojure.set/union #{:a :b :c} #{:b :c :d}))
     ]))

(def s8 #{:a :b :c :d})

(defn p8
  []
  (println (str "problem 8: " (v8 s8)))
  )


;; problem 9
(defn v9
  [x]
  (= #{1 2 3 4} (conj #{1 4 3} x))
  )

(def s9 2)

(defn p9
  []
  (println (str "problem 9: " (v9 s9)))
  )


;; problem 10
(defn v10
  [x]
  (every? identity
    [
     (= x ((hash-map :a 10, :b 20, :c 30) :b))
     (= x (:b {:a 10, :b 20, :c 30}))
     ]))

(def s10 20)

(defn p10
  []
  (println (str "problem 10: " (v10 s10)))
  )


;; problem 11
(defn v11
  [x]
  (= {:a 1 :b 2 :c 3}
     (conj {:a 1} x [:c 3])
     ))

(def s11 {:b 2})

(defn p11
  []
  (println (str "problem 11: " (v11 s11)))
  )


;; problem 12
(defn v12
  [x]
  (every? identity
    [
     (= x (first '(3 2 1)))
     (= x (second [2 3 4]))
     (= x (last (list 1 2 3)))
     ]))

(def s12 3)

(defn p12
  []
  (println (str "problem 12: " (v12 s12)))
  )


;; problem 13
(defn v13
  [x]
  (= x (rest [10 20 30 40]))
  )

(def s13 [20 30 40])

(defn p13
  []
  (println (str "problem 13: " (v13 s13)))
  )


;; problem 14
(defn v14
  [x]
  (every? identity
    [
     (= x ((fn add-five [x] (+ x 5)) 3))
     (= x ((fn [x] (+ x 5)) 3))
     (= x (#(+ % 5) 3))
     (= x ((partial + 5) 3))
     ]))

(def s14 8)

(defn p14
  []
  (println (str "problem 14: " (v14 s14)))
  )


;; problem 15
(defn v15
  [x]
  (every? identity
    [
     (= (x 2) 4)
     (= (x 3) 6)
     (= (x 11) 22)
     (= (x 7) 14)
     ]))

(def s15 #(* % 2))

(defn p15
  []
  (println (str "problem 15: " (v15 s15)))
  )


;; problem 16
(defn v16
  [x]
  (every? identity
    [
     (= (x "Dave") "Hello, Dave!")
     (= (x "Jenn") "Hello, Jenn!")
     (= (x "Rhea") "Hello, Rhea!")
     ]))

(def s16 #(str "Hello, " % "!"))

(defn p16
  []
  (println (str "problem 16: " (v16 s16)))
  )


;; problem 17
(defn v17
  [x]
  (= x (map #(+ % 5) '(1 2 3)))
  )

(def s17 '(6 7 8))

(defn p17
  []
  (println (str "problem 17: " (v17 s17)))
  )


;; problem 18
(defn v18
  [x]
  (= x (filter #(> % 5) '(3 4 5 6 7)))
  )

(def s18 '(6 7))

(defn p18
  []
  (println (str "problem 18: " (v18 s18)))
  )


;; problem 35
(defn v35
  [x]
  (every? identity
    [
     (= x (let [x 5] (+ 2 x)))
     (= x (let [x 3, y 10] (- y x)))
     (= x (let [x 21] (let [y 3] (/ x y))))
     ]))

(def s35 7)

(defn p35
  []
  (println (str "problem 35: " (v35 s35)))
  )


;; problem 36
(defn v36
  []
  (every? identity
    [
     (= 10 (let [z 1 y 3 x 7] (+ x y)))
     (= 4 (let [z 1 y 3 x 7] (+ y z)))
     (= 1 (let [z 1 y 3 x 7] z))
     ]))

(defn p36
  []
  (println (str "problem 36: " (v36)))
  )


;; problem 37
(defn v37
  [x]
  (= x (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))
  )

(def s37 "ABC")

(defn p37
  []
  (println (str "problem 37: " (v37 s37)))
  )


;; problem 52
(defn v52
  []
  (= [2 4] (let [[a b c d e] [0 1 2 3 4]] [c e]))
  )

(defn p52
  []
  (println (str "problem 52: " (v52)))
  )


;; problem 57
(defn v57
  [x]
  (= x ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))
  )

(def s57
  '(5 4 3 2 1)
  )

(defn p57
  []
  (println (str "problem 57: " (v57 s57)))
  )


;; problem 64
(defn v64
  [x]
  (every? identity
    [
     (= 15 (reduce x [1 2 3 4 5]))
     (=  0 (reduce x []))
     (=  6 (reduce x 1 [2 3]))
     ]))

(def s64
  +
  )

(defn p64
  []
  (println (str "problem 64: " (v64 s64)))
  )


;; problem 68
(defn v68
  [x]
  (= x
     (loop [x 5
            result []]
       (if (> x 0)
         (recur (dec x) (conj result (+ 2 x)))
         result))))

(def s68
  [7, 6, 5, 4, 3]
  )

(defn p68
  []
  (println (str "problem 68: " (v68 s68)))
  )


;; problem 71
(defn v71
  [x]
  (= (x (sort (rest (reverse [2 5 4 1 3 6]))))
     (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (x))
     5))

(def s71
  last
  ; alternatively:
  ; #(apply max %)
  ; (fn [_] 5)
  )

(defn p71
  []
  (println (str "problem 71: " (v71 s71))))


;; problem 72
(defn v72
  [x]
  (= (x (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
     (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (x))
     11)
  )

(def s72
  #(apply + %)
  )

(defn p72
  []
  (println (str "problem 72: " (v72 s72))))


;; problem 134
(defn v134
  [x]
  (every? identity
    [
     (true?  (x :a {:a nil :b 2}))   
     (false? (x :b {:a nil :b 2}))
     (false? (x :c {:a nil :b 2}))
     ]))

(def s134
  (fn [k m]
    (if (contains? m k)
      (if (nil? (k m))
        true false)
      false
      ))
  )

(def s134-a
  ; Using get with a default argument to return something
  ; other than nil if key not in map. The same can be
  ; achieved with: #(nil? (%1 %2 "asd"))
  #(nil? (get %2 %1 "qwe"))
  )

(defn p134
  []
  (println (str "problem 134: " (v134 s134-a))))


;; problem 145
(defn v145
  [x]
  (every? identity
    [
     (= x (for [x (range 40)
                :when (= 1 (rem x 4))]
            x))
     (= x (for [x (iterate #(+ 4 %) 0)
                :let [z (inc x)]
                :while (< z 40)]
            z))
     (= x (for [[x y] (partition 2 (range 20))]
            (+ x y)))
     ]))

(def s145
  [1 5 9 13 17 21 25 29 33 37]
  )

(defn p145
  []
  (println (str "problem 145: " (v145 s145))))


;; problem 156
(defn v156
  [x]
  (every? identity
    [
     (= (x 0 [:a :b :c]) {:a 0 :b 0 :c 0})    
     (= (x "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})
     (= (x [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]})
     ]))

(def s156
  #(zipmap %2 (repeat %1))
  )

(defn p156
  []
  (println (str "problem 156: " (v156 s156))))


;; problem 162
(defn v162
  [x]
  (every? identity
    [
     (= x (if-not false 1 0))
     (= x (if-not nil 1 0))
     (= x (if true 1 0))
     (= x (if [] 1 0))
     (= x (if [0] 1 0))
     (= x (if 0 1 0))
     (= x (if 1 1 0))
     ]))

(def s162
  1
  )

(defn p162
  []
  (println (str "problem 162: " (v162 s162))))
