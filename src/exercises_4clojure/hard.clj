(ns exercises-4clojure.hard)


;; problem 53
(defn v53
  [x]
  (every? identity
    [
     (= (x [1 0 1 2 3 0 4 5]) [0 1 2 3])
     (= (x [5 6 1 3 2 7]) [5 6])
     (= (x [2 3 3 4 5]) [3 4 5])
     (= (x [7 6 5 4]) [])
     ]))

(def s53
  (fn lcs
    ([x]
     (lcs (rest x) [(first x)] [(first x)])
     )
    ([x c l]
     (if (empty? x)
       (if (> (count c) (count l))
         (if (> (count c) 1) c [])
         (if (> (count l) 1) l [])
         )
       (if (= (first x) (inc (last c)))
         (lcs (rest x) (conj c (first x)) l)
         (if (> (count c) (count l))
           (lcs (rest x) [(first x)] c)
           (lcs (rest x) [(first x)] l)
           )))))
  )

(defn p53
  []
  (println (str "problem 53: " (v53 s53)))
  )


;; problem 73
(defn v73
  [x]
  (every? identity
    [
     (= nil (x [[:e :e :e]
                [:e :e :e]
                [:e :e :e]]))
     (= :x (x [[:x :e :o]
               [:x :e :e]
               [:x :e :o]]))
     (= :o (x [[:e :x :e]
               [:o :o :o]
               [:x :e :x]]))
     (= nil (x [[:x :e :o]
                [:x :x :e]
                [:o :x :o]]))
     (= :x (x [[:x :e :e]
               [:o :x :e]
               [:o :e :x]]))
     (= :o (x [[:x :e :o]
               [:x :o :e]
               [:o :e :x]]))
     (= nil (x [[:x :o :x]
                [:x :o :x]
                [:o :x :o]]))
     ]))

(def s73
  (fn [board]
    (let [rows board
          cols (map (fn [i] (map #(get % i) board)) [0 1 2])
          diag [(map #(get (get board %) %) [0 1 2])
                (map #(get (get board %) (Math/abs (- % 2))) [0 1 2])]
          check (fn [k l] (some (fn [x] (every? #(= % k) x)) l))]
      (cond
        (or (check :x rows) (check :x cols) (check :x diag)) :x
        (or (check :o rows) (check :o cols) (check :o diag)) :o
        :default nil)))
  )

(defn p73
  []
  (println (str "problem 73: " (v73 s73)))
  )
