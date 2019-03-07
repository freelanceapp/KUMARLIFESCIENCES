package com.infobite.life.ui.fragment;

import android.app.Dialog;
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
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.utils.BaseFragment;

import infobite.kumar.life.R;

import static com.infobite.life.ui.activity.LoginMainActivity.fragmentManager;

public class SignUpFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;
    private Button btn_signUp;
    private TextView fullname,emailAddress,password,cPassword;
    private String strName,strEmailAddress,strPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_signup_layout,container,false);
        init();
        return rootview;
    }

    private void init() {
        btn_signUp = rootview.findViewById(R.id.btn_signUp);
        fullname = rootview.findViewById(R.id.tv_fullname);
        emailAddress = rootview.findViewById(R.id.tv_email_address);
        password = rootview.findViewById(R.id.tv_password);
        cPassword = rootview.findViewById(R.id.tv_cpassword);


        btn_signUp.setOnClickListener(this);
        ((TextView)rootview.findViewById(R.id.tv_Login)).setOnClickListener(this);


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
            case R.id.btn_signUp:
                startFragment(Constant.LoginFragment,new LoginFragment());
                break;
            case R.id.tv_Login:
                startFragment(Constant.LoginFragment,new LoginFragment());
                break;
        }
    }
    private void signUpApi(){
        if (cd.isNetWorkAvailable()){
            strName = fullname.getText().toString();
            strEmailAddress = emailAddress.getText().toString();
            strPassword = password.getText().toString();
          //  RetrofitService.getSignUpData(new Dialog(mContext),retrofitApiClient.signUp());
        }
    }
}
