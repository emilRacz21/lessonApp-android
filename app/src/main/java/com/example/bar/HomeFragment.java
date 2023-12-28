package com.example.bar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bar.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    int choosedLang;
    FragmentHomeBinding fragmentHomeBinding;
    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = fragmentHomeBinding.getRoot();
        LanguageVocabulary languageVocabulary = new LanguageVocabulary();

        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);
        fragmentHomeBinding.textTopLesson.setText(languageVocabulary.home[0]);
        fragmentHomeBinding.previousText.setText(languageVocabulary.home[1]);
        fragmentHomeBinding.nextText.setText(languageVocabulary.home[2]);
        fragmentHomeBinding.emptyLessonText.setText(languageVocabulary.home[3]);
        fragmentHomeBinding.showMoreText.setText(languageVocabulary.home[4]);
        fragmentHomeBinding.showMoreText.setOnClickListener( view1 -> ((MainActivity)getActivity()).setFragment(new LessonFragment()));
        ((MainActivity)getActivity()).setActionBar(languageVocabulary.actionBarTitle[0], R.drawable.baseline_home_24);
        return view;
    }
}