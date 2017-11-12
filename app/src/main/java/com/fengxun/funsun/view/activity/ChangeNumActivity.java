package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.widget.EditText;

import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    }
}
