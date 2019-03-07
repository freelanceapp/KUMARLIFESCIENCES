package com.infobite.technology.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.infobite.technology.R;
import com.infobite.technology.ui.activity.MainActivity;
import com.infobite.technology.utils.BaseFragment;

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
