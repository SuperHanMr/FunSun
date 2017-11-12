package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/12.
 * Holle Android
 *
 *我的消息-查看提示
 */

public class ToViewPromitngAdapter extends BasePromtingAdapter {


    public ToViewPromitngAdapter(Context context) {
        super(context);
    }

    @Override
    public int layoutId() {
        return R.layout.item_toviewtariespromting;
    }


    /*
    绑定数据
     */
    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
