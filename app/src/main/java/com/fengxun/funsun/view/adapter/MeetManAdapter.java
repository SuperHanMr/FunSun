package com.fengxun.funsun.view.adapter;

import android.content.Context;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.MeetTheManBean;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/22.
 * Holle Android
 */

public class MeetManAdapter extends BasePromtingAdapter {


    List<MeetTheManBean.DataBean> list;




    public MeetManAdapter(Context context) {
        super(context);
        list = new ArrayList<>();
    }


    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);
        MeetTheManBean.DataBean dataBean = list.get(position);
        CircleImageView circleImageView = holder.getCircleImageView(R.id.item_meetman_head);
        Picasso.with(context).load(dataBean.getUser_avatar()).into(circleImageView);
        holder.setText(R.id.item_meetman_school_name,dataBean.getUser_school());
        holder.setText(R.id.item_meetman_user_name,dataBean.getUser_nick());
        holder.setText(R.id.item_meetman_number,String.valueOf(dataBean.getUser_encounters()));




    }

    @Override
    public int layoutId() {
        return R.layout.item_meetman;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }






    /**
     * 刷新加载更多的数据
     *
     * @param datas
     */
    public void setLoadMoreData(List<MeetTheManBean.DataBean> datas) {
        int size = list.size();
        list.addAll(datas);
        notifyItemInserted(size);
    }


    /**
     * 下拉刷新，得到的新数据查到原数据起始
     *
     * @param datas
     */
    public void setData(List<MeetTheManBean.DataBean> datas) {
        if (list!=null){
            list.addAll(0, datas);
            notifyDataSetChanged();
        }
    }




}
