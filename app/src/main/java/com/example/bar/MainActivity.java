package com.example.bar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.bar.databinding.ActivityMainBinding;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    LanguageVocabulary languageVocabulary = new LanguageVocabulary();
    ActivityMainBinding activityMainBinding;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        value = intent.getIntExtra("value",0);
        setLanguage(value, languageVocabulary);


        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
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
            menu.getItem(i).setTitle(languageVocabulary.menu[i]);
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
        }else {
            return super.onOptionsItemSelected(item);
        }
        return false;
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
}