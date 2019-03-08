package com.infobite.life.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.life.utils.BaseFragment;

import infobite.kumar.life.R;


public class IAppDetailFragment extends BaseFragment {

    private View view;
    public IAppDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_iapp_detail, container, false);


        return view;
    }
}
