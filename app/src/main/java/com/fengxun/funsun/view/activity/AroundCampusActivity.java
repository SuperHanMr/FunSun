package com.fengxun.funsun.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.fragment.AroundCampusFragmnet;

import butterknife.ButterKnife;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/12.
 * Holle Android
 * 登录之后的逛校园是一个Activity 添加一个Fragment
 */

public class AroundCampusActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }



    public void initView() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        AroundCampusFragmnet fragmnet = new AroundCampusFragmnet();
        transaction.replace(R.id.ac_aroundcampus_fragemnt,fragmnet);
        transaction.commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aroundcampus;
    }



    @Override
    protected int getBoolarColors() {
        return R.color.colorBooblar;
    }



}
