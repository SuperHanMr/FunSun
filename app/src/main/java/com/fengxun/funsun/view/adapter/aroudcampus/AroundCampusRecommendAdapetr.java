package com.fengxun.funsun.view.adapter.aroudcampus;

import android.content.Context;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/13.
 * Holle Android
 */

public class AroundCampusRecommendAdapetr extends BasePromtingAdapter {


    public AroundCampusRecommendAdapetr(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int layoutId() {
        return R.layout.item_aroundcampus_recommend;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
