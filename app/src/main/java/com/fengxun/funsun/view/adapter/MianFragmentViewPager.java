package com.fengxun.funsun.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/3.
 * Holle Android
 */

public class MianFragmentViewPager extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public MianFragmentViewPager(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size()==0?0:fragments.size();
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
////        super.destroyItem(container, position, object);
//    }


    public void setFragments(List<Fragment> fragments){
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}
