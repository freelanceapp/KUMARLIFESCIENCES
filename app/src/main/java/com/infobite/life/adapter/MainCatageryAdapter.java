package com.infobite.life.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.products_modal.Datum;
import com.infobite.life.utils.AppPreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class MainCatageryAdapter extends RecyclerView.Adapter<MainCatageryAdapter.ViewHolder> {
    private View rootview;
    private Context mContext;
    private ArrayList<Datum> mainCategoryList ;
    private View.OnClickListener onClickListener;
    private String strCategoryId;

    public MainCatageryAdapter(Context mContext, ArrayList<Datum> mainCategoryList, View.OnClickListener onClickListener) {
        this.mContext = mContext;
        this.mainCategoryList = mainCategoryList;
        this.onClickListener = onClickListener;
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
        Datum mainCategeryModal = mainCategoryList.get(i);

        viewHolder.mainCatageryName.setText(mainCategeryModal.getCategoryName());
        final ImageView imageView = (ImageView) rootview.findViewById(R.id.img_products);
        Glide.with(mContext).load(mainCategeryModal.getCategoryImage())
                .into(imageView);

      //  AppPreference.setStringPreference(mContext,Constant.CategeryId,mainCategeryModal.getCategoryId());

        viewHolder.rlMainCategory.setTag(i);
        viewHolder.rlMainCategory.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return mainCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mainCatageryName;
        private ImageView imgProduct;
        private RelativeLayout rlMainCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainCatageryName = itemView.findViewById(R.id.tv_main_categery);
            imgProduct = itemView.findViewById(R.id.img_products);
            rlMainCategory = itemView.findViewById(R.id.rl_mainCategory);
        }
    }
}
