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
- [x] Przedwczesne zakończenie własnej. Nie interesuje nas czy się udało sprzedać czy nie
- [x] Zakończenie aukcji nie własnej (dla admina)
- [ ] Endpoint all-offers dla niezalogowanych pobiera wszystkie aktualne aukcje a dla zalogowanych aktualne i nieswoje.

## Ważne technologicznie

- Sprawdzenie admina przez uproszczony interface, pytając baze kim jest. Wolne ale w miare bezpieczne. Używać tylko jak potrzeba!

## Usówanie ogłoszeń

- Jak admin usówa ogłoszenie znaczy że prawdopodobnie jest nieodpowiednie, dlatego nie chcemy go w bazie
- Użytkownik usówając ogłoszenie ma powody ale zostawiamy w historii.
