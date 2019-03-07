package com.infobite.life.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.infobite.life.utils.BaseFragment;

import infobite.kumar.life.R;

public class LoginFragment extends BaseFragment {
    private View rootview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_login_layout,container,false);
        init();
        return rootview;
    }

    private void init() {
        mContext = getActivity();
        Button loginbutton = rootview.findViewById(R.id.btn_login);
    }
}
