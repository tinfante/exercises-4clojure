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
