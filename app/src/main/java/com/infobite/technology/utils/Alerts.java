package com.infobite.technology.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class Alerts {
    public static void show(Context mContext,String message){
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT);
    }
}
