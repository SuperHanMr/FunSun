package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.ToViewListBean;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.view.activity.ToviewPromtingParticuarsActivity;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/12.
 * Holle Android
 *
 *我的消息-查看提示
 */

public class ToViewPromitngAdapter extends BasePromtingAdapter {


    List<ToViewListBean.DataBeanX.DataBean> mList;
    private Context context;

    public ToViewPromitngAdapter(Context context) {
        super(context);
        this.context = context;
        mList = new ArrayList<>();
    }


    @Override
    public int layoutId() {
        return R.layout.item_toviewtariespromting;
    }

    /*
    绑定数据
     */
    @Override
    public void onBindViewHolder(PromtingViewholder holder, final int position) {
        super.onBindViewHolder(holder, position);
        ImageView imagViewHead = holder.getImagViewHead(R.id.toviewtariespromting_item_head);
        Picasso.with(context).load(mList.get(position).getFriend_avatar()).into(imagViewHead);

        holder.setText(R.id.toviewtariespromting_tv_school,mList.get(position).getFriend_school());
        holder.setText(R.id.toviewtariespromting_tv_name,mList.get(position).getFriend_nick());

        if (mList.get(position).getVisit_count()>=20){
            holder.setText(R.id.toviewtariespromting_tv_labelling_2,"经常瞄你");
        }else {
            holder.setText(R.id.toviewtariespromting_tv_labelling_2,"偷瞄了"+mList.get(position).getVisit_count()+"次");
        }

        if (!mList.get(position).getFriend_relation().equals("")){
            holder.getView(R.id.toviewtariespromting_tv_labelling_1).setVisibility(View.VISIBLE);
            holder.setText(R.id.toviewtariespromting_tv_labelling_1,mList.get(position).getFriend_relation());
        }

        holder.setOnClickListener(R.id.toviewtariespromting_rl_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ToviewPromtingParticuarsActivity.class).putExtra("userid",mList.get(position).getFriend_id()));
            }
        });

        int update_time = (int) mList.get(position).getUpdate_time();
        holder.setText(R.id.toviewtariespromting_tv_time, TimeUtils.getTimeFormatText(String.valueOf(update_time)));


    }

    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size();
    }

    public void setData(List<ToViewListBean.DataBeanX.DataBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    public void setLoadData(List<ToViewListBean.DataBeanX.DataBean> data){
        mList.addAll(data);
        notifyDataSetChanged();
    }


}
