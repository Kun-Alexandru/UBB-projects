%                               { 
%                               	  [], n = 0
%insert(l1,l2,..,ln,POS,N,EL) =     {EL,l1} U insert(l2,..,ln,POS+1,N,EL), if POS = N
%                                   {l1} U insert(l2,..,ln,POS+1,N,EL), if POS != N
%                               }


%(i,i,i,i,o)
%insert(L,POS,N,EL,R)
%L - initial list
%POS - currentPositon we are on the array, always initialized with 0
%N - the position in which we want to add the element
%EL - the element we want to add in array
%R - resulted list after insert
insert([],_,_,_,[]):- !.
insert([H|T],POS,N,EL,[EL,H|R]):-
    POS=:=N,
    POS1 is POS+1,
    insert(T,POS1,N,EL,R).
insert([H|T],POS,N,EL,[H|R]):-
    POS1 is POS+1,
    insert(T,POS1,N,EL,R).
