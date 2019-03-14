package com.infobite.life.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.infobite.life.constant.Constant;
import com.infobite.life.ui.fragment.LoginFragment;
import com.infobite.life.ui.fragment.SignUpFragment;

import infobite.kumar.life.R;

public class LoginMainActivity extends AppCompatActivity {
    public static  FragmentManager fragmentManager;
    private FrameLayout frameContainer;
    private TextView btnSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        frameContainer = findViewById(R.id.frame_container);

        btnSkip = findViewById(R.id.btnSkip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginMainActivity.this , HomeNavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if (savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container,new LoginFragment()
                            , Constant.LoginFragment).commit();
        }
        replaceFragment();
        }

    private void replaceFragment(){
        fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container,new LoginFragment()
                            , Constant.LoginFragment).commit();
    }
    public void onBackPressed() {

        Fragment Login_Password = fragmentManager.findFragmentByTag(Constant.LoginFragment);
        Fragment SignUp_Fragment = fragmentManager.findFragmentByTag(Constant.SignUpFragment);
        Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Constant.ForgotPasswordFragment);

        if (SignUp_Fragment != null)
            replaceFragment();
        else if (Login_Password != null)
            replaceFragment();
        else if (ForgotPassword_Fragment != null)
            replaceFragment();
        else
            super.onBackPressed();
    }

}
