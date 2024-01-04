package com.example.bar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;
import com.example.bar.databinding.ActivityMainBinding;
import com.example.bar.databinding.CustomLayoutDialogBinding;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LanguageVocabulary languageVocabulary = new LanguageVocabulary();
    ActivityMainBinding activityMainBinding;
    CustomLayoutDialogBinding customLayoutDialogBinding;
    String dataFrom;
    String schoolName;
    String timeBegin;
    String timeEnd;
    DatabaseActivitySchedule db;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        value = intent.getIntExtra("value",0);
        setLanguage(value, languageVocabulary);
        db = new DatabaseActivitySchedule(this);
        setFragment(new HomeFragment());
        setActionBar(languageVocabulary.actionBarTitle[1], R.drawable.baseline_home_24);
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE
        | ActionBar.DISPLAY_SHOW_HOME
        | ActionBar.DISPLAY_SHOW_CUSTOM
        | ActionBar.DISPLAY_USE_LOGO);
        setBottomMenu();
        activityMainBinding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.lessons){
                setFragment(new LessonFragment());
            }
            else if(item.getItemId() == R.id.grades){
                setFragment(new GradesFragment());
            }else if(item.getItemId() == R.id.more){
                setFragment(new MoreFragment());
            }
            return true;
        });

    }

    void setBottomMenu(){
        Menu menu = activityMainBinding.bottomNavigationView.getMenu();
        for(int i = 0; i < languageVocabulary.menu.length; i++){
            menu.getItem(i).setTitle(languageVocabulary.menuBottom[i]);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_top, menu);
        for(int i = 0; i < languageVocabulary.menu.length; i++){
            menu.getItem(i+1).setTitle(languageVocabulary.menu[i]);
        }
        return true;
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if(item.getItemId() == R.id.lang){
            setFragment(new LanguageFragment());
        }
        else if(item.getItemId() == R.id.logout) {
            Toast.makeText(this, languageVocabulary.menu[2], Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.profile) {
            setFragment(new EditProfileFragment());
        }else if(item.getItemId() == R.id.addSchedule) {
            customDialog();
            System.out.println("siema");
        }else {
            return super.onOptionsItemSelected(item);
        }
        return false;
    }


    void customDialog(){
        customLayoutDialogBinding = CustomLayoutDialogBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.show();
        customLayoutDialogBinding.titleForm.setText(languageVocabulary.formData[0]);
        customLayoutDialogBinding.editFormText.setHint(languageVocabulary.formData[1]);
        customLayoutDialogBinding.editFormText1.setHint(languageVocabulary.formData[2]);
        customLayoutDialogBinding.editFormText2.setHint(languageVocabulary.formData[3]);
        customLayoutDialogBinding.acceptBtnForm.setText(languageVocabulary.formData[4]);
        customLayoutDialogBinding.cancelBtnForm.setText(languageVocabulary.formData[5]);

        dialog.setContentView(customLayoutDialogBinding.getRoot());
        customLayoutDialogBinding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> dataFrom = addZero(dayOfMonth) + "." + addZero((month+1))+"."+year);
        customLayoutDialogBinding.acceptBtnForm.setOnClickListener(v -> {
            schoolName = customLayoutDialogBinding.editFormText.getText().toString();
            timeBegin = customLayoutDialogBinding.editFormText1.getText().toString();
            timeEnd = customLayoutDialogBinding.editFormText2.getText().toString();
            db.addSchedule(timeBegin, timeEnd,schoolName,dataFrom);
            dialog.dismiss();
        });
        customLayoutDialogBinding.cancelBtnForm.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
    String addZero(int value){
        String res = "";
        if(value <= 9){
            res = "0"+ value;
        }else res = String.valueOf(value);
        return res;
    }
    void setFragment(Fragment fragmenty){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmenty);
        transaction.commit();

    }

    void setActionBar(String title, int logo){
        SpannableString spannableString = new SpannableString(title);
        spannableString.setSpan(new RelativeSizeSpan(1.2f), 0, spannableString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(spannableString);
        getSupportActionBar().setLogo(logo);
    }

    void setLanguage(int choosedLang, LanguageVocabulary languageVocabulary){
        switch(choosedLang) {
            case 0:
                languageVocabulary.setPolish();
                break;
            case 1:
                languageVocabulary.setGerman();
                break;
            case 2:
                languageVocabulary.setEnglish();
                break;
            case 3:
                languageVocabulary.setFrench();
                break;
            case 4:
                languageVocabulary.setRussian();
                break;
            case 5:
                languageVocabulary.setSpain();
                break;
            default:
                languageVocabulary.setPolish();
                break;
        }
    }
    //konwertowanie daty na cyfry w string.
    public String getDayOfWeekFromActivityDate(String activityDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        try {
            Date date = sdf.parse(activityDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String dayOfWeek = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
            switch (dayOfWeek) {
                case "2":
                    return languageVocabulary.daysOfWeek[0];
                case "3":
                    return languageVocabulary.daysOfWeek[1];
                case "4":
                    return languageVocabulary.daysOfWeek[2];
                case "5":
                    return languageVocabulary.daysOfWeek[3];
                case "6":
                    return languageVocabulary.daysOfWeek[4];
                default:
                    return "";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}