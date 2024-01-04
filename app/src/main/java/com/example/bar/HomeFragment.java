package com.example.bar;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bar.databinding.FragmentHomeBinding;
import com.example.bar.databinding.FrameLessonSelectBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    int choosedLang;
    FragmentHomeBinding fragmentHomeBinding;
    String profileLogin;
    String timeBegin;
    String timeEnd;
    String school;
    List<LessonList> lessonList = new ArrayList<>();
    String activityDate;
    LanguageVocabulary languageVocabulary = new LanguageVocabulary();
    int increment;
    Cursor cursor;
    FrameLessonSelectBinding frameBinding;
    DatabaseActivitySchedule db;
    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = fragmentHomeBinding.getRoot();
        db= new DatabaseActivitySchedule(getContext());
        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);
        fragmentHomeBinding.textTopLesson.setText(languageVocabulary.home[0]);
        fragmentHomeBinding.previousText.setText(languageVocabulary.home[1]);
        fragmentHomeBinding.nextText.setText(languageVocabulary.home[2]);
        fragmentHomeBinding.emptyLessonText.setText(languageVocabulary.home[3]);
        fragmentHomeBinding.showMoreText.setText(languageVocabulary.home[4]);
        fragmentHomeBinding.showMoreText.setOnClickListener( view1 -> ((MainActivity)getActivity()).setFragment(new LessonFragment()));
        ((MainActivity)getActivity()).setActionBar(languageVocabulary.actionBarTitle[0], R.drawable.baseline_home_24);

        increment = 0;
        printDays();

        fragmentHomeBinding.nextText.setOnClickListener(view1 -> {
            System.out.println(increment);
            getDays();
            increment++;
        });
        fragmentHomeBinding.previousText.setOnClickListener(v -> {
            if(increment >0){
                increment--;
            } else if (increment < 0) return;
            getDays();
        });

        return view;
    }
    void getDays() {
        cursor = db.takeAllSchedules();
        fragmentHomeBinding.mtFrame.removeAllViews();
        boolean hasSchedule = false;

        while (cursor.moveToNext()) {
            frameBinding = FrameLessonSelectBinding.inflate(getLayoutInflater());
            View singleFrame = frameBinding.getRoot();
            getCursor(cursor);
            fragmentHomeBinding.textTopLesson.setText(lessonList.get(increment).data1);
            if (convertToFormattedDate(lessonList.get(increment).data1).equals(activityDate)) {
                frameBinding.daysMonth.setText(profileLogin);
                frameBinding.timeBegin.setText(timeBegin);
                frameBinding.timeEnd.setText(timeEnd);
                frameBinding.daysNum.setVisibility(View.GONE);
                frameBinding.schoolText.setText(school);
                fragmentHomeBinding.mtFrame.addView(singleFrame);
                hasSchedule = true;
            }
        }
        fragmentHomeBinding.hideMyLayout.setVisibility(hasSchedule ? View.GONE : View.VISIBLE);
    }


    @SuppressLint("Range")
    void getCursor(Cursor cursor){
        timeBegin = cursor.getString(cursor.getColumnIndex("timeBegin"));
        timeEnd = cursor.getString(cursor.getColumnIndex("timeEnd"));
        school = cursor.getString(cursor.getColumnIndex("schoolName"));
        activityDate = cursor.getString(cursor.getColumnIndex("activityDate"));
        profileLogin = ((MainActivity)getActivity()).getDayOfWeekFromActivityDate(activityDate);
    }
    void printDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (int week = 1; week <= 52; week++) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            // Loop through Monday to Friday and store the formatted dates
            for (int dayOfWeek = 0; dayOfWeek < 5; dayOfWeek++) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String formattedDate = dateFormat.format(calendar.getTime());
                lessonList.add(new LessonList(formattedDate, formattedDate));
                calendar.add(Calendar.DAY_OF_WEEK, 1); // Move to the next day
            }
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
        }
    }

    //Konwersja long na string z formatem.
    @SuppressLint("SimpleDateFormat")
    private String convertToFormattedDate(String dateStr) {
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            Date date = inputDateFormat.parse(dateStr);
            return inputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error parsing date";
        }
    }
}