%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Einsteins Raetsel                                           (show on 29 rows)
%
% 1) Es gibt fünf Häuser mit je einer anderen Farbe
% 2) In jedem Haus wohnt eine Person einer anderen Nationalität
% 3) Jeder Hausbewohner bevorzugt ein bestimmtes Getränk, raucht eine bestimmte Zigarettenmarke
%    und hält ein bestimmtes Haustier
% 4) Keine der 5 Personen bevorzugt das gleiche Getränk, raucht die gleiche Zigarettenmarke
%    oder hält das gleiche Tier wie irgend eine der anderen Personen
% 5) Der Brite lebt im roten Haus
% 6) Der Schwede hält einen Hund
% 7) Der Däne trinkt gerne Tee
% 8) Der Deutsche raucht Rothmanns
% 9) Der Besitzer des grünen Hauses trinkt Kaffee
% 10)Der Winfield-Raucher trinkt gerne Bier
% 11)Der Norweger wohnt im ersten Haus
% 12)Der Norweger wohnt neben dem blauen Haus
% 13)Der Besitzer des gelben Hauses raucht Dunhill
% 14)Die Person, die Pall Mall raucht, hält einen Vogel
% 15)Der Mann, der im mittleren Haus wohnt, trinkt Milch
% 16)Das grüne Haus steht unmittelbar links vom weißen Haus
% 17)Der Marlboro-Raucher wohnt neben dem, der eine Katze hält
% 18)Der Marlboro-Raucher hat einen Nachbarn, der Wasser trinkt
% 19)Der Mann, der ein Pferd hält, wohnt neben dem, der Dunhill raucht
% 
% Frage: wer hält einen Fisch als Haustier?
%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Aus Aufgabenstellung abgeleitete Traegermengen
%
% Hausfarben: rot, gruen, blau, gelb, weiss
% Nationen: norweger, schwede, daene, brite, deutscher
% Getraenke: tee, bier, wasser, kaffee, milch
% Tiere: hund, vogel, katze, pferd, fisch
% Zigaretten: rothmanns, winfield, dunhill, pallmall, marlboro
%
%
% Modellierung durch Listen
%
% Kodierung einer Hausbelegung: Liste der Laenge 5 bestehend aus Atomen
%
%  [Farbe, Nation, Getraenk, Tier, Zigaretten]
%
% Kodierung einer moeglichen Belegung fuer alle Haeuser:
%  Liste der Laenge 5 bestehend aus Hausbelegungen
%
%  [
%    [Farbe1, Nation1, Getraenk1, Tier1, Zigaretten1],  <-- Haus1
%    [Farbe2, Nation2, Getraenk2, Tier2, Zigaretten2],  <-- Haus2
%    [Farbe3, Nation3, Getraenk3, Tier3, Zigaretten3],  <-- Haus3
%    [Farbe4, Nation4, Getraenk4, Tier4, Zigaretten4],  <-- Haus4
%    [Farbe5, Nation5, Getraenk5, Tier5, Zigaretten5],  <-- Haus5
%  ]
%


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Laden und Aufruf:
%
% gprolog
% | ?- [einstein].            <--- Regelbasis einlesen (Consult)
%
% | ?- loesung(Hs).           <--- Loesung berechnen lassen
%
%
% Ausgabe:
%
% Hs = [[gelb,norweger,wasser,katze,dunhill],
%       [blau,daene,tee,pferd,marlboro],
%       [rot,brite,milch,vogel,pallmall],
%       [gruen,deutscher,kaffee,fisch,rothmanns],
%       [weiss,schwede,bier,hund,winfield]
%       ] ? ;
% 
% (120 ms) no
%








%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Contraints 1-4
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Belege jedes Haus mit einer Farbe
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
belegeFarben(Hs) :-
    belegeFarben(Hs, [rot, gruen, blau, gelb, weiss]).

