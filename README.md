# Android Task App (Week 2) 

#TEHTÄVÄ LÖYTYY TAG WEEK22 TAKANTA VIIKKO1TEHTV SISÄLTÄ

Tämä projekti on mobiiliohjelmoinnin kurssin harjoitustyö, jota laajennetaan viikoittain.

## 📅 Viikko 2: MVVM, ViewModel ja State

Toisella viikolla projekti laajennettiin käyttämään **MVVM-arkkitehtuuria** (Model-View-ViewModel) ja dynaamisempaa käyttöliittymää. Sovelluksen visuaalinen ilme päivitettiin **vaaleanpunaiseksi teemaksi**.

### 🚀 Uudet ominaisuudet
* **ViewModel:** Sovelluslogiikka on siirretty `TaskViewModel`-luokkaan.
* **LazyColumn:** Tehtävät näytetään suorituskykyisessä listassa.
* **Interaktiivisuus:**
    * Uuden tehtävän lisääminen (TextField + Button).
    * Tehtävän merkintä tehdyksi (Checkbox).
    * Tehtävän poistaminen listalta.
* **Logiikka:** Lajittelu (`sortByDueDate`) ja suodatus (`filterByDone`) toimivat nyt ViewModelin kautta.

### 🧠 Tekninen toteutus: Compose-tilanhallinta

Tässä vaiheessa siirryttiin `remember`-muuttujista keskitettyyn tilanhallintaan.

**Miten Compose-tilanhallinta toimii?**
Jetpack Compose on reaktiivinen: kun data (State) muuttuu, käyttöliittymä piirretään automaattisesti uudelleen (*recomposition*). UI tarkkailee ViewModelissa olevaa tilaa (esim. `MutableState<List<Task>>`), ja kun listaan lisätään alkio, näkymä päivittyy ilman manuaalista kutsua.

**Miksi ViewModel on parempi kuin pelkkä `remember`?**
1.  **Elinkaari (Lifecycle):** `remember` pitää tiedon tallessa vain niin kauan kuin komponentti on ruudulla. ViewModel säilyy muistissa myös konfiguraatiomuutosten (kuten näytön kääntämisen) yli, joten käyttäjän syöttämä data ei katoa.
2.  **Koodin vastuut:** ViewModel erottaa bisneslogiikan (kuten tietokantakutsut tai datan käsittelyn) käyttöliittymäkoodista (UI). Tämä tekee koodista selkeämpää ja helpommin testattavaa.

---

## 📅 Viikko 1: Kotlin-perusteet ja Compose (Historia)

Ensimmäisellä viikolla harjoiteltiin Kotlinin perusteita ja projektin pystytystä.
* **Opittiin:** Luomaan `data class`, käsittelemään listoja ja rakentamaan ensimmäinen `Column`-pohjainen UI.
* **Logiikka:** Toteutettiin perusfunktiot (`addTask`, `toggleDone`, `filterByDone`, `sortByDueDate`).
* **UI:** Yksinkertainen `HomeScreen`, joka listasi mock-datan tekstiriveinä.
