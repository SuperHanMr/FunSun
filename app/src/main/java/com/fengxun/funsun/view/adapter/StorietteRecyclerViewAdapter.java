package com.fengxun.funsun.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CamPusStorietteBean;
import com.fengxun.funsun.model.bean.RoostBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.listener.NewItemListener;
import com.fengxun.funsun.utils.DisplayUtils;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.activity.CampusLittlestoryActivity;
import com.fengxun.funsun.view.activity.TowInterRootsActvity;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/5.
 * Holle Android
 * 内容 校内小故事 瀑布流
 * 溯源
 * 标内容圆角图片处理
 * 热度头像 以及热度值
 * 标题 (判断是否为空)
 * 最热评论头像 最热评论内容
 * 判断 内容是什么类型 图文/视频 跳转到具体页面
 *
 */

public class StorietteRecyclerViewAdapter extends BasePromtingAdapter {

    /*
    数据源 集合
     */

    private List<CamPusStorietteBean.DataBean> beanList;
    private Activity activity;
    private final int itemWidth;
    private boolean isRoost;
    private double rotion;
    private RecyclerView recyclerView;

    public StorietteRecyclerViewAdapter(Activity activity,boolean isRoots) {
        super(activity);
        //计算item的宽度
        this.activity = activity;
        this.isRoost = isRoots;

        itemWidth = (DisplayUtils.getScreenWidth(activity)) / 2-20;
        beanList = new ArrayList<>();
    }


    /*
    瀑布流的 图片比例
    获取 item的宽度 = 屏幕的宽度/2-间隙
    获取 服务器的图片宽高
    图片的高=item的宽度/服务器的宽度 得出比例*服务器返回的高度
    图片的宽 = item的宽度
    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
    layoutParams.width = itemWidth;
    layoutParams.height = height;
    roundedImageViewBg.setLayoutParams(layoutParams);
    再if判断 算出的比例最高不能超过1.36 最低不能低于1.0
     */

    @Override
    public void onBindViewHolder(PromtingViewholder holder, int position) {
        super.onBindViewHolder(holder, position);
        RoundedImageView roundedImageViewBg = holder.getRoundedImageViewBg(R.id.campus_storiette_bg);
        final CamPusStorietteBean.DataBean bean = beanList.get(position);
        LogUtils.e("图片的比例："+(String) bean.getSize());
        if (bean!=null){
                if (bean.getSize()!=null){
                    String  size = (String) bean.getSize(); // 宽高比例
                    String[] split = size.split(":");
                    int widhtHeight[] = new int[split.length];

                    for (int i = 0; i < split.length; i++) {
                        if (split[i].contains(".")){
                            widhtHeight[i] =Integer.parseInt(split[i].substring(0, split[i].indexOf(".")));
                        }else {
                            widhtHeight[i] =Integer.parseInt(split[i]);
                        }

                    }
                    rotion = widhtHeight[1]/(double)widhtHeight[0];
                    if (rotion >1.36){
                        rotion = 1.36;
                    }else if (rotion <1.0){
                        rotion = 1.0;
                    }
                }else {
                    rotion = 1.0;
                }
                int height = (int) (itemWidth*rotion);
                ViewGroup.LayoutParams layoutParams = roundedImageViewBg.getLayoutParams();
                layoutParams.width = itemWidth;
                layoutParams.height = height;
                roundedImageViewBg.setLayoutParams(layoutParams);
                Glide.with(activity).load(bean.getSchool_cover_img_url().get(0)).into(roundedImageViewBg);
                AutoLinearLayout autoLinearLayout = holder.getAutoLinearLayout(R.id.campus_storiette_redu_ll);
                ViewGroup.LayoutParams layoutParams1 = autoLinearLayout.getLayoutParams();
                layoutParams1.height = height;
                layoutParams1.width = itemWidth;


//            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                        Picasso.with(context).resumeTag(tag);
//                    }else {
//                        Picasso.with(mContext).pauseTag(tag);
//                    }
//                }
//            });




        /*
        溯源
         */

        if (isRoost){
            AutoRelativeLayout rl =   holder.getView(R.id.new_roots2);
            rl.setVisibility(View.VISIBLE);
            holder.setText(R.id.roots2_content,bean.getSecond_root_tag());
            holder.setOnClickListener(R.id.new_roots2, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RoostBean roostBean = new RoostBean(bean.getSecond_root_tag(),bean.getSecond_root_tag_id()+"");

                    Intent intent = new Intent(context, CampusLittlestoryActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(KEY.KEY_ROOTSTAG, roostBean);
                    intent.putExtras(mBundle);
                    intent.putExtra(KEY.KEY_SCHOOLID,bean.getSchool_id()+"");
                    context.startActivity(intent);
                }
            });
        }

        /*
        判断 是否为播放视频
         */
        if (bean.getVedio_url().equals("")){
            holder.getView(R.id.campus_storiette_iv_video).setVisibility(View.GONE);
        }else {
            holder.getView(R.id.campus_storiette_iv_video).setVisibility(View.VISIBLE);
        }
        Picasso.with(activity).load(bean.getPublish_user_avatar()).into(holder.getCircleImageView(R.id.campus_storiette_redu_head));
        holder.setText(R.id.campus_storiette_redu_tv_num,String.valueOf(bean.getHot_cnt()));
        holder.setText(R.id.campus_storiette_tv_title,bean.getContent_title());
        Picasso.with(activity).load(bean.getComment_user_avatar()).into(holder.getCircleImageView(R.id.campus_storiette_comment_iv_head));
        holder.setText(R.id.campus_storiette_comment_,bean.getComment());
        holder.setOnClickListener(R.id.campus_storiette_fl, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.getVedio_url().equals("")){
                        listener.OnPostInfoListener(String.valueOf(bean.getContent_id()),bean.getContent_type());
                    }else {
                        VideoInfoBean videoInfoBean = new VideoInfoBean(bean.getVedio_url(),bean.getSchool_cover_img_url().get(0),bean.getContent_id());
                        listener.OnVideoInfoListener(videoInfoBean);
                    }
                }
            });

            holder.setOnClickListener(R.id.campus_storiette_comment_iv_comment, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnCommentContentListener(String.valueOf(String.valueOf(bean.getContent_id())),String.valueOf(bean.getPublish_user()));
                }
            });



            holder.setOnClickListener(R.id.campus_storiette_comment_iv_head, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRelationListener(bean.getComment_user_id()+"",bean.getContent_id()+"",2);
                }
            });

        }


    }


    private NewItemListener listener;
    public void setItemListener(NewItemListener listener){
        this.listener = listener;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_campus_storiette;
    }


    /**
     * @return
     */
    @Override
    public int getItemCount() {

        return beanList.size();
    }



    /*
    设置数据源
     */

    public void setData(List<CamPusStorietteBean.DataBean> datas){
        if (beanList!=null){
            beanList = datas;
            notifyDataSetChanged();
        }

        notifyDataSetChanged();
    }


    public void setLoadData(List<CamPusStorietteBean.DataBean> datas){
        int size = beanList.size();
        beanList.addAll(datas);
        notifyItemInserted(size);
    }

}
