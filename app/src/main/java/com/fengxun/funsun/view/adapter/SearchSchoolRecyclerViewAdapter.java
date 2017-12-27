package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.SchoolListBean;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/23.
 * Holle Android
 */

public class SearchSchoolRecyclerViewAdapter extends BasePromtingAdapter {

    private List< SchoolListBean.DataBean> mList;

    public SearchSchoolRecyclerViewAdapter(Context context) {
        super(context);

        mList = new ArrayList<>();
    }


    @Override
    public void onBindViewHolder(PromtingViewholder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.setText(R.id.search_school_name,mList.get(position).getName());
        holder.setOnClickListener(R.id.search_school_list_re, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSchoolName.onItemSchoolName(mList.get(position).getName(),String.valueOf(mList.get(position).getId()));
                // 这里跳到学校溯源

            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.item_search_school;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setmList(List< SchoolListBean.DataBean> mList){
        this.mList = mList;

        notifyDataSetChanged();
    }


    private OnItemSchoolName onItemSchoolName;

    public void setOnItemSchoolName(OnItemSchoolName onItemSchoolName){
        this.onItemSchoolName = onItemSchoolName;
    }

    public interface OnItemSchoolName{
        void onItemSchoolName(String schoolName,String schoolId);
    }


}
