package com.example.admin;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.fragment.FragHome;
import com.example.admin.fragment.FragSetting;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mainLayout;
    private static final int FHOME = 0;
    private static final int FSETTING = 1;
    private static int CurrenFrag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout homeItem = findViewById(R.id.home);
        ConstraintLayout databaseItem = findViewById(R.id.database);
        ConstraintLayout statisticItem = findViewById(R.id.statistic);
        ConstraintLayout settingItem = findViewById(R.id.setting);
        NavigationView navigationView = findViewById(R.id.customNav);
        Toolbar customToolbar = findViewById(R.id.toolbar);

//        Toolbar
        setSupportActionBar(customToolbar);
        ImageView iconMenu = findViewById(R.id.iconMenu);
        mainLayout = findViewById(R.id.main_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mainLayout, customToolbar, 0, 0);
        mainLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        iconMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainLayout.isDrawerVisible(GravityCompat.START)) {
                    mainLayout.closeDrawer(GravityCompat.START);
                } else {
                    mainLayout.openDrawer(GravityCompat.START);
                }
            }
        });
//        Handle click nav items
        homeItem.setOnClickListener(view -> {
            if(CurrenFrag != FHOME){
                replaceFrag(new FragHome());
                CurrenFrag = FHOME;
            }else {
                mainLayout.closeDrawer(GravityCompat.START);
            }

        });
//        databaseItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        statisticItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        settingItem.setOnClickListener(view -> {
            if(CurrenFrag != FSETTING){
                replaceFrag(new FragSetting());
                CurrenFrag = FSETTING;
            }else {
                mainLayout.closeDrawer(GravityCompat.START);
            }
        });
// Add to Activity
        toggle.syncState();
        replaceFrag(new FragHome());
    }


    @Override
    public void onBackPressed() {
        if (mainLayout.isDrawerVisible(GravityCompat.START)) {
            mainLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void replaceFrag(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentLayout, fragment);
        transaction.commit();
    }
}