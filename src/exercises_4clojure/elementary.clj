(ns exercises-4clojure.elementary)


;; problem 1
(defn v1
  [x]
  (= x true)
  )

(def s1
  true
  )

(defn p1
  []
  (println (str "problem 1: " (v1 s1)))
  )


;; problem 2
(defn v2
  [x]
  (= (- 10 (* 2 3)) x)
  )

(def s2
  4
  )

(defn p2
  []
  (println (str "problem 2: " (v2 s2)))
  )


;; problem 3
(defn v3
  [x]
  (=  x (.toUpperCase "hello world"))
  )

(def s3
  "HELLO WORLD"
  )

(defn p3
  []
  (println (str "problem 3: " (v3 s3)))
  )


;; problem 4
(defn v4
  [x]
  (= (apply list x) '(:a :b :c))
  )

(def s4
  '(:a :b :c)
  )

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
     ]
    )
  )

(def s5
  '(1 2 3 4)
  )

(defn p5
  []
  (println (str "problem 5: " (v5 s5))))


;; problem 6
(defn v6
  [x]
  (= x (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))
  )

(def s6
  [:a :b :c]
  )

(defn p6
  []
  (println (str "problem 6: " (v6 s6))))


;; problem 7
(defn v7
  [x]
  (every? identity 
    [
     (= x (conj [1 2] 3 4))
     (= x (conj [1 2 3] 4))
     ]
    )
  )

(def s7
  [1 2 3 4]
  )

(defn p7
  []
  (println (str "problem 7: " (v7 s7))))


;; problem 8
(defn v8
  [x]
  (every? identity 
    [
     (= x (set '(:a :a :b :c :c :c :c :d :d)))
     ;; Below causes error: clojure.set not found. Probably have to import lib.
     ;(= x (clojure.set/union #{:a :b :c} #{:b :c :d}))
     ]
    )
  )

(def s8
  #{:a :b :c :d}
  )

(defn p8
  []
  (println (str "problem 8: " (v8 s8))))


;; problem 9
(defn v9
  [x]
  (= #{1 2 3 4} (conj #{1 4 3} x))
  )

(def s9
  2
  )

(defn p9
  []
  (println (str "problem 9: " (v9 s9))))


;; problem 10
(defn v10
  [x]
  (every? identity 
    [
     (= x ((hash-map :a 10, :b 20, :c 30) :b))
     (= x (:b {:a 10, :b 20, :c 30}))
     ]
    )
  )

(def s10
  20
  )

(defn p10
  []
  (println (str "problem 10: " (v10 s10))))
