(ns exercises-4clojure.easy)


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
      f)
    ))

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
        ))))

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
        ))))

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
        ))))

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

(def s24 (fn [s] (reduce + s)))

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
