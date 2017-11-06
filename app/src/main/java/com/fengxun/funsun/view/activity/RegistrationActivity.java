package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

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
 * 内容 填写基本信息
 */

public class RegistrationActivity extends BaseActivity {
    @BindView(R.id.ac_registration_name)
    EditText acRegistrationName;
    @BindView(R.id.ac_registration_rb)
    RadioGroup acRegistrationRb;

    @BindView(R.id.ac_registration_time)
    TextView acRegistrationTime;
    @BindView(R.id.ac_registration_location)
    TextView acRegistrationLocation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_registration;
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

    /**
     * @param view
     */
    @OnClick({R.id.ac_registration_btn, R.id.ac_registration_paizaho,R.id.ac_registration_time,R.id.ac_registration_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_registration_btn:
                openActivity(PhoneNumActivtiy.class);
                break;
            case R.id.ac_registration_paizaho:
                LogUtils.d("拍照");
                break;
            case R.id.ac_registration_location:
                openActivity(LocationActivity.class);
                LogUtils.d("选择学校");
                break;
            case R.id.ac_registration_time:
                LogUtils.d("选择入校时间");
                break;
        }
    }
}
