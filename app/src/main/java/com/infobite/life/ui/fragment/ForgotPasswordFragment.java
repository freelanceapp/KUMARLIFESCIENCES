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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.infobite.life.constant.Constant;
import com.infobite.life.modal.Text;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
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

public class ForgotPasswordFragment extends BaseFragment implements View.OnClickListener{
    private View rootview;
    private Button btn_fplogin;
    private EditText etMail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_forgot_password,container,false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootview;
    }

    private void init() {
        ((Button)rootview.findViewById(R.id.btn_fplogin)).setOnClickListener(this);
        ((TextView)rootview.findViewById(R.id.tv_back_login)).setOnClickListener(this);
        btn_fplogin = rootview.findViewById(R.id.btn_fplogin);
        etMail = rootview.findViewById(R.id.et_forgot_password_mail);
        btn_fplogin.setOnClickListener(this);
    }

    private void startFragment(String tag, Fragment fragment){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frame_container, fragment, tag).commit();
    }

    private void forgotPasswordApi() {
        if (cd.isNetWorkAvailable()) {
            String strEmail = etMail.getText().toString();
            RetrofitService.forgotPassword(new Dialog(mContext), retrofitApiClient.forgotPasswordApi(strEmail), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody responseBody = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        if (jsonObject.getString("message").equalsIgnoreCase("Check Your Email Id We Have Sent You An Email With New Password")) {
                            startFragment(Constant.LoginFragment, new LoginFragment());
                            Alerts.show(mContext,jsonObject.getString("message"));
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fplogin:
                forgotPasswordApi();
            break;
            case R.id.tv_back_login:
                startFragment(Constant.LoginFragment,new LoginFragment());
                break;
        }

    }
}
