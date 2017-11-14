package com.fengxun.funsun.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.LoginBean;
import com.fengxun.funsun.model.eventbus.MainActivityEventBus;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.view.base.ActivityStack;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;
import com.lzy.okgo.model.HttpParams;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
                loginRequest();

                break;
        }
    }

    /**
     *
     */
    private void loginRequest() {
        HttpParams params = new HttpParams();
        String phone = acLoginPhone.getText().toString().trim();
        String password = acLoginPassword.getText().toString().trim();
        params.put("account",phone);
        params.put("password",password);
        NetworkReuset.getInstance().PostReuset(RequestUrl.LOGIN, params, new onCallBack<LoginBean>(this) {
            @Override
            public void onSucceed(LoginBean loginBean, Call call, String string) {
                if (loginBean.getCode()!=400){
                    //保用户信息持久化
                    SPUtils.putBoolean(KEY.KEY_ISLOGIN,true);
                    SPUtils.putString(KEY.KEY_USERTOKEN,loginBean.getData().getAccess_token());
                    SPUtils.putString(KEY.KEY_REFRESHTOKEN,loginBean.getData().getRefresh_token());
                    SPUtils.putString(KEY.KEY_USERSCHOOL,loginBean.getData().getUser_info().getSchool_name());
                    SPUtils.putString(KEY.KEY_UNIVERSITY,loginBean.getData().getUser_info().getSchool_img());
                    SPUtils.putString(KEY.KEY_USERNAME,loginBean.getData().getUser_info().getNick());
                    SPUtils.putString(KEY.KEY_USERHEAD,loginBean.getData().getUser_info().getAvatar());
                    SPUtils.putString(KEY.KEY_USERFUNSUNNUM,loginBean.getData().getUser_info().getFunsun_id());
                    SPUtils.putInt(KEY.KEY_USERGENDER,loginBean.getData().getUser_info().getSex());
                    openActivity(MainActivity.class);
                    // 发送消息 通知Main刷新界面
                    EventBus.getDefault().post(new MainActivityEventBus(2));
                }else {
                    new SuperHanDialog(LoginActivity.this,R.style.dialog,loginBean.getMsg()).show();
                }
            }
        });


    }


}
