package com.infobite.life.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.infobite.life.modal.products_modal.Subcategory;
import com.infobite.life.modal.subcategory_modal.Datum;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class SubCategoryProductListAdapter extends RecyclerView.Adapter<SubCategoryProductListAdapter.ViewHolder> implements View.OnClickListener{
    private View rootview;
    private Context mContext;
    private ArrayList<Datum> subcategoryArrayList;
    private View.OnClickListener onClickListener;
    private String strSubCategoryName;
    private Boolean cheked = false;
    private int pos1;
    public SubCategoryProductListAdapter(Context mContext, ArrayList<Datum> subcategoryArrayList,View.OnClickListener onClickListener , int pos1) {
        this.mContext = mContext;
        this.subcategoryArrayList = subcategoryArrayList;
        this.onClickListener = onClickListener;
        this.pos1 = pos1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        rootview = li.inflate(R.layout.row_subcategorylsit_layout, null);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Datum subcategorylist = subcategoryArrayList.get(i);

        ImageView imgSubcategory = rootview.findViewById(R.id.imgSubCategory);
        TextView nameSubcategory = rootview.findViewById(R.id.tv_subcategoryName);

        nameSubcategory.setText(subcategorylist.getSubCategoryName());
        if (subcategorylist.getSubCategoryImage() != null){

            Glide.with(mContext).load(subcategorylist.getSubCategoryImage()).into(imgSubcategory);
        }else {
            imgSubcategory.setVisibility(View.GONE);
        }

        if (pos1 == i)
        {
            viewHolder.llSubcategory.setBackgroundColor(Color.RED);
        }else {
            viewHolder.llSubcategory.setBackgroundColor(Color.WHITE);
        }
        viewHolder.llSubcategory.setTag(i);
        viewHolder.llSubcategory.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return subcategoryArrayList.size();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void check(){
        if (cheked){
            cheked = false;
            ((LinearLayout)rootview.findViewById(R.id.ll_bgchange)).setBackgroundColor(Color.TRANSPARENT);
        }else {
            cheked = true;
            ((LinearLayout)rootview.findViewById(R.id.ll_bgchange)).setBackground((mContext.getResources().getDrawable(R.drawable.bg_yellow)));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_subcategory :
                check();
            break;

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llbgYellow;
        private ImageView subcategoryImage;
        private LinearLayout llSubcategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llbgYellow = itemView.findViewById(R.id.ll_bgchange);
            llSubcategory = itemView.findViewById(R.id.ll_subcategory);
        }

    }
}
