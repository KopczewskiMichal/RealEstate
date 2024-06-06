# Pomysł na dalsze prace

## Sposób insertu do bazy

- Ufamy że obecnie nie ma tej oferty w systemie
- Tylko zalogowani na jakąkolwiek rolę mogą dodawać ogłoszenia.
- 

## Po dodaniu oferty
- Sprawdzamy czy jest w bazie dana osoba, jeśli nie to dodajemy wraz z foto itp. Jeśli tak sprawdzamy czy nie należy zaktualizować.

## Potrzebne endpointy

- [x] Dodanie oferty
- [x] Pobranie ofert (dla wszystkich)
- [x] Pobranie wszystkich również historycznych stworzonych przez użytkownika, po jego danych
- [ ] Przedwczesne zakończenie (positive/negatve jako requestParam. Możliwe tylko dla twórcy, sprawdzenie po bazie danych. Zapisanie do bazy czy się udało czy nie)
- [ ] Zakończenie aukcji nie własnej (dla admina)
- [ ] Modyfikacj aukcji robić mi się nie chce, ale jak zostanie czas to zrobię.

## Ważne technologicznie

- Sprawdzenie admina przez uproszczoną funkcję, pytając baze kim jest. Wolne ale w miare bezpieczne. Używać tylko jak potrzeba!