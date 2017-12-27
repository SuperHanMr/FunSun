package com.fengxun.funsun.view.adapter;

import android.content.Context;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.InformationAimBean;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/19.
 * Holle Android
 */

public class InformationAimAdapter extends BasePromtingAdapter {



    private List<InformationAimBean.DataBean.UsersBean> headList;

    public InformationAimAdapter(Context context) {
        super(context);
        headList = new ArrayList<>();
    }


    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);
        InformationAimBean.DataBean.UsersBean data = headList.get(position);
        CircleImageView aimHead = holder.getCircleImageView(R.id.item_information_iv_head);
        Picasso.with(context).load(data.getUser_avatar()).into(aimHead);
        holder.setText(R.id.item_information_tv_name,data.getUser_nick());


    }


    @Override
    public int layoutId() {
        return R.layout.inforamtion_aim_head;
    }

    @Override
    public int getItemCount() {
        return headList.size();
    }


    public void setHeadList(   List<InformationAimBean.DataBean.UsersBean> headList){
        this.headList = headList;
        notifyDataSetChanged();
    }
}
