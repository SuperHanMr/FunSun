package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CommentPromtingBean;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.view.activity.CommentPromtingParticuarsActivity;
import com.fengxun.funsun.view.activity.CommentariesPromtingActivity;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/12.
 * Holle Android
 */

public  class CommentariesPromtingAdapter extends BasePromtingAdapter {

    List<CommentPromtingBean.DataBeanX.DataBean> mList;

    private Context context;

    public CommentariesPromtingAdapter(Context context) {
        super(context);
        this.context = context;
        mList = new ArrayList<>();
    }

    /*
        绑定数据
     */
    @Override
    public void onBindViewHolder(PromtingViewholder holder, final int position) {
        ImageView imagViewHead = holder.getImagViewHead(R.id.commentariespromting_item_head);
        Picasso.with(context).load(mList.get(position).getComment_user_avatar()).into(imagViewHead);
        holder.setText(R.id.commentariespromting_tv_name,mList.get(position).getComment_user_nick());
        holder.setText(R.id.commentariespromting_tv_school,mList.get(position).getComment_school());
        String comment_relation = mList.get(position).getComment_relation();
        if (!comment_relation.equals("")){
            holder.getView(R.id.commentariespromting_tv_type).setVisibility(View.VISIBLE);
            holder.setText(R.id.commentariespromting_tv_type,comment_relation);
        }
        holder.setText(R.id.commentariespromting_tv_massage,mList.get(position).getComment_content());
        holder.setOnClickListener(R.id.commentariespromting_rl_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CommentPromtingParticuarsActivity.class).putExtra("userid",mList.get(position).getComment_user()));
            }
        });

        int comment_time = (int) mList.get(position).getComment_time();
        holder.setText(R.id.commentariespromting_tv_time, TimeUtils.getTimeFormatText(String.valueOf(comment_time)));
    }

    @Override
    public int layoutId() {
        return R.layout.item_commentariespromting;
    }

    public void setData(List<CommentPromtingBean.DataBeanX.DataBean> data){
        mList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size();
    }
}
