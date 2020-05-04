package com.erkankrcr.mylibraryproject.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.erkankrcr.mylibraryproject.R;
import com.erkankrcr.mylibraryproject.ui.fragment.LibraryAddFragment;
import com.erkankrcr.mylibraryproject.ui.fragment.LibrarySearchFragment;
import com.erkankrcr.mylibraryproject.ui.fragment.LoginFragment;
import com.erkankrcr.mylibraryproject.ui.fragment.SignFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * For The Glory Of Machine
 * ╔════════════════════════════╗
 * ║  Created by Erkan Karacar  ║
 * ╠════════════════════════════╣
 * ║ erkankrcr@outlook.com.tr   ║
 * ╠════════════════════════════╣
 * ║     30/04/2020 - 22:11     ║
 * ╚════════════════════════════╝
 */
public class LoginActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager manager;
    Toolbar toolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        manager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.login_bottom_navigation);
        toolbar = findViewById(R.id.login_toolbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_login_login:
                        manager.beginTransaction().replace(R.id.login_container,new LoginFragment()).commitNow();
                        break;
                    case R.id.nav_login_sign:
                        manager.beginTransaction().replace(R.id.login_container,new SignFragment()).commitNow();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        manager.beginTransaction().replace(R.id.login_container,new LoginFragment()).commitNow();
    }
}
