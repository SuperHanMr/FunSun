package com.fengxun.funsun.view.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.fengxun.funsun.R;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/8.
 * Holle Android
 */

public class SuperHanLoginDiglog extends Dialog{


    private Context context;
    public SuperHanLoginDiglog(@NonNull Context context) {
        super(context,R.style.MyDialogStyle);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false); // 触摸外面不消失
        setCanceledOnTouchOutside(true);
    }
}
