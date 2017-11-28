package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.eventbus.SttingActivityEventBus;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.lzy.okgo.model.HttpParams;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/10.
 * Holle Android
 * 内容：修改密码和修改FunSun号
 */

public class ChangeNumActivity extends BaseActivity {
    @BindView(R.id.ac_chang_xin_num)
    EditText acChangXinPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chang_xin_num;
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
        setTvTitle("设置FunSun号",R.color.colorbBlack);
        setBarLeftIcon(true,R.drawable.dingbuback);
    }

    @OnClick(R.id.ac_chang_num_btn)
    public void onViewClicked() {
        final String password = acChangXinPassword.getText().toString().trim();

        if (password.length()<8&password.length()==11){
            new SuperHanDialog(this,"FunSun号太短了");
            return;
        }

        HttpParams params = new HttpParams();
        params.put("set_type","4");
        params.put("set_value",password);
        NetworkReuset.getInstance().GetReuset(RequestUrl.XIUGAIUERINFO, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean codeBean, Call call, String string) {
                if (codeBean.getCode()==200){
                    SPUtils.putString(KEY.KEY_USERFUNSUNNUM,password);
                    EventBus.getDefault().post(new SttingActivityEventBus(3));
                    finish();
                }else {
                    new SuperHanDialog(ChangeNumActivity.this,codeBean.getMsg()).show();
                }
            }
        });

    }
}
