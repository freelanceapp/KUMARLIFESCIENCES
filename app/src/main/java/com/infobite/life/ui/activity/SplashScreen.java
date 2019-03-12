package com.infobite.life.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.infobite.life.constant.Constant;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseActivity;
import com.squareup.picasso.Picasso;

import infobite.kumar.life.R;

public class SplashScreen extends BaseActivity {
    private ImageView imgSplash;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imgSplash = findViewById(R.id.img_splash);

        Glide.with(mContext)
                .load("http://kumarlifesciences.com/images/preloaders/1.gif")
                .into(imgSplash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppPreference.getBooleanPreference(mContext, Constant.Is_Login))
                {
                   startActivity(new Intent(mContext,HomeNavigationActivity.class));
                   finish();
                } else if (AppPreference.getBooleanPreference(mContext,Constant.Is_SignUp)){
                    startActivity(new Intent(mContext, HomeNavigationActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(mContext, LoginMainActivity.class));
                    finish();
                }
            }
        }, 3000);
    }

}
