package com.infobite.life.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.infobite.life.modal.subcategory_modal.Datum;
import com.infobite.life.modal.subcategory_modal.Product;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private Context mcontext;
    private View rootview;
    private ArrayList<Product> productArrayList;
    private View.OnClickListener onClickListener;

    public ProductListAdapter(Context mcontext, ArrayList<Product> productArrayList,View.OnClickListener onClickListener) {
        this.mcontext = mcontext;
        this.productArrayList = productArrayList;
        this.onClickListener = onClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mcontext);
        rootview = li.inflate(R.layout.row_productlist_layout,null);

        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Product productlist = productArrayList.get(i);

        ImageView imgProducts = rootview.findViewById(R.id.imgMainProduct);
        TextView nameProducts = rootview.findViewById(R.id.tv_productName);

        nameProducts.setText(productlist.getProductType());
        if (productlist.getProductImage().get(i).getProductImage() != null){
            Glide.with(mcontext).load(productlist.getProductImage().get(i).getProductImage()).into(imgProducts);
        }else {
            imgProducts.setVisibility(View.GONE);
        }
        viewHolder.llProduct.setTag(i);
        viewHolder.llProduct.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llProduct = itemView.findViewById(R.id.ll_product);

        }
    }
}
