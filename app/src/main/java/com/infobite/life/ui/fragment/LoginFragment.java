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

import com.infobite.life.constant.Constant;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.ui.activity.HomeNavigationActivity;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseFragment;
import com.infobite.life.utils.ConnectionDirector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import infobite.kumar.life.R;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.infobite.life.ui.activity.LoginMainActivity.fragmentManager;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;
    private String strEmail, strPassword;
    private String strEmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_login_layout, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootview;
    }

    private void init() {
        Button loginbutton = rootview.findViewById(R.id.btn_login);
        loginbutton.setOnClickListener(this);
        ((TextView) rootview.findViewById(R.id.tv_forgot_password)).setOnClickListener(this);
        ((TextView) rootview.findViewById(R.id.tv_signUp)).setOnClickListener(this);
    }

    private void startFragment(String tag, Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.frame_container, fragment, tag).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginApi();
                break;
            case R.id.tv_forgot_password:
                startFragment(Constant.ForgotPasswordFragment, new ForgotPasswordFragment());
                break;
            case R.id.tv_signUp:
                startFragment(Constant.SignUpFragment, new SignUpFragment());
        }
    }

    private void loginApi() {
        if (cd.isNetWorkAvailable()) {

            strEmail = ((EditText) rootview.findViewById(R.id.et_login_email)).getText().toString();
            strPassword = ((EditText) rootview.findViewById(R.id.et_login_password)).getText().toString();
            if (strEmail.isEmpty()) {
                ((EditText) rootview.findViewById(R.id.et_login_email)).setError("Please enter email address");
            } else if (!strEmail.matches(strEmailPattern)) {
                ((EditText) rootview.findViewById(R.id.et_login_email)).setError("Please enter valid email address");
            } else if (strPassword.isEmpty()) {
                ((EditText) rootview.findViewById(R.id.et_login_password)).setError("Please enter password");
            } else if (strPassword.length() < 6) {
                ((EditText) rootview.findViewById(R.id.et_login_password)).setError("Please enter minimum 6 character password !!");
            } else {
                RetrofitService.getLoginData(new Dialog(mContext), retrofitApiClient.loginData(strEmail, strPassword), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (jsonObject.getString("message").equalsIgnoreCase("Login Success")) {
                                Toast.makeText(mContext, "login successfully", Toast.LENGTH_SHORT).show();

                                AppPreference.setBooleanPreference(mContext, Constant.Is_Login, true);
                                AppPreference.setStringPreference(mContext, Constant.Name, jsonObject.getString("full_name"));
                                AppPreference.setStringPreference(mContext, Constant.Email, jsonObject.getString("user_email"));

                                Intent intent = new Intent(mContext, HomeNavigationActivity.class);
                                intent.putExtra("email", strEmail);
                                startActivity(intent);
                                activity.finish();
                            } else {
                                Alerts.show(mContext, jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
