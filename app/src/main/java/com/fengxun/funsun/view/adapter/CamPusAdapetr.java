package com.fengxun.funsun.view.adapter;

import android.support.v4.view.PagerAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/5.
 * Holle Android
 */

public class CamPusAdapetr extends PagerAdapter {

    private List<RecyclerView> recyclerViews;
    private String[] titles;

    public CamPusAdapetr(List<RecyclerView> recyclerViews,String[] titles) {
        this.recyclerViews = recyclerViews;
        this.titles = titles;
    }



    @Override
    public int getCount() {
        return recyclerViews.size()==0?0:recyclerViews.size();
    }

    // PagerAdapter只缓存三个View实例，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView(recyclerViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    public Object instantiateItem(ViewGroup view, int position) {
        view.addView(recyclerViews.get(position));
        return recyclerViews.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
