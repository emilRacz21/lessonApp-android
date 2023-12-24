package com.example.bar;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bar.databinding.FragmentMoreBinding;
public class MoreFragment extends Fragment {
    FragmentMoreBinding fragmentMoreBinding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    int choosedLang;
    public MoreFragment() {}

    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
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
        fragmentMoreBinding = FragmentMoreBinding.inflate(inflater, container, false);
        View view = fragmentMoreBinding.getRoot();

        LanguageVocabulary languageVocabulary = new LanguageVocabulary();
        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);

        setLang(languageVocabulary);

        fragmentMoreBinding.changeHomeScreenFragment.setOnClickListener( view1 ->{
            ((MainActivity)getActivity()).setFragment(new HomeFragment());
        });

        fragmentMoreBinding.editProfile.setOnClickListener( view1 ->{
            ((MainActivity)getActivity()).setFragment(new EditProfileFragment());
        });

        fragmentMoreBinding.languageSet.setOnClickListener( view1->{
            ((MainActivity)getActivity()).setFragment(new LanguageFragment());
        });

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