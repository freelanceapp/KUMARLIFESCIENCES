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
    public static void hide(Dialog mProcessDialog){
        if (mProcessDialog  != null && mProcessDialog.isShowing()){
            mProcessDialog.dismiss();
        }
    }
}
