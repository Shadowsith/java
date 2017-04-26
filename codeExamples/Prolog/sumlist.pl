%   Purpose: a short demonstration of logic programming
%   Author: FABR
%
%   Install GProlog:   apt-get install gprolog
%
%   Usage:
%       gprolog
%
%       > cut & paste definitions from below
%
%   Loading files:
%
%       [sumlist].
%

% Define a logical relation for the sum of a list of integers
%
% Relation (predicate): sumlist(List,PrefixSum,SumAll)
%
% Meaning:    sum(List) + PrefixSum = SumAll
%
%
% Some facts:
%
%   sumlist([1,2,3,4,5,6], 0,21).
%   sumlist([  2,3,4,5,6], 1,21).
%   sumlist([    3,4,5,6], 3,21).
%   sumlist([      4,5,6], 6,21).
%   sumlist([        5,6],10,21).
%   sumlist([          6],15,21).
%   sumlist([           ],21,21).
%
% Derivation:
%
% A basic fact:
%         sumlist([],S,S).
%   
% In order to prove
%         sumlist([1,2,3,4,5,6], 0,21)
%
% it is enough to prove
%         1 is  0+1
% and 
%         sumlist(  [2,3,4,5,6], 1,21)


% In PROLOG

sumlist([],S,S).

sumlist([Head|Tail],PrefixSum,SumAll) :-
	PrefixSum2 is PrefixSum + Head,
	sumlist(Tail,PrefixSum2,SumAll).

% Usage (query):
%
%     sumlist([1,2,3,4,5,6],0,21).
%     sumlist([1,2,3,4,5,6],0,42).
%     sumlist([1,2,3,4,5,6],0,Sum).
%
