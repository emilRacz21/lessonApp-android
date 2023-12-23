package com.example.bar;

import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bar.databinding.FragmentLessonBinding;
import java.util.ArrayList;
import java.util.List;

public class LessonFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    int choosedLang;
    FragmentLessonBinding fragmentLessonBinding;
    List<Lesson> lessonList = new ArrayList<>();
    public LessonFragment() {}
    public static LessonFragment newInstance(String param1, String param2) {
        LessonFragment fragment = new LessonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLessonBinding = FragmentLessonBinding.inflate(inflater, container, false);
        View view = fragmentLessonBinding.getRoot();
        Language language = new Language();
        lessonList.add(new Lesson("11.12.2023","15.12.2023"));
        lessonList.add(new Lesson("18.12.2023","22.12.2023"));
        lessonList.add(new Lesson("25.12.2023","29.12.2023"));

        LessonCustomAdapter lessonCustomAdapter = new LessonCustomAdapter(lessonList,getContext());
        fragmentLessonBinding.listView.setAdapter(lessonCustomAdapter);
        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, language);
        ((MainActivity)getActivity()).setActionBar(language.actionBarTitle[1], R.drawable.baseline_calendar_month_24);
        return view;
    }

}