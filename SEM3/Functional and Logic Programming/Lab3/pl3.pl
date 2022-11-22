% 9. Generate all permutation of N (N - given) respecting the property: 
% for every 2<=i<=n exists an 1<=j<=i, so |v(i)-v(j)|=1.

%							{
% insert(l1...ln, e) = [e], n = 0
% 						 e + l1...ln, n >= 1
% 						 l1 + insert(l2...ln, e), otherwise
%							}
% insert(L:list, E:number, R:list)
% insert(i, i, o)

insert([], E, [E]).
insert([H|T], E, [E,H|T]).
insert([H|T], E, [H|R]) :- 
         insert(T, E, R).

%						{
% permutations(l1...ln) =	[], n = 0
% 							insert(permutations(l2...ln), l1), otherwise
%						}
% permutations(L:list, R:list)
% permutations(i, o)

permutations([], []).
permutations([H|T], R) :-
    permutations(T, RP),
    insert(RP, H, R).

%					{
% createList(n) =	[], n = 0
% 					n + createList(n - 1), n > 0
%					}
% createList(N:number, R:list)
% createList(i, o)

createList(0, []).
createList(N, [N|R]) :-
    N > 0,
    NN is N - 1,
    createList(NN, R).

%				{
% diff(a, b) =	a - b, a > b
% 				b - a, a < b
%				}
% diff(A:number, B:number, R:number)
% diff(i, i, o)

diff(A, B, R) :-
    A > B,
    R is A - B.
diff(A, B, R) :-
    A =< B,
    R is B - A.

% Checks if for element EL (which has index POS), there exists at least an 
% index < CURRENT_POS that respects the given constraint
%										{
% checkConstraint(l1l2...ln, EL, CURRENT_POS) =	false, if CURRENT_POS <= 0,
%   									true,  if abs(l1, EL) = 1,
%   									checkConstraint(l2...ln, EL, CURRENT_POS - 1), otherwise.
%										}
% checkConstraint(L - list, EL - number, CURRENT_POS - number)
% flow model: (i, i, i)

checkConstraint(_, _, CURRENT_POS) :-
    CURRENT_POS =< 0,
    !,
    fail.
checkConstraint([H | _], EL, _) :-
    diff(H, EL, R),
    R =:= 1,
    !.
checkConstraint([_ | T], EL, CURRENT_POS) :-
    NEXT_POS is CURRENT_POS - 1,
	checkConstraint(T, EL, NEXT_POS).

%									{
% check(l1l2...ln, c1c2...cm, CURRENT_POS) = true, if m = 0,
% 									 check(L, c2...cm, CURRENT_POS + 1), if checkConstraint(L, c1, CURRENT_POS),
% 									 check(L, c2...cm, CURRENT_POS + 1), if CURRENT_POS = 1,
% 									 false, otherwise.
%									}
% check(L - list, L2 - list, CURRENT_POS - number)
% flow model: (i, i, i)

check(_, [], _) :- !.
check(L, [H | T], CURRENT_POS) :-
    checkConstraint(L, H, CURRENT_POS),
    !,
    NEXT_POS is CURRENT_POS + 1,
    check(L, T, NEXT_POS).
check(L, [_ | T], CURRENT_POS) :-
    CURRENT_POS is 1,
    NEXT_POS is CURRENT_POS + 1,
    check(L, T, NEXT_POS).

% filter(L:list, R:list)
% filter(i, o)
filter(L, R) :-
    permutations(L, R),
    check(R,R,1).

% allsolutions(N:number, R:list)
% allsolutions(i, o)

allsolutions(N, R) :-
    createList(N, RESL),
    findall(RP, filter(RESL, RP), R).
