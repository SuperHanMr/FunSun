package com.fengxun.funsun.view.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fengxun.funsun.FunSunAPP;
import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.utils.FitStateUI;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.view.adapter.MianFragmentViewPager;
import com.fengxun.funsun.view.fragment.AroundCampusFragmnet;
import com.fengxun.funsun.view.fragment.CampusFragment;
import com.fengxun.funsun.view.fragment.LoginFragment;
import com.fengxun.funsun.view.fragment.MeFragment;
import com.fengxun.funsun.view.fragment.NewsFragment;
import com.fengxun.funsun.view.views.NoSorollViewpager;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.mian_no_viewpager)
    NoSorollViewpager mianNoViewpager;
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;
    private List<Fragment> fragments;


    private SuperHanLoginDiglog diglog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        fragments = new ArrayList<>();
        fragments.add(new NewsFragment());
        fragments.add(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?new CampusFragment():new AroundCampusFragmnet());
        LogUtils.d(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)+"");
        fragments.add(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?new MeFragment(): new LoginFragment());
        mianNoViewpager.setAdapter(new MianFragmentViewPager(getSupportFragmentManager(),fragments));
        rgTab.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int item = 0;
        switch (checkedId) {
            case R.id.rb_new:
                item = 0;
                break;
            case R.id.rb_campus:
                item = 1;
                break;
            case R.id.rb_me:
                item = 2;
                break;
        }
        mianNoViewpager.setCurrentItem(item, false);
    }


    public SuperHanLoginDiglog diaLogin(Context context){
        if (diglog==null){
            diglog = new SuperHanLoginDiglog(context);
        }
        return diglog;
    }
}
