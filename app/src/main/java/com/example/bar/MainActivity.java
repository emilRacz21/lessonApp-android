package com.example.bar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    String[] myDays;
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
        myDays = getResources().getStringArray(R.array.days);
        setContentView(view);
        Intent intent = getIntent();
        value = intent.getIntExtra("value",0);
        setActionBar(getResources().getString(R.string.strona_g_wna), R.drawable.baseline_home_24);
        db = new DatabaseActivitySchedule(this);
        setFragment(new HomeFragment());
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE
        | ActionBar.DISPLAY_SHOW_HOME
        | ActionBar.DISPLAY_SHOW_CUSTOM
        | ActionBar.DISPLAY_USE_LOGO);

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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if(item.getItemId() == R.id.lang){
            setFragment(new LanguageFragment());
        }
        else if(item.getItemId() == R.id.logout) {
            Toast.makeText(this, getResources().getString(R.string.wyloguj_si), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, getResources().getString(R.string.zajecia_form),Toast.LENGTH_SHORT).show();
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
                    return myDays[0];
                case "3":
                    return myDays[1];
                case "4":
                    return myDays[2];
                case "5":
                    return myDays[3];
                case "6":
                    return myDays[4];
                default:
                    return "";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    void setLocal(Activity activity, String lang){
        Locale locale = new Locale(lang);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}