package com.infobite.life.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.infobite.life.constant.Constant;
import com.infobite.life.ui.activity.HomeNavigationActivity;
import com.infobite.life.utils.BaseFragment;

import infobite.kumar.life.R;

import static com.infobite.life.ui.activity.LoginMainActivity.fragmentManager;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_login_layout,container,false);
        init();
        return rootview;
    }

    private void init() {
        mContext = getActivity();
        Button loginbutton = rootview.findViewById(R.id.btn_login);
        loginbutton.setOnClickListener(this);
        ((TextView)rootview.findViewById(R.id.tv_forgot_password)).setOnClickListener(this);
        ((TextView)rootview.findViewById(R.id.tv_signUp)).setOnClickListener(this);
    }
    private void startFragment(String tag, Fragment fragment){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.frame_container, fragment, tag).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                startActivity(new Intent(mContext, HomeNavigationActivity.class));
                break;
            case R.id.tv_forgot_password:
                startFragment(Constant.ForgotPasswordFragment,new ForgotPasswordFragment());
                break;
            case R.id.tv_signUp:
                startFragment(Constant.SignUpFragment,new SignUpFragment());
        }

    }
}