% Belege jedes Haus in der Liste H = [H1|Hs] mit einer Farbe aus der Liste Fs
belegeFarben([H1,H2,H3,H4,H5], Fs) :-
    permutation(Fs,[F1,F2,F3,F4,F5]),
    H1 = [F1,_,_,_,_],
    H2 = [F2,_,_,_,_],
    H3 = [F3,_,_,_,_],
    H4 = [F4,_,_,_,_],
    H5 = [F5,_,_,_,_].










%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Belege jedes Haus mit einer Nation
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
belegeNationen(Hs) :-
    belegeNationen(Hs, [norweger, schwede, daene, brite, deutscher]).

% Belege jedes Haus in der Liste H = [H1|Hs] mit einer Nation aus der Liste Ns
belegeNationen([H1,H2,H3,H4,H5], Ns) :-
    permutation(Ns,[N1,N2,N3,N4,N5]),
    H1 = [_,N1,_,_,_],
    H2 = [_,N2,_,_,_],
    H3 = [_,N3,_,_,_],
    H4 = [_,N4,_,_,_],
    H5 = [_,N5,_,_,_].

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Belege jedes Haus mit einem Getraenk
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
belegeGetraenke(Hs) :-
    belegeGetraenke(Hs, [tee, bier, wasser, kaffee, milch]).

% Belege jedes Haus in der Liste H = [H1|Hs] mit einem Getraenk aus der Liste Gs
belegeGetraenke([H1,H2,H3,H4,H5], Gs) :-
    permutation(Gs,[G1,G2,G3,G4,G5]),
    H1 = [_,_,G1,_,_],
    H2 = [_,_,G2,_,_],
    H3 = [_,_,G3,_,_],
    H4 = [_,_,G4,_,_],
    H5 = [_,_,G5,_,_].

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Belege jedes Haus mit einem Tier
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
belegeTiere(Hs) :-
    belegeTiere(Hs, [hund, vogel, katze, pferd, fisch]).

% Belege jedes Haus in der Liste H = [H1|Hs] mit einem Tier aus der Liste Ts
belegeTiere([H1,H2,H3,H4,H5], Ts) :-
    permutation(Ts,[T1,T2,T3,T4,T5]),
    H1 = [_,_,_,T1,_],
    H2 = [_,_,_,T2,_],
    H3 = [_,_,_,T3,_],
    H4 = [_,_,_,T4,_],
    H5 = [_,_,_,T5,_].

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Belege jedes Haus mit einer Zigarettenmarke
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
belegeZigaretten(Hs) :-
    belegeZigaretten(Hs, [rothmanns, winfield, dunhill, pallmall, marlboro]).

