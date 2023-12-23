package com.example.bar;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.bar.databinding.FragmentGradesBinding;

public class GradesFragment extends Fragment {
    int choosedLang;
    FragmentGradesBinding fragmentGradesBinding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public GradesFragment() {}

    public static GradesFragment newInstance(String param1, String param2) {
        GradesFragment fragment = new GradesFragment();
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
        fragmentGradesBinding = FragmentGradesBinding.inflate(inflater, container, false);
        View view = fragmentGradesBinding.getRoot();
        Language language = new Language();
        choosedLang = ((MainActivity)getActivity()).value;
        ((MainActivity)getActivity()).setLanguage(choosedLang, language);
        ((MainActivity)getActivity()).setActionBar(language.actionBarTitle[2], R.drawable.baseline_plus_one_24);
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(language.spinnerChoose, getContext());
        fragmentGradesBinding.spinnerGrades.setAdapter(customSpinnerAdapter);

        fragmentGradesBinding.spinnerGrades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = language.spinnerChoose[position];
                if(!(selected == language.spinnerChoose[0])){
                    Toast.makeText(getActivity(), "Wybrano: "+selected,Toast.LENGTH_SHORT).show();
                }
            } @Override public void onNothingSelected(AdapterView<?> parent) {}});
        return view;
    }
}