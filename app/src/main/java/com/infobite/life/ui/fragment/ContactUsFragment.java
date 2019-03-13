package com.infobite.life.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.BaseFragment;
import com.infobite.life.utils.ConnectionDirector;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import infobite.kumar.life.R;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ContactUsFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;
    private EditText etname,etEmail,etSubject,etPhone,etMessage;
    private String strName = "",strEmail = "",strSubject = "",strPhone = "",strMessage = "";
    private String strEmaiPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_contact_us,container,false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootview;
    }

    private void init() {
        etname = rootview.findViewById(R.id.et_name);
        etEmail = rootview.findViewById(R.id.et_email);
        etSubject = rootview.findViewById(R.id.et_subject);
        etPhone = rootview.findViewById(R.id.et_phone);
        etMessage = rootview.findViewById(R.id.et_message);

        ((Button)rootview.findViewById(R.id.btn_send_message)).setOnClickListener(this);
        ((Button)rootview.findViewById(R.id.btn_reset_message)).setOnClickListener(this);

    }
    private void contactUsApi(){
        if (cd.isNetWorkAvailable()){

            strName = etname.getText().toString();
            strEmail = etEmail.getText().toString();
            strSubject = etSubject.getText().toString();
            strPhone = etPhone.getText().toString();
            strMessage = etMessage.getText().toString();

            if (strName.isEmpty()){
                etname.setError("Please Enter Your Name !!!");
            }else if (strEmail.isEmpty()){
                etEmail.setError("Please enter email address !!!");
            }else if (strEmail.matches(strEmaiPattern)){
                etEmail.setError("Please enter valid email address !!!");
            }else if (strSubject.isEmpty()){
                etSubject.setError("Please enter your contacting subject !!!");
            }else if (strPhone.isEmpty()){
                etPhone.setError("Please enter phone number !!!");
            }else if (strMessage.isEmpty()){
                etMessage.setError("Please enter a message !!!");
            }

            RetrofitService.getContactUsData(new Dialog(mContext), retrofitApiClient.contactUs(strName, strEmail, strPhone,
                    strSubject, strMessage), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody responseBody = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        if (jsonObject.getString("message").equalsIgnoreCase("Successfully Contact Us")){
                            Toast.makeText(mContext,"You have contact us Successfully",Toast.LENGTH_SHORT).show();
                            clearData();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext,error);
                }
            });
        }
    }
    private void clearData(){
        etname.setText("");
        etEmail.setText("");
        etSubject.setText("");
        etPhone.setText("");
        etMessage.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_message:
               contactUsApi();
                break;
            case R.id.btn_reset_message:
                clearData();
                break;
        }
    }
}