% Belege jedes Haus in der Liste H = [H1|Hs] mit einem Tier aus der Liste Zs
belegeZigaretten([H1,H2,H3,H4,H5], Zs) :-
    permutation(Zs,[Z1,Z2,Z3,Z4,Z5]),
    H1 = [_,_,_,_,Z1],
    H2 = [_,_,_,_,Z2],
    H3 = [_,_,_,_,Z3],
    H4 = [_,_,_,_,Z4],
    H5 = [_,_,_,_,Z5].

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 5: Der Brite lebt im roten Haus
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint05([]).
constraint05([[rot,brite,_,_,_]|_Hs]) :- !.
constraint05([[rot,_N,_,_,_]|_Hs]) :- !,fail.
constraint05([[_C,brite,_,_,_]|_Hs]) :- !,fail.
constraint05([[_C,_N,_,_,_]|Hs]) :- constraint05(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 6: Der Schwede hält einen Hund
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint06([]).
constraint06([[_,schwede,_,hund,_]|_Hs]) :- !.
constraint06([[_,_N,_,hund,_]|_Hs]) :- !,fail.
constraint06([[_,schwede,_,_T,_]|_Hs]) :- !,fail.
constraint06([[_,_N,_,_T,_]|Hs]) :- constraint06(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 7: Der Däne trinkt gerne Tee
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint07([]).
constraint07([[_,daene,tee,_,_]|_Hs]) :- !.
constraint07([[_,_N,tee,_,_]|_Hs]) :- !,fail.
constraint07([[_,daene,_G,_,_]|_Hs]) :- !,fail.
constraint07([[_,_N,_G,_,_]|Hs]) :- constraint07(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 8: Der Deutsche raucht Rothmanns
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint08([]).
constraint08([[_,deutscher,_,_,rothmanns]|_Hs]) :- !.
constraint08([[_,_N,_,_,rothmanns]|_Hs]) :- !,fail.
constraint08([[_,deutscher,_,_,_Z]|_Hs]) :- !,fail.
constraint08([[_,_N,_,_,_Z]|Hs]) :- constraint08(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 9: Der Besitzer des grünen Hauses trinkt Kaffee
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint09([]).
constraint09([[gruen,_,kaffee,_,_]|_Hs]) :- !.
constraint09([[_F,_,kaffee,_,_]|_Hs]) :- !,fail.
constraint09([[gruen,_,_G,_,_]|_Hs]) :- !,fail.
constraint09([[_F,_,_G,_,_]|Hs]) :- constraint09(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 10: Der Winfield-Raucher trinkt gerne Bier
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint10([]).
constraint10([[_,_,bier,_,winfield]|_Hs]) :- !.
constraint10([[_,_,bier,_,_Z]|_Hs]) :- !,fail.
constraint10([[_,_,_G,_,winfield]|_Hs]) :- !,fail.
constraint10([[_,_,_G,_,_Z]|Hs]) :- constraint10(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 11: Der Norweger wohnt im ersten Haus
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint11([[_,norweger,_,_,_],_H2,_H3,_H4,_H5]) :- !.
constraint11(_) :- !,fail.


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 12: Der Norweger wohnt neben dem blauen Haus
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint12([]).
constraint12([[_F,norweger,_,_,_],[blau,_N,_,_,_]|_Hs]) :- !.
constraint12([[blau,_N,_,_,_],[_F,norweger,_,_,_]|_Hs]) :- !.
constraint12([[_F,norweger,_,_,_]|_Hs]) :- !,fail.
constraint12([[blau,_,_,_,_]|_Hs]) :- !,fail.
constraint12([[_C,_N,_,_,_]|Hs]) :- constraint12(Hs).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 13: Der Besitzer des gelben Hauses raucht Dunhill
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint13([]).
constraint13([[gelb,_,_,_,dunhill]|_Hs]) :- !.
constraint13([[_F,_,_,_,dunhill]|_Hs]) :- !,fail.
constraint13([[gelb,_,_,_,_Z]|_Hs]) :- !,fail.
constraint13([[_F,_,_,_,_Z]|Hs]) :- constraint13(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 14: Die Person, die Pall Mall raucht, hält einen Vogel
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint14([]).
constraint14([[_,_,_,vogel,pallmall]|_Hs]) :- !.
constraint14([[_,_,_,_T,pallmall]|_Hs]) :- !,fail.
constraint14([[_,_,_,vogel,_Z]|_Hs]) :- !,fail.
constraint14([[_,_,_,_T,_Z]|Hs]) :- constraint14(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 15: Der Mann, der im mittleren Haus wohnt, trinkt Milch
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint15([_H1,_H2,[_,_,milch,_,_],_H4,_H5]).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 16: Das grüne Haus steht unmittelbar links vom weißen Haus
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint16([]).
constraint16([[gruen,_,_,_,_],[weiss,_,_,_,_]|_Hs]) :- !.
constraint16([[gruen,_,_,_,_],[_F,_,_,_,_]|_Hs]) :- !,fail.
constraint16([[_F,_,_,_,_],[weiss,_,_,_,_]|_Hs]) :- !,fail.
constraint16([[weiss,_,_,_,_]|_Hs]) :- !,fail.
constraint16([[_F,_,_,_,_]|Hs]) :- constraint16(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 17: Der Marlboro-Raucher wohnt neben dem, der eine Katze hält
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint17([]).
constraint17([[_,_,_,katze,_Z],[_,_,_,_T,marlboro]|_Hs]) :- !.
constraint17([[_,_,_,_T,marlboro],[_,_,_,katze,_Z]|_Hs]) :- !.
constraint17([[_,_,_,katze,_Z]|_Hs]) :- !,fail.
constraint17([[_,_,_,_T,marlboro]|_Hs]) :- !,fail.
constraint17([[_,_,_,_T,_Z]|Hs]) :- constraint17(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 18: Der Marlboro-Raucher hat einen Nachbarn, der Wasser trinkt
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint18([]).
constraint18([[_,_,wasser,_,_Z],[_,_,_G,_,marlboro]|_Hs]) :- !.
constraint18([[_,_,_G,_,marlboro],[_,_,wasser,_,_Z]|_Hs]) :- !.
constraint18([[_,_,wasser,_,_Z]|_Hs]) :- !,fail.
constraint18([[_,_,_G,_,marlboro]|_Hs]) :- !,fail.
constraint18([[_,_,_G,_,_Z]|Hs]) :- constraint18(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Constraint 19: Der Mann, der ein Pferd hält, wohnt neben dem, der Dunhill raucht
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

constraint19([]).
constraint19([[_,_,_,pferd,_Z],[_,_,_,_T,dunhill]|_Hs]) :- !.
constraint19([[_,_,_,_T,dunhill],[_,_,_,pferd,_Z]|_Hs]) :- !.
constraint19([[_,_,_,pferd,_Z]|_Hs]) :- !,fail.
constraint19([[_,_,_,_T,dunhill]|_Hs]) :- !,fail.
constraint19([[_,_,_,_T,_Z]|Hs]) :- constraint19(Hs).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Praedikat zur Loesung des eines Teils des Raetsels
% Nicht alle Constraints werden benutzt
% findall(Hs,loese_partiell(Hs),_S), length(_S,X).
loese_partiell(Hs) :-
    belegeNationen(Hs),
    constraint11(Hs),

    belegeFarben(Hs),
    constraint12(Hs),
    constraint16(Hs),
    constraint05(Hs),

    belegeTiere(Hs),
    constraint06(Hs),

    belegeGetraenke(Hs),
    constraint15(Hs),
    constraint07(Hs),
    constraint09(Hs),

    belegeZigaretten(Hs).
%    constraint08(Hs),
%    constraint10(Hs),
%    constraint13(Hs),
%    constraint14(Hs),
%    constraint17(Hs),
%    constraint18(Hs),
%    constraint19(Hs).




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Praedikat zur Loesung des ganzen Raetsels
% Ohne Optimierung
loesung0(Hs) :-
    belegeNationen(Hs),
    belegeFarben(Hs),
    belegeTiere(Hs),
    belegeGetraenke(Hs),
    belegeZigaretten(Hs),

    constraint11(Hs),
    constraint12(Hs),
    constraint16(Hs),
    constraint05(Hs),
    constraint06(Hs),
    constraint15(Hs),
    constraint07(Hs),
    constraint09(Hs),
    constraint08(Hs),
    constraint10(Hs),
    constraint13(Hs),
    constraint14(Hs),
    constraint17(Hs),
    constraint18(Hs),
    constraint19(Hs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Praedikat zur Loesung des ganzen Raetsels
loesung(Hs) :-
    belegeNationen(Hs),
    constraint11(Hs),

    belegeFarben(Hs),
    constraint12(Hs),
    constraint16(Hs),
    constraint05(Hs),

    belegeTiere(Hs),
    constraint06(Hs),

    belegeGetraenke(Hs),
    constraint15(Hs),
    constraint07(Hs),
    constraint09(Hs),

    belegeZigaretten(Hs),
    constraint08(Hs),
    constraint10(Hs),
    constraint13(Hs),
    constraint14(Hs),
    constraint17(Hs),
    constraint18(Hs),
    constraint19(Hs).

