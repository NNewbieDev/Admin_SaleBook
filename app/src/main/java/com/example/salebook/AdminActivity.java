package com.example.salebook;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.salebook.fragment.FragAccountManager;
import com.example.salebook.fragment.FragBestSeller;
import com.example.salebook.fragment.FragCategoryManager;
import com.example.salebook.fragment.FragHome;
import com.example.salebook.fragment.FragProductManager;
import com.example.salebook.fragment.FragSignIn;
import com.example.salebook.fragment.FragSignUp;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity {
    private DrawerLayout mainLayout;
    private static final int FHOME = 0;
    private static final int FACCOUNTMANAGER = 1;
    private static final int FCATEGORYMANAGER = 2;
    private static final int FPRODUCTMANAGER = 3;
    private static final int FBESTSELLER = 4;
    private static final int FSIGNIN = 5;
    private static final int FSIGNUP = 6;
    private static int CurrenFrag = 0;
    private ConstraintLayout homeItem;
    private ConstraintLayout statisticItem;
    private ConstraintLayout databaseItem;
    private ConstraintLayout settingItem;
    private ImageView iconUser;
    private NavigationView navigationView;
    private Toolbar customToolbar;
    protected ImageView iconMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        iconUser = findViewById(R.id.iconUser);
        databaseItem = findViewById(R.id.database);
        statisticItem = findViewById(R.id.statistic);
        registerForContextMenu(iconUser);
        registerForContextMenu(databaseItem);
        registerForContextMenu(statisticItem);
//        declaration
        homeItem = findViewById(R.id.home);
//        set background transparent
        navigationView = findViewById(R.id.customNav);
        navigationView.setBackgroundColor(Color.TRANSPARENT);
        customToolbar = findViewById(R.id.toolbar);
//        Toolbar
        setSupportActionBar(customToolbar);
        iconMenu = findViewById(R.id.iconMenu);
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
// Add to Activity
        toggle.syncState();
        replaceFrag(new FragHome());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.iconUser) {
            getMenuInflater().inflate(R.menu.menu_user, menu);
        } else if (v.getId() == R.id.database) {
            getMenuInflater().inflate(R.menu.menu_database, menu);
        } else if (v.getId() == R.id.statistic) {
            getMenuInflater().inflate(R.menu.menu_statistic, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.accountManager) {
            if (CurrenFrag != FACCOUNTMANAGER) {
                replaceFrag(new FragAccountManager());
                CurrenFrag = FACCOUNTMANAGER;
                return true;
            } else {
                mainLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        } else if (id == R.id.categoryManager) {
            if (CurrenFrag != FCATEGORYMANAGER) {
                replaceFrag(new FragCategoryManager());
                CurrenFrag = FCATEGORYMANAGER;
                return true;
            } else {
                mainLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        } else if (id == R.id.productManager) {
            if (CurrenFrag != FPRODUCTMANAGER) {
                replaceFrag(new FragProductManager());
                CurrenFrag = FPRODUCTMANAGER;
                return true;
            } else {
                mainLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        } else if (id == R.id.bestSeller) {
            if (CurrenFrag != FBESTSELLER) {
                replaceFrag(new FragBestSeller());
                CurrenFrag = FBESTSELLER;
                return true;
            } else {
                mainLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        } else if (id == R.id.userSignIn) {
            if (CurrenFrag != FSIGNIN) {
                replaceFrag(new FragSignIn());
                CurrenFrag = FSIGNIN;
                return true;
            } else {
                return false;
            }
        } else if (id == R.id.userSignUp) {
            if (CurrenFrag != FSIGNIN) {
                replaceFrag(new FragSignUp());
                CurrenFrag = FSIGNUP;
                return true;
            } else {
                return false;
            }
        } else {
            return super.onContextItemSelected(item);
        }
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
