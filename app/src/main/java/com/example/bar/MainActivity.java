package com.example.bar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.bar.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    Language language = new Language();
    ActivityMainBinding activityMainBinding;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        value = intent.getIntExtra("value",0);
        setLanguage(value,language);


        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        setFragment(new HomeFragment());
        setActionBar(language.actionBarTitle[1], R.drawable.baseline_home_24);

        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE
        | ActionBar.DISPLAY_SHOW_HOME
        | ActionBar.DISPLAY_SHOW_CUSTOM
        | ActionBar.DISPLAY_USE_LOGO);

        setBottomMenu();

        activityMainBinding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.lessons){
                    setFragment(new LessonFragment());
                }
                else if(item.getItemId() == R.id.grades){
                    setFragment(new GradesFragment());
                }else if(item.getItemId() == R.id.more){
                    setFragment(new MoreFragment());
                }
                return true;
            }
        });


    }

    void setBottomMenu(){
        Menu menu = activityMainBinding.bottomNavigationView.getMenu();
        for( int i = 0 ; i < language.menu.length; i++){
            menu.getItem(i).setTitle(language.menuBottom[i]);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        for( int i = 0 ; i < language.menu.length; i++){
            menu.getItem(i).setTitle(language.menu[i]);
        }
        return true;
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if(item.getItemId() == R.id.lang){
            setFragment(new LanguageFragment());
        }
        else if(item.getItemId() == R.id.logout) {
            Toast.makeText(this,language.menu[2], Toast.LENGTH_SHORT).show();
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

    void setLanguage(int choosedLang, Language language){
        switch(choosedLang) {
            case 0:
                language.setPolish();
                break;
            case 1:
                language.setGerman();
                break;
            case 2:
                language.setEnglish();
                break;
            case 3:
                language.setFrench();
                break;
            case 4:
                language.setRussian();
                break;
            case 5:
                language.setSpain();
                break;
            default:
                language.setPolish();
                break;
        }
    }

}