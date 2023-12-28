package com.example.bar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bar.databinding.FragmentMoreBinding;
public class MoreFragment extends Fragment {
    FragmentMoreBinding fragmentMoreBinding;
    int choosedLang;
    public MoreFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentMoreBinding = FragmentMoreBinding.inflate(inflater, container, false);
        View view = fragmentMoreBinding.getRoot();

        LanguageVocabulary languageVocabulary = new LanguageVocabulary();
        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);

        setLang(languageVocabulary);

        fragmentMoreBinding.changeHomeScreenFragment.setOnClickListener( view1 -> ((MainActivity)getActivity()).setFragment(new HomeFragment()));
        fragmentMoreBinding.editProfile.setOnClickListener( view1 -> ((MainActivity)getActivity()).setFragment(new EditProfileFragment()));
        fragmentMoreBinding.languageSet.setOnClickListener( view1-> ((MainActivity)getActivity()).setFragment(new LanguageFragment()));
        return view;
    }

    void setLang(LanguageVocabulary languageVocabulary){
        fragmentMoreBinding.titleHome.setText(languageVocabulary.actionBarTitle[0]);
        fragmentMoreBinding.editProfileText.setText(languageVocabulary.actionBarTitle[3]);
        fragmentMoreBinding.changeLanguage.setText(languageVocabulary.actionBarTitle[4]);
        fragmentMoreBinding.optionsText.setText(languageVocabulary.options[0]);
        ((MainActivity)getActivity()).setActionBar(languageVocabulary.actionBarTitle[5], R.drawable.baseline_more_horiz_24);
    }
}