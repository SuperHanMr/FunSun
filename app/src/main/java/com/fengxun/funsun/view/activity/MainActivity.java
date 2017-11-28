package com.fengxun.funsun.view.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.fengxun.funsun.model.eventbus.MainActivityEventBus;
import com.fengxun.funsun.utils.FitStateUI;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.view.adapter.MianFragmentViewPager;
import com.fengxun.funsun.view.base.ActivityStack;
import com.fengxun.funsun.view.fragment.AroundCampusFragmnet;
import com.fengxun.funsun.view.fragment.CampusFragment;
import com.fengxun.funsun.view.fragment.LoginFragment;
import com.fengxun.funsun.view.fragment.MeFragment;
import com.fengxun.funsun.view.fragment.NewsFragment;
import com.fengxun.funsun.view.views.NoSorollViewpager;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transparencyBar(this);
        EventBus.getDefault().register(this);
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
        mianNoViewpager.setOffscreenPageLimit(2);
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








    /*
        用EventBus 通知MainActivity 重新添加一次Fragment
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBusRefresh(MainActivityEventBus bus){
        int mainActivityEventBus = bus.getMainActivityEventBus();
        fragments.clear();
        fragments.add(new NewsFragment());
        fragments.add(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?new CampusFragment():new AroundCampusFragmnet());
        LogUtils.d(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)+"");
        fragments.add(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?new MeFragment(): new LoginFragment());
        mianNoViewpager.setAdapter(new MianFragmentViewPager(getSupportFragmentManager(),fragments));
        mianNoViewpager.setCurrentItem(mainActivityEventBus, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 修改状态栏为全透明
     *
     * @param activity
     */
    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
//        ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(true);//设置跟布局fitsystemwindow=true
    }

}
