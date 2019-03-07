package com.infobite.life.ui.fragment;

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
import com.infobite.life.modal.Text;
import com.infobite.life.utils.BaseFragment;

import infobite.kumar.life.R;

import static com.infobite.life.ui.activity.LoginMainActivity.fragmentManager;

public class ForgotPasswordFragment extends BaseFragment implements View.OnClickListener{
    private View rootview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_forgot_password,container,false);
        init();
        return rootview;
    }

    private void init() {
        ((Button)rootview.findViewById(R.id.btn_fplogin)).setOnClickListener(this);
        ((TextView)rootview.findViewById(R.id.tv_back_login)).setOnClickListener(this);
    }

    private void startFragment(String tag, Fragment fragment){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frame_container, fragment, tag).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fplogin:
                startFragment(Constant.LoginFragment,new LoginFragment());
            break;
            case R.id.tv_back_login:
                startFragment(Constant.LoginFragment,new LoginFragment());
                break;
        }

    }
}
