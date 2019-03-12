package com.infobite.life.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.infobite.life.constant.Constant;
import com.infobite.life.database.DatabaseHandler;
import com.infobite.life.modal.ProductDetail;
import com.infobite.life.modal.subcategory_modal.Product;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.AppPreference;
import com.infobite.life.utils.BaseActivity;

import java.util.ArrayList;

import infobite.kumar.life.R;

import static com.infobite.life.ui.activity.HomeNavigationActivity.cart_count;
import static com.infobite.life.ui.activity.HomeNavigationActivity.cart_number;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener{
    private TextView tvname;
    private TextView tvdescription;
    private TextView tvCategory;
    private TextView tvAmount;
    private TextView tvManuDate;
    private TextView tvExDate;
    private TextView tv_add_to_cart;
    private TextView tv_total_quantity;
    private ImageView ivRemove, ivAdd,imgProduct;
    private Product productDetail;
    private ProductDetail productDetail1;
    private boolean checked = false;
    private int i = 1;
    private ImageView ivToolbar;
    private Button btnViewMore;
    private boolean check = false;

    private String DATABASE_CART = "cart.db";
    private String DATABASE_WISHLIST = "wishlist.db";
    private DatabaseHandler databaseCart, databaseWishlist;
    private ArrayList<ProductDetail> cartProductList = new ArrayList<>();

    private Button btnAddToCart;

    private Context ctx;
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
        tv_add_to_cart = findViewById(R.id.tv_add_to_cart);
       // btnAddToCart = findViewById(R.id.btnAddToCart);

        imgProduct = findViewById(R.id.img_product_detail);

       // btnAddToCart.setOnClickListener(this);
        tv_add_to_cart.setOnClickListener(this);
        ctx = this;

        databaseCart = new DatabaseHandler(ctx, DATABASE_CART);
        databaseWishlist = new DatabaseHandler(ctx, DATABASE_WISHLIST);
        cartProductList.clear();
        if (databaseCart.getContactsCount()) {
            cartProductList = databaseCart.getAllUrlList();
        }
        ivToolbar = findViewById(R.id.iv_tolbar_back);
        ivToolbar.setOnClickListener(this);
        btnViewMore = findViewById(R.id.btn_viewmore);
        btnViewMore.setOnClickListener(this);

        getIntentData();
    }
    private void moreDetail(){
        if (checked){
            checked = false;
            ((LinearLayout)findViewById(R.id.ll_moreDetail)).setVisibility(View.GONE);
        }else {
            checked = true;
            ((LinearLayout)findViewById(R.id.ll_moreDetail)).setVisibility(View.VISIBLE);
        }
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
            tvCategory.setText(productDetail.getProductSubCategory());
            tvAmount.setText(productDetail.getProductPrice());
            tvManuDate.setText(productDetail.getManufacturingDate());
            tvExDate.setText(productDetail.getExpiryDate());

            productDetail1 = new ProductDetail();
            productDetail1.setId(productDetail.getProductId());
            productDetail1.setManufacturing_date(productDetail.getModifiedDate());
            productDetail1.setDescription(productDetail.getProductDescription());
            productDetail1.setCategory(productDetail.getProductCategory());
            productDetail1.setEnd_date(productDetail.getExpiryDate());
            productDetail1.setPrice(productDetail.getProductPrice());
            productDetail1.setImage(productDetail.getProductImage().get(0).getProductImage());
            productDetail1.setName(productDetail.getProductType());
            productDetail1.setQuantity(1);
           // productDetail1.setQuantity(Integer.parseInt(productDetail.getQuantityType()));

        } else {
            Alerts.show(mContext, "error in data fatchd");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_tolbar_back:
                finish();
                break;
            case R.id.btn_viewmore:
                moreDetail();
                break;
            case R.id.tv_add_to_cart :
                addtoCart();
                break;
        }

    }

    /*private void addQuantity(){
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



    private void addtoCart() {
        /*********************************************************************************************************/
        if (databaseCart.getContactsCount()) {
            cartProductList = databaseCart.getAllUrlList();
        }

        if (cartProductList.size() > 2) {
            //Alerts.show(this, "Cart full");
            Toast.makeText(ctx, "Cart full", Toast.LENGTH_SHORT).show();
        } else {
            if (cartProductList.size() > 0) {
                if (databaseCart.verification(productDetail1.getId())) {
                    //Alerts.show(ctx, "Already added to Cart");
                    Toast.makeText(ctx, "Already added to Cart", Toast.LENGTH_SHORT).show();
                } else {
                   // productDetail.setSelected_size(selected_size);
                   // productDetail.setSelected_color(selected_color);
                    cart_count = cart_count + 1;
                    cart_number.setText("" + cart_count);
                    AppPreference.setIntegerPreference(ctx, Constant.CART_ITEM_COUNT, cart_count);
                    Toast.makeText(ctx, "Added to Cart", Toast.LENGTH_SHORT).show();
                    //Alerts.show(ctx, "Added to Cart");
                    databaseCart.addItemCart(productDetail1);
                    Intent intent = new Intent(ProductDetailActivity.this , AddtoCartActivity.class);
                    startActivity(intent);
                }
            } else {
               // productDetail.setSelected_size(selected_size);
               // productDetail.setSelected_color(selected_color);
                cart_count = cart_count + 1;
                cart_number.setText("" + cart_count);
                AppPreference.setIntegerPreference(ctx, Constant.CART_ITEM_COUNT, cart_count);
                Toast.makeText(ctx, "Added to Cart", Toast.LENGTH_SHORT).show();
                //Alerts.show(ctx, "Added to Cart");
                databaseCart.addItemCart(productDetail1);
                Intent intent = new Intent(ProductDetailActivity.this , AddtoCartActivity.class);
                startActivity(intent);
            }
        }

    }
}
