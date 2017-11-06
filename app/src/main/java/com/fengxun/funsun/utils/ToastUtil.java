package com.fengxun.funsun.utils;

import android.content.Context;
import android.widget.Toast;

/**
 *
 */

public class ToastUtil {

    private static String oldMsg;
    protected static Toast toast   = null;
    private static long oneTime=0;
    private static long twoTime=0;

    public static void showNormalToast(Context mContext, String str) {
        if(str == null){
            return;
        }
        if(toast==null){
            toast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
            toast.show();
            oneTime= System.currentTimeMillis();
        }else{
            twoTime= System.currentTimeMillis();
            if(str.equals(oldMsg)){
                if(twoTime-oneTime> Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = str;
                toast.setText(str);
                toast.show();
            }
        }
        oneTime=twoTime;
    }
}
