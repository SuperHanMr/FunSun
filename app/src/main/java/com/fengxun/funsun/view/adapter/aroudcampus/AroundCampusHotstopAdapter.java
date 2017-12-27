package com.fengxun.funsun.view.adapter.aroudcampus;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.HotSchoolBean;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.activity.SchoolRootsActivity;
import com.fengxun.funsun.view.activity.SearchSchoolActivity;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/13.
 * Holle Android
 */

public class AroundCampusHotstopAdapter extends BasePromtingAdapter{


    private List<HotSchoolBean.DataBean> data;



    public AroundCampusHotstopAdapter(Context context) {
        super(context);
        data = new ArrayList<>();
    }


    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);
        final HotSchoolBean.DataBean dataBean = data.get(position);
        Picasso.with(context).load(dataBean.getTag_img()).into(holder.getCircleImageView(R.id.item_aroundcampus_hot_head));
        holder.setText(R.id.item_aroundcampus_hot_tvName,dataBean.getTag_name());
        TextView view = holder.getView(R.id.item_aroundcampus_hot_content);
        view.setText(view.getText().toString().trim().replace("22",String.valueOf(dataBean.getContent_count())));
        holder.setText(R.id.item_aroundcampus_hot_redu,String.valueOf(dataBean.getHot_cnt()));
        ImageView imagview= holder.getView(R.id.item_aroundcampus_iv_paiming);
        TextView tv =  holder.getView(R.id.item_aroundcampus_tv_paiming);



        switch (position){
            case 0:
                imagview.setImageResource(R.drawable.diyi);
                break;
            case 1:
                imagview.setImageResource(R.drawable.er);
                break;
            case 2:
                imagview.setImageResource(R.drawable.san);
                break;
            default:
                imagview.setVisibility(View.GONE);
                tv.setVisibility(View.VISIBLE);
                holder.setText(R.id.item_aroundcampus_tv_paiming,String.valueOf(position));
                break;

        }


        holder.setOnClickListener(R.id.item_aroundcampus_iv_rl, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SchoolRootsActivity.class);
                intent.putExtra(KEY.KEY_SCHOOLID,dataBean.getTag_id()+"");
                intent.putExtra(KEY.KEY_SCHOOLNAME,dataBean.getTag_name());

                context.startActivity(intent);
            }
        });




    }

    @Override
    public int layoutId() {
        return R.layout.item_aroundcampus_hotspot;
    }



    @Override
    public int getItemCount() {
        return data.size()==0?0:5;
    }


    public void setData(List<HotSchoolBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
