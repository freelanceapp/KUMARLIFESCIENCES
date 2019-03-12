package com.infobite.life.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.life.adapter.ProductListAdapter;
import com.infobite.life.adapter.SubCategoryProductListAdapter;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.subcategory_modal.Datum;
import com.infobite.life.modal.subcategory_modal.Product;
import com.infobite.life.modal.subcategory_modal.SubcategoryMainModal;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.ui.activity.ProductDetailActivity;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseFragment;
import com.infobite.life.utils.ConnectionDirector;

import java.util.ArrayList;

import infobite.kumar.life.R;
import retrofit2.Response;

import static com.infobite.life.ui.activity.HomeNavigationActivity.fragmentHomeManager;
import static com.infobite.life.ui.activity.HomeNavigationActivity.toolbar;

public class SubCategoryFragment extends BaseFragment implements View.OnClickListener {
    ArrayList<Parcelable> mainCategoryList;
    private View rootview;
    private RecyclerView rvSubcategory, rvProducts;
    private SubCategoryProductListAdapter subCategoryProductListAdapter;
    private ProductListAdapter productAdapter;
    private ArrayList<Datum> subcategoryArrayList = new ArrayList<>();
    private ArrayList<Product> productArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_subcategory_product_list, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        subCategoryApi();
        init();
        return rootview;
    }

    private void init() {
        rvSubcategory = rootview.findViewById(R.id.rv_subCategory);
        rvProducts = rootview.findViewById(R.id.rv_product);

        subCategoryProductListAdapter = new SubCategoryProductListAdapter(mContext, subcategoryArrayList, this);
        rvSubcategory.setHasFixedSize(true);
        rvSubcategory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvSubcategory.setAdapter(subCategoryProductListAdapter);

        productAdapter = new ProductListAdapter(mContext, productArrayList, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        rvProducts.setLayoutManager(gridLayoutManager);
        rvProducts.setItemAnimator(new DefaultItemAnimator());
        rvProducts.setAdapter(productAdapter);
    }

    private void subCategoryApi() {
        if (cd.isNetWorkAvailable()) {

            String strId = AppPreference.getStringPreference(mContext, Constant.CategeryId);

            RetrofitService.getSubcateogryData(new Dialog(mContext), retrofitApiClient.subCategoryData(strId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    SubcategoryMainModal subcategeryModal = (SubcategoryMainModal) result.body();
                    subcategoryArrayList.clear();
                    productArrayList.clear();
                    if (subcategeryModal != null) {
                        subcategoryArrayList.addAll(subcategeryModal.getData());
                        for (int i = 0; i < subcategeryModal.getData().size(); i++) {
                            productArrayList.addAll(subcategeryModal.getData().get(i).getProducts());
                        }
                        Alerts.show(mContext, result.message());
                    } else {
                        Alerts.show(mContext, result.message());
                    }
                    subCategoryProductListAdapter.notifyDataSetChanged();
                    productAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void startFragment(Fragment fragment) {
        toolbar.setTitle(Constant.ProductDetailFragment);
        fragmentHomeManager.beginTransaction()
                .replace(R.id.home_content_frame, fragment
                        , Constant.ProductDetailFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_product:
                int position = Integer.parseInt(v.getTag().toString());
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                intent.putExtra("productArrayList", (Parcelable) productArrayList.get(position));
                startActivity(intent);
                break;
        }
    }
   /* private void getBundleData(){
        subcategoryArrayList.clear();
        productArrayList.clear();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mainCategoryList = bundle.getParcelableArrayList("MainCategoryArrayList");
        }
        subCategoryProductListAdapter.notifyDataSetChanged();
        productAdapter.notifyDataSetChanged();
    }*/
}
