package com.example.bar;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import com.example.bar.databinding.FragmentLanguageBinding;
public class LanguageFragment extends Fragment {
    FragmentLanguageBinding fragmentLanguageBinding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    int choosedLang;
    public LanguageFragment() {}
    public static LanguageFragment newInstance(String param1, String param2) {
        LanguageFragment fragment = new LanguageFragment();
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
        fragmentLanguageBinding = FragmentLanguageBinding.inflate(inflater, container, false);
        View view = fragmentLanguageBinding.getRoot();

        LanguageVocabulary languageVocabulary = new LanguageVocabulary();
        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);
        ((MainActivity)getActivity()).setActionBar(languageVocabulary.actionBarTitle[4], R.drawable.baseline_language_24);
        fragmentLanguageBinding.languageText.setText(languageVocabulary.options[1]);

        CustomLanguageAdapter customLanguageAdapter = new CustomLanguageAdapter(languageVocabulary.languageChoose, languageVocabulary.flag,getContext());
        fragmentLanguageBinding.gridView.setAdapter(customLanguageAdapter);
        fragmentLanguageBinding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), languageVocabulary.languageChoose[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("value",position);
                startActivity(intent);
            }
        });
        fragmentLanguageBinding.backArrow.setOnClickListener(view1 ->((MainActivity)getActivity()).setFragment(new MoreFragment()));
        return view;
    }
}