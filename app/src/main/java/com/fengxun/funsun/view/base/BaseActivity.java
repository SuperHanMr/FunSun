package com.fengxun.funsun.view.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fengxun.funsun.FunSunAPP;
import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.view.activity.RelationCalorieActivity;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

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
    public RelativeLayout barLeftIcon;
    private TextView barLeftTv;
    private ImageView barRightIcon;
    private TextView barRightTv;
    private SuperHanLoginDiglog diglog;
    private TextView tvTitle;
    public ImageView imageView;
    public SuperHanLoginDiglog superHanLoginDigloger;
    private ImageView centerIamgView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载布局
        setContentView(getLayoutId());

        //添加一个Activity实例
        ActivityStack.addAct(this);
        ButterKnife.bind(this);
        //设置状态栏和标题栏颜色一致
        SteBoolarUtil.setWindowStatusBarColor(this,getBoolarColors());
        mToolbar = (Toolbar) findViewById(R.id.tooblar);
        barLeftIcon = (RelativeLayout) findViewById(R.id.tooblar_left_icon);
        barLeftTv = (TextView) findViewById(R.id.tooblar_left_text);
        barRightIcon = (ImageView) findViewById(R.id.tooblar_right_icon);
        barRightTv = (TextView) findViewById(R.id.tooblar_right_text);
        tvTitle = (TextView) findViewById(R.id.tooblar_title_tv);
        imageView = (ImageView) findViewById(R.id.tooblar_iv_left_icon);
        centerIamgView = (ImageView) findViewById(R.id.tooblar_ittle_center);
        mToolbar.setBackgroundResource(getBoolarColors());
        ButterKnife.bind(this);
        initView();
    }



    /**
     * @param leftTv 左边设置文字
     */
    public void setBarLeftTv(String leftTv){
        barLeftTv.setText(leftTv);
    }



    /*
    cneteriCON
     */

    public void setCenterIamgView(){
        centerIamgView.setVisibility(View.VISIBLE);
    }

    /**
     * @return 左边的ICON
     */
    public void setBarLeftIcon(boolean is){
        barLeftIcon.setVisibility(View.VISIBLE);
    }


    public void setBarLeftIcon(boolean is,int res){
        imageView.setImageResource(res);
        barLeftIcon.setVisibility(View.VISIBLE);
    }


   public ImageView getBarRightIcon(){
       return barRightIcon;
   }




    public void setTvTitle(String title){
        tvTitle.setText(title);
    }
    public void setTvTitle(String title,int res){
        tvTitle.setTextColor(res);
        tvTitle.setText(title);
    }

    public void initView() {
        superHanLoginDigloger = new SuperHanLoginDiglog(this);
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
        LogUtils.d("------>创建dialog");
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


    /*
    修改状态栏字体颜色
     */
    public void setStatusBarTextColocr(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


    /*
       处理返回的Code
     */

    public void handlingCode(CodeBean codeBean){
        if (codeBean.getCode()==200){
            finish();
        }else {
            new SuperHanDialog(this,codeBean.getMsg()).show();
        }
    }


    /*
       弹窗 只能再Activity里面用
     */
    public void DialogPromting(String string){
      new SuperHanDialog(this,string).show();
    }


    /*
   跳转到 关系卡
     */

    public void relationStaActivity(RelationInfBean bean){
        Intent intent = new Intent(this, RelationCalorieActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
        intent.putExtras(mBundle);
        startActivity(intent);


    }



    /**
     * 修改状态栏为全透明
     *
     * @param activity
     */
    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
//        ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(true);//设置跟布局fitsystemwindow=true
    }



}
