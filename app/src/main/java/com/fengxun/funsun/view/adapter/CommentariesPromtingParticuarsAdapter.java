package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CommentariesPromtingBean;
import com.fengxun.funsun.utils.InspectionPhoneUtils;
import com.fengxun.funsun.utils.SPUtils;
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

public class CommentariesPromtingParticuarsAdapter extends BasePromtingAdapter {


    private List<CommentariesPromtingBean.DataBeanX.DataBean> mList;
    private Context context;


    public CommentariesPromtingParticuarsAdapter(Context context) {
        super(context);
        this.context = context;
        mList = new ArrayList<>();
    }



    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);

        CommentariesPromtingBean.DataBeanX.DataBean dataBean = mList.get(position);
        ImageView imagViewHead = holder.getImagViewHead(R.id.item_commentariespomting_head);
        Picasso.with(context).load(dataBean.getComment_user_avatar()).into(imagViewHead);
        holder.setText(R.id.item_commentariespomting_schol_name,dataBean.getComment_school());
        holder.setText(R.id.item_commentariespomting_pinglun_user,dataBean.getComment_user_nick());
        holder.setText(R.id.item_commentariespomting_context,dataBean.getSecond_comment_content());
        ImageView imagViewHead1 = holder.getImagViewHead(R.id.item_commentariespomting_user_head);
        Picasso.with(context).load(SPUtils.getString(KEY.KEY_USERHEAD)).into(imagViewHead1);
        holder.setText(R.id.item_commentariespomting_user_name,SPUtils.getString(KEY.KEY_USERNAME));
        holder.setText(R.id.item_commentariespomting_user_content,dataBean.getComment_content());
        int comment_time = (int) dataBean.getComment_time();
        holder.setText(R.id.item_commentariespomting_time, TimeUtils.getTimeFormatText(String.valueOf(comment_time)));


    }

    @Override
    public int layoutId() {
        return R.layout.item_commentpromtingparticuars;
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size();
    }


    public void setData(List<CommentariesPromtingBean.DataBeanX.DataBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }
}
