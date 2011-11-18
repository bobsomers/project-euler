#lang racket

(define (compute n sum)
  (cond
    [(equal? n 0) sum]
    [(equal? (remainder n 3) 0)
     (compute (- n 1) (+ sum n))]
    [(equal? (remainder n 5) 0)
     (compute (- n 1) (+ sum n))]
    [else
     (compute (- n 1) sum)]))

(time (compute 999 0))
