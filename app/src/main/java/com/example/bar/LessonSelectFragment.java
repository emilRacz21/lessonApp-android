package com.example.bar;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bar.databinding.FragmentLessonSelectBinding;
import com.example.bar.databinding.FrameLessonSelectBinding;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class LessonSelectFragment extends Fragment {
    String monday;
    int increment;
    FragmentLessonSelectBinding fragmentLessonSelectBinding;
    FrameLessonSelectBinding frameBinding;
    String profileLogin;
    String timeBegin;
    String timeEnd;
    String school;
    String month;
    String activityDate;
    boolean frameAdded = false;
    int choosedLang;
    LanguageVocabulary languageVocabulary = new LanguageVocabulary();
    String[] DAYS_OF_WEEK;
    public LessonSelectFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentLessonSelectBinding = FragmentLessonSelectBinding.inflate(getLayoutInflater());
        View view = fragmentLessonSelectBinding.getRoot();

        //deklaracja jezyka.
        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);
        //deklaracja dni tygodnia do zmiany jezyka.
        DAYS_OF_WEEK = new String[]{
                languageVocabulary.daysOfWeek[0],
                languageVocabulary.daysOfWeek[1],
                languageVocabulary.daysOfWeek[2],
                languageVocabulary.daysOfWeek[3],
                languageVocabulary.daysOfWeek[4]
        };
        //Klasa z baza danych o zajeciach.
        DatabaseActivitySchedule db = new DatabaseActivitySchedule(getContext());
        getParentFragmentManager().setFragmentResultListener("lessons", this, (requestKey, result) -> {
            monday = result.getString("monday");
            Cursor cursor = db.takeAllSchedules();
            String singleMonth = formatDate(monday, 0, "MMMM").toUpperCase();
            System.out.println(singleMonth);
            //petla while z wszytskimi elelemntami znajdujacymi się w DB.
            while (cursor.moveToNext()) {
                frameBinding = FrameLessonSelectBinding.inflate(getLayoutInflater());
                View singleFrame = frameBinding.getRoot();
                singleFrame.setId(increment);
                //odwołanie do metody cursor, ustawia na stringi dane z DB.
                getCursor(cursor);
                if (singleMonth.equals(month) && formatDate(monday, getDayNumber(profileLogin), "dd.MM.yyyy").equals(activityDate)) {
                    fragmentLessonSelectBinding.hideRelativeLayout.setVisibility(View.GONE);
                    //binding na elementy znajdujące sie w frame_lesson_select.xml
                    frameBinding.daysMonth.setText(profileLogin);
                    frameBinding.timeBegin.setText(timeBegin);
                    frameBinding.timeEnd.setText(timeEnd);
                    frameBinding.schoolText.setText(school);
                    frameBinding.daysNum.setText(formatDate(monday, getDayNumber(profileLogin), "dd"));
                    //odwołanie do metody timeResult
                    timeResult(singleFrame, timeBegin, timeEnd);
                    //binding na elelemnt id=viewGroup znajdujący się w fragment_lesson_select.xml
                    fragmentLessonSelectBinding.viewGroup.addView(singleFrame);
                    increment++;
                    // Ustawienie flagi, że dodano minimum 1 singleFrame.
                    frameAdded = true;
                }
            }
            //Warunek else spełni się tylko, gdy increment wynosi 0 i nie dodano żadnej ramki.
            if (!frameAdded && increment == 0) {
                //dodanie do layoutu elelementu hideRelativeLayout gdy frameadded = false i nie nastąpiła inkrementacja w pętli while.
                fragmentLessonSelectBinding.hideRelativeLayout.setVisibility(View.VISIBLE);
                //Dodanie jezyka gdy jest widoczny element oznajmijacy o braku elelemntów.
                fragmentLessonSelectBinding.emptyElements.setText(languageVocabulary.menuBottom[4]);
            }
            //binding na elelemnt id=textMonth znajdujący się w fragment_lesson_select.xml
            fragmentLessonSelectBinding.textMonth.setText(formatDate(monday, 0, "M").toUpperCase());
            setLanguage();
            //Zamkniecie kursora i bazy danych (database schedule)
            cursor.close();
            db.close();
        });
        //Nasłuch na strzałke powrotną, powraca do fragmentu "LessonFragment"
        fragmentLessonSelectBinding.backArrow.setOnClickListener(view1-> ((MainActivity)getActivity()).setFragment(new LessonFragment()));
        return view;
    }

    //Ustawienie za pomocą kursora zmiennych, kursor pobiera z bazy danych komórki o danym ideksie.
    @SuppressLint("Range")
    void getCursor(Cursor cursor){
        timeBegin = cursor.getString(cursor.getColumnIndex("timeBegin"));
        timeEnd = cursor.getString(cursor.getColumnIndex("timeEnd"));
        school = cursor.getString(cursor.getColumnIndex("schoolName"));
        month = cursor.getString(cursor.getColumnIndex("month"));
        activityDate = cursor.getString(cursor.getColumnIndex("activityDate"));
        profileLogin = getDayOfWeekFromActivityDate(activityDate);
    }
    //Oblicznie ile trwają zajęcia.
    void timeResult(View singleFrame, String timeBegin, String timeEnd) {
        TextView timeResult = singleFrame.findViewById(R.id.timeResult);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        try {
            Date dateBegin = sdf.parse(timeBegin);
            Date dateEnd = sdf.parse(timeEnd);
            long diffInMilliseconds = dateEnd.getTime() - dateBegin.getTime();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds);
            timeResult.setText(String.valueOf(diffInMinutes));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //Formatowanie daty.
    private String formatDate(String unixTimestamp, int daysToAdd, String format) {
        long timestamp = Long.parseLong(unixTimestamp) * 1000;
        Date date = new Date(timestamp + (long) daysToAdd * 24 * 60 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }
    //Określ czy jest to PON, WTO, itd.
    private int getDayNumber(String dayName) {
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            if (DAYS_OF_WEEK[i].equals(dayName)) {
                return i;
            }
        }
        return -1;
    }
    //Zmień język i dodaj rok.
    void setLanguage() {
        String currentMonth = fragmentLessonSelectBinding.textMonth.getText().toString().toUpperCase();
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        for (int i = 0; i < data.length; i++) {
            if (currentMonth.equals(String.valueOf(data[i]))) {
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                String monthWithYear = languageVocabulary.seasons[i] + " " + currentYear;
                fragmentLessonSelectBinding.textMonth.setText(monthWithYear);
                break;
            }
        }
    }
    //konwertowanie daty na skrót tygodnia.
    private String getDayOfWeekFromActivityDate(String activityDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        try {
            Date date = sdf.parse(activityDate);
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", Locale.getDefault());
            String dayOfWeek = dayFormat.format(date);
            switch (dayOfWeek) {
                case "Mon":
                    return languageVocabulary.daysOfWeek[0];
                case "Tue":
                    return languageVocabulary.daysOfWeek[1];
                case "Wed":
                    return languageVocabulary.daysOfWeek[2];
                case "Thu":
                    return languageVocabulary.daysOfWeek[3];
                case "Fri":
                    return languageVocabulary.daysOfWeek[4];
                default:
                    return "";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
