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
    FragmentGradesBinding fragmentGradesBinding;
    public GradesFragment() {}
    String[]spinnerChoose;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        spinnerChoose = getResources().getStringArray(R.array.spinner);
        fragmentGradesBinding = FragmentGradesBinding.inflate(inflater, container, false);
        View view = fragmentGradesBinding.getRoot();
        ((MainActivity)getActivity()).setActionBar(getResources().getString(R.string.oceny), R.drawable.baseline_plus_one_24);
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(spinnerChoose, getContext());
        fragmentGradesBinding.spinnerGrades.setAdapter(customSpinnerAdapter);
        fragmentGradesBinding.spinnerGrades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinnerChoose[position];
                if(!(Objects.equals(selected, spinnerChoose[0]))){
                    Toast.makeText(getActivity(), selected,Toast.LENGTH_SHORT).show();
                }
            } @Override public void onNothingSelected(AdapterView<?> parent) {}});
        return view;
    }
}