package com.example.salebook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.database.sqlite.SQLiteDatabase;
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

import com.example.salebook.fragment.FragAccountManager;
import com.example.salebook.fragment.FragBestSeller;
import com.example.salebook.fragment.FragCategoryManager;
import com.example.salebook.fragment.FragHome;
import com.example.salebook.fragment.FragProductManager;
import com.example.salebook.fragment.FragSetting;
import com.example.salebook.fragment.FragSignIn;

import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity {
    private DrawerLayout mainLayout;
    private static final int FHOME = 0;
    private static final int FSETTING = 1;
    private static final int FACCOUNTMANAGER = 2;
    private static final int FCATEGORYMANAGER = 3;
    private static final int FPRODUCTMANAGER = 4;
    private static final int FBESTSELLER = 5;
    private static int CurrenFrag = 0;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

//        declaration
        ConstraintLayout homeItem = findViewById(R.id.home);
        ConstraintLayout databaseItem = findViewById(R.id.database);
        ConstraintLayout statisticItem = findViewById(R.id.statistic);
        ConstraintLayout settingItem = findViewById(R.id.setting);
        ImageView iconUser = findViewById(R.id.iconUser);
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
                PopupMenu popupMenuDatabase = new PopupMenu(AdminActivity.this, databaseItem);
                popupMenuDatabase.getMenuInflater().inflate(R.menu.menu_database, popupMenuDatabase.getMenu());
                popupMenuDatabase.setForceShowIcon(true);
                popupMenuDatabase.setOnMenuItemClickListener(menuItem -> {
                    int idItem = menuItem.getItemId();
                    if (idItem == R.id.accountManager) {
                        if (CurrenFrag != FACCOUNTMANAGER) {
                            replaceFrag(new FragAccountManager());
                            CurrenFrag = FACCOUNTMANAGER;
                            return true;
                        } else {
                            mainLayout.closeDrawer(GravityCompat.START);
                            return false;
                        }
                    } else if (idItem == R.id.categoryManager) {
                        if (CurrenFrag != FCATEGORYMANAGER) {
                            replaceFrag(new FragCategoryManager());
                            CurrenFrag = FCATEGORYMANAGER;
                            return true;
                        } else {
                            mainLayout.closeDrawer(GravityCompat.START);
                            return false;
                        }
                    } else if (idItem == R.id.productManager) {
                        if(CurrenFrag != FPRODUCTMANAGER){
                            replaceFrag(new FragProductManager());
                            CurrenFrag = FPRODUCTMANAGER;
                            return true;
                        }else {
                            mainLayout.closeDrawer(GravityCompat.START);
                            return false;
                        }
                    } else {
                        mainLayout.closeDrawer(GravityCompat.START);
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
                PopupMenu popupMenuStatistic = new PopupMenu(AdminActivity.this, statisticItem);
                popupMenuStatistic.getMenuInflater().inflate(R.menu.menu_statistic, popupMenuStatistic.getMenu());
                popupMenuStatistic.setForceShowIcon(true);
                popupMenuStatistic.setOnMenuItemClickListener(menuItem -> {
                    int idItem = menuItem.getItemId();
                    if (idItem == R.id.bestSeller) {
                        if(CurrenFrag != FBESTSELLER) {
                            replaceFrag(new FragBestSeller());
                            CurrenFrag = FBESTSELLER;
                            return true;
                        }
                        else {
                            mainLayout.closeDrawer(GravityCompat.START);
                            return false;
                        }
                    } else {
                        mainLayout.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
                popupMenuStatistic.show();
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
        iconUser.setOnClickListener(view -> {
            PopupMenu popupMenuUser = new PopupMenu(AdminActivity.this, iconUser);
            popupMenuUser.getMenuInflater().inflate(R.menu.menu_user, popupMenuUser.getMenu());
            popupMenuUser.setForceShowIcon(true);
            popupMenuUser.setOnMenuItemClickListener(menuItem -> {
                int idItem = menuItem.getItemId();
                if(idItem == R.id.userSignIn ){
                    replaceFrag(new FragSignIn());
                    return true;
                }
                else if(idItem == R.id.userSignUp){
                    return true;
                }
                return false;
            });

            popupMenuUser.show();
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