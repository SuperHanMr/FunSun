package com.fengxun.funsun.view.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.StatusBarUtil;
import com.fengxun.funsun.view.activity.MainActivity;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/30.
 * Holle Android
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this);

        // 判断是否第一次启动APP
        boolean isFirstOpen = SPUtils.getBoolean(KEY.FIRST_OPEN, false);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen){
            Intent intent = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 1000);

    }

    private void enterHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
