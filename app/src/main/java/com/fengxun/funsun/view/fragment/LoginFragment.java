package com.fengxun.funsun.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.LoginBean;
import com.fengxun.funsun.model.request.JsonCallback;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.base.FunSunResponseBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/3.
 * Holle Android
 */

public class LoginFragment extends BaseFragment {


    Unbinder unbinder;

    private HttpParams params = new HttpParams();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        setBarLeftTv("我");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.login_btn_login, R.id.login_tv_registration})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn_login:
                LogUtils.d("登录");
                LogiReuset();
                break;
            case R.id.login_tv_registration:
                LogUtils.d("注册");
                break;
        }
    }

    /**
     * 登录 网络请求
     */
    private void LogiReuset() {
        params.put("account","17710558669");
        params.put("password","12345678");
        NetworkReuset.getInstance().PostReuset(RequestUrl.LOGIN, params, new onCallBack<LoginBean>(this) {

            @Override
            public void onSucceed(LoginBean loginBean, Call call, String string) {
               LogUtils.d(loginBean.getData().getUser_info().getNick());
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
