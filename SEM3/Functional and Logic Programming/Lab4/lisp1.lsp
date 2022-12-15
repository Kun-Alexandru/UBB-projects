;                               {
;insert_el(l,l2,...,ln,e,p) =   [], n = 0
;                               l1 U e + insert_el(l2,l3,...,ln,e,p+1), if p % 2 == 0
;                               l1 U insert_el(l2,l3,...,ln,e,p+1), othwerwise
;                               }

(defun insert_el (l e p)
    (cond
        ((null l) nil)
        ((equal (mod p 2) 0) (cons (car l) (cons e (insert_el (cdr l) e (+ p 1)))))
        (t (cons (car l) (insert_el (cdr l) e (+ p 1))))
    )
)

(print (insert_el '(b 1 b 2 3 w1 4 5) `a 1))














;                           {
;all_atoms(l1,l2,...,ln) =  [], n = 0
;                           all_atoms(l2,l3,...,ln) U all_atoms(l1), if l1 is list
;                            all_atoms(l2,l3,...,ln) U l1, otherwise 
;                           }
(defun all_atoms (l)
    (cond
        ((null l) nil)
        ((listp (car l)) (append (all_atoms (cdr l)) (all_atoms (car l))))
        (t (append (all_atoms (cdr l)) (list (car l))))
    )
)

(print (all_atoms '(((A B) C) (D E))))





























;               {
;my_gcd(a b) =  false, if a IS NOT a number and b IS NOT a number
;               b, if a IS NOT a number
;               a, if b IS NOT a number
;               a, if b = 0
;               my_gcd(b, a%b)
;               }
(defun my_gcd (a b)
    (cond
        ((and (not (numberp a)) (not (numberp b))) nil)
        ((not (numberp a)) b)
        ((not (numberp b)) a)
        ((equal b 0) a)
        (t (my_gcd b (mod a b)))
    )
)

;                           {
;list_gcd(l1,l2,...,ln) =   false, l1 is atom and n = 1
;                           my_gcd(list_gcd(l1),list_gcd(l2,l3,..,ln)), l1 list
;                           my_gcd(l1,list_gcd(l2,l3,...,ln)), otherwise
;                           }
(defun list_gcd (l)
    (cond
        ((and (atom (car l)) (null (cdr l))) nil)
        ((listp (car l)) (my_gcd (list_gcd (car l)) (list_gcd (cdr l))))
        (t (my_gcd (car l) (list_gcd (cdr l))))
    )
)

(print (list_gcd '(6 ( 18 (36 (a B) (C D)) 6) 72)))





























;                               {
;occurances(l1,l2,...,ln,e) =   1, if l is an atom (which also implies n = 1) and l = e
;                               0, if l is an atom (which also implies n = 1) 
;                               1 + occurances(l2,l3,...,ln,e), if l1 = e
;                               occurances(l2,l3,...,ln,e), if l1 = atom and l1 != e
;                               occurances(l2,l3,..,ln,e) + occurances(l1,e), if l1 = list
;                               }
(defun occurances (l e)
    (cond 
        ((and (atom l) (equal l e)) 1)
        ((atom l) 0)
        ((equal (car l) e) (+ 1 (occurances (cdr l) e)))
        ((and (atom (car l)) (not (equal l e))) (occurances (cdr l) e))
        ((listp l) (+ (occurances (cdr l) e) (occurances (car l) e)))
    )
)

(print (occurances '(1 (a (5 4 a) (5 a)) 3 a) `a))
