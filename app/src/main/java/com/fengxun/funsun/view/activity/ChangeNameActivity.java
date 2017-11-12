package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.FitStateUI;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/10.
 * Holle Android
 * 内容：修改姓名
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

    }
}
