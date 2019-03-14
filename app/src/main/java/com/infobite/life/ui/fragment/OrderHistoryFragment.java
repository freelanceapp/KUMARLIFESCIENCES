package com.infobite.life.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.life.adapter.OrderHistoryAdapter;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.order_history_modal.Datum;
import com.infobite.life.modal.order_history_modal.OrderHistoryMainModal;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.ui.activity.ShowOrderHistory;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseFragment;
import com.infobite.life.utils.ConnectionDirector;

import java.util.ArrayList;

import infobite.kumar.life.R;
import retrofit2.Response;

public class OrderHistoryFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;
    private RecyclerView rvOrderHistory;
    private OrderHistoryAdapter orderHistoryAdapter;
    private ArrayList<Datum> orderHistoryArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_order_history, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        productApi();
        init();
        return rootview;
    }

    private void init() {
        rvOrderHistory = rootview.findViewById(R.id.rv_order_history);

        orderHistoryAdapter = new OrderHistoryAdapter(mContext, orderHistoryArrayList, this);
        rvOrderHistory.setHasFixedSize(true);
        rvOrderHistory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvOrderHistory.setAdapter(orderHistoryAdapter);
    }

    private void productApi() {
        if (cd.isNetWorkAvailable()) {
            String strUserId = AppPreference.getStringPreference(mContext, Constant.User_Id);
            RetrofitService.orderHistoryData(new Dialog(mContext), retrofitApiClient.orderHistoryData(strUserId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    OrderHistoryMainModal historyMainModal = (OrderHistoryMainModal) result.body();
                    orderHistoryArrayList.clear();
                    if (historyMainModal != null) {
                        orderHistoryArrayList.addAll(historyMainModal.getData());
                        Alerts.show(mContext, result.message());
                    } else {
                        Alerts.show(mContext, result.message());
                    }
                    orderHistoryAdapter.notifyDataSetChanged();
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
            case R.id.ll_orderhistory:
                int position = Integer.parseInt(v.getTag().toString());
                Intent intent = new Intent(mContext, ShowOrderHistory.class);
                Datum datum = orderHistoryArrayList.get(position);
                intent.putExtra("orderhistoryarraylist", (Parcelable) datum);
                startActivity(intent);
                break;
        }
    }
}
