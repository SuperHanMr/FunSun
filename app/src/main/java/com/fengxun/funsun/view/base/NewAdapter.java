package com.fengxun.funsun.view.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/24.
 * Holle Android
 * 内容：趣闻模块 这个Adapter里面并不是装数据 而是 装载View
 * 每个 View 有自己的适配器 这样 每个item就模块化了 方便操作数据
 * 思路： 创建一个 ViewHolder 参数没View 设置几个辅助方法
 * 文字 图片 点击事件
 * 注：每个item都有自己的适配器 这个Adapter只作为展示 不操作任何item
 * 废弃......
 */

public class NewAdapter extends RecyclerView.Adapter<NewViewHolder> {


    private final int NEWITEM_TYPE1 = 1;//第一种大图 标题帖子item
    private final int NEWITEM_TYPE2 = 2;//第二种 视频大图 标题item
    private final int NEWITEM_TYPE3 = 3;//第三种 标题左侧 小图右侧item
    private final int NEWITEM_TYPE4 = 4;//标题居中 小图并列排成一排
    private int TYPE;

    /*
    上下文、和view条目
     */
    private Context context;
    private ArrayList<View> views;

    private ArrayList<Integer> type;

    public NewAdapter(Context context, ArrayList<View> views,ArrayList<Integer> type) {
        this.context = context;
        this.views = views;

    }

    @Override
    public int getItemViewType(int position) {


        return super.getItemViewType(position);

    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return  new NewViewHolder(views.get(viewType));
    }


    /*
    这里 并不操作任何数据 只是展示
     */
    @Override
    public void onBindViewHolder(NewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
