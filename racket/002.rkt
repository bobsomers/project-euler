#lang racket

(define (compute one two limit sum)
  (let ([n (+ one two)])
    (cond
      [(> n limit)
       (+ sum 2)]
      [(equal? (remainder n 2) 0)
       (compute two n limit (+ sum n))]
      [else
       (compute two n limit sum)])))

(time (compute 1 2 4000000 0))
