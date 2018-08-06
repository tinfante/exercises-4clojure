(ns exercises-4clojure.hard
  )


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


;; problem 79
(defn v79
  [x]
  (every? identity
    [
     (= 7 (x '([1]
              [2 4]
             [5 1 4]
            [2 3 4 5])))
     (= 20 (x '([3]
               [2 4]
              [1 9 3]
             [9 9 2 4]
            [4 6 6 7 8]
           [5 7 3 5 1 4])))
     ]))

(def s79
  (fn [x]
    (letfn [
            ;; No fancy shortest path graph algorithms, just make all
            ;; the possible paths (mp ), flatten the resulting output
            ;; (fp ), select the path with the lowest sum, that is,
            ;; the shortest path (sp ).
            (mp
              ([t]
               (mp (rest t) (first t) 0))
              ([t p i]
               (if (empty? t)
                 p
                 (for [i (range i (+ i 2))]
                   (mp (rest t) (conj p ((first t) i)) i)))))
            (fp [t p] (partition (count t) (flatten p)))
            (sp [fps] (apply min-key (partial reduce +) fps))
            (psp [s] (clojure.string/join "->" (map str s)))]
      (let [shortest-path (sp (fp x (mp x)))]
        ;(println (psp shortest-path))
        (apply + shortest-path))))
  )

(defn p79
  []
  (println (str "problem 79: " (v79 s79)))
  )


;; problem 92
(defn v92
  [x]
  (every? identity
    [
     (= 14 (x "XIV"))
     (= 827 (x "DCCCXXVII"))
     (= 3999 (x "MMMCMXCIX"))
     (= 48 (x "XLVIII"))
     ]))

(def s92
  (fn [numeral]
    (let [m (apply hash-map (interleave [\M   \D  \C  \L \X \V \I]
                                        [1000 500 100 50 10 5  1]))
          n (concat (map #(m %) (map char numeral)) [0 0])
          b (interleave (partition 2 n) (partition 2 (rest n)))
          ; alternatively, if the step in smaller than the partition size,
          ; partition will return overlapping elements.
          ;b (partition 2 1 n)
          ]
      (loop [p b a 0]
        (if (empty? p)
          a
          (let [[f s] (first p)]
            (recur (rest p) (if (< f s) (- a f) (+ a f))))))))
  )

(defn p92
  []
  (println (str "problem 92: " (v92 s92)))
  )
