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
    public MoreFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentMoreBinding = FragmentMoreBinding.inflate(inflater, container, false);
        View view = fragmentMoreBinding.getRoot();
        fragmentMoreBinding.changeHomeScreenFragment.setOnClickListener( view1 -> ((MainActivity)getActivity()).setFragment(new HomeFragment()));
        fragmentMoreBinding.editProfile.setOnClickListener( view1 -> ((MainActivity)getActivity()).setFragment(new EditProfileFragment()));
        fragmentMoreBinding.languageSet.setOnClickListener( view1-> ((MainActivity)getActivity()).setFragment(new LanguageFragment()));
        ((MainActivity) getActivity()).setActionBar(getResources().getString(R.string.wi_cej), R.drawable.baseline_more_horiz_24);
        return view;
    }
}