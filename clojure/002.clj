(ns pe002
  (:use clojure.test))

; Function that produces a lazily generated Fibonacci sequence starting with 1,
; 2, 3, 5, ...
(defn fib-seq [a b]
  (cons (+ a b)
        (lazy-seq (fib-seq b (+ a b)))))

(def fibonacci (cons 1 (lazy-seq (fib-seq 1 1))))

(deftest test-fibonacci
         (is (= '(1 2 3 5 8 13 21 34 55 89)
                (take 10 fibonacci))))

; Function that produces a lazily generated sequence of the *even* Fibonacci
; numbers, by consuming the regular Fibonacci sequence and producing only the
; even terms.
(defn even-fib-seq [fib]
  (def warped-fib (loop [f fib]
                    (if (= 0 (mod (first f) 2))
                      f
                      (recur (rest f)))))
  (cons (first warped-fib)
        (lazy-seq (even-fib-seq (rest warped-fib)))))

(def even-fibonacci (even-fib-seq fibonacci))

(deftest test-even-fibonacci
         (is (= '(2 8 34 144 610)
                (take 5 even-fibonacci))))

; Function that finds the sum of all the even Fibonacci numbers up to (and
; including) the passed number.
(defn sum-even-fibs [limit]
  (loop [even-fibs even-fibonacci
         sum 0]
    (if (> (first even-fibs) limit)
      sum
      (recur (rest even-fibs)
             (+ (first even-fibs) sum)))))

(deftest test-sum-even-fibs
         (is (= 44 (sum-even-fibs 89))))

; Run the tests!
(run-tests)

; Find the answer!
(println (time (sum-even-fibs 4000000)))
