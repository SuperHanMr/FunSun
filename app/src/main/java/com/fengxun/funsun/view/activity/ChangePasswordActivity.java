package com.fengxun.funsun.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.InspectionPhoneUtils;
import com.fengxun.funsun.view.base.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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

    /**
     */
    @OnClick(R.id.ac_chang_btn)
    public void onViewClicked() {
        String jinPassword = acChangJiuPassword.getText().toString();
        String xinPassword = acChangXinPassword.getText().toString();
        if (!InspectionPhoneUtils.rexCheckPassword(xinPassword)){
            DialogPromting("旧密码短");
            return;
        }

        HttpParams params = new HttpParams();
        params.put("new_password",xinPassword);
        params.put("old_password",jinPassword);

        NetworkReuset.getInstance().PostReuset(RequestUrl.WANGJIPASSWORD, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean codeBean, Call call, String string) {
                handlingCode(codeBean);
            }
        });




    }
}
