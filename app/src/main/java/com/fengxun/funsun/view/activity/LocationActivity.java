package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/6.
 * Holle Android
 * 内容 定位学校
 */

public class LocationActivity extends BaseActivity {


    @BindView(R.id.ac_location_recyclerview)
    RecyclerView acLocationRecyclerview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorBooblar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setBarLeftIcon(true);
    }

    /**
     * 点击刷新地理位置
     */
    @OnClick(R.id.ac_location_refresh)
    public void onViewClicked() {

    }
}
