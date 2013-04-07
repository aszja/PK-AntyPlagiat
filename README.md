PK-AntyPlagiat
==============

PK-AntyPlagiat

Zmienilem strukture projektu na java'owa.
Do tego zmienilem projekt na maven'owy - latwiejsze zarzadzanie jar'ami.
Dodalem obsluge baze danych (H2 - plikowa baza).
Stworzylem 3 tabele - strukture mozna podgladnaz w pl.pk.antyplagiat.domain.*
Do 3 sa porobione DAO z podstawowymi metodami (save, delete, find).
Przykladowe uzycie dao w src/main/test.
W przypadku dodawania nowych metod w dao, nalezy pamietac o rozpoczeciu transakcji i zacommitowaniu jej.
Do tego kilak prostych testow sprawdzajacych czy hibernate dziala prawidlowo.

Pytania, RFC kierowac na gg (dostepne na forum).
