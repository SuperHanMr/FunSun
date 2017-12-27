package com.fengxun.funsun.view.adapter.aroudcampus;

import android.content.Context;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.base.BasePromtingAdapter;

import com.fengxun.funsun.view.base.ViewHolder;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/13.
 * Holle Android
 */

public class AroundCampusVicinityAdapter extends BasePromtingAdapter {


    public AroundCampusVicinityAdapter(Context context) {
        super(context);
    }

    @Override
    public int layoutId() {
        return R.layout.item_aroundcampus_vicinity;
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
