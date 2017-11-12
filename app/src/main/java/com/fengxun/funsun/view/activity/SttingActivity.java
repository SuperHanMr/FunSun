package com.fengxun.funsun.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.hanyonghui.mylibrary.BottomMenuFragment;
import com.example.hanyonghui.mylibrary.MenuItem;
import com.example.hanyonghui.mylibrary.MenuItemOnClickListener;
import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.BaseActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/10.
 * Holle Android
 */

public class SttingActivity extends BaseActivity {
    @BindView(R.id.ac_stting_iv_head)
    CircleImageView acSttingIvHead;
    @BindView(R.id.ac_stting_tv_name)
    TextView acSttingTvName;
    @BindView(R.id.ac_stting_tv_xingbie)
    TextView acSttingTvXingbie;
    @BindView(R.id.ac_stting_tv_school)
    TextView acSttingTvSchool;
    @BindView(R.id.ac_stting_iv_school)
    CircleImageView acSttingIvSchool;
    @BindView(R.id.ac_stting_tv_funsun)
    TextView acSttingTvFunsun;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stting;

    }

    /**
     * @return
     */
    @Override
    protected int getBoolarColors() {
        return R.color.colorBooblar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        setBarLeftIcon(true);
        setTvTitle("设置");
    }

    @OnClick({R.id.ac_stting_rl_head, R.id.ac_stting_rl_name, R.id.ac_stting_rl_xingbie, R.id.ac_stting_rl_num, R.id.ac_stting_rl_password, R.id.ac_stting_rl_huancun, R.id.ac_stting_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_stting_rl_head:
                break;
            case R.id.ac_stting_rl_name:
                openActivity(ChangeNameActivity.class);
                break;
            case R.id.ac_stting_rl_xingbie:


                modificationXingBie();


                break;
            case R.id.ac_stting_rl_num:
                openActivity(ChangeNumActivity.class);
                break;
            case R.id.ac_stting_rl_password:
                openActivity(ChangePasswordActivity.class);
                break;
            case R.id.ac_stting_rl_huancun:
                break;
            case R.id.ac_stting_btn:
                break;
        }
    }

    private void modificationXingBie() {
        final BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        List<MenuItem> meunList = new ArrayList<>();
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setText("性别只能选择一次哟~");
        menuItem1.setStyle(MenuItem.MenuItemStyle.COMMON);
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setText("男");
        MenuItem menuItem3 = new MenuItem();
        menuItem3.setText("女");
        MenuItem menuItem4 = new MenuItem();
        menuItem4.setText("女");
        meunList.add(menuItem1);
        meunList.add(menuItem2);
        meunList.add(menuItem3);


        bottomMenuFragment.setMenuItems(meunList);
        bottomMenuFragment.show(getFragmentManager(), "BottomMenuFragment");
    }
}
