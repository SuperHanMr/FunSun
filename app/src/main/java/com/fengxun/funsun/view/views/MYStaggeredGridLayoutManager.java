package com.fengxun.funsun.view.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/28.
 * Holle Android
 */

public class MYStaggeredGridLayoutManager extends StaggeredGridLayoutManager {

    public MYStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public MYStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
