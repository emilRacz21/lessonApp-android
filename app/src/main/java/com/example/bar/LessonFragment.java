package com.example.bar;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.bar.databinding.FragmentLessonBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LessonFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    int choosedLang;
    FragmentLessonBinding fragmentLessonBinding;
    List<LessonList> lessonList = new ArrayList<>();
    public LessonFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLessonBinding = FragmentLessonBinding.inflate(inflater, container, false);
        View view = fragmentLessonBinding.getRoot();
        LanguageVocabulary languageVocabulary = new LanguageVocabulary();
        printDays();
        CustomLessonAdapter customLessonAdapter = new CustomLessonAdapter(lessonList, getContext());
        fragmentLessonBinding.listView.setAdapter(customLessonAdapter);
        choosedLang = ((MainActivity) getActivity()).value;
        fragmentLessonBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).setFragment(new LessonSelectFragment());
                System.out.println(lessonList.get(position).data1);
                System.out.println(lessonList.get(position).data2);

                Bundle bundle = new Bundle();
                bundle.putString("monday", String.valueOf(convertToUnixTimestamp(lessonList.get(position).data1)));
                bundle.putString("friday", String.valueOf(convertToUnixTimestamp(lessonList.get(position).data2)));
                getParentFragmentManager().setFragmentResult("lessons", bundle);

            }
        });
        ((MainActivity) getActivity()).setLanguage(choosedLang, languageVocabulary);
        ((MainActivity) getActivity()).setActionBar(languageVocabulary.actionBarTitle[1], R.drawable.baseline_calendar_month_24);
        return view;
    }

    void printDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (int week = 1; week <= 27; week++) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String formattedDateMonday = dateFormat.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            String formattedDateFriday = dateFormat.format(calendar.getTime());
            lessonList.add(new LessonList(formattedDateMonday, formattedDateFriday));
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
        }
    }

    private long convertToUnixTimestamp(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            Date date = dateFormat.parse(dateStr);
            return date.getTime() / 1000; // converting milliseconds to seconds
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}