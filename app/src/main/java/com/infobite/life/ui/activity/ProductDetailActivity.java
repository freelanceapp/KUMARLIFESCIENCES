package com.infobite.life.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.infobite.life.modal.subcategory_modal.Product;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.BaseActivity;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener{
    private TextView tvname;
    private TextView tvdescription;
    private TextView tvCategory;
    private TextView tvAmount;
    private TextView tvManuDate;
    private TextView tvExDate;
    private TextView tv_total_quantity;
    private ImageView ivRemove, ivAdd,imgProduct;
    private Product productDetail;
    private boolean checked = false;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        tvname = findViewById(R.id.tv_detail_productname);
        tvdescription = findViewById(R.id.tv_detail_description);
        tvCategory = findViewById(R.id.tv_detail_category);
        tvAmount = findViewById(R.id.tv_detail_amount);
        tvManuDate = findViewById(R.id.tv_detail_manufacturingDate);
        tvExDate = findViewById(R.id.tv_detail_expiry_date);

        imgProduct = findViewById(R.id.img_product_detail);
        ivRemove = findViewById(R.id.iv_remove);
        ivRemove.setOnClickListener(this);
        ivAdd = findViewById(R.id.iv_add);
        ivAdd.setOnClickListener(this);
        tv_total_quantity = findViewById(R.id.tv_total_quantity);
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        productDetail = intent.getParcelableExtra("productArrayList");
        if (productDetail != null) {
                  for (int i=0; i< productDetail.getProductImage().size(); i++) {
                      if (productDetail.getProductImage().get(i).getProductImage() != null) {
                          Glide.with(mContext).load(productDetail.getProductImage().get(i).getProductImage()).into(imgProduct);
                      } else {
                          imgProduct.setVisibility(View.GONE);
                      }
                  }
            tvname.setText(productDetail.getProductType());
            tvdescription.setText(productDetail.getProductDescription());
            tvCategory.setText(productDetail.getProductCategory());
            tvAmount.setText(productDetail.getProductPrice());
            tvManuDate.setText(productDetail.getManufacturingDate());
            tvExDate.setText(productDetail.getExpiryDate());
        } else {
            Alerts.show(mContext, "error in data fatchd");
        }


    }

   /* private void addQuantity(){
        if (checked){
            checked = true;
             i =i++;
            tv_total_quantity.setText(String.valueOf(i));

        }else {
            checked = false;
            tv_total_quantity.setText(String.valueOf(i));
        }
    }
    private void removeQuantity(){
        if (checked){
            checked = true;
             i =i--;
            tv_total_quantity.setText(String.valueOf(i));

        }else {
            checked = false;
            tv_total_quantity.setText(String.valueOf(i));
        }
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add:
             //   addQuantity();
                break;
            case R.id.iv_remove:
             //   removeQuantity();
                break;
        }
    }
}
