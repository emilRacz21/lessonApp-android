package com.example.bar;

public class LanguageVocabulary {

    String[] actionBarTitle = new String[5];
    String[] options = new String[2];
    String[] home = new String[5];
    String[] languageChoose = new String[5];
    String[] spinnerChoose = new String[1];
    String[] editProfileLang = new String[10];
    String[] menu = new String[3];
    String[] menuBottom = new String[5];
    String[] toastMessage = new String[2];
    String[] database = new String[2];
    String[] dialog = new String[4];
    String[] seasons = new String[12];
    String[] daysOfWeek = new String[5];
    String[] formData = new String[6];
    int[] flag = {R.drawable.flag_poland, R.drawable.flag_germany, R.drawable.flag_united_kingdom, R.drawable.flag_france, R.drawable.flag_russia, R.drawable.flag_spain};
    void setPolish(){
        spinnerChoose = new String[]{"Wybierz grupę","1A", "1B", "1C", "1D", "2A", "2B", "2C", "2D", "3A", "3B", "3C", "3D"};
        languageChoose =new String[]{"Polski", "Niemiecki", "Angielski", "Francuski","Rosyjski","Hiszpański"};
        home = new String[]{" DZISIEJSZE ZAJĘCIA ", "POPRZEDNI", "NASTĘPNY","Brak zajęć w podanym terminie", "ZOBACZ WSZYTSKIE ZAJĘCIA"};
        options = new String[]{"Wybierz opcje", "Wybierz język "};
        actionBarTitle = new String[]{" Strona główna ", " Zajęcia ", " Oceny ", " Edytuj profil ", " Zmień język ", " Więcej "};
        editProfileLang = new String[]{"Twój profil","Twój login", "Imię", "Nazwisko", "Telefon", "Dodatkowe informacje", "Hasło", "Powtórz hasło", "ZASTOSUJ", "USUŃ"};
        menu = new String[]{"Edytuj profil", "Zmień język", "Wyloguj się"};
        menuBottom = new String[]{"Zajęcia", "Oceny", "Więcej","Lista realizacji zajęć", "Brak elementów do wyświetlenia"};
        toastMessage = new String[]{"Uzupełnij wszystkie pola!", "Podaj takie same hasła!"};
        database = new String[]{"Login", "Imię", "Nazwisko", "Telefon", "Informacje", "Hasło", "Powtórzone hasło"};
        dialog = new String[]{"Uwaga","Czy jesteś pewny?","Tak","Nie"};
        seasons = new String[]{"STYCZEŃ", "LUTY", "MARZEC", "KWIECIEŃ", "MAJ", "CZERWIEC", "LIPIEC", "SIERPIEŃ", "WRZESIEŃ", "PAŹDZIERNIK", "LISTOPAD", "GRUDZIEŃ"};
        daysOfWeek = new String[] {"PON", "WTO", "ŚRO", "CZW", "PT"};
        formData = new String[]{"DODAJ ZAJĘCIA", "Wpisz nazwę szkoły", "Godzina rozpoczęcia zajęć", "Godzina zakończenia zajęć", "AKCEPTUJ", "ANULUJ"};
    }
    void setEnglish(){
        spinnerChoose = new String[]{"Select group","1A", "1B", "1C", "1D", "2A", "2B", "2C", "2D", "3A", "3B", "3C", "3D"};
        languageChoose = new String[]{"Polish", "German", "English", "French", "Russian", "Spain"};
        home = new String[]{"TODAY'S CLASSES", "PREVIOUS", "NEXT", "No classes in the given term", "SEE ALL CLASSES"};
        options = new String[]{" Choose options ", "Choose language "};
        actionBarTitle = new String[]{" Home ", " Lesson ", " Grades ", " change profile ", " change language ", " More options "};
        editProfileLang = new String[]{"Your profile", "Your Username", "First Name", "Last Name", "Phone", "Additional Information", "Password", "Repeat Password", "APPLY", "DELETE"};
        menu = new String[]{"Edit Profile", "Change Language", "Log Out"};
        menuBottom = new String[]{"Classes", "Grades", "More", "List of Classes Achievements", "No items to display"};
        toastMessage = new String[]{"Fill in all fields!", "Enter the same passwords!"};
        database = new String[]{"Login", "First Name", "Last Name", "Phone", "Information", "Password", "Repeated Password"};
        dialog = new String[]{"Warning","Are you sure?","Yes","No"};
        seasons = new String[]{"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
        daysOfWeek = new String[] {"MON", "TUE", "WED", "THU", "FRI"};
        formData = new String[]{"ADD CLASSES", "Enter school name", "Class start time", "Class end time", "ACCEPT", "CANCEL"};
    }
    void setGerman(){
        spinnerChoose = new String[]{"Gruppe auswählen","1A", "1B", "1C", "1D", "2A", "2B", "2C", "2D", "3A", "3B", "3C", "3D"};
        languageChoose = new String[]{"Polnisch", "Deutsch", "Englisch", "Französisch", "Russisch", "Spanisch"};
        home = new String[]{"HEUTIGER UNTERRICHT", "VORHERIGE", "NÄCHSTE", "Kein Unterricht im angegebenen Zeitraum", "ALLE KURSE ANZEIGEN"};
        options = new String[]{"Optionen auswählen", "Sprache wählen"};
        actionBarTitle = new String[]{" Startseite ", " Kurse ", " Noten ", " Profil bearbeiten ", " Sprache ändern ", " Mehr "};
        editProfileLang = new String[]{"Informationen", "Dein Benutzername", "Vorname", "Nachname", "Telefon", "Zusätzliche Informationen", "Passwort", "Passwort wiederholen", "ANWENDEN", "LÖSCHEN"};
        menu = new String[]{"Profil bearbeiten", "Sprache ändern", "Abmelden"};
        menuBottom = new String[]{"Unterricht", "Noten", "Mehr", "Der abgeschlossenen Kurse", "Keine Elemente zum Anzeigen"};
        toastMessage = new String[]{"Füllen Sie alle Felder aus!", "Geben Sie dieselben Passwörter ein!"};
        database = new String[]{"Login", "Vorname", "Nachname", "Telefon", "Informationen", "Passwort", "Wiederholtes Passwort"};
        dialog = new String[]{"Achtung","Bist du sicher?","Ja","Nein"};
        seasons = new String[]{"JANUAR", "FEBRUAR", "MÄRZ", "APRIL", "MAI", "JUNI", "JULI", "AUGUST", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DEZEMBER"};
        daysOfWeek = new String[] {"MO", "DI", "MI", "DO", "FR"};
        formData = new String[]{"UNTERRICHT HINZUFÜGEN", "Schulname eingeben", "Unterrichtsbeginnzeit", "Unterrichtsendzeit", "AKZEPTIEREN", "ABBRECHEN"};
    }
    void setFrench(){
        spinnerChoose = new String[]{"Choisissez un groupe", "1A", "1B", "1C", "1D", "2A", "2B", "2C", "2D", "3A", "3B", "3C", "3D"};
        languageChoose = new String[]{"Polonais", "Allemand", "Anglais", "Français", "Russe", "Espagnol"};
        home = new String[]{" COURS D'AUJOURD'HUI ", "PRÉCÉDENT", "SUIVANT", "Aucun cours à la date spécifiée", "VOIR TOUS LES COURS"};
        options = new String[]{"Choisissez une option", "Une langue "};
        actionBarTitle = new String[]{" Page d'accueil ", " Cours ", " Notes ", " Modifier le profil ", " Changer de langue ", " Plus "};
        editProfileLang = new String[]{"Informations", "Votre nom d'utilisateur", "Prénom", "Nom de famille", "Téléphone", "Informations supplémentaires", "Mot de passe", "Répéter le mot de passe", "APPLIQUER", "ANNULER"};
        menu = new String[]{"Modifier le profil", "Changer de langue", "Déconnexion"};
        menuBottom = new String[]{"Cours", "Notes", "Plus", "Liste des cours terminés", "Aucun élément à afficher"};
        toastMessage = new String[]{"Remplissez tous les champs !", "Entrez les mêmes mots de passe !"};
        database = new String[]{"Titre", "Auteur"};
        database = new String[]{"Login", "Prénom", "Nom de famille", "Téléphone", "Informations", "Mot de passe", "Mot de passe répété"};
        dialog = new String[]{"Attention", "Êtes-vous sûr ?", "Oui", "Non"};
        seasons = new String[]{ "JANVIER", "FÉVRIER", "MARS", "AVRIL", "MAI", "JUIN", "JUILLET", "AOÛT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DÉCEMBRE"};
        daysOfWeek = new String[] {"LUN", "MAR", "MER", "JEU", "VEN"};
        formData = new String[]{"AJOUTER DES COURS", "Entrez le nom de l'école", "Heure de début des cours", "Heure de fin des cours", "ACCEPTER", "ANNULER"};
    }
    void setRussian(){
        spinnerChoose = new String[]{"Выберите группу","1А", "1Б", "1В", "1Г", "2А", "2Б", "2В", "2Г", "3А", "3Б", "3В", "3Г"};
        languageChoose = new String[]{"Польский", "Немецкий", "Английский", "Французский","Русский","Испанский"};
        home = new String[]{" ЗАНЯТИЯ СЕГОДНЯ ", "ПРЕДЫДУЩИЕ", "СЛЕДУЮЩИЕ","Нет занятий на указанную дату", "ПОСМОТРЕТЬ ВСЕ ЗАНЯТИЯ"};
        options = new String[]{"Выберите опцию", "Выберите язык "};
        actionBarTitle = new String[]{" Главная страница ", " Занятия ", " Оценки ", " Редактировать профиль ", " Изменить язык ", " Еще "};
        editProfileLang = new String[]{"информация","Ваш логин", "Имя", "Фамилия", "Телефон", "Дополнительная информация", "Пароль", "Повторите пароль", "ПРИМЕНИТЬ", "ОТМЕНА"};
        menu = new String[]{"Редактировать профиль", "Изменить язык", "Выйти"};
        menuBottom = new String[]{"Занятия", "Оценки", "Ещё", "Список занятий", "Нет элементов для отображения"};
        toastMessage = new String[]{"Заполните все поля!", "Введите одинаковые пароли!"};
        database = new String[]{"Заголовок", "Автор"};
        database = new String[]{"Логин", "Имя", "Фамилия", "Телефон", "Информация", "Пароль", "Повторите пароль"};
        dialog = new String[]{"Предупреждение", "Вы уверены?", "Да", "Нет"};
        seasons = new String[]{"ЯНВАРЬ", "ФЕВРАЛЬ", "МАРТ", "АПРЕЛЬ", "МАЙ", "ИЮНЬ", "ИЮЛЬ", "АВГУСТ", "СЕНТЯБРЬ", "ОКТЯБРЬ", "НОЯБРЬ", "ДЕКАБРЬ"};
        daysOfWeek = new String[] {"ПН", "ВТ", "СР", "ЧТ", "ПТ"};
        formData = new String[]{"ДОБАВИТЬ ЗАНЯТИЯ", "Введите название школы", "Время начала занятий", "Время окончания занятий", "ПРИНЯТЬ", "ОТМЕНА"};
    }

    void setSpain(){
        spinnerChoose = new String[]{"Selecciona un grupo", "1A", "1B", "1C", "1D", "2A", "2B", "2C", "2D", "3A", "3B", "3C", "3D"};
        languageChoose = new String[]{"Polaco", "Alemán", "Inglés", "Francés", "Ruso", "Español"};
        home = new String[]{" CLASES DE HOY ", "ANTERIOR", "SIGUIENTE", "Sin clases en el horario especificado", "VER TODAS LAS CLASES"};
        options = new String[]{"Selecciona una opción", "Un idioma "};
        actionBarTitle = new String[]{" Página principal ", " Clases ", " Calificaciones ", " Editar perfil ", " Cambiar idioma ", " Más "};
        editProfileLang = new String[]{"Información", "Tu nombre de usuario", "Nombre", "Apellido", "Teléfono", "Información adicional", "Contraseña", "Repetir contraseña", "APLICAR", "CANCELAR"};
        menu = new String[]{"Editar perfil", "Cambiar idioma", "Cerrar sesión"};
        menuBottom = new String[]{"Clases", "Calificaciones", "Más", "Lista de clases completadas", "No hay elementos para mostrar"};
        toastMessage = new String[]{"¡Complete todos los campos!", "¡Ingrese las mismas contraseñas!"};
        database = new String[]{"Login", "Nombre", "Apellido", "Teléfono", "Información", "Contraseña", "Repetir Contraseña"};
        dialog = new String[]{"Advertencia", "¿Estás seguro?", "Sí", "No"};
        seasons = new String[]{"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
        daysOfWeek = new String[] {"LUN", "MAR", "MIÉ", "JUE", "VIE"};
        formData = new String[]{"AGREGAR CLASES", "Ingrese el nombre de la escuela", "Hora de inicio de clases", "Hora de finalización de clases", "ACEPTAR", "CANCELAR"};
    }
}
