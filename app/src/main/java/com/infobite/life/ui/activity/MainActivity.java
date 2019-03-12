package com.infobite.life.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.infobite.life.adapter.MainCatageryAdapter;
import com.infobite.life.modal.main_categry_products.CategeryMainModal;
import com.infobite.life.modal.main_categry_products.Datum;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.BaseActivity;

import java.util.ArrayList;

import infobite.kumar.life.R;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private RecyclerView rclvAllProducts;
    private MainCatageryAdapter mainCatageryAdapter;
    private ArrayList<Datum> mainCategeryModalArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       rclvAllProducts = findViewById(R.id.rclv_kls_allproducts);

       rclvAllProducts.setHasFixedSize(true);
       rclvAllProducts.setLayoutManager(new LinearLayoutManager(this));

     //  mainCatageryAdapter = new MainCatageryAdapter(this,mainCategeryModalArrayList);
      rclvAllProducts.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
      rclvAllProducts.setItemAnimator(new DefaultItemAnimator());
      rclvAllProducts.setAdapter(mainCatageryAdapter);
      productsApi();
    }

    private void productsApi() {
        if (cd.isNetWorkAvailable()){
            RetrofitService.getProductsData(new Dialog(mContext), retrofitApiClient.productData(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    CategeryMainModal categeryModal = (CategeryMainModal) result.body();
                    mainCategeryModalArrayList.clear();
                    if (categeryModal != null){
                       mainCategeryModalArrayList.addAll(categeryModal.getData());
                       mainCatageryAdapter.notifyDataSetChanged();
                        Alerts.show(mContext,result.message());
                    }
                    else {
                        Alerts.show(mContext,result.message());
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
