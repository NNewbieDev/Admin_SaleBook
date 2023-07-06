package com.example.admin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.admin.fragment.FragAccountManager;
import com.example.admin.fragment.FragCategoryManager;
import com.example.admin.fragment.FragHome;
import com.example.admin.fragment.FragProductManager;
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
//        set background transparent
        NavigationView navigationView = findViewById(R.id.customNav);
        navigationView.setBackgroundColor(Color.TRANSPARENT);
        Toolbar customToolbar = findViewById(R.id.toolbar);
//        Toolbar
        setSupportActionBar(customToolbar);
        ImageView iconMenu = findViewById(R.id.iconMenu);
        mainLayout = findViewById(R.id.main_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mainLayout, customToolbar, 0, 0);
        mainLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
//        handle click menu to open nav
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
            if (CurrenFrag != FHOME) {
                replaceFrag(new FragHome());
                CurrenFrag = FHOME;
            } else {
                mainLayout.closeDrawer(GravityCompat.START);
            }

        });
        databaseItem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                PopupMenu popupMenuDatabase = new PopupMenu(MainActivity.this, databaseItem);
                popupMenuDatabase.getMenuInflater().inflate(R.menu.menu_database, popupMenuDatabase.getMenu());
                popupMenuDatabase.setForceShowIcon(true);
                popupMenuDatabase.setOnMenuItemClickListener(menuItem -> {
                    int idItem = menuItem.getItemId();
                    if (idItem == R.id.accountManager) {
                        replaceFrag(new FragAccountManager());
                        return true;
                    } else if (idItem == R.id.categoryManager) {
                        replaceFrag(new FragCategoryManager());
                        return true;
                    } else if (idItem == R.id.productManager) {
                        replaceFrag(new FragProductManager());
                        return true;
                    } else {
                        return false;
                    }

                });
                // Showing the popup menu
                popupMenuDatabase.show();
            }
        });
        statisticItem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                PopupMenu popupMenuStatistic = new PopupMenu(MainActivity.this, statisticItem);
                popupMenuStatistic.getMenuInflater().inflate(R.menu.menu_statistic, popupMenuStatistic.getMenu());
                popupMenuStatistic.setForceShowIcon(true);
                popupMenuStatistic.setOnMenuItemClickListener(menuItem -> {
                    int idItem = menuItem.getItemId();
                    if(idItem == R.id.bestSeller){

                        return true;
                    }else {
                        return false;
                    }
                });
            }
        });

        settingItem.setOnClickListener(view -> {
            if (CurrenFrag != FSETTING) {
                replaceFrag(new FragSetting());
                CurrenFrag = FSETTING;
            } else {
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

    // handle change fragment
    public void replaceFrag(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentLayout, fragment);
        transaction.commit();
    }
}