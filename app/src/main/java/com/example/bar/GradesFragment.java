package com.example.bar;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.bar.databinding.FragmentGradesBinding;

import java.util.Objects;

public class GradesFragment extends Fragment {
    int choosedLang;
    FragmentGradesBinding fragmentGradesBinding;
    public GradesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentGradesBinding = FragmentGradesBinding.inflate(inflater, container, false);
        View view = fragmentGradesBinding.getRoot();
        LanguageVocabulary languageVocabulary = new LanguageVocabulary();
        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);
        ((MainActivity)getActivity()).setActionBar(languageVocabulary.actionBarTitle[2], R.drawable.baseline_plus_one_24);
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(languageVocabulary.spinnerChoose, getContext());
        fragmentGradesBinding.spinnerGrades.setAdapter(customSpinnerAdapter);
        fragmentGradesBinding.emptyGrades.setText(languageVocabulary.menuBottom[4]);

        fragmentGradesBinding.spinnerGrades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = languageVocabulary.spinnerChoose[position];
                if(!(Objects.equals(selected, languageVocabulary.spinnerChoose[0]))){
                    Toast.makeText(getActivity(), selected,Toast.LENGTH_SHORT).show();
                }
            } @Override public void onNothingSelected(AdapterView<?> parent) {}});
        return view;
    }
}