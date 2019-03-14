package com.infobite.life.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import java.util.List;

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
    private String strSubcateogryName;
    private Boolean checked = false;
    private FloatingActionButton backArrow;
    private TextView tvSubcategoryname;
    private int position = 0;

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
        backArrow = rootview.findViewById(R.id.icback_subcategory);
        tvSubcategoryname = rootview.findViewById(R.id.tv_subcaname);
        backArrow.setOnClickListener(this);

        subCategoryProductListAdapter = new SubCategoryProductListAdapter(mContext, subcategoryArrayList, this , position);
        rvSubcategory.setHasFixedSize(true);
        rvSubcategory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvSubcategory.setAdapter(subCategoryProductListAdapter);

        productAdapter = new ProductListAdapter(mContext, productArrayList, this );
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
                        Alerts.show(mContext, result.message());
                        if (subcategoryArrayList.size() > 0) {
                            ((TextView) rootview.findViewById(R.id.tv_subcaname)).
                                    setText(subcategoryArrayList.get(0).getSubCategoryName() + " : Product");
                            firstCategory(subcategoryArrayList.get(0).getProducts());
                        }
                    } else {
                        Alerts.show(mContext, result.message());
                    }
                    subCategoryProductListAdapter.notifyDataSetChanged();

                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }


    private void firstCategory(List<Product> arrayList) {
        productArrayList.clear();
        productArrayList.addAll(arrayList);
        productAdapter.notifyDataSetChanged();
    }

    private void startFragment(Fragment fragment) {
        toolbar.setTitle(Constant.ProductsFragment);
        fragmentHomeManager.beginTransaction()
                .replace(R.id.home_content_frame, new ProductsFragment()
                        , Constant.ProductsFragment).commit();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icback_subcategory:
                startFragment(new ProductsFragment());
                break;
            case R.id.ll_subcategory:
                position = Integer.parseInt(v.getTag().toString());

               /* View view = rvSubcategory.getChildAt(position);
                LinearLayout ll_subcategory = view.findViewById(R.id.ll_subcategory);
                ll_subcategory.setBackgroundColor(Color.RED);*/

                strSubcateogryName = subcategoryArrayList.get(position).getSubCategoryName();
                tvSubcategoryname.setText(strSubcateogryName + " : Product");
                AppPreference.setStringPreference(mContext, Constant.SubcategoryName, strSubcateogryName);
                productArrayList.clear();
                productArrayList.addAll(subcategoryArrayList.get(position).getProducts());
                productAdapter.notifyDataSetChanged();
                break;
            case R.id.ll_product:

                int position1 = Integer.parseInt(v.getTag().toString());
                String strProductId = productArrayList.get(position1).getProductId();
                String strProductName = productArrayList.get(position1).getProductType();
                String strProductQuantity = productArrayList.get(position1).getProductQty();
                String strProductPrice = productArrayList.get(position1).getProductPrice();
                String strProductImage = productArrayList.get(position1).getProductImage().get(position1).getProductImage();

                AppPreference.setStringPreference(mContext, Constant.ProductId, strProductId);
                AppPreference.setStringPreference(mContext, Constant.ProductName, strProductName);
                AppPreference.setStringPreference(mContext, Constant.ProductQuantity, strProductQuantity);
                AppPreference.setStringPreference(mContext, Constant.ProductPrice, strProductPrice);
                AppPreference.setStringPreference(mContext, Constant.ProductImage, strProductImage);

                Intent intent1 = new Intent(mContext, ProductDetailActivity.class);
                intent1.putExtra("subcategoryName", subcategoryArrayList.get(position1).getSubCategoryName());
                intent1.putExtra("productArrayList", (Parcelable) productArrayList.get(position1));
                startActivity(intent1);
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
