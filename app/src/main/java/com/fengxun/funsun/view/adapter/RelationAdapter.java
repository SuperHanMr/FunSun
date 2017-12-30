package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.QuotationBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.view.activity.InformationParticularsActivity;
import com.fengxun.funsun.view.activity.RelationCalorieActivity;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.MultiBaseAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;
import com.fengxun.funsun.view.base.ViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/11.
 * Holle Android
 */

public class RelationAdapter extends BasePromtingAdapter {


    private List<QuotationBean.DataBean.RetBean> beanArrayList;
    private Context context;

    public RelationAdapter(Context context) {
        super(context);
        this.context = context;
        beanArrayList = new ArrayList<>();
    }


    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);

        final QuotationBean.DataBean.RetBean retBean = beanArrayList.get(position);
        Picasso.with(context).load(retBean.getAvatar()).into(holder.getCircleImageView(R.id.item_relation_head));
        holder.setText(R.id.item_relation_tv_name,retBean.getNick());

        RoundedImageView roundedImageViewBg = holder.getRoundedImageViewBg(R.id.item_relation_bg);
        Picasso.with(context).load(retBean.getCover_img_url()).into(roundedImageViewBg);
        holder.setText(R.id.item_relation_tv_school_name,retBean.getRoot_tag());
        holder.setText(R.id.item_relattv_comment_content_titleion_bg,retBean.getContent_title());
        holder.setText(R.id.item_relation_tv_time, TimeUtils.getTimeFormatText(String.valueOf((int)retBean.getCreate_time())));


        if (retBean.getRecord_type()==2){
            holder.getView(R.id.item_relation_tv_comment).setVisibility(View.VISIBLE);
            holder.setText(R.id.item_relation_tv_comment,retBean.getComment_content());
            holder.setText(R.id.item_blacklist_tv_posted_user_name,retBean.getContent_nick());
        }else {
            holder.getView(R.id.item_relation_tv_comment).setVisibility(View.GONE);
            holder.setText(R.id.item_blacklist_tv_posted_user_name,retBean.getNick());
        }

        holder.setOnClickListener(R.id.item_relation_content, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, InformationParticularsActivity.class);
                intent.putExtra(BaseNewFragmnet.POSTINFO,retBean.getContent_id()+"");
                context.startActivity(intent);
            }
        });







    }

    @Override
    public int layoutId() {
        return R.layout.item_relation_recyclerview;
    }

    @Override
    public int getItemCount() {
        return beanArrayList.size();
    }

    public void setData(List<QuotationBean.DataBean.RetBean> datas){
//        beanArrayList.addAll(beanArrayList)
        this.beanArrayList = datas;
        notifyDataSetChanged();
    }

    public void setLoadData(List<QuotationBean.DataBean.RetBean> datas){
        beanArrayList.addAll(datas);
        notifyDataSetChanged();

    }

}
