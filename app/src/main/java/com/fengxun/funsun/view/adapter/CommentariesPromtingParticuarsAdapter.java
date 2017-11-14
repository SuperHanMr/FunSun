package com.fengxun.funsun.view.adapter;

import android.content.Context;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/14.
 * Holle Android
 */

public class CommentariesPromtingParticuarsAdapter extends BasePromtingAdapter {



    public CommentariesPromtingParticuarsAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);



    }

    @Override
    public int layoutId() {
        return R.layout.item_commentpromtingparticuars;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
