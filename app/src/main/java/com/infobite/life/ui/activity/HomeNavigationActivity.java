package com.infobite.life.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infobite.life.adapter.ExpandableListAdapter;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.MenuModel;
import com.infobite.life.ui.fragment.AboutUs;
import com.infobite.life.ui.fragment.ContactUsFragment;
import com.infobite.life.ui.fragment.GalleryFragment;
import com.infobite.life.ui.fragment.HomeFragment;
import com.infobite.life.ui.fragment.OrderHistoryFragment;
import com.infobite.life.ui.fragment.ProductsFragment;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import infobite.kumar.life.R;

public class HomeNavigationActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvProfileName, tvProfileEmail , tvLogout;
    private String strName = "", strEmail = "";
    public static Toolbar toolbar;
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private List<MenuModel> headerList = new ArrayList<>();
    private HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private TextView tvHome, tvProduct, tvGallery, tvOrderHisotry, tvAountUs, tvContactUs, tvAddtoCart;
    private FrameLayout home_content_frame;
    public static FragmentManager fragmentHomeManager;
    public static TextView cart_number;
    private RelativeLayout rlCart;
    public static int cart_count = 0;
    private NavigationView nav_view;
    private ImageView imgFb,imgInsta,imgGoogle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        cart_number = findViewById(R.id.cart_number);
        cart_number.setText("" + cart_count);
        home_content_frame = findViewById(R.id.home_content_frame);
        rlCart = findViewById(R.id.rlCart);

        if (savedInstanceState == null) {
            fragmentHomeManager = getSupportFragmentManager();
            fragmentHomeManager.beginTransaction()
                    .replace(R.id.home_content_frame, new HomeFragment()
                            , Constant.HomeFragment).commit();
        }
        replaceFragment(new HomeFragment(),Constant.HomeFragment);

        strName = AppPreference.getStringPreference(mContext, Constant.Name);
        strEmail = AppPreference.getStringPreference(mContext, Constant.Email);

        tvHome = findViewById(R.id.tvHome);
        tvProduct = findViewById(R.id.tvProduct);
        tvGallery = findViewById(R.id.tvGallery);
        tvOrderHisotry = findViewById(R.id.tvOrderHisotry);
        tvAountUs = findViewById(R.id.tvAboutus);
        tvContactUs = findViewById(R.id.tvContactus);
        tvAddtoCart = findViewById(R.id.tvAddtoCart);

        imgFb = findViewById(R.id.fb_link);
        imgFb.setOnClickListener(this);
        imgInsta = findViewById(R.id.insta_link);
        imgInsta.setOnClickListener(this);
        imgGoogle = findViewById(R.id.google_link);
        imgGoogle.setOnClickListener(this);

        nav_view = findViewById(R.id.nav_view);
        tvProfileName = findViewById(R.id.tv_profile_name);
        tvProfileEmail = findViewById(R.id.tv_profile_email);
        tvProfileName.setText(strName);
        tvProfileEmail.setText(strEmail);


        tvHome.setOnClickListener(this);
        tvProduct.setOnClickListener(this);
        tvGallery.setOnClickListener(this);
        tvOrderHisotry.setOnClickListener(this);
        tvAountUs.setOnClickListener(this);
        tvContactUs.setOnClickListener(this);
        tvAddtoCart.setOnClickListener(this);
        rlCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeNavigationActivity.this, AddtoCartActivity.class);
                startActivity(intent);
            }
        });

        expandableListView = findViewById(R.id.expandableListView);
        ((ImageView) findViewById(R.id.iv_search)).setOnClickListener(this);

        tvLogout = findViewById(R.id.tvLogout);
        tvLogout.setOnClickListener(this);


        if (AppPreference.getStringPreference(mContext,Constant.Is_Login).equals(false))
        {
            tvLogout.setText("Login");
        }else {
            tvLogout.setText("Logout");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void replaceFragment(Fragment fragment,String tag) {
        fragmentHomeManager = getSupportFragmentManager();
        toolbar.setTitle(Constant.HomeFragment);
        fragmentHomeManager.beginTransaction()
                .replace(R.id.home_content_frame, fragment
                        , tag).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment HomeFragment = fragmentHomeManager.findFragmentByTag(Constant.HomeFragment);
        Fragment InfoFragment = fragmentHomeManager.findFragmentByTag(Constant.InfoFragment);
        Fragment GalleryFragment = fragmentHomeManager.findFragmentByTag(Constant.GalleryFragment);
        Fragment OrderHistoryFragment = fragmentHomeManager.findFragmentByTag(Constant.OrderHistoryFragment);
        Fragment ContactUsFragment = fragmentHomeManager.findFragmentByTag(Constant.ContactUsFragment);
        Fragment AboutFragment = fragmentHomeManager.findFragmentByTag(Constant.AboutFragment);
        Fragment ProductsFragment = fragmentHomeManager.findFragmentByTag(Constant.ProductsFragment);
        Fragment SubCategoryFragment = fragmentHomeManager.findFragmentByTag(Constant.SubCategoryFragment);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (HomeFragment != null)
            finish();
        else if (InfoFragment != null)
            replaceFragment(new HomeFragment(),Constant.HomeFragment);
        else if (GalleryFragment != null)
            replaceFragment(new HomeFragment(),Constant.HomeFragment);
        else if (OrderHistoryFragment != null)
            replaceFragment(new HomeFragment(),Constant.HomeFragment);
        else if (ContactUsFragment != null)
            replaceFragment(new HomeFragment(),Constant.HomeFragment);
        else if (AboutFragment != null)
            replaceFragment(new HomeFragment(),Constant.HomeFragment);
        else if (ProductsFragment != null)
            replaceFragment(new HomeFragment(),Constant.HomeFragment);
        else if (SubCategoryFragment != null)
            replaceFragment(new ProductsFragment(),Constant.ProductsFragment);
        else
            super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fb_link:
                String fburl = "https://www.facebook.com/kumarlifeofficial";
                Uri strfb = Uri.parse(fburl);
                Intent urlintent = new Intent();
                urlintent.setData(strfb);
                urlintent.setAction(Intent.ACTION_VIEW);
                startActivity(urlintent);
                break;
            case R.id.insta_link:
                String instaurl = "https://www.instagram.com/kumarlifeofficial/";
                Uri strinsta = Uri.parse(instaurl);
                Intent urlintent1 = new Intent();
                urlintent1.setData(strinsta);
                urlintent1.setAction(Intent.ACTION_VIEW);
                startActivity(urlintent1);
                break;
            case R.id.google_link:
                String instaur2 = "https://plus.google.com/109736142215118837457";
                Uri strinsta2 = Uri.parse(instaur2);
                Intent urlintent2 = new Intent();
                urlintent2.setData(strinsta2);
                urlintent2.setAction(Intent.ACTION_VIEW);
                startActivity(urlintent2);
                break;
            case R.id.iv_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.tvLogout:
                AppPreference.clearAllPreferences(mContext);
                Intent intent = new Intent(mContext, LoginMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                break;
        }
        int id = view.getId();
        if (id == R.id.tvHome) {
            toolbar.setTitle(Constant.HomeFragment);
            fragmentHomeManager.beginTransaction()
                    .replace(R.id.home_content_frame, new HomeFragment()
                            , Constant.HomeFragment).commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.tvProduct) {
            toolbar.setTitle(Constant.ProductsFragment);
            fragmentHomeManager.beginTransaction()
                    .replace(R.id.home_content_frame, new ProductsFragment()
                            , Constant.ProductsFragment).commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.tvGallery) {
            toolbar.setTitle(Constant.GalleryFragment);
            fragmentHomeManager.beginTransaction()
                    .replace(R.id.home_content_frame, new GalleryFragment()
                            , Constant.GalleryFragment).commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.tvOrderHisotry) {
            toolbar.setTitle(Constant.OrderHistoryFragment);
            fragmentHomeManager.beginTransaction()
                    .replace(R.id.home_content_frame, new OrderHistoryFragment()
                            , Constant.OrderHistoryFragment).commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.tvAddtoCart) {
            Intent intent = new Intent(HomeNavigationActivity.this, AddtoCartActivity.class);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.tvAboutus) {
            toolbar.setTitle(Constant.AboutFragment);
            fragmentHomeManager.beginTransaction()
                    .replace(R.id.home_content_frame, new AboutUs()
                            , Constant.AboutFragment).commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.tvContactus) {
            toolbar.setTitle(Constant.ContactUsFragment);
            fragmentHomeManager.beginTransaction()
                    .replace(R.id.home_content_frame, new ContactUsFragment()
                            , Constant.ContactUsFragment).commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cart_count = AppPreference.getIntegerPreference(this, Constant.CART_ITEM_COUNT); //0 is the default value.
        cart_number.setText("" + cart_count);
    }
}
