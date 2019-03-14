package com.infobite.life.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.life.adapter.GalleryAdapter;
import com.infobite.life.modal.gallery_modal.Datum;
import com.infobite.life.modal.gallery_modal.GalleryMainModal;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.BaseFragment;
import com.infobite.life.utils.ConnectionDirector;

import java.util.ArrayList;

import infobite.kumar.life.R;
import retrofit2.Response;

public class GalleryFragment extends BaseFragment {
    private View rootview;
    private RecyclerView rv_gallery;
    private GalleryAdapter galleryAdapter;
    private ArrayList<Datum> galleryModalList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_gallery, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        galleryApi();
        init();
        return rootview;
    }

    private void init() {
        rv_gallery = rootview.findViewById(R.id.rv_gallery);

        galleryAdapter = new GalleryAdapter(mContext, galleryModalList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        rv_gallery.setLayoutManager(gridLayoutManager);
        rv_gallery.setItemAnimator(new DefaultItemAnimator());
        rv_gallery.setAdapter(galleryAdapter);
    }

    private void galleryApi() {
        RetrofitService.getGalleryData(new Dialog(mContext), retrofitApiClient.galleryData(), new WebResponse() {
            @Override
            public void onResponseSuccess(Response<?> result) {
                GalleryMainModal galleryMainModal = (GalleryMainModal) result.body();
                galleryModalList.clear();
                if (galleryMainModal != null) {
                    if (galleryMainModal.getData() != null) {
                        galleryModalList.addAll(galleryMainModal.getData());
                        Alerts.show(mContext, "Gallery Successfully Fatched");
                    }
                }
                galleryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onResponseFailed(String error) {
                Alerts.show(mContext, error);

            }
        });

    }

}
