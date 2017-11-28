package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.adapter.LocationSchoolListAdapter;
import com.fengxun.funsun.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/9.
 * Holle Android
 * <p>
 * 内容:语录
 */

public class QuotationsActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.quotation_activity;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorBooblar;
    }



}
