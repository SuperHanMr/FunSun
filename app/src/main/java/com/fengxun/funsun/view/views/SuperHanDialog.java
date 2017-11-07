package com.fengxun.funsun.view.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.LoginBean;
import com.fengxun.funsun.model.request.onCallBack;


/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/7.
 * Holle Android
 */

public class SuperHanDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private String mag;
    private RelativeLayout tv;
    private TextView tvConent;
    private onCloseListener listener;

    public SuperHanDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public SuperHanDialog(Context context, String mag){
        super(context, R.style.dialog);
        this.mContext = context;
        this.mag = mag;

    }


    public SuperHanDialog(Context context, int themeResId, String mag) {
        super(context, themeResId);
        this.mContext = context;
        this.mag = mag;
    }

    public SuperHanDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;

    }

    protected SuperHanDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.superhan_dialog);
        setCanceledOnTouchOutside(false); // 触摸外面不消失
        initView();
    }

    private void initView() {
        tv = (RelativeLayout) findViewById(R.id.superhan);
        tvConent = (TextView) findViewById(R.id.superhan_content);
        tvConent.setText(mag);
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.superhan:
                dismiss();
                break;
        }
    }

    public interface onCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }

}
