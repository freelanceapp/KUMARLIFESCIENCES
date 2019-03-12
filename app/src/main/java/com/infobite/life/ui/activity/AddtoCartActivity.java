package com.infobite.life.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.infobite.life.adapter.AdapterCart;
import com.infobite.life.constant.Constant;
import com.infobite.life.database.DatabaseHandler;
import com.infobite.life.database.HelperManager;
import com.infobite.life.modal.ProductDetail;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseActivity;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class AddtoCartActivity extends BaseActivity implements View.OnClickListener{
    Context ctx;
    RecyclerView recyclerView;
    Button place_bt;
    ArrayList<ProductDetail> list = new ArrayList<>();

    HelperManager helperManager;

    Activity activity;
    String user_id = "0";
    private String DATABASE_CART = "cart.db";
    public DatabaseHandler databaseCart;
    private ArrayList<ProductDetail> cartProductList = new ArrayList<>();
    private AdapterCart adapterCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto_cart);
        ctx = this;

        recyclerView = findViewById(R.id.rv_wishlist_recyclerview);
        place_bt = findViewById(R.id.bt_wishlist_placeorder);
        helperManager = new HelperManager(ctx);

        list = helperManager.readAllCart();
        place_bt.setVisibility(View.VISIBLE);
        place_bt.setOnClickListener(this);

        findViewById(R.id.btnBack).setOnClickListener(this);

        initDatabase();
        setTotal();
    }


    private void initDatabase() {
        databaseCart = new DatabaseHandler(ctx, DATABASE_CART);
        cartProductList.clear();
        if (databaseCart.getContactsCount()) {
            cartProductList.addAll(databaseCart.getAllUrlList());
        }

        adapterCart = new AdapterCart(cartProductList, ctx, this, databaseCart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterCart);
    }

    public void setTotal() {
        float total = 0;
        ArrayList<ProductDetail> total_list = databaseCart.getAllUrlList();
        //cart_number.setText("" + total_list.size());
       // AppPreference.setIntegerPreference(ctx, Constant.CART_ITEM_COUNT, total_list.size());
        for (int i = 0; i < total_list.size(); i++) {
            float pr = Float.parseFloat(total_list.get(i).getPrice());
            int qty = total_list.get(i).getQuantity();

            float tot = pr * qty;
            total += tot;
            total = Math.round(total);
        }
        place_bt.setText("Place this Order :   $" + total);
    }

    public String getTotal() {
        float total = 0;
        float round_total = 0;
        ArrayList<ProductDetail> total_list = databaseCart.getAllUrlList();
       // cart_number.setText(total_list.size());
      //  AppPreference.setIntegerPreference(ctx, Constant.CART_ITEM_COUNT, total_list.size());
        for (int i = 0; i < total_list.size(); i++) {
            float pr = Float.parseFloat(total_list.get(i).getPrice());
            int qty = total_list.get(i).getQuantity();

            float tot = pr * qty;
            total += tot;
            round_total = Math.round(total);
        }
        return String.valueOf(round_total);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_wishlist_placeorder:
               // locationPermission();
                placeThisOrder();
                break;
            case R.id.iv_adpcart_minus:
                minusItem(view);
                break;
            case R.id.iv_adpcart_plus:
                plusItem(view);
                break;
            case R.id.btnBack:
               // onBackClick();
                finish();
                break;
        }
    }

    private void plusItem(View view) {
        int pos = Integer.parseInt(view.getTag().toString());
        ProductDetail productDetail = cartProductList.get(pos);
        View v = recyclerView.getChildAt(pos);
        TextView tvQty = (TextView) v.findViewById(R.id.tv_adpcart_qty);
        ImageView minus_iv = (ImageView) v.findViewById(R.id.iv_adpcart_minus);

        int qty = Integer.parseInt(tvQty.getText().toString());
        qty++;
        productDetail.setQuantity(qty);
        databaseCart.updateUrl(productDetail);
        tvQty.setText(qty + "");
        setTotal();
        if (qty > 1) {
            minus_iv.setImageResource(R.drawable.ic_minus);
        } else {
            minus_iv.setImageResource(R.drawable.ic_delete);
        }
        //AppPreference.setIntegerPreference(ctx, Constant.CART_ITEM_COUNT, cartProductList.size());
    }

    private void minusItem(View view) {
        int pos = Integer.parseInt(view.getTag().toString());
        ProductDetail productDetail = cartProductList.get(pos);
        View v = recyclerView.getChildAt(pos);
        TextView tvQty = (TextView) v.findViewById(R.id.tv_adpcart_qty);
        ImageView minus_iv = (ImageView) v.findViewById(R.id.iv_adpcart_minus);

        int qty = Integer.parseInt(tvQty.getText().toString());
        if (qty == 1) {
            databaseCart.deleteContact(productDetail);
            cartProductList.remove(pos);
            adapterCart.notifyDataSetChanged();
        } else {
            qty--;
            productDetail.setQuantity(qty);
            databaseCart.updateUrl(productDetail);
            tvQty.setText(qty + "");
        }
        if (qty > 1) {
            minus_iv.setImageResource(R.drawable.ic_minus);
        } else {
            minus_iv.setImageResource(R.drawable.ic_delete);
        }
        setTotal();
        //AppPreference.setIntegerPreference(ctx, Constant.CART_ITEM_COUNT, list.size());
    }

    private void placeThisOrder() {
        if ( !AppPreference.getBooleanPreference(mContext, Constant.Is_Login)) {
            startActivity(new Intent(ctx, LoginMainActivity.class));
            finish();
        } else {
            ArrayList<ProductDetail> cartlist = databaseCart.getAllUrlList();
            if (cartlist.size() > 0) {
                startActivity(new Intent(ctx, CheckOutActivity.class));
                finish();
            }
        }
    }
}
