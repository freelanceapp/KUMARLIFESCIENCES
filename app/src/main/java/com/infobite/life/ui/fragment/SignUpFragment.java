package com.infobite.life.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.Text;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.ui.activity.HomeNavigationActivity;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.BaseFragment;
import com.infobite.life.utils.ConnectionDirector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import infobite.kumar.life.R;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.infobite.life.ui.activity.LoginMainActivity.fragmentManager;

public class SignUpFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;
    private Button btn_signUp;
    private EditText fullname,emailAddress,password,cPassword;
    private String strName,strEmailAddress,strPassword,strConfirmPassword;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_signup_layout,container,false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootview;
    }

    private void init() {
        btn_signUp = rootview.findViewById(R.id.btn_signUp);
        fullname = rootview.findViewById(R.id.et_fullname);
        emailAddress = rootview.findViewById(R.id.et_email_address);
        password = rootview.findViewById(R.id.et_password);
        cPassword = rootview.findViewById(R.id.et_cpassword);
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
                signUpApi();
                break;
            case R.id.tv_Login:
                startFragment(Constant.LoginFragment,new LoginFragment());
                break;
        }
    }
    private void signUpApi(){
        if (cd.isNetWorkAvailable()) {
            strName = fullname.getText().toString();
            strEmailAddress = emailAddress.getText().toString();
            strPassword = password.getText().toString();
            strConfirmPassword = cPassword.getText().toString();

            if (strName.isEmpty()) {
                fullname.setError("Please enter fullname !!!");
            } else if (strEmailAddress.isEmpty()) {
                emailAddress.setError("Please enter email address !!!");
            } else if (!strEmailAddress.matches(emailPattern)) {
                emailAddress.setError("Please enter valid email address !!!");
            } else if (strPassword.isEmpty()) {
                password.setError("Please enter password !!!");
            } else if (strPassword.length() < 6) {
                password.setError("Password lengh must be greater than 6 letter !!!");
            } else if (!strPassword.equalsIgnoreCase(strConfirmPassword)) {
                password.setError("Password not match !!!");
            } else {
                RetrofitService.getSignUpData(new Dialog(mContext), retrofitApiClient.signUp(strName, strPassword, strEmailAddress), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (jsonObject.getString("message").equalsIgnoreCase("Successfully Sign Up")){
                                Intent intent = new Intent(mContext, HomeNavigationActivity.class);
                                startActivity(intent);
                                Toast.makeText(mContext,"signup successfully",Toast.LENGTH_SHORT).show();
                            }else{
                                Alerts.show(mContext,jsonObject.getString("message"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace(); }
                    }
                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, error);
                    }
                });
            }
        }
    }
}
