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
import com.infobite.life.modal.order_history_modal.Datum;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Datum> orderHistoryArrayList;
    private View.OnClickListener onClickListener;

    public OrderHistoryAdapter(Context mContext, ArrayList<Datum> orderHistoryArrayList,View.OnClickListener onClickListener) {
        this.mContext = mContext;
        this.orderHistoryArrayList = orderHistoryArrayList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View rootview = li.inflate(R.layout.row_order_history, null);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Datum orderHistoryModal = orderHistoryArrayList.get(i);
        viewHolder.ordPerName.setText(orderHistoryModal.getFirstName());
        viewHolder.ordNo.setText(orderHistoryModal.getOrderNumber());

        viewHolder.llOrderHistory.setTag(i);
        viewHolder.llOrderHistory.setOnClickListener(onClickListener);

/*
        for (int j = 0; j < orderHistoryModal.getOrderProducts().size(); j++) {
            viewHolder.tvOrderNo.setText(orderHistoryModal.getOrderNumber());
            viewHolder.orderDate.setText(orderHistoryModal.getOrderProducts().get(j).getCreatedDate());
            viewHolder.productName.setText(orderHistoryModal.getOrderProducts().get(j).getProductName());
            viewHolder.productPrice.setText(orderHistoryModal.getOrderProducts().get(j).getProductPrice());
            viewHolder.productQty.setText(orderHistoryModal.getOrderProducts().get(j).getProductQty());
            viewHolder.totalPrice.setText(orderHistoryModal.getOrderProducts().get(j).getTotalPrice());
            viewHolder.status.setText(orderHistoryModal.getOrderProducts().get(j).getProductStatus());
            if (orderHistoryModal.getOrderProducts().get(j).getProductImage() != null) {
                Glide.with(mContext).load(orderHistoryModal.getOrderProducts().get(j).getProductImage()).into(viewHolder.productImage);
            } else {
                viewHolder.productImage.setVisibility(View.GONE);
            }
        }
*/
    }

    @Override
    public int getItemCount() {
        return orderHistoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llOrderHistory;
        private TextView ordPerName,ordNo;
        private ImageView productImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ordPerName = itemView.findViewById(R.id.tv_ordered_name);
            ordNo = itemView.findViewById(R.id.tv_ordered_no_);
            llOrderHistory = itemView.findViewById(R.id.ll_orderhistory);

        }
    }
}
