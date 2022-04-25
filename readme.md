### (lek. 3) Struktura klas oraz pseudo klas

- Nowy Projekt maven
- IdeasApplication w odpowiednim pakiecie
- Klasy - model - Category, Question, Answer
    - konstruktory
    - relacje między nimi
- Pseudokod głównej logiki </br>

### (Lek. 4) Główna pętla programu oraz komunikacja z użytkownikiem

- Gółówna pętla programu: pętla (zmienna sterująca i obsługa wyjątków)
- UserInputManager, UserInputCommand - szkic
- Implementacja UserInputManager
- Implementacja UserInputCommand
- Wyświetlenie UserInputCommand w głównej pętli

### (lek. 5)  Testy aplikacji i TDD

- Dodanie JUnit5 do projektu
- Wygenerowanie testu dla UserInputCommand

### (lek. 6) Implementacja obsługi komend

- Pusta lista commandHandlerów
- Interfejs CommandHandler
- BaseCommandHandler
- QuiteCommandHandler
- Dodanie handlerów do listy i obsługa listy
    - optional
    - log nieznany command handler
    - break głównej pętli

### (Lek. 7) Komunikacja z systemem plików oraz utrwalenie danych

- CategoryCommandHandler - szablon
- Zapis do pliku, komunikacja z DAO, CategoryDAO
- Wypełnienie CategoryCommandHandler
- Category.toString

### (Lek. 8) Serializacja obiektów

- Copy CategoryDao to QuestionDao -> problem z serializacją
    - zależność jackson-databind
    - Refaktor QuestionDao z jackson (dodanie do projektu Jackson'a)

### (Lek. 9) Refaktoryzacja (wprowadzanie drobnych ulepszeń) kodu

- Wprowadzenie enum Action
    - Refaktor po wprowadzeniu UserInputCommand.action
- QuestionCommandHandler
    - dodanie CategoryDao i pobieranie kategorii
    - metoda CategoryDao.findOne
    - refaktor CategoryDao na jackson
    - Model.Question - default konstruktor dla jackson

### (Lek. 10) Logi, obsługa wyjątków oraz walidacja danych

- dodajemy loggery: do wszyskich klas
- AnswerCommandHandler <-- kopia z question
    - questionDao.findOne
    - questionDao.addAnswer
- walidacja parametrów: niepoprawne dane
    - spróbować wprowadzić nieprawidłowe dane
    - CategoryCommandHandler walidacja parametrów
    - główna pętla dodatkowy catch na walidację
