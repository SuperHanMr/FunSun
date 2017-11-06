package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/6.
 * Holle Android
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.ac_login_phone)
    EditText acLoginPhone;
    @BindView(R.id.ac_login_password)
    EditText acLoginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setBarRightTv("注册");
        setBarLeftIcon(true);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorBooblar;
    }


    @OnClick({R.id.tooblar_right_text, R.id.ac_login_forgetpassword, R.id.ac_login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tooblar_right_text:
                LogUtils.d("注册账号");
               openActivity(RegistrationActivity.class);
                break;
            case R.id.ac_login_forgetpassword:
                LogUtils.d("忘记密码");
                openActivity(ForgetPasswordActivity.class);
                break;
            case R.id.ac_login_btn:
                LogUtils.d("登录"); // 登录逻辑
                break;
        }
    }


}
