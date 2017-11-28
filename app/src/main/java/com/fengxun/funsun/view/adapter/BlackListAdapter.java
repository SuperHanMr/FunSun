package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.BlackListBean;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/15.
 * Holle Android
 */

public class BlackListAdapter extends BasePromtingAdapter {


    private List<BlackListBean.DataBean> data;

    private Context context;

    public BlackListAdapter(Context context) {
        super(context);
        this.context = context;

        data = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(PromtingViewholder holder, final int position) {
        super.onBindViewHolder(holder, position);
        final BlackListBean.DataBean dataBean = data.get(position);
        ImageView imagViewHead = holder.getImagViewHead(R.id.item_blacklist_head);
        Picasso.with(context).load(dataBean.getUser_avatar()).into(imagViewHead);
        holder.setText(R.id.item_blacklist_tv_name,dataBean.getUser_nick());
        holder.setText(R.id.item_blacklist_tv_schoolname,dataBean.getUser_school());
        holder.setOnClickListener(R.id.item_blacklist_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackListener.onBlackListener(dataBean.getUser_id());
                data.remove(position);
                notifyDataSetChanged();
            }
        });


    }



    private BlackListener blackListener;
    public interface BlackListener{
        void onBlackListener(int id);
    }

    public void setBlackListener(BlackListener blackListener){
        this.blackListener = blackListener;
    }


    @Override
    public int layoutId() {
        return R.layout.item_blacklist;
    }

    public void setData(List<BlackListBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size()==0?0:data.size();
    }
}
