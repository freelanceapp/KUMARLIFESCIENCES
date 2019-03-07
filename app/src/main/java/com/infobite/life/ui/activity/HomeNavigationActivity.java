package com.infobite.life.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.infobite.life.adapter.ExpandableListAdapter;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.MenuModel;
import com.infobite.life.ui.fragment.HomeFragment;
import com.infobite.life.ui.fragment.LoginFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import infobite.kumar.life.R;

public class HomeNavigationActivity extends AppCompatActivity implements View.OnClickListener {
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    boolean a = true;
    TextView tvHome,tvProduct,tvGallery,tvAountUs,tvContactUs;
    FrameLayout home_content_frame;
    public static FragmentManager fragmentManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        home_content_frame = findViewById(R.id.home_content_frame);

        if (savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.home_content_frame,new HomeFragment()
                            , Constant.HomeFragment).commit();
        }
        replaceFragment();

        tvHome = findViewById(R.id.tvHome);
        tvProduct = findViewById(R.id.tvProduct);
        tvGallery = findViewById(R.id.tvGallery);
        tvAountUs = findViewById(R.id.tvAboutus);
        tvContactUs = findViewById(R.id.tvContactus);

        tvHome.setOnClickListener(this);
        tvProduct.setOnClickListener(this);
        tvGallery.setOnClickListener(this);
        tvAountUs.setOnClickListener(this);
        tvContactUs.setOnClickListener(this);

        expandableListView = findViewById(R.id.expandableListView);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });

    }

    private void replaceFragment(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.home_content_frame,new HomeFragment()
                        , Constant.HomeFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        Fragment HomeFragment = fragmentManager.findFragmentByTag(Constant.HomeFragment);
         if (HomeFragment != null)
            replaceFragment();
         else
             super.onBackPressed();
    }


    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel("Tablets", true, false, "https://www.journaldev.com/9333/android-webview-example-tutorial"); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Capsules & Softgels", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Softgels", false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);

        childModel = new MenuModel("Capsules", false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("Syrups & Suspensions", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList1 = new ArrayList<>();
        MenuModel childModel1 = new MenuModel("Syrups", false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList1.add(childModel1);

        childModel1 = new MenuModel("Suspensions", false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList1.add(childModel1);

        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList1);
        }

        menuModel = new MenuModel("About Us", true, false, ""); //Menu of Python Tutorials
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Contact Us", true, false, ""); //Menu of Python Tutorials
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList1);
        }

    }

    private void populateExpandableList() {
        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        MenuModel model = headerList.get(groupPosition);
                        Toast.makeText(HomeNavigationActivity.this, model.getMenuName(), Toast.LENGTH_SHORT).show();

                        onBackPressed();
                    }
                }

                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    Toast.makeText(HomeNavigationActivity.this, model.getMenuName(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tvHome) {
            // Handle the camera action
            Toast.makeText(HomeNavigationActivity.this, "Home" , Toast.LENGTH_SHORT).show();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.tvProduct) {

            if (a == true)
            {
                a= false;
                expandableListView.setVisibility(View.VISIBLE);
                headerList.clear();
                childList.clear();
                prepareMenuData();
                populateExpandableList();
            }else {
                a = true;
                expandableListView.setVisibility(View.GONE);
            }
        } else if (id == R.id.tvGallery) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.tvAboutus) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.tvContactus) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
    }
}
