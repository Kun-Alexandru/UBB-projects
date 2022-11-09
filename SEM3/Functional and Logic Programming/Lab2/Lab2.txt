%				{
% isEven(NUM) = True, if NUM % 2 = 0
% 	 			False, otherwise
% 				}
% NUM - given number to check if it's even or not
%(i)


isEven(NUM):-
    NUM mod 2 =:= 0.


%					{
% lenList(l1...ln) =   0, if n = 0
% 					   1 + lenList(l2...ln), otherwise
%					}
%List - given list to check it's lenght
%RES - lenght of the list
%(i, o)

lenList([], 0).
lenList([_|T], RES):-
    lenList(T, RES1),
    RES is RES1 + 1.


%							{
% addLast(l1,l2...ln, e) =	[e], if n = 0
% 							{l1} + addLast(l2...ln), otherwise
%							}
%List - initial list
%E - given element to add at the end of the initial list
%RES - resulting list after the add is performed
%(i, i, o)

addLast([], E, [E]):- !.
addLast([H|T], E, [H|RES]):-
    addLast(T, E, RES).

%											{
% longestSequence(l1...ln, seq1...seqn,curr1...currn) =	seq1...seqn, if l1...ln = [] && len(seq) >= len(curr)
% 														curr1...currn, if l1...ln = [] &&  len(seq) < len(curr)
% 														longestSequence(l2...ln, seq1...seqn, {l1} + curr1...currn), if even(l1) = True
% 														longestSequence(l2...ln, seq1...seqn, []), if even(l1) = False && len(seq) >= len(curr)
% 														longestSequence(l2...ln, curr1...currn, []), if even(l1) = False && len(seq) < len(curr)
%											}
%List - initial list
%SEQ - biggest sequence of even numbers found so far in the list, always initialized with []
%CURR - current sequence of even numbers found, always initialized with []
%RES - biggest sequence of even numbers from the given list, output
% (i, i, i, o)

longestSequence([], SEQ, CURR_SEQ, SEQ):-
    lenList(SEQ, LENS),
    lenList(CURR_SEQ, LENCURR),
    LENS >= LENCURR, !.
longestSequence([], SEQ, CURR_SEQ, CURR_SEQ):-
    lenList(SEQ, LENS),
    lenList(CURR_SEQ, LENCURR),
    LENS < LENCURR, !.
longestSequence([H|T], SEQ, CURR_SEQ, RES):- 
    isEven(H), !,
    addLast(CURR_SEQ, H, RES_CURR),
    longestSequence(T, SEQ, RES_CURR, RES).
longestSequence([_|T], SEQ, CURR_SEQ, RES):-
    lenList(SEQ, LENS),
    lenList(CURR_SEQ, LENCURR),
    LENCURR < LENS, !,
    longestSequence(T, SEQ, [], RES).
longestSequence([_|T], SEQ, CURR_SEQ, RES):-
    lenList(SEQ, LENS),
    lenList(CURR_SEQ, LENCURR),
    LENCURR >= LENS, !,
    longestSequence(T, CURR_SEQ, [], RES).

% 							{
% heterogeneousList(l1...ln, list) = [], n = 0
% 							 		{longestSequence(l1)} + heterogeneousList(l2...ln), is_list(l1) = True
% 							 		{l1} + heterogeneousList(l2...ln), otherwise
%							}
%List - initial list
%RES - resulting list after the asked operation is perfermed
%(i, o)

heterogeneousList([], []).
heterogeneousList([H|T], [HRES|RES]):- 
    is_list(H), !,
    longestSequence(H, [], [], HRES),
    heterogeneousList(T, RES).
heterogeneousList([H|T], [H|RES]) :-
    heterogeneousList(T, RES).
