package com.infobite.life.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.infobite.life.adapter.SearchListAdapter;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.products_modal.Subcategory;
import com.infobite.life.modal.search_model.Datum;
import com.infobite.life.modal.search_model.SearchModel;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import infobite.kumar.life.R;
import retrofit2.Response;

public class SearchActivity extends BaseActivity implements View.OnClickListener, SearchListAdapter.SearchAdapterListener {

    private List<Datum> allUserLists = new ArrayList<>();
    private RecyclerView gridDetailrclv;
    private SearchListAdapter searchListAdapter;
    private EditText edtSearch;
    private String strUserId = "";
    private ImageView backActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

    }


    private void init() {
        strUserId = AppPreference.getStringPreference(mContext, Constant.User_Id);
        edtSearch = findViewById(R.id.et_search);
        //findViewById(R.id.imgBack).setOnClickListener(this);

        gridDetailrclv = findViewById(R.id.gridDetailrclv);
        backActivity = findViewById(R.id.ic_back_search);
        backActivity.setOnClickListener(this);
        gridDetailrclv.setHasFixedSize(true);

        searchListAdapter = new SearchListAdapter(allUserLists, mContext, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
        gridDetailrclv.setLayoutManager(gridLayoutManager);
        gridDetailrclv.setItemAnimator(new DefaultItemAnimator());
        gridDetailrclv.setAdapter(searchListAdapter);

        productApi();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchListAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void productApi() {
        if (cd.isNetWorkAvailable()) {
            RetrofitService.getSearchData(new Dialog(mContext), retrofitApiClient.searchData(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    SearchModel categeryModal = (SearchModel) result.body();
                    allUserLists.clear();
                    if (categeryModal != null) {
                        allUserLists.addAll(categeryModal.getData());
                        //  productMainCategoryAdapter.notifyDataSetChanged();
                        Alerts.show(mContext, result.message());
                    } else {
                        Alerts.show(mContext, result.message());
                    }
                    searchListAdapter.notifyDataSetChanged();
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
            case R.id.ic_back_search:
                finish();
                break;
        }

    }

    @Override
    public void onSearchSelected(Datum contact) {
        /*Intent postUserId = new Intent(mContext, UserProfileActivity.class);
        postUserId.putExtra("fan_id", contact.getUserId());
        startActivity(postUserId);*/
    }
}
