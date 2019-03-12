package com.infobite.life.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.infobite.life.adapter.AdapterConfirmation;
import com.infobite.life.constant.Constant;
import com.infobite.life.database.DatabaseHandler;
import com.infobite.life.modal.ProductDetail;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseFragment;
import com.infobite.life.utils.ConnectionDirector;
import com.infobite.life.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import infobite.kumar.life.R;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.infobite.life.ui.activity.SplashScreen.mypreference;

@SuppressLint("ValidFragment")
public class ConfirmationFragment extends BaseFragment implements View.OnClickListener {

    Context ctx;
    RecyclerView recyclerView;
    TextView total_tv,tv_payment;
    LinearLayout ordernow_ll;
    public static String Payment_Package = "";
    SessionManager sessionManager;
    public DatabaseHandler databaseCart;
    private String DATABASE_CART = "cart.db";
    //ConnectionDetector connectionDetector;
    String totalAmount1 = "0";
    String Offer_Amount = "0";
    @SuppressLint("ValidFragment")
    public ConfirmationFragment(Context ctx) {
        this.ctx = ctx;
        sessionManager = new SessionManager(ctx);
        databaseCart = new DatabaseHandler(ctx, DATABASE_CART);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirmation, null);

        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        initXml(view);
        setOrder();
        return view;
    }

    private void setOrder() {

        ArrayList<ProductDetail> orderlist = databaseCart.getAllUrlList();
        AdapterConfirmation adapter = new AdapterConfirmation(orderlist, ctx);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void initXml(View view) {
        //cart_count = AppPreference.getIntegerPreference(ctx, Constant.TOTAL_AMOUNT); //0 is the default value.

       /* SharedPreferences prefs = getActivity().getSharedPreferences(Constant.TOTAL_AMOUNT, MODE_PRIVATE);
        totalAmount = prefs.getString("Total", "0");//"No name defined" is the default value.
        Offer_Amount = prefs.getString("Offer", "0");//"No name defined" is the default value.
        Log.e("Total ",".."+totalAmount);*/

       /* totalAmount = sessionManager.getData(SessionManager.KEY_ORDER_PRICE);
        Log.e("total ",".."+totalAmount);*/

        SharedPreferences prefs = getActivity().getSharedPreferences(mypreference, MODE_PRIVATE);
        totalAmount1 = prefs.getString("total_price", "0");//"No name defined" is the default value.

        ordernow_ll = view.findViewById(R.id.ll_conforder_ordernow);
        recyclerView = view.findViewById(R.id.rv_conforder_recycler);
        total_tv = view.findViewById(R.id.tv_confirmation_total);
        tv_payment = view.findViewById(R.id.tv_payment);

        ordernow_ll.setOnClickListener(this);
        total_tv.setText("Rs. "+totalAmount1);
        tv_payment.setText("Rs. "+totalAmount1);

        /*total_tv.setText(Utility.getCartTotal(databaseCart));
        Payment_Package = Utility.getCartTotal(databaseCart);*/
        Payment_Package = totalAmount1;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ll_conforder_ordernow:
                Toast.makeText(mContext,"click",Toast.LENGTH_SHORT).show();
                orderApi();
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = getActivity().getSharedPreferences(mypreference, MODE_PRIVATE);
        totalAmount1 = prefs.getString("total_price", "0");//"No name defined" is the default value.
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            SharedPreferences prefs = getActivity().getSharedPreferences(mypreference, MODE_PRIVATE);
            totalAmount1 = prefs.getString("total_price", "0");//"No name defined" is the default value.        }
        }
    }

    private void orderApi(){
        if (cd.isNetWorkAvailable()){
            String name = AppPreference.getStringPreference(ctx, Constant.Name);
            String user_id = AppPreference.getStringPreference(ctx, Constant.User_Id);
            String email = AppPreference.getStringPreference(ctx, Constant.Email);

            String mobile = AppPreference.getStringPreference(ctx, Constant.MobileNumber);
            String address = AppPreference.getStringPreference(ctx, Constant.Address);
            String city = AppPreference.getStringPreference(ctx, Constant.City);
            String state = AppPreference.getStringPreference(ctx, Constant.State);
            String country = sessionManager.getData(SessionManager.KEY_ORDER_COUNTRY);
            String code = AppPreference.getStringPreference(ctx, Constant.PinCode);
            String paytype = sessionManager.getData(SessionManager.KEY_PAYMENT_TYPE);
            String product_id = "";
            String product_name = "";
            String company_name = "";
            String product_category = "";
            String product_sub_category = "";
            String product_qty = "";
            String product_price = null;
            float tot = 0;
             ArrayList<ProductDetail> list = databaseCart.getAllUrlList();
            for (int i = 0; i < list.size(); i++) {
                tot = list.get(i).getQuantity() * Float.parseFloat(list.get(i).getPrice());
                product_id = list.get(0).getId();
                product_qty = String.valueOf(list.get(0).getQuantity());
                company_name = list.get(0).getCategory();
                product_price = list.get(0).getPrice();
                product_name = list.get(0).getName();

            }

            RetrofitService.getOrderData(new Dialog(mContext), retrofitApiClient.order(name,user_id,"comapany_name",email,
                    address,mobile,state,city,code,product_id,product_name,product_category,"a2","image",
                    product_price,product_qty, String.valueOf(tot)), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody responseBody = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.toString());
                        if (jsonObject.getString("message").equalsIgnoreCase("Successfully Contact Us")){
                            Toast.makeText(mContext,"You have contact us Successfully",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
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
}