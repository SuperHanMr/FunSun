package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CommentPromtingBean;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/12.
 * Holle Android
 */

public  class CommentariesPromtingAdapter extends BasePromtingAdapter {

    List<CommentPromtingBean.DataBeanX.DataBean> mList;


    public CommentariesPromtingAdapter(Context context) {
        super(context);
    }

    /*
        绑定数据
     */
    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {

    }

    @Override
    public int layoutId() {
        return R.layout.item_commentariespromting;
    }

    public void setData(List<CommentPromtingBean.DataBeanX.DataBean> data){
        mList = new ArrayList<>();
        mList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
