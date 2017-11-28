package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.adapter.SystemPormtingAdapter;
import com.fengxun.funsun.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/10.
 * Holle Android
 */

public class SystemPromtingActivity extends BaseActivity {


    @BindView(R.id.ac_systempormting_recycleview)
    RecyclerView acSystempormtingRecycleview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_pormting;
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
        setTvTitle("系统提示",R.color.colorbBlack);
        initViews();
        loData();
    }

    private void initViews() {
        LinearLayoutManager manage = new LinearLayoutManager(this);
        SystemPormtingAdapter adapter = new SystemPormtingAdapter(this);
        manage.setOrientation(LinearLayoutManager.VERTICAL);
        acSystempormtingRecycleview.setLayoutManager(manage);
        acSystempormtingRecycleview.setAdapter(adapter);
    }






    private void loData() {
    }
}
