package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.ToviewPromtingParticuarsBean;
import com.fengxun.funsun.utils.InspectionPhoneUtils;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/14.
 * Holle Android
 */

public class ToviewPromtingParticuarsAdapter extends BasePromtingAdapter {


    private Context context;
    private List<ToviewPromtingParticuarsBean.DataBeanX.DataBean> mList;

    public ToviewPromtingParticuarsAdapter(Context context) {
        super(context);
        this.context = context;
        mList = new ArrayList<>();
    }

    /*** @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);
        ImageView imagViewHead = holder.getImagViewHead(R.id.item_toviewpromtingdatail_iv_head);
        ToviewPromtingParticuarsBean.DataBeanX.DataBean dataBean = mList.get(position);
        Picasso.with(context).load(dataBean.getFriend_avatar()).into(imagViewHead);
        holder.setText(R.id.item_toviewpromtingdatail_tv_name,dataBean.getFriend_nick());
        holder.setText(R.id.item_toviewpromtingdatail_tv_school_name,dataBean.getFriend_school());
        ImageView imagViewHead1 = holder.getImagViewHead(R.id.item_toviewpromtingdatail_iv_context);
        Picasso.with(context).load(dataBean.getContent_url()).into(imagViewHead1);


        final TextView tvBiaoTi = holder.getView(R.id.item_toviewpromtingdatail_tv_biaoti);
        final TextView tvContent = holder.getView(R.id.item_toviewpromtingdatail_tv_content);
        int content_time = (int) dataBean.getContent_time();
        holder.setText(R.id.item_toviewpromtingdatail_tv_biaoti,dataBean.getContent_title());
        holder.setText(R.id.item_toviewpromtingdatail_tv_content, TimeUtils.getTimeFormatText(String.valueOf(content_time))+"发布了一条资讯");

        /*
        监听
         */
        tvBiaoTi.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                LogUtils.e(tvBiaoTi.getLineCount()+"----->");
                tvBiaoTi.getViewTreeObserver().removeOnPreDrawListener(this);
                if (tvBiaoTi.getLineCount()>=2){
                    tvContent.setVisibility(View.GONE);
//                    if (tvContent.getTag()==tvContent){
//                        tvContent.setVisibility(View.GONE);
//                    }

                }
                return false;
            }
        });

        int update_time = (int) dataBean.getUpdate_time();
        holder.setText(R.id.item_toviewpromtingdatail_tv_time,TimeUtils.getTimeFormatText(String.valueOf(update_time)));
    }

    @Override
    public int layoutId() {
        return R.layout.item_toviewpromtingdatail;
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size();
    }


    public void setData(List<ToviewPromtingParticuarsBean.DataBeanX.DataBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    public void setLoadData(List<ToviewPromtingParticuarsBean.DataBeanX.DataBean> data){
        mList.addAll(data);
        notifyDataSetChanged();
    }

}
