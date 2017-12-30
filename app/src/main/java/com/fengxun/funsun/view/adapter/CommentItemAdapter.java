package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CommentInfoBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.listener.OnIikeListener;
import com.fengxun.funsun.model.listener.OnTowCommentBeanListener;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.view.activity.RelationCalorieActivity;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.MultiBaseAdapter;
import com.fengxun.funsun.view.base.ViewHolder;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.github.rockerhieu.emojicon.EmojiconTextView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/19.
 * Holle Android
 */

public class CommentItemAdapter extends MultiBaseAdapter<CommentInfoBean.DataBean>  {

    private final int COMMENTITEMLEFT = 0; // 左边条目

    private final int COMMENTITEMRIGHT = 1; //右边条目


    public CommentItemAdapter(Context context, List<CommentInfoBean.DataBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, CommentInfoBean.DataBean data, int position, int viewType) {
        switch (viewType){

            case COMMENTITEMLEFT:
                /*
                左边评论
                 */


                processLeftComment(holder,data,position);

                break;

            case COMMENTITEMRIGHT:
                /*
                右边评论
                 */
                processRightComment(holder,data,position);

                break;

        }

    }

    private void processRightComment(ViewHolder holder, final CommentInfoBean.DataBean data, final int position) {
        Picasso.with(mContext).load(data.getComment_user_avatar()).into(holder.getCircleImageView(R.id.comment_item_right));
        holder.setText(R.id.comment_item_right_school_name,data.getComment_school());
        holder.setText(R.id.comment_item_right_user_name,data.getComment_user_nick());

        holder.setText(R.id.comment_item_right_tiem, TimeUtils.getTimeFormatText(String.valueOf((int)data.getComment_time())));

//        holder.setText(R.id.comment_item_right_content,data.getComment_content());

        if (!data.getLatest_child_user_avatar().equals("")){
            holder.getView(R.id.tow_comment_al).setVisibility(View.VISIBLE);
            Glide.with(mContext).load(data.getLatest_child_user_avatar()).into(holder.getCircleImageView(R.id.tow_right_comment_head));
            EmojiconTextView  towTexView = (EmojiconTextView) holder.itemView.findViewById(R.id.tow_comment_right_conmment);
            towTexView.setText(data.getLatest_child_comment());
            holder.setText(R.id.tow_right_comment_content,data.getLatest_child_user_nick());

        }else {
            holder.getView(R.id.tow_comment_al).setVisibility(View.GONE);
        }


        EmojiconTextView  textView = (EmojiconTextView) holder.itemView.findViewById(R.id.comment_item_right_content);
        if (textView!=null){
            textView.setText(data.getComment_content());
        }

        final TextView tvNumber = holder.getView(R.id.comment_item_right_tv_number);





    /*
      IamgViewButton
     */

        final ImageButton checkBoxLike = holder.getView(R.id.comment_item_right_btn_zan);
        final ImageButton checkBoxCai = holder.getView(R.id.comment_item_right_btn_cai);
        final List<ImageButton> buttons = new ArrayList<>();
        buttons.add(checkBoxLike);
        buttons.add(checkBoxCai);
        if (data.getComment_evaluation()!=null){
            if (data.getComment_evaluation().equals("like")){
                checkBoxLike.setBackgroundResource(R.drawable.zan_h);
            }else if (data.getComment_evaluation().equals("unlike")){
                checkBoxCai.setBackgroundResource(R.drawable.cai_h);
            }
        }


        checkBoxLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onIikeListener.onIikeListener(buttons,0,String.valueOf(data.getComment_id()),String.valueOf(data.getComment_user()),tvNumber);
            }
        });

        checkBoxCai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onIikeListener.onIikeListener(buttons,1,String.valueOf(data.getComment_id()),String.valueOf(data.getComment_user()),tvNumber);
            }
        });



         /*
        点击进入 二级评论页
         */
        holder.setOnClickListener(R.id.comment_item_right_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentBeanListener.onTowCommentBeanListenr(data);
            }
        });

        /*
        点击进入关系卡
         */
        holder.setOnClickListener(R.id.comment_item_right, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("-------------->");
                commentBeanListener.onCommentRelationUserId(data.getComment_user()+"");
            }
        });


    }

    /**
     * @param holder
     * @param data
     * @param position
     */
    private void processLeftComment(ViewHolder holder, final CommentInfoBean.DataBean data, int position) {

        LogUtils.d("--------->");
        Glide.with(mContext).load(data.getComment_user_avatar()).into(holder.getCircleImageView(R.id.comment_item_left_head));
        holder.setText(R.id.comment_item_left_scholl_name,data.getComment_school());
        holder.setText(R.id.comment_item_left_user_name,data.getComment_user_nick());
        holder.setText(R.id.comment_item_left_time, TimeUtils.getTimeFormatText(String.valueOf((int)data.getComment_time())));
        holder.setText(R.id.comment_item_left_number,String.valueOf(data.getLike_cnt()));

        final TextView tvNumber = holder.getView(R.id.comment_item_left_number);

        if (!data.getLatest_child_user_avatar().equals("")){
            holder.getView(R.id.tow_comment_al).setVisibility(View.VISIBLE);
            Glide.with(mContext).load(data.getLatest_child_user_avatar()).into(holder.getCircleImageView(R.id.tow_left_comment_head));
            EmojiconTextView  towTexView = (EmojiconTextView) holder.itemView.findViewById(R.id.tow_comment_left_conmment);
            towTexView.setText(data.getLatest_child_comment());
            holder.setText(R.id.tow_left_comment_content,data.getLatest_child_user_nick());

        }else {
            holder.getView(R.id.tow_comment_al).setVisibility(View.GONE);
        }


        EmojiconTextView  textView = (EmojiconTextView) holder.itemView.findViewById(R.id.comment_iten_left_content);
        textView.setText(data.getComment_content());
        if (textView!=null){

        }

        final ImageButton checkBoxLike = holder.getView(R.id.comment_item_left_btn_zan);
        final ImageButton checkBoxCai = holder.getView(R.id.comment_item_left_btn_cai);
        final List<ImageButton> buttons = new ArrayList<>();
        buttons.add(checkBoxLike);
        buttons.add(checkBoxCai);


        if (data.getComment_evaluation()!=null){
            if (data.getComment_evaluation().equals("like")){
                checkBoxLike.setBackgroundResource(R.drawable.zan_h);
            }else if (data.getComment_evaluation().equals("unlike")){
                checkBoxCai.setBackgroundResource(R.drawable.cai_h);
            }
        }



        checkBoxLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onIikeListener.onIikeListener(buttons,0,String.valueOf(data.getComment_id()),String.valueOf(data.getComment_user()),tvNumber);
            }
        });
        checkBoxCai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onIikeListener.onIikeListener(buttons,1,String.valueOf(data.getComment_id()),String.valueOf(data.getComment_user()),tvNumber);
            }
        });

        /*
        点击进入 二级评论页
         */
        holder.setOnClickListener(R.id.comment_item_left_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentBeanListener.onTowCommentBeanListenr(data);
            }
        });

        /*
        点击进入关系卡
         */
        holder.setOnClickListener(R.id.comment_item_left_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("-------------->");
                commentBeanListener.onCommentRelationUserId(data.getComment_user()+"");
            }
        });


    }


    @Override
    protected int getItemLayoutId(int viewType) {

        switch (viewType){
            case COMMENTITEMLEFT:
                return R.layout.comment_item_left;
            case COMMENTITEMRIGHT:
                return R.layout.comment_item_right;
        }

        return 0;
    }


    @Override
    protected int getViewType(int position, CommentInfoBean.DataBean data) {

        switch (data.getComment_direction()){
            case COMMENTITEMLEFT:
                return COMMENTITEMLEFT;
            case COMMENTITEMRIGHT:
                return COMMENTITEMRIGHT;
        }
        return 0;

    }




    private OnIikeListener onIikeListener;
    public void setOnIikeListener(OnIikeListener onIikeListener){
        this.onIikeListener = onIikeListener;
    }


    private OnTowCommentBeanListener commentBeanListener;
    public void setCommentItem(OnTowCommentBeanListener commentBeanListener){
        this.commentBeanListener = commentBeanListener;
    }


}
