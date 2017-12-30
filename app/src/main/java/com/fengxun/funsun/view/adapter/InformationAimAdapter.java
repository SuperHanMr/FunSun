package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.InformationAimBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.view.activity.RelationCalorieActivity;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
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
    private String contentID;

    /**
     * @param context
     * @param contentId
     */
    public InformationAimAdapter(Context context,String contentId) {
        super(context);
        headList = new ArrayList<>();
        this.contentID = contentId;
    }


    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);
        final InformationAimBean.DataBean.UsersBean data = headList.get(position);
        CircleImageView aimHead = holder.getCircleImageView(R.id.item_information_iv_head);
        Picasso.with(context).load(data.getUser_avatar()).into(aimHead);
        holder.setText(R.id.item_information_tv_name,data.getUser_nick());
        aimHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelationInfBean bean = new RelationInfBean(1,data.getUser_id()+"",contentID);
                Intent intent = new Intent(context, RelationCalorieActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
                intent.putExtras(mBundle);
                context .startActivity(intent);



            }
        });




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
