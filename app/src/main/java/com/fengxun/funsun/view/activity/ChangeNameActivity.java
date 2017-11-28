package com.fengxun.funsun.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.SpannableString;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.eventbus.MainActivityEventBus;
import com.fengxun.funsun.model.eventbus.SttingActivityEventBus;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.FitStateUI;
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
 * 内容：修改姓名
 *
 */

public class ChangeNameActivity extends BaseActivity {
    @BindView(R.id.ac_chang_xin_name)
    EditText acChangXinPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chang_name;
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
        setTvTitle("修改昵称",R.color.colorbBlack);
        setBarLeftIcon(true,R.drawable.dingbuback);
    }

    @OnClick(R.id.ac_chang_name_btn)
    public void onViewClicked() {
        final String trim = acChangXinPassword.getText().toString();
        if (trim.contains(" ")){
            DialogPromting("名字不允许有空格");
            return;
        }

        HttpParams params = new HttpParams();
        params.put("set_type",2);
        params.put("set_value",trim);
        NetworkReuset.getInstance().GetReuset(RequestUrl.XIUGAIUERINFO, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean codeBean, Call call, String string) {
                if (codeBean.getCode()==200){
                    new SuperHanDialog(ChangeNameActivity.this, "修改成功", true, new SuperHanDialog.onCloseListener() {
                        @Override
                        public void onClick(Dialog dialog) {
                            SPUtils.putString(KEY.KEY_USERNAME,trim);
                            EventBus.getDefault().post(new SttingActivityEventBus(2));
                            dialog.dismiss();
                            finish();
                        }
                    }).show();
                }else {
                    new SuperHanDialog(ChangeNameActivity.this,codeBean.getMsg()).show();
                }

            }
        });


    }
}
