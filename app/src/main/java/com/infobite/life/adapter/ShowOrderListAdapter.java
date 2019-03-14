package com.infobite.life.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.infobite.life.modal.order_history_modal.OrderProduct;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class ShowOrderListAdapter extends RecyclerView.Adapter<ShowOrderListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<OrderProduct> orderProductArrayList;
    private View.OnClickListener onClickListener;

    public ShowOrderListAdapter(Context mContext, ArrayList<OrderProduct> orderProductArrayList) {
        this.mContext = mContext;
        this.orderProductArrayList = orderProductArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View rootview = li.inflate(R.layout.row_orderlist, null);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        OrderProduct orderProduct = orderProductArrayList.get(i);

        viewHolder.orderDate.setText(orderProduct.getCreatedDate());
        viewHolder.productName.setText(orderProduct.getProductName());
        viewHolder.productPrice.setText(orderProduct.getProductPrice());
        viewHolder.productQty.setText(orderProduct.getProductQty());
        viewHolder.totalPrice.setText(orderProduct.getTotalPrice());
        if (orderProduct.getProductImage() != null) {
            Glide.with(mContext).load(orderProduct.getProductImage()).into(viewHolder.productImage);
        } else {
            viewHolder.productImage.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return orderProductArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        TextView orderDate, productName, productPrice, productQty, totalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderDate = itemView.findViewById(R.id.tv_ordDate);
            productName = itemView.findViewById(R.id.tv_history_productName);
            productPrice = itemView.findViewById(R.id.tv_product_price);
            productQty = itemView.findViewById(R.id.tv_product_qty);
            totalPrice = itemView.findViewById(R.id.tv_total_price);
            productImage = itemView.findViewById(R.id.iv_product_image);
        }
    }
}
