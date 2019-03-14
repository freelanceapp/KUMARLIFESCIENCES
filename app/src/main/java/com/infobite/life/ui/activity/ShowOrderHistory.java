package com.infobite.life.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.infobite.life.adapter.ShowOrderListAdapter;
import com.infobite.life.modal.order_history_modal.Datum;
import com.infobite.life.modal.order_history_modal.OrderProduct;
import com.infobite.life.utils.BaseActivity;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class ShowOrderHistory extends BaseActivity implements View.OnClickListener {

    private ArrayList<OrderProduct> orderProduct = new ArrayList<>();
    TextView tvOrderNo, orderDate, productName, productPrice, productQty, totalPrice, status;
    private ImageView productImage;
    private ShowOrderListAdapter showOrderListAdapter;
    private Datum datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order_history);

        findViewById(R.id.imgBack).setOnClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.rv_show_list);
        showOrderListAdapter = new ShowOrderListAdapter(mContext, orderProduct);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(showOrderListAdapter);

        getIntentData();
    }

    private void getIntentData() {
        orderProduct.clear();
        Intent intent = getIntent();
        datum = intent.getParcelableExtra("orderhistoryarraylist");
        orderProduct.addAll(datum.getOrderProducts());
        showOrderListAdapter.notifyDataSetChanged();
    }

    private void subItemClick(View view) {
        int position = Integer.parseInt(view.getTag().toString());
        OrderProduct product = orderProduct.get(position);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
