package com.fengxun.funsun.view.views.bottomdialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.RoostBean;
import com.fengxun.funsun.model.listener.OnMenuItemListener;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;

import java.util.List;

import me.shaohui.bottomdialog.BaseBottomDialog;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/24.
 * Holle Android
 */

public class BottonDialogList extends BaseBottomDialog {

    private List<RoostBean> list;

    public BottonDialogList(List<RoostBean> list) {
        this.list = list;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.meunlist_layout;
    }

    @Override
    public void bindView(View v) {
        /*
        这个View 回调的是添加进去的 布局
         */
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyAdapter(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));

        v.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }



    private OnMenuItemListener listener;
    public void setOnMeunListItem(OnMenuItemListener onMeunListItem){
        this.listener = onMeunListItem;
    }


    class MyAdapter extends BasePromtingAdapter {



        public MyAdapter(Context context) {
            super(context);
        }

        @Override
        public int layoutId() {
            return R.layout.meunitem_list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onBindViewHolder(PromtingViewholder holder, final int position) {
            super.onBindViewHolder(holder, position);
            holder.setText(R.id.menu_item_text,list.get(position).interestName);
            holder.setOnClickListener(R.id.menu_item_text, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMenuItemListener(list.get(position).interesId,list.get(position).interestName);

                }
            });
        }
    }


}
