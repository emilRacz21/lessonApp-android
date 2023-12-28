package com.example.bar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bar.databinding.FragmentLessonSelectBinding;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LessonSelectFragment extends Fragment {
    String monday;
    String friday;
    FragmentLessonSelectBinding fragmentLessonSelectBinding;
    public LessonSelectFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLessonSelectBinding = FragmentLessonSelectBinding.inflate(getLayoutInflater());
        View view = fragmentLessonSelectBinding.getRoot();

        getParentFragmentManager().setFragmentResultListener("lessons", this, (requestKey, result) -> {
            monday = result.getString("monday");
            friday = result.getString("friday");

            for (int i = 0; i < 5; i++) {
                final View singleFrame = getLayoutInflater().inflate(R.layout.frame_lesson_select, null);
                singleFrame.setId(i);

                TextView dayMonth = singleFrame.findViewById(R.id.daysMonth);
                dayMonth.setText(getDayOfWeek(i));

                TextView daysNum = singleFrame.findViewById(R.id.daysNum);
                daysNum.setText(getFormattedNextDay(monday, i));

                fragmentLessonSelectBinding.viewGroup.addView(singleFrame);
            }
            fragmentLessonSelectBinding.textMonth.setText(getCurrentMonth(monday, 0) + "\n");
        });

        fragmentLessonSelectBinding.backArrow.setOnClickListener(view1-> ((MainActivity)getActivity()).setFragment(new LessonFragment()));
        return view;
    }
    private String getDayOfWeek(int daysToAdd) {
        String[] dayOfWeek = {"", "PON", "WT", "ŚR", "CZW", "PT"};
        return dayOfWeek[(daysToAdd + 1) % dayOfWeek.length];
    }

    private String getFormattedNextDay(String unixTimestamp, int daysToAdd) {
        long timestamp = Long.parseLong(unixTimestamp) * 1000;
        Date date = new Date(timestamp + daysToAdd * 24 * 60 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.getDefault());
        return sdf.format(date);
    }
    private String getCurrentMonth(String unixTimestamp, int daysToAdd) {
        long timestamp = Long.parseLong(unixTimestamp) * 1000;
        Date date = new Date(timestamp + daysToAdd * 24 * 60 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        return sdf.format(date).toUpperCase();
    }
}
