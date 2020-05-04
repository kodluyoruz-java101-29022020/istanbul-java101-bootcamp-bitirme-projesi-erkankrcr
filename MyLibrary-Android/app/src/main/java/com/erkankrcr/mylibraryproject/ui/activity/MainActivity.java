package com.erkankrcr.mylibraryproject.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.erkankrcr.mylibraryproject.R;
import com.erkankrcr.mylibraryproject.repostory.model.User;
import com.erkankrcr.mylibraryproject.ui.fragment.LibraryAddFragment;
import com.erkankrcr.mylibraryproject.ui.fragment.LibrarySearchFragment;
import com.erkankrcr.mylibraryproject.ui.fragment.LoginFragment;
import com.erkankrcr.mylibraryproject.ui.fragment.SignFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager manager;
    Toolbar toolbar;
    User user;
    Fragment libraryAdd, librarySearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        toolbar = findViewById(R.id.main_toolbar);
        String userString = getSharedPreferences("AppInfo",MODE_PRIVATE).getString("UserInfo","null");
        user = new Gson().fromJson(userString,User.class);
        toolbar.setTitle("Hoşgeldin "+user.getName()+" "+user.getLastname());
        libraryAdd = new LibraryAddFragment();
        librarySearch = new LibrarySearchFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_library_add:
                        manager.beginTransaction().replace(R.id.main_container,libraryAdd).commitNow();
                        break;
                    case R.id.nav_library_search:
                        manager.beginTransaction().replace(R.id.main_container,librarySearch).commitNow();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.nav_library_search);
        manager.beginTransaction().replace(R.id.main_container,librarySearch).commitNow();
    }
}
