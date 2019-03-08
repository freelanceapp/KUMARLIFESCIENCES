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
import com.infobite.life.modal.main_categry_products.Datum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class MainCatageryAdapter extends RecyclerView.Adapter<MainCatageryAdapter.ViewHolder> {
    private View rootview;
    private Context mContext;
    private ArrayList<Datum> mainCategeryModalList ;

    public MainCatageryAdapter(Context mContext, ArrayList<Datum> mainCategeryModalList) {
        this.mContext = mContext;
        this.mainCategeryModalList = mainCategeryModalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        rootview = li.inflate(R.layout.row_main_categery,null);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Datum mainCategeryModal = mainCategeryModalList.get(i);
        viewHolder.mainCatageryName.setText(mainCategeryModal.getCategoryName());
        if (mainCategeryModal.getCategoryImage() != null){
            Glide.with(mContext).load(mainCategeryModal.getCategoryImage()).into(viewHolder.imgProduct);
        }else {
            viewHolder.imgProduct.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mainCategeryModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mainCatageryName;
        private ImageView imgProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainCatageryName = itemView.findViewById(R.id.tv_main_categery);
            imgProduct = itemView.findViewById(R.id.img_products);
        }
    }
}
