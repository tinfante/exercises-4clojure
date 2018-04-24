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
  (println (str "problem 12: " (v2 s2)))
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
