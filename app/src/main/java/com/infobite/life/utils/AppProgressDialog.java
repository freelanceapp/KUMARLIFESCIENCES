package com.infobite.life.utils;

import android.app.Dialog;
import android.view.Window;

import infobite.kumar.life.R;

public class AppProgressDialog {

    public static void
    showDialog(Dialog mProcessDialog){
        try {
            if (mProcessDialog.isShowing()){
                mProcessDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mProcessDialog.setContentView(R.layout.layout_progress_bar);
                mProcessDialog.setCancelable(false);
                mProcessDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                mProcessDialog.show();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void show(Dialog mProgressDialog) {
        try {
            if (mProgressDialog.isShowing())
                return;
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setContentView(R.layout.layout_progress_bar);
            // ((TextView) mProgressDialog.findViewById(R.id.title)).setText(msg);
            mProgressDialog.setCancelable(false);
            mProgressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            // startAnim(mProgressDialog);
            mProgressDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hide(Dialog mProcessDialog){
        if (mProcessDialog  != null && mProcessDialog.isShowing()){
            mProcessDialog.dismiss();
        }
    }
}
