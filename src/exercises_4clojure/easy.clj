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
        ))))

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
      )))

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
      z)))

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
  ))

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
         )))))

(defn p30
  []
  (println (str "problem 30: " (v30 s30)))
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
     )))

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
