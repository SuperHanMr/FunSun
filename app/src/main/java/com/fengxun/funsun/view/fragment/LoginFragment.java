package com.fengxun.funsun.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.view.activity.LoginActivity;
import com.fengxun.funsun.view.activity.RegistrationActivity;
import com.fengxun.funsun.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/3.
 * Holle Android
 */

public class LoginFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.status_bar_fix)
    View statusBarFix;

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
        statusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SteBoolarUtil.getStateBarHeight(getActivity())));//填充状态栏
        return rootView;
    }


    @OnClick({R.id.login_btn_login, R.id.login_tv_registration})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn_login:
                LogUtils.d("登录");
                getActivity().startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.login_tv_registration:
                getActivity().startActivity(new Intent(getContext(), RegistrationActivity.class));
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
