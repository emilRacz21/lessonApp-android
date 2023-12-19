package com.example.bar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.bar.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        setFragment(new HomeFragment());

        getSupportActionBar().setTitle("  Strona główna ");
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.white));
        getSupportActionBar().setLogo(R.drawable.baseline_home_24);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE
        | ActionBar.DISPLAY_SHOW_HOME
        | ActionBar.DISPLAY_SHOW_CUSTOM
        | ActionBar.DISPLAY_USE_LOGO);

        activityMainBinding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getTitle().equals("Zajęcia")){
                    setFragment(new LessonFragment());
                }
                else if(item.getTitle().equals("Oceny")){
                    setFragment(new GradesFragment());
                }else if(item.getTitle().equals("Więcej")){
                    setFragment(new MoreFragment());
                }
                return true;
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if(item.getItemId() == R.id.lang){
            setFragment(new LanguageFragment());
        }
        else if(item.getItemId() == R.id.logout) {
            Toast.makeText(this,"Open logout", Toast.LENGTH_SHORT).show();
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
}