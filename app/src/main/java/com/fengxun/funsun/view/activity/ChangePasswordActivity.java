package com.fengxun.funsun.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/10.
 * Holle Android
 */

public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.ac_chang_jiu_password)
    EditText acChangJiuPassword;
    @BindView(R.id.ac_chang_xin_password)
    EditText acChangXinPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_changpassword;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorbWhite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setStatusBarTextColocr();
        ButterKnife.bind(this);
        setBarLeftIcon(true,R.drawable.dingbuback);
        setTvTitle("修改密码",R.color.colorbBlack);
    }

    @OnClick(R.id.ac_chang_btn)
    public void onViewClicked() {

    }
}
