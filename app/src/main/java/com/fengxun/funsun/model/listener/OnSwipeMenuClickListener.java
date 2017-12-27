package com.fengxun.funsun.model.listener;


import com.fengxun.funsun.view.base.ViewHolder;

/**
 * Author: Othershe
 * Time: 2016/8/29 10:48
 */
public interface OnSwipeMenuClickListener<T> {
    void onSwipMenuClick(ViewHolder viewHolder, T data, int position);
}
