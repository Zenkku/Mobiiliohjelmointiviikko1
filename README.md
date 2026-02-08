#TEHTÄVÄ LÖYTYY TAG WEEK3 TAKANTA VIIKKO1TEHTV SISÄLTÄ

Android Task App (Week 3)
Tämä projekti on mobiiliohjelmoinnin kurssin harjoitustyö, jota laajennetaan viikoittain. Sovellus on "To-Do List" -tyyppinen tehtävälista, joka on toteutettu moderneilla Android-työkaluilla (Kotlin & Jetpack Compose).

📅 Viikko 4: Navigointi ja Kalenterinäkymä Neljännellä viikolla sovellus muutettiin tukemaan useampaa näkymää hyödyntämällä Jetpack Navigation -kirjastoa. Sovellukseen luotiin uusi kalenterinäkymä tehtävien tarkasteluun.

🚀 Uudet ominaisuudet

Navigointi: Sovelluksessa on nyt selkeä siirtyminen listanäkymän (HomeScreen) ja kalenterinäkymän (CalendarScreen) välillä.

CalendarScreen: Tehtävät näytetään ryhmiteltynä päivämäärän (dueDate) mukaan, jolloin käyttäjä hahmottaa aikataulun paremmin.

Jaettu tila: Muokkaukset päivittyvät reaaliajassa molempiin näkymiin.

🧠 Tekninen toteutus

Navigation Compose (Single-Activity): NavHost ja NavController hallitsevat reittejä (routes), jolloin siirtymät ovat sujuvia ilman useita Activityjä.

Jaettu ViewModel: Sama TaskViewModel-instanssi jaetaan navigaation kautta molemmille näkymille. Tämä takaa sen, että StateFlow pitää datan synkronoituna: kun tehtävä lisätään "Home"-ruudussa, se näkyy heti myös "Calendar"-ruudussa.

Dialogit: Lisäys ja muokkaus (AlertDialog) on pidetty erillään navigaatiosta, jotta ne toimivat identtisesti molemmissa näkymissä.


📅 Viikko 3: MVVM-rakenne, StateFlow ja Dialogit
Kolmannella viikolla projekti laajennettiin noudattamaan puhdasta MVVM-arkkitehtuuria ja koodi organisoitiin omiin paketteihinsa. Käyttöliittymään lisättiin muokkausnäkymä dialogina.

🚀 Uudet ominaisuudet
Kerrosrakenne: Koodi on jaettu selkeästi paketteihin: model, view ja viewmodel.

DetailScreen (Dialog): Tehtävää klikkaamalla aukeaa dialogi, jossa tehtävää voi muokata tai sen voi poistaa. Erillistä navigaatiota ei tarvita.

Reaktiivisuus: Käyttöliittymä reagoi välittömästi ViewModelin tilan muutoksiin StateFlow:n avulla.

🧠 Tekninen toteutus
MVVM (Model-View-ViewModel) Composessa
Tällä viikolla arkkitehtuuri eriytettiin selkeästi. Miksi MVVM on hyödyllinen?

Vastuiden erottelu (Separation of Concerns): UI (View) vastaa vain piirtämisestä, kun taas logiikka ja tilanhallinta ovat ViewModelissa. Tämä pitää koodin siistinä.

Testattavuus: ViewModelia voidaan testata ilman emulaattoria tai UI-elementtejä.

Elinkaari (Lifecycle): ViewModel säilyy hengissä, vaikka laitetta käännetään (screen rotation), jolloin käyttäjän syöttämä data ei katoa. Composessa tämä on kriittistä, koska UI piirretään uudelleen (recomposition) usein.

Miten StateFlow toimii?
Viikolla 3 otettiin käyttöön StateFlow (tai MutableState) tilanhallinnassa.

StateFlow on Kotlinin Coroutines-kirjaston osa, joka toimii "kuumana virtana" (hot stream). Se pitää sisällään aina viimeisimmän tilan.

Käyttöliittymässä (UI) tilaa kuunnellaan collectAsState()-funktiolla.

Kun ViewModel päivittää listaa (esim. addTask), StateFlow lähettää tiedon automaattisesti kaikille kuuntelijoille, ja Compose päivittää vain ne osat ruudusta, jotka muuttuivat.

📅 Viikko 2: Ensimmäinen ViewModel ja State
Toisella viikolla projekti laajennettiin käyttämään alustavaa MVVM-mallia ja dynaamisempaa käyttöliittymää. Sovelluksen visuaalinen ilme päivitettiin vaaleanpunaiseksi teemaksi.

Ominaisuudet
ViewModel: Sovelluslogiikka siirrettiin TaskViewModel-luokkaan.

LazyColumn: Tehtävät näytetään suorituskykyisessä listassa.

Interaktiivisuus: Uuden tehtävän lisääminen, merkintä tehdyksi ja poistaminen.

Logiikka: Lajittelu (sortByDueDate) ja suodatus (filterByDone) ViewModelin kautta.

Compose-tilanhallinta
Tässä vaiheessa siirryttiin remember-muuttujista keskitettyyn tilanhallintaan. Jetpack Compose on reaktiivinen: kun data (State) muuttuu, käyttöliittymä piirretään automaattisesti uudelleen.

📅 Viikko 1: Kotlin-perusteet ja Compose (Historia)
Ensimmäisellä viikolla harjoiteltiin Kotlinin perusteita ja projektin pystytystä.

Opittiin: Luomaan data class, käsittelemään listoja ja rakentamaan ensimmäinen Column-pohjainen UI.

Logiikka: Toteutettiin perusfunktiot (addTask, toggleDone, filterByDone, sortByDueDate).

UI: Yksinkertainen HomeScreen, joka listasi mock-datan tekstiriveinä.
