package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.bean.LoginBean;
import com.fengxun.funsun.model.bean.RegistrationUserBean;
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

public class PhoneNumActivtiy extends BaseActivity {
    @BindView(R.id.ac_phone_ed_phone)
    EditText acPhoneEdPhone;
    @BindView(R.id.ac_phone_ed_code)
    EditText acPhoneEdCode;
    @BindView(R.id.ac_phone_ed_password)
    EditText acPhoneEdPassword;
    @BindView(R.id.ac_phone_btn_validation_code)
    Button acPhoneBtnValidationCode;

    private HttpParams params = new HttpParams();
    private RegistrationUserBean bean;

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
        bean = (RegistrationUserBean) getIntent().getSerializableExtra(RegistrationActivity.USERINFO);
        LogUtils.d(bean.toString());
    }

    @OnClick({R.id.ac_phone_btn_validation_code, R.id.ac_phone_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_phone_btn_validation_code:
                LogUtils.d("获取验证码");
                getverifyCode();
                break;
            case R.id.ac_phone_btn:
                registrationUser();
                break;
        }
    }

    /**
     *
     */
    private void registrationUser() {

        String phone = acPhoneEdPhone.getText().toString().trim();
        if (!InspectionPhoneUtils.validateUserName(phone)) {
            new SuperHanDialog(this,"手机号格式不正确").show();
            return;
        }

        String code = acPhoneEdCode.getText().toString().trim();
        if (code.equals("")){
            new SuperHanDialog(this,"验证码为空").show();
            return;
        }

        String password = acPhoneEdPassword.getText().toString();
        if (!InspectionPhoneUtils.rexCheckPassword(password)){
            new SuperHanDialog(this,"密码格式不正确").show();
            return;
        }

        // 提交参数
        params.put("phone",phone);
        params.put("code",code);
        params.put("password",password);
        params.put("avatar",bean.avatar);
        params.put("nick",bean.nick);
        params.put("sex",bean.sex);
        params.put("school",bean.school);
        params.put("admission",bean.admission);

        NetworkReuset.getInstance().PostReuset(RequestUrl.REGISTRATION, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean codeBean, Call call, String string) {
                isCode(codeBean);
            }
        });

    }

    private void getverifyCode() {
        String phone = acPhoneEdPhone.getText().toString().trim();
        if (!InspectionPhoneUtils.validateUserName(phone)) {
            new SuperHanDialog(this,"手机号格式不正确").show();
            return;
        }

        CountDownTimerUtils countDownTimer = new CountDownTimerUtils(acPhoneBtnValidationCode, 60000, 1000);
        countDownTimer.start();
        params.put("phone_number",phone);
        params.put("code_type","register");
        NetworkReuset.getInstance().PostReuset(RequestUrl.CODE, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean codeBean, Call call, String string) {
                isCode(codeBean);
            }
        });
    }

    private void isCode(CodeBean codeBean){
        if (codeBean.getCode()==200){
            // TODO: 注册成功 跳转我的个人页面
        }else {
            new SuperHanDialog(this,codeBean.getMsg()).show();
        }
    }


}
