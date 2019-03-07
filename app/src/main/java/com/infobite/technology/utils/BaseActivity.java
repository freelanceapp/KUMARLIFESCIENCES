package com.infobite.technology.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.infobite.technology.R;
import com.infobite.technology.retrofit_provider.RetrofitApiClient;
import com.infobite.technology.retrofit_provider.RetrofitService;

public class BaseActivity extends AppCompatActivity {
    public Context mContext;
    public ConnectionDirector cd;
    public RetrofitApiClient retrofitApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = this;
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
    }
}
