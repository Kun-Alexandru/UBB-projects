%                            {
% gcd_2_numbers(X,Y) =            Y, if X = 0
%                                 gcd_2_numbers(X-Y,Y), if X > Y
%                                 gcd_2_numers(Y-X,X), if Y >= X
%                            }




%(i,i,o)
%gcd_2_numbers(X,Y,Z)
%X = first number
%Y = 2nd number
%Z = variable to store the findal GCD of the 2 numbers
gcd_2_numbers(0, X, X):- !.
gcd_2_numbers(X, Y, Z):- 
    X >= Y, 
    X1 is X-Y,
    gcd_2_numbers(X1,Y,Z).
gcd_2_numbers(X, Y, Z):- 
    X < Y, 
    X1 is Y-X, 
    gcd_2_numbers(X1,X,Z).


%                               {
%gcd_list(l1,l2,...,ln) =    	 0, if {l1,...,ln} = []
%                                l1, if {l1,...,ln} = [_] (list only has 1 element left in it)
%                                gcd(l1,l2) if {l1,l2,...ln} = [_,_] (list only has 2 elements left in it)
%                                {gcd(l1,l2)} U {l3,..,ln}, otherwise (list has more than 2 elements lefts in it)
%                              }


%(i,o)
%gcd_list(L,RES)
%L - starting list
%RES - gcd of the list
gcd_list([],_):- !.
gcd_list([H],H):- !.
gcd_list([H1,H2],RES):-
    gcd_2_numbers(H1,H2,RES).
gcd_list([H1,H2|T],RES):-
    gcd_2_numbers(H1,H2,ResGCD),
    gcd_list([ResGCD|T],RES).
