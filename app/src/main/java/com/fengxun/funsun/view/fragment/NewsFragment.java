package com.fengxun.funsun.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.view.adapter.NewFragmentViewPagerAdapter;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.fragment.newfragment.NewChinaFragmnet;
import com.fengxun.funsun.view.fragment.newfragment.NewAbroadFragment;
import com.fengxun.funsun.view.fragment.newfragment.NewBeiJingFragment;
import com.fengxun.funsun.view.fragment.newfragment.NewGlobalFragment;
import com.fengxun.funsun.view.fragment.newfragment.NewVideoFragment;
import com.fengxun.funsun.view.views.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/3.
 * Holle Android
 * 趣闻模块：
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.status_bar_fix)
    View statusBarFix;
    Unbinder unbinder;
    @BindView(R.id.news_viewpager)
    ViewPager newsViewpager;
    @BindView(R.id.tab_sildingtab)
    SlidingTabLayout tabSildingtab;


    private String[] titles = {"国内", "北京", "短视频", "全球", "国外"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        statusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SteBoolarUtil.getStateBarHeight(getActivity())));//填充状态栏
        return rootView;
    }

    /**
     *
     */
    @Override
    protected void initView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewChinaFragmnet());
        fragments.add(new NewBeiJingFragment());
        fragments.add(new NewVideoFragment());
        fragments.add(new NewGlobalFragment());
        fragments.add(new NewAbroadFragment());
        NewFragmentViewPagerAdapter adapter = new NewFragmentViewPagerAdapter(getChildFragmentManager(), fragments, titles);
        newsViewpager.setAdapter(adapter);
        tabSildingtab.setViewPager(newsViewpager);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        LogUtils.e("销毁");
    }
}
