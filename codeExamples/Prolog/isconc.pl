/*
Wie kann man logisch beschreiben, was es bedeutet, zwei Listen zu konkatenieren?

Eigenschaft: isconc(L1, L2, Conc)

Die Eigenschaft soll gelten, wenn Anhängen der Liste L2
an die Liste L1 die Liste Conc ergibt

Beispiele:

isconc([1,2,3],[4],[1,2,3,4])  trifft zu
isconc([1,2],[3,4],[1,2,3,4])  trifft zu
isconc([2],[6,7],[2,3,4])      trifft nicht zu
isconc([],[3,4],[3,4])         trifft zu
...

Problem: 
Finde allgemeine Beschreibung des logischen Zusammenhangs.
Aufzählung endlich vieler konkreter Beispiele reicht nicht.

#=================================================================
Herleitung:

#--------------
Einfacher Fall:
#--------------

Wenn L1 eine leere Liste ist, dann besteht die konkatenierte Liste
lediglich aus L2.
Das ist ein Fakt, der für beliebige Listen L2 gilt.

isconc([],L2,L2) 

#-----------------------------------------------------------
Rückführung eines allgemeinen Falls auf den einfachen Fall.
#-----------------------------------------------------------

Notation für Listen:   [X|L]
bedeutet: füge Element X vorne an Liste L an.

Regel
-----

Wenn gilt:              isconc(L1,L2,Conc)
Dann gilt sicher auch:  isconc([X|L1], L2, [X|Conc])

Regel umgekehrt formuliert:

Um zu zeigen, dass gilt: isconc([X|L1], L2, [X|Conc])
reicht es zu zeigen:     isconc(L1,L2,Conc)

Kurz:

isconc([X|L1], L2, [X|Conc])  <--- isconc(L1,L2,Conc)


Mehrfache Andwendung die Regel reduziert jedes allgemeine Problem
        isconc(L1,L2,Conc)
nach endlich vielen Schritten auf den einfachen Fall
        isconc([],L2,L2)

*/




% In PROLOG statt <--- Notation :-

isconc([],L2,L2).

isconc([X|L1],L2,[X|Conc]):-
        isconc(L1,L2,Conc).






/* Ausprobieren

Laden:  [isconc].

Konkrete Fälle prüfen:

isconc([1,2,3],[4],[1,2,3,4]).
isconc([1,2],[3,4],[1,2,3,4]).
isconc([2],[6,7],[2,3,4]).
isconc([],[3,4],[3,4]).

Aufrufe nach dem üblichen E/A-Denken 

isconc([1,2],[3,4],Conc).
isconc([1,2,3],[4,5],Conc).


Und nun neue Möglichkeiten, die PROLOG eröffnet (cool stuff):

isconc(X,[3,4],[1,2,3,4]).
isconc(X,Y,[1,2,3,4]).

isconc([1,2],X,[1,2,3,4]).
isconc([X,2],[3,Y],[1,2,3,4]).
isconc([1|[2|X]],Y,[1,2,3,4,5,6]).

*/
