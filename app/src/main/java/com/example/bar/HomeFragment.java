package com.example.bar;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bar.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    int choosedLang;
    FragmentHomeBinding fragmentHomeBinding;
    public HomeFragment() {}
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = fragmentHomeBinding.getRoot();
        Language language = new Language();

        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, language);
        fragmentHomeBinding.textTopLesson.setText(language.home[0]);
        fragmentHomeBinding.previousText.setText(language.home[1]);
        fragmentHomeBinding.nextText.setText(language.home[2]);
        fragmentHomeBinding.emptyLessonText.setText(language.home[3]);
        fragmentHomeBinding.showMoreText.setText(language.home[4]);

        ((MainActivity)getActivity()).setActionBar(language.actionBarTitle[0], R.drawable.baseline_home_24);

        return view;
    }
}