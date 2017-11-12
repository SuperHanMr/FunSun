package com.fengxun.funsun.view.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.activity.CommentariesPromtingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/12.
 * Holle Android
 * 内容
 */

public abstract class BasePromtingAdapter extends RecyclerView.Adapter<PromtingViewholder> {


    private Context context;

    public BasePromtingAdapter(Context context) {
        this.context = context;
    }


    @Override
    public PromtingViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        PromtingViewholder holder = PromtingViewholder.get(context,parent,layoutId());
        return holder;
    }

    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {

    }


    public abstract int layoutId();

    @Override
    public int getItemCount() {
        return 0;
    }


}
