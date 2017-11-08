package com.fengxun.funsun.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.CountDownTimerUtils;
import com.fengxun.funsun.utils.InspectionPhoneUtils;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.lzy.okgo.model.HttpParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/6.
 * Holle Android
 */

public class ForgetPasswordActivity extends BaseActivity {


    @BindView(R.id.ac_forget_ed_phone)
    EditText acForgetEdPhone;
    @BindView(R.id.ac_forget_ed_code)
    EditText acForgetEdCode;
    @BindView(R.id.ac_forget_ed_password)
    EditText acForgetEdPassword;
    @BindView(R.id.ac_forget_btn_validation_code)
    Button acForgetBtnValidationCode;

    private HttpParams params = new HttpParams();
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
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

    @OnClick({R.id.ac_forget_btn_validation_code, R.id.ac_forget_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_forget_btn_validation_code:
                // 获取验证码
                getverifyCode();
                break;
            case R.id.ac_forget_btn:
                // 提交修改密码
                modification();
                break;
        }
    }

    private void modification() {
        params.clear();
        String code = acForgetEdCode.getText().toString().trim();
        String password = acForgetEdPassword.getText().toString().trim();
        if (!InspectionPhoneUtils.rexCheckPassword(password)){
            new SuperHanDialog(this,"密码格式不正确").show();
            return;
        }
        params.put("account",phone);
        params.put("code",code);
        params.put("password",password);

        NetworkReuset.getInstance().PostReuset(RequestUrl.FOGETPASSWORD, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean codeBean, Call call, String string) {
                if (codeBean.getCode()==200){
                    new SuperHanDialog(ForgetPasswordActivity.this, codeBean.getMsg(), true, new SuperHanDialog.onCloseListener() {
                        @Override
                        public void onClick(Dialog dialog) {
                            openActivity(LoginActivity.class);
                            dialog.dismiss();
                            finish();
                        }
                    }).show();
                }else {
                    new SuperHanDialog(ForgetPasswordActivity.this,codeBean.getMsg()).show();
                }
            }
        });
    }


    private void getverifyCode() {

        phone = acForgetEdPhone.getText().toString().trim();
        if (!InspectionPhoneUtils.validateUserName(phone)){
            new SuperHanDialog(this,"手机号格式不正确").show();
            return;
        }
        CountDownTimerUtils  countDownTimer = new CountDownTimerUtils(acForgetBtnValidationCode, 60000, 1000);
        countDownTimer.start();
        params.put("phone_number", phone);
        params.put("code_type","forget");
        NetworkReuset.getInstance().PostReuset(RequestUrl.CODE, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean codeBean, Call call, String string) {
                if (codeBean.getCode()==200){
                    new SuperHanDialog(ForgetPasswordActivity.this,codeBean.getMsg()).show();
                }else {
                    new SuperHanDialog(ForgetPasswordActivity.this,codeBean.getMsg()).show();
                }
            }
        });
    }

}
