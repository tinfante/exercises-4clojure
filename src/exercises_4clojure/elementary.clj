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
