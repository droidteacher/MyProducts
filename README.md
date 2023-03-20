## My Products

Android alkalmazás, amely implementálja az adatbázis CRUD műveleteket

### Felhasznált technológiák, library-k, best practice-ek

* Programnyelv: Kotlin
* Architektúra: MVVM 
* Dependency Injection: Koin
* Perzisztencia: SQLite, Room
* Reaktívitás és szálkezelés: RxJava, RxAndroid
* UI komponensek: Jetpack Compose
* Tesztek, mockolás: JUnit, MockK

### Működés rövid leírása

Az alkalmazás egyetlen `Activity`-t használ, amely felelős a képernyők renderelésért. A felhasználói felület elemeit Jetpack Compose `@Composable` függvények definiálják.

Kezdetben egy üres lokális adatbázissal indul el az alkalmazás. A felhasználó tetszőleges termékeket tud felvinni az adatbázisba egy egyszerű formon keresztül. A felvitt termékek megtekinthetők egy lista nézeten.

A lista nézet elemein klikkelve lehetőség van a termék tulajdonságait szerkeszteni (név, leírás, darabszám), illetve kitörölni a terméket az adatbázisból.

#### Főbb UI elemek

* Tab bar: a navigációt valósítja meg a _Create_ és a _Read_ képernyők között. Implementáció: `BottomTabs.kt`
* Új termék felvitele: form 3 beviteli mezővel, felhasználói bevitel validációjával, hiba visszejelzéssel. Implementáció: `TabContentCreate.kt`
* Meglévő termék szerkesztése: a termék felviteli form teljes újrahasznosításával, előre kitöltött mezőkkel. Implementáció: `TabContentCreate.kt`
* Listás nézet: az adatbázisban lévő összes termék scrollozható listája. Az egyes elemek `Card` komponensen jelennek meg. Implementáció: `ProductList.kt`

### Tesztelés

* UI tesztek: az egyes `@Composable` felületi elem darabkák izolált tesztje a Jetpack Compose könyvtár által biztosított _semantic tree_ használatával. Implementáció: `ProductListTest.kt`
* Üzleti logika tesztje: a perzisztencia réteghez való fordulások illetve a felhasználói bevitelre történő reagálás tesztesetei. Implementáció: `CRUDViewModelTest.kt`









