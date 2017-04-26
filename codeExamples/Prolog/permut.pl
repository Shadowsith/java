% Berechne alle Permutation einer Liste
% http://www.dreamincode.net/code/snippet3411.htm
%
%  | ?- [permut].
%
%  | ?- del(b,[a,b,c],Y).
%  | ?- del(b,[a,b,c],[a,c]).
%  | ?- del(a,L,[b,c,d]).
%  | ?- del(X,[a,b,c],Y).
%
%  | ?- permut([a,b,c],[c,b,a]).
%  | ?- permut([a,b,c,d],Ps).
%  | ?- findall(Ps,permut([a,b,c,d],Ps),_S), length(_S,L).

% Loesche Element aus Liste
del(X, [X|Ts], Ts).

del(X, [H|Ts], [H|Ss]):-
    del(X, Ts, Ss).

% Finde Permutation
permut([], []).

permut([X|Ts], Rs):-
    permut(Ts, Ss),
    del(X, Rs, Ss).


