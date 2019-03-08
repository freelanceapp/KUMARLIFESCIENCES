package com.infobite.life.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.gallery_modal.Datum;

import java.util.ArrayList;

import infobite.kumar.life.R;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{
    private Context mcontext;
    private ArrayList<Datum> galleryModalList;

    public GalleryAdapter(Context mcontext, ArrayList<Datum> galleryModalList) {
        this.mcontext = mcontext;
        this.galleryModalList = galleryModalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        View rootview = layoutInflater.inflate(R.layout.row_gallery_layout,null);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Datum galleryModal = galleryModalList.get(i);
        Glide.with(mcontext).load(Constant.IMAGE_BASE_URL + galleryModal.getGalleryImage()).into(viewHolder.galleryImage);
    }
    @Override
    public int getItemCount() {
        return galleryModalList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView galleryImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryImage = itemView.findViewById(R.id.imgGallery);


        }
    }
}
