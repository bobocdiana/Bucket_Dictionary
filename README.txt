Boboc Diana-Andreea 322CA

In rezolvarea temei am implementat interfata MyHashMap in clasa 	MyHashMapImpl.Aceasta clasa reprezinta un HashMap clasic, in care metoda put(cheie,valoare) adauga tuplet-ul daca cheia nu exista in hashmap,iar in cazul in care aceasta exista ii actualizeaza valoarea cu cea noua data ca parametru si o intoarce pe cea veche.

Clasa Dictionary reprezinta intregul dictionar si contine cuvinte,definitiile cuvintelor si sinonimele lor (=> are un camp de tip DisjointSets si mosteneste clasa MyHashMapImpl).Am suprascris metoda put(cheie,valoare) din clasa MyHashMapImpl pentru a retine ptr o cheie mai multe valori distincte.Astfel, in clasa Dictionary am implementat metoda de adaugare a unui cuvant si a definitiei lui in hashmap (in aceasta metoda am adaugat si cuvantul in DisjointSets),metoda de adaugare a unei perechi de sinonime in DisjointSets,metoda de numarare a definitiilor unui cuvant si metoda de returnare a tuturor sinonimelor (ordonati alfabetic) ai unui cuvant.

In clasa Main am implementat doar citirea fisierului de configurare (implicit formarea dictionarului pe baza configuratiilor) si citirea fisierului de query-uri si apelarea metodelor specifice pentru raspunsul la query (implicit formarea fisierului de iesire).

Analiza:
Pentru HMAX=1 timpul de rulare este de 1200.
Pentru HMAX=10 timpul de rulare este de 270.
Pentru HMAX=100 timpul de rulare este de 170.
Concluzie:
Timpul de rulare creste o data cu scaderea numarului maxim de bucket-uri din cauza faptului ca pentru fiecare operatie de get(),put(),getNumerOfDefinitions() este parcurs un bucket din ce in ce mai mare(din cauza numarului marit de coliziuni).