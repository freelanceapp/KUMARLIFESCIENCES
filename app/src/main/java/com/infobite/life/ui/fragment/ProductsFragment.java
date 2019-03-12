package com.infobite.life.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.life.adapter.MainCatageryAdapter;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.products_modal.Datum;
import com.infobite.life.modal.products_modal.Product;
import com.infobite.life.modal.products_modal.ProductsMainModal;
import com.infobite.life.modal.products_modal.Subcategory;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseFragment;
import com.infobite.life.utils.ConnectionDirector;

import java.util.ArrayList;

import infobite.kumar.life.R;
import retrofit2.Response;

import static com.infobite.life.ui.activity.HomeNavigationActivity.fragmentHomeManager;
import static com.infobite.life.ui.activity.HomeNavigationActivity.toolbar;

public class ProductsFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;
    private RecyclerView rvProductCategory;
    private MainCatageryAdapter productMainCategoryAdapter;
    private ArrayList<Datum> mainCategoryList = new ArrayList<>();
    private ArrayList<Subcategory> subcategoryArrayList = new ArrayList<>();
    private ArrayList<Product> productArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_products, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        productApi();
        init();
        return rootview;
    }

    private void init() {
        mContext = getActivity();
        rvProductCategory = rootview.findViewById(R.id.rv_product_maincategory);

        productMainCategoryAdapter = new MainCatageryAdapter(mContext, mainCategoryList, this);
        rvProductCategory.setHasFixedSize(true);
        rvProductCategory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvProductCategory.setAdapter(productMainCategoryAdapter);
    }

    private void startFragment(Fragment fragment) {
        toolbar.setTitle(Constant.SubCategoryFragment);
        fragmentHomeManager.beginTransaction()
                .replace(R.id.home_content_frame, fragment
                        , Constant.SubCategoryFragment).commit();
    }

  /*  private void sendFragmentData() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MainCategoryArrayList", mainCategoryList);
        SubCategoryFragment fragment = new SubCategoryFragment();
        fragment.setArguments(bundle);
      // startFragment(fragment);
    }*/

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
        switch (v.getId()) {
            case R.id.rl_mainCategory:
                int position = Integer.parseInt(v.getTag().toString());
                String strCatId = mainCategoryList.get(position).getCategoryId();
                String strCategoryName = mainCategoryList.get(position).getCategoryName();
                AppPreference.setStringPreference(mContext,Constant.CategeryId,strCatId);
                AppPreference.setStringPreference(mContext,Constant.CategoryName,strCategoryName);

                startFragment(new SubCategoryFragment());
                break;
        }
    }
}
