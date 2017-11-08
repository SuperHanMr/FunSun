package com.fengxun.funsun.view.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fengxun.funsun.FunSunAPP;
import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 程序员：韩永辉
 * 时间：2017/11/2
 * 联系方式：17710558669
 * Hello Android！
 * 内容 ：抽取父类的Activity
 * 1. 标题栏和状态栏一制（沉浸式标题栏）
 * 2.抽取toolbar
 * 3.网络请求
 *
 *
 */

public abstract class BaseActivity extends AutoLayoutActivity {


    private Toolbar mToolbar;
    private RelativeLayout barLeftIcon;
    private TextView barLeftTv;
    private ImageView barRightIcon;
    private TextView barRightTv;
    private SuperHanLoginDiglog diglog;
    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载布局
        setContentView(getLayoutId());
        //设置状态栏和标题栏颜色一致
        SteBoolarUtil.setWindowStatusBarColor(this,getBoolarColors());
        mToolbar = (Toolbar) findViewById(R.id.tooblar);
        barLeftIcon = (RelativeLayout) findViewById(R.id.tooblar_left_icon);
        barLeftTv = (TextView) findViewById(R.id.tooblar_left_text);
        barRightIcon = (ImageView) findViewById(R.id.tooblar_right_icon);
        barRightTv = (TextView) findViewById(R.id.tooblar_right_text);
        tvTitle = (TextView) findViewById(R.id.tooblar_title_tv);
        initView();
    }



    /**
     * @param leftTv 左边设置文字
     */
    public void setBarLeftTv(String leftTv){
        barLeftTv.setText(leftTv);
    }




    /**
     * @return 左边的ICON
     */
    public void setBarLeftIcon(boolean is){
        if (is){
            barLeftIcon.setVisibility(View.VISIBLE);
        }
    }

    public void setTvTitle(String title){
        tvTitle.setText(title);
    }

    private void initView() {
        barLeftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * @param rightTv 右边设置的文字
     */
    public void setBarRightTv(String rightTv){
        barRightTv.setText(rightTv);
    }

    /**
     * @return 右边的ICON
     */
    public void setBarRightIcon(boolean is){
        barRightIcon.setVisibility(View.VISIBLE);
    }

    /**
     * @return 布局ID
     */
    protected abstract int getLayoutId();


    /**
     * @return 状态栏颜色
     */
    protected abstract int getBoolarColors();


    public SuperHanLoginDiglog diaLogin(Context context){
        if (diglog==null){
            diglog = new SuperHanLoginDiglog(context);
        }
        return diglog;
    }

    // 子类 可以直接跳转Activity
    public void openActivity(Class<?> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        startActivity(intent);
    }


    // Toast
    public void showShart(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

}
