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

public class PhoneNumActivtiy extends BaseActivity {
    @BindView(R.id.ac_phone_ed_phone)
    EditText acPhoneEdPhone;
    @BindView(R.id.ac_phone_ed_code)
    EditText acPhoneEdCode;
    @BindView(R.id.ac_phone_ed_password)
    EditText acPhoneEdPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_num;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorBooblar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        setBarLeftIcon(true);
    }

    @OnClick({R.id.ac_phone_btn_validation_code, R.id.ac_phone_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_phone_btn_validation_code:
                LogUtils.d("获取验证码");
                break;
            case R.id.ac_phone_btn:
                LogUtils.d("上传信息");
                break;
        }
    }
}
