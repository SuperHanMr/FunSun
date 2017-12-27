package com.fengxun.funsun.view.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.autolayout.AutoLinearLayout;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/12.
 * Holle Android
 */

public class PromtingViewholder extends RecyclerView.ViewHolder {


        private SparseArray<View> mViews;
        private View mConvertView;
        private Context mContext;


        public PromtingViewholder(Context context, View itemView, ViewGroup parent) {
            super(itemView);
            mContext = context;
            mConvertView = itemView;
            mViews = new SparseArray<View>();
        }


    public static PromtingViewholder get(Context context,ViewGroup parent,int layoutId){
            View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
            PromtingViewholder holder = new PromtingViewholder(context,itemView,parent);
            return holder;
        }



        /**
         * 通过viewId获取控件
         * @param viewId
         * @return
         */
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null)
            {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        /*
        设置一些辅助方法 例如 设置Text文本 或者 圆形头像
         */

        public PromtingViewholder setText(int viewId,String text){
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }

        // 获取IamgeView 加载图片
        public ImageView getImagViewHead(int viewid){
            ImageView head = getView(viewid);
            return head;
        }

    public PromtingViewholder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }


    /*
    获取圆形头像
     */
    public CircleImageView getCircleImageView(int viewid){
        CircleImageView head = getView(viewid);
        return head;
    }

    /*
    获取圆角图片
     */
    public RoundedImageView getRoundedImageViewBg(int viewid){
        RoundedImageView head = getView(viewid);
        return head;
    }


    public AutoLinearLayout getAutoLinearLayout(int viewId){
        AutoLinearLayout linearLayout = getView(viewId);
        return linearLayout;
    }




}
