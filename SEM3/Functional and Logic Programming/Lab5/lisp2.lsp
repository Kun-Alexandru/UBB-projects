














;                            {
;appears(l1,l2,l3,e) =      False, if n = 0
;                           True, if l1 = e
;                           appears(l2,e) || appears(l3,e), otherwise
;                            }
(defun appears (l e)
    (cond 
        ((null l) nil)
        ((equal (car l) e) t )
        (t (or (appears  (car (cdr l)) e) (appears (car (cddr l)) e)))
    )
)

;                    {
;path(l1,l2,l3,e) =  [e], l1 = e
;                    l1 U path(l2,e), appears(l2,e) == True
;                    l1 U path(l3,e), appears(l3,e) == True
;                    False, otherwise
;                    }
(defun path (l e)
    (cond
        ((equal (car l) e)(list  e))
        ((appears (car (cdr l)) e) (cons (car l) (path (car (cdr l)) e)))
        ((appears (car (cddr l)) e) (cons (car l) (path (car (cddr l)) e)))
    )
)    

(print (path '(A (B) (C (D) (E))) 'D))
