package com.fengxun.funsun.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fengxun.funsun.R;

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


    public static void massageToast(Context context,String massage){
        Toast toast = new Toast(context);
        View layout = View.inflate(context, R.layout.massage_toast, null);
        TextView textView = (TextView) layout.findViewById(R.id.toast_mun);
//        if (num==0){
//           textView.setText("趣闻消耗太快，我们正在开采");
//        }else {
//            textView.setText("发现"+num+"条新趣闻");
//        }


        textView.setText(massage);
        toast.setView(layout);
        toast.show();
    }






}
