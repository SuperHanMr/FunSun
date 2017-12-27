package com.fengxun.funsun.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.fengxun.funsun.utils.LogUtils;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/21.
 * Holle Android
 */

public class NewFragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    private String titles[];
    private List<Fragment> fragments ;
    public NewFragmentViewPagerAdapter(FragmentManager fm, List<Fragment> fragments,String[] titles ) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //如果注释这行，那么不管怎么切换，page都不会被销毁
//        super.destroyItem(container, position, object);
    }

}
