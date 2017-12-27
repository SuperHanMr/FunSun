package com.fengxun.funsun.view.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.view.base.BaseActivity;

import com.fengxun.funsun.view.fragment.CampusFragment;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/23.
 * Holle Android
 * 学校溯源
 */

public class SchoolRootsActivity extends AppCompatActivity {


    private String schoolId;
    private String schoolNam;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_roots);
        Intent intent = getIntent();
        schoolId = intent.getStringExtra(KEY.KEY_SCHOOLID);
        schoolNam = intent.getStringExtra(KEY.KEY_SCHOOLNAME);
        initView();
    }

    public void initView() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CampusFragment fragmnet = new CampusFragment(schoolId,schoolNam,true);
        transaction.replace(R.id.ac_school_roots,fragmnet);
        transaction.commit();
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
