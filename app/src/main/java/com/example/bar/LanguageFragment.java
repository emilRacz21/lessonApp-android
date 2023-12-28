package com.example.bar;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.bar.databinding.FragmentLanguageBinding;
public class LanguageFragment extends Fragment {
    FragmentLanguageBinding fragmentLanguageBinding;
    int choosedLang;
    public LanguageFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLanguageBinding = FragmentLanguageBinding.inflate(inflater, container, false);
        View view = fragmentLanguageBinding.getRoot();

        LanguageVocabulary languageVocabulary = new LanguageVocabulary();
        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);
        ((MainActivity)getActivity()).setActionBar(languageVocabulary.actionBarTitle[4], R.drawable.baseline_language_24);
        fragmentLanguageBinding.languageText.setText(languageVocabulary.options[1]);

        CustomLanguageAdapter customLanguageAdapter = new CustomLanguageAdapter(languageVocabulary.languageChoose, languageVocabulary.flag,getContext());
        fragmentLanguageBinding.gridView.setAdapter(customLanguageAdapter);
        fragmentLanguageBinding.gridView.setOnItemClickListener((parent, view12, position, id) -> {
            Toast.makeText(getContext(), languageVocabulary.languageChoose[position], Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("value",position);
            startActivity(intent);
        });
        fragmentLanguageBinding.backArrow.setOnClickListener(view1 ->((MainActivity)getActivity()).setFragment(new MoreFragment()));
        return view;
    }
}