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
    String[] languageChoose;
    int[] flag = {R.drawable.flag_poland, R.drawable.flag_germany, R.drawable.flag_united_kingdom, R.drawable.flag_france, R.drawable.flag_russia, R.drawable.flag_spain};
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
        languageChoose = getResources().getStringArray(R.array.languageChoose);
        ((MainActivity)getActivity()).setActionBar(getResources().getString(R.string.zmie_j_zyk), R.drawable.baseline_language_24);

        CustomLanguageAdapter customLanguageAdapter = new CustomLanguageAdapter(languageChoose, flag,getContext());
        fragmentLanguageBinding.gridView.setAdapter(customLanguageAdapter);
        fragmentLanguageBinding.gridView.setOnItemClickListener((parent, view12, position, id) -> {
            Toast.makeText(getContext(), languageChoose[position], Toast.LENGTH_SHORT).show();
            switch (position) {
                case 0: setLanguage("pl"); break;
                case 1: setLanguage("de"); break;
                case 2: setLanguage("en"); break;
                case 3: setLanguage("fr"); break;
                case 4: setLanguage("ru"); break;
                case 5: setLanguage("es"); break;
                default: setLanguage("pl"); break;
            }
        });
        fragmentLanguageBinding.backArrow.setOnClickListener(view1 ->((MainActivity)getActivity()).setFragment(new MoreFragment()));
        return view;
    }
    void setLanguage(String language){
        ((MainActivity)getActivity()).setLocal(getActivity(), language);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}