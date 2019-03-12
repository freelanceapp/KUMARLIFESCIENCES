package com.infobite.life.ui.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.infobite.life.adapter.MainCatageryAdapter;
import com.infobite.life.modal.products_modal.Datum;
import com.infobite.life.modal.products_modal.Product;
import com.infobite.life.modal.products_modal.ProductsMainModal;
import com.infobite.life.modal.products_modal.Subcategory;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.BaseActivity;

import java.util.ArrayList;

import infobite.kumar.life.R;
import retrofit2.Response;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText etSearch;
    private MainCatageryAdapter productMainCategoryAdapter;
    private ArrayList<Datum> mainCategoryList = new ArrayList<>();
    private ArrayList<Subcategory> subcategoryArrayList = new ArrayList<>();
    private ArrayList<Product> productArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        productApi();

        etSearch = findViewById(R.id.et_search);
        etSearch.setOnClickListener(this);
    }
    private void productApi() {
        if (cd.isNetWorkAvailable()) {
            RetrofitService.getProductsData(new Dialog(mContext), retrofitApiClient.productData(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ProductsMainModal categeryModal = (ProductsMainModal) result.body();
                    mainCategoryList.clear();
                    if (categeryModal != null) {
                        mainCategoryList.addAll(categeryModal.getData());
                        //  productMainCategoryAdapter.notifyDataSetChanged();
                        Alerts.show(mContext, result.message());
                    } else {
                        Alerts.show(mContext, result.message());
                    }
                    productMainCategoryAdapter.notifyDataSetChanged();
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

        }

    }
}
