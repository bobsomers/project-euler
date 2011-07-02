(ns pe001
  (:use clojure.test))

; Function that returns true or false depending on whether the passed number is
; a multiple of 3.
(defn mult-of-3 [x]
  (= (mod x 3) 0))

(deftest test-mult-of-3
         (is (= false (mult-of-3 2)))
         (is (= true (mult-of-3 9))))

; Function that returns true or false depending on whether the passed number is
; a multiple of 5.
(defn mult-of-5 [x]
  (= (mod x 5) 0))

(deftest test-mult-of-5
         (is (= false (mult-of-5 3)))
         (is (= true (mult-of-5 15))))

; Function that finds the sum of all natural numbers that are multiples of 3 or
; 5, that are below (and include) the passed upper bound.
(defn sum-of-mults [x]
  (loop [current x
         sum 0]
    (if (<= current 0)
      sum
      (if (or (mult-of-3 current) (mult-of-5 current))
        (recur (dec current) (+ sum current))
        (recur (dec current) sum)))))

(deftest test-sum-of-mults
         (is (= 23 (sum-of-mults 9))))

; Run the tests!
(run-tests)

; Find the answer!
(println (sum-of-mults 999))
