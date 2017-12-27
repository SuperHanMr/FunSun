package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.HeadlinesBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.listener.NewItemListener;
import com.fengxun.funsun.model.listener.OnLoadMoreListener;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.utils.Util;
import com.fengxun.funsun.view.activity.InterestRootsActivity;
import com.fengxun.funsun.view.activity.SchoolRootsActivity;
import com.fengxun.funsun.view.base.MultiBaseAdapter;
import com.fengxun.funsun.view.base.ViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/21.
 * Holle Android
 * 内容：在这里的RecyclerView的上拉加载
 * 思路：创建五种item 一种是内容 一种是上拉加载
 * 第一种大图 标题帖子item
 * 第二种 视频大图 标题item
 * 第三种 标题左侧 小图右侧item
 * 第四种 标题居中 小图并列排成一排
 * 重写 getTpye方法 判断 item返回的type 展示相应的item
 */


public class NewRecyclerViewAdapter extends MultiBaseAdapter<HeadlinesBean.DataBean>{

    private final int NEWITEM_TYPE1 = 2;//第一种大图 标题帖子item
    private final int NEWITEM_TYPE2 = 1;//第二种 视频大图 标题item
    private final int NEWITEM_TYPE3 = 0;//第三种 标题左侧 小图右侧item
    private final int NEWITEM_TYPE4 = 3;//标题居中 小图并列排成一排;


    /*
    有参数构造 传入上下文 以及数据源 和 是否刷新
     */
    public NewRecyclerViewAdapter(Context context, List datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, HeadlinesBean.DataBean data, int position, int viewType) {

            /*
            给每个控件设置 数据
             */
        switch (viewType){
            case NEWITEM_TYPE1:
                setType1ItemData(holder,data);
                break;
            case NEWITEM_TYPE2:
                setType2ItemData(holder,data);
                break;
            case NEWITEM_TYPE3:
                setType3ItemData(holder,data);
                break;
            case NEWITEM_TYPE4:
                setType4ItemData(holder,data);
                break;
        }

    }


    /*
        viewType 根据 ViewType去判断返回对应的item布局
         */
    @Override
    protected int getItemLayoutId(int viewType) {
        switch (viewType){
            case NEWITEM_TYPE1:
                return R.layout.newfragement_type1_item;
            case NEWITEM_TYPE2:
                return R.layout.newfragement_type2_item;
            case NEWITEM_TYPE3:
                return R.layout.newfragement_type3_item;
            case NEWITEM_TYPE4:
                return R.layout.newfragement_type4_item;
        }
        return 0;
    }


    /*
    position当前item
    data 数据源
     */
    @Override
    protected int getViewType(int position, HeadlinesBean.DataBean data) {

        /*
        data 就是数据源 然后 根据数据源的的类型去判断 展示什么样的item
         */
        int content_type = data.getContent_type_v2();

        switch (content_type){

            case NEWITEM_TYPE1:
                return NEWITEM_TYPE1;

            case NEWITEM_TYPE2:
                return NEWITEM_TYPE2;

            case NEWITEM_TYPE3:
                return NEWITEM_TYPE3;

            case NEWITEM_TYPE4:
                return NEWITEM_TYPE4;
        }
        return 0;
    }

    /*
    刷新数据
     */
    @Override
    public void setData(List<HeadlinesBean.DataBean> datas) {
        super.setData(datas);
    }



    /*
    点击Item 跳转详情
     */
    private NewItemListener listener;
    public void setNewItemListenet(NewItemListener listenet){
        this.listener = listenet;
    }



    /*
    ===============================设置数据================================
     */

    /*
    标题居中 小图并列排成一排
     */
    private void setType4ItemData(ViewHolder holder, final HeadlinesBean.DataBean data) {
        /*
           设置标题
         */
        holder.setText(R.id.new_item_type3_title,data.getContent_title());
        /*
        设置三图
         */
        ArrayList<RoundedImageView> views = new ArrayList<>();
        views.add(holder.getRoundedImageView(R.id.new_item_type4_imagview1));
        views.add(holder.getRoundedImageView(R.id.new_item_type4_imagview2));
        views.add(holder.getRoundedImageView(R.id.new_item_type4_imagview3));
        for (int i = 0; i < views.size(); i++) {
            Picasso.with(mContext).load(data.getContent_cover_img_url_v2().get(i)).into(views.get(i));
        }


        /*
        设置 推荐标签 发帖用户姓名 发帖时间
         */
        if (data.getRecommend()==0){
            holder.getView(R.id.new_item_type4_recommend).setVisibility(View.GONE);
        }else {
            holder.getView(R.id.new_item_type4_recommend).setVisibility(View.VISIBLE);
        }


        holder.setText(R.id.new_item_type4_tv_name,data.getContent_publish_user_nick());
        holder.setText(R.id.new_item_type4_tv_time,TimeUtils.getTimeFormatText(String.valueOf(data.getContent_publish_time())));


        /*
        两种溯源的展示
         */
        final Intent intent = new Intent();
        intent.putExtra(KEY.KEY_SCHOOLID,String.valueOf(data.getContent_root_tag_id()));
        intent.putExtra(KEY.KEY_SCHOOLNAME,data.getContent_root_tag());


        AutoRelativeLayout view1 = holder.getView(R.id.new_roots1); // 学校溯源
        AutoRelativeLayout view2 = holder.getView(R.id.new_roots2); // 兴趣溯源
        if (!data.getContent_root_tag_img().equals("")){
            /*
            这是学校溯源
             */
            ImageView imagvIew = holder.getImagvIew(R.id.roots1_head);
            Picasso.with(mContext).load(data.getContent_root_tag_img()).into(imagvIew);
            holder.setText(R.id.roots1_school,data.getContent_root_tag());
            view2.setVisibility(View.GONE);
            view1.setVisibility(View.VISIBLE);

            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(mContext,SchoolRootsActivity.class);
                    mContext.startActivity(intent);
                }
            });

        }else {
            /*
            隐藏学校溯源 显示兴趣溯源
             */
            holder.setText(R.id.roots2_content,data.getContent_root_tag());
            view2.setVisibility(View.VISIBLE);
            view1.setVisibility(View.GONE);
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("跳转到兴趣溯源");
                    intent.setClass(mContext,InterestRootsActivity.class);
                    mContext.startActivity(intent);

                }
            });
        }


        /*
        热评用户信息
         */
        holder.setHeadIamgview(mContext,R.id.comment_user_head,data.getComment_user_avatar());
        holder.setText(R.id.comment_tv_biaoqian,data.getComment_user_nick());
        holder.setText(R.id.comment_tv_content,data.getComment());
        if (!data.getComment_relation().equals("")){
            holder.setText(R.id.comment_tv_zuire,data.getComment_relation());
        }

        holder.setOnClickListener(R.id.new_item_type3_iv_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("-------->");
                listener.OnCommentContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getContent_publish_user_id()));
            }
        });


        holder.setOnClickListener(R.id.comment_user_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRelationListener(data.getComment_user_id()+"",data.getContent_id()+"",2);
            }
        });


        holder.setOnClickListener(R.id.new_type4_itemview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnPostInfoListener(String.valueOf(data.getContent_id()),1);
            }
        });


    }

    /*
    第三种 标题左侧 小图右侧
     */
    private void setType3ItemData(ViewHolder holder, final HeadlinesBean.DataBean data) {

        /*
        标题左边的 圆角图片
         */
        RoundedImageView bg = holder.getRoundedImageView(R.id.new_item_type3_imagview);
        Picasso.with(mContext).load(data.getContent_cover_img_url_v2().get(0)).into(bg);

        /*
        标题右边的 标题 发帖用户以及发帖时间
         */
        if (data.getContent_title()!=null){
            holder.setText(R.id.new_item_type3_tv_biaoti,data.getContent_title());
        }else {
            TextView tvTilet = holder.getView(R.id.new_item_type3_tv_biaoti);
            tvTilet.setVisibility(View.GONE);
        }
        holder.setText(R.id.new_item_type3_tv_tiezi_user_name,data.getContent_publish_user_nick());
        holder.setText(R.id.new_item_type3_tv_time,TimeUtils.getTimeFormatText(String.valueOf(data.getContent_publish_time())));

        /*
        两种溯源的展示
         */
        final Intent intent = new Intent();
        intent.putExtra(KEY.KEY_SCHOOLID,String.valueOf(data.getContent_root_tag_id()));
        intent.putExtra(KEY.KEY_SCHOOLNAME,data.getContent_root_tag());


        AutoRelativeLayout view1 = holder.getView(R.id.new_roots1); // 学校溯源
        AutoRelativeLayout view2 = holder.getView(R.id.new_roots2); // 兴趣溯源
        if (!data.getContent_root_tag_img().equals("")){
            /*
            这是学校溯源
             */
            ImageView imagvIew = holder.getImagvIew(R.id.roots1_head);
            Picasso.with(mContext).load(data.getContent_root_tag_img()).into(imagvIew);
            holder.setText(R.id.roots1_school,data.getContent_root_tag());
            view2.setVisibility(View.GONE);
            view1.setVisibility(View.VISIBLE);

            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(mContext,SchoolRootsActivity.class);
                    mContext.startActivity(intent);
                }
            });

        }else {

            /*
            隐藏学校溯源 显示兴趣溯源
             */
            holder.setText(R.id.roots2_content,data.getContent_root_tag());
            view2.setVisibility(View.VISIBLE);
            view1.setVisibility(View.GONE);
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("跳转到兴趣溯源");
                    intent.setClass(mContext,InterestRootsActivity.class);
                    mContext.startActivity(intent);

                }
            });

        }


        /*
        热评用户信息
         */
//        ImageView head = holder.getImagvIew(R.id.comment_user_head);
//        Picasso.with(mContext).load(data.getComment_user_avatar()).into(head);

        holder.setHeadIamgview(mContext,R.id.comment_user_head,data.getComment_user_avatar());
        holder.setText(R.id.comment_tv_biaoqian,data.getComment_user_nick());
        holder.setText(R.id.comment_tv_content,data.getComment());
        if (!data.getComment_relation().equals("")){
            holder.setText(R.id.comment_tv_zuire,data.getComment_relation());
        }

        holder.setOnClickListener(R.id.new_item_type3_iv_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("-------->");
                listener.OnCommentContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getContent_publish_user_id()));
            }
        });


        holder.setOnClickListener(R.id.comment_user_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRelationListener(data.getComment_user_id()+"",data.getContent_id()+"",2);
            }
        });

        holder.setOnClickListener(R.id.new_type3_itemview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnPostInfoListener(String.valueOf(data.getContent_id()),1);
            }
        });


    }


    /**
     * @param holder
     * @param data
     */
    /*
    第二种 视频大图 标题item
     */
    private void setType2ItemData(ViewHolder holder, final HeadlinesBean.DataBean data) {

        /*
        背景圆角大图
         */
        RoundedImageView roundedImageView = holder.getRoundedImageView(R.id.new_item_type2_bg);
        Picasso.with(mContext).load(data.getContent_cover_img_url_v2().get(0)).into(roundedImageView);


        /*
        标题 推荐标签是否显示 发帖用户名称 发帖时间
         */
        if (data.getContent_title()!=null){
            holder.setText(R.id.new_item_type2_biaoti,data.getContent_title());
        }else {
            TextView tvTilet = holder.getView(R.id.new_item_type2_biaoti);
            tvTilet.setVisibility(View.GONE);
        }
        holder.setText(R.id.new_type2_item_school,data.getContent_publish_user_nick());
        holder.setText(R.id.new_item_type2_time, TimeUtils.getTimeFormatText(String.valueOf(data.getContent_publish_time())));
        AutoRelativeLayout viewRecommend = holder.getView(R.id.new_item_type2_tuijian_icon);
        if (data.getRecommend()==0){
            viewRecommend.setVisibility(View.GONE);
        }else {
            viewRecommend.setVisibility(View.VISIBLE);
        }

        /*
        两种溯源显示
         */

        final Intent intent = new Intent();
        intent.putExtra(KEY.KEY_SCHOOLID,String.valueOf(data.getContent_root_tag_id()));
        intent.putExtra(KEY.KEY_SCHOOLNAME,data.getContent_root_tag());

        AutoRelativeLayout view1 = holder.getView(R.id.new_roots1); // 学校溯源
        AutoRelativeLayout view2 = holder.getView(R.id.new_roots2); // 兴趣溯源
        if (!data.getContent_root_tag_img().equals("")){
            /*
            这是学校溯源
             */
            ImageView imagvIew = holder.getImagvIew(R.id.roots1_head);
            Picasso.with(mContext).load(data.getContent_root_tag_img()).into(imagvIew);
            holder.setText(R.id.roots1_school,data.getContent_root_tag());
            view2.setVisibility(View.GONE);
            view1.setVisibility(View.VISIBLE);

            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(mContext,SchoolRootsActivity.class);
                    mContext.startActivity(intent);
                }
            });

        }else {
            /*
            隐藏学校溯源 显示兴趣溯源
             */
            holder.setText(R.id.roots2_content,data.getContent_root_tag());
            view2.setVisibility(View.VISIBLE);
            view1.setVisibility(View.GONE);
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(mContext,InterestRootsActivity.class);
                    mContext.startActivity(intent);
                }
            });

        }

        /*
        热评的用户的信息
         */

        holder.setHeadIamgview(mContext,R.id.new_item_type2_hottest_user_head,data.getComment_user_avatar());
        if (!data.getComment_relation().equals("")){
            holder.setText(R.id.new_item_type2_hottest_biaoqian,data.getComment_relation());
        }

        holder.setText(R.id.new_item_type2_hottest_biaoqian_text,data.getComment_school());
        holder.setText(R.id.new_item_type2_comment,data.getComment());


        /*
        视频跳转
         */
        holder.setOnClickListener(R.id.new_type2_itemview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int videoID = data.getVedio_info().getContent_id();
                String cover = data.getVedio_info().getSchool_cover_img_url().get(0);
                String vedio_url = data.getVedio_info().getVedio_url();
                listener.OnVideoInfoListener(new VideoInfoBean(vedio_url,cover,videoID));
            }
        });


        holder.setOnClickListener(R.id.new_item_comment_type2_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("-------->");
                listener.OnCommentContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getContent_publish_user_id()));
            }
        });

        holder.setOnClickListener(R.id.new_item_type2_hottest_user_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRelationListener(data.getComment_user_id()+"",data.getContent_id()+"",2);
            }
        });



    }


    /*
    第一种大图 标题帖子item
     */
    private void setType1ItemData(ViewHolder holder, final HeadlinesBean.DataBean data) {

        /*
        Item 背景大图
         */
        RoundedImageView bg = holder.getRoundedImageView(R.id.new_item_type1_bg);
        Picasso.with(mContext).load(data.getContent_cover_img_url_v2().get(0)).into(bg);

          /*
        推荐 标题 推荐标签 发帖用户名 以及发帖时间
         */
        if (data.getContent_title()!=null){
            holder.setText(R.id.new_item_type1_biaoti,data.getContent_title());
        }else {
            TextView tvTilet = holder.getView(R.id.new_item_type1_biaoti);
            tvTilet.setVisibility(View.GONE);
        }


        holder.setText(R.id.new_china_item_school,data.getContent_publish_user_nick());
        holder.setText(R.id.new_item_type1_time, TimeUtils.getTimeFormatText(String.valueOf(data.getContent_publish_time())));
        AutoRelativeLayout viewRecommend = holder.getView(R.id.new_china_item_recommend);
        if (data.getRecommend()==0){
            viewRecommend.setVisibility(View.GONE);
        }else {
            viewRecommend.setVisibility(View.VISIBLE);
        }

        /*
        溯源标签显示
        判断 溯源的头像是否为空 不为空 则是学校溯源 否则就是 兴趣溯源
        隐藏 兴趣溯源 显示学校溯源
         */

        final Intent intent = new Intent();
        intent.putExtra(KEY.KEY_SCHOOLID,String.valueOf(data.getContent_root_tag_id()));
        intent.putExtra(KEY.KEY_SCHOOLNAME,data.getContent_root_tag());

        AutoRelativeLayout view1 = holder.getView(R.id.new_roots1);
        AutoRelativeLayout view2 = holder.getView(R.id.new_roots2);
        if (!data.getContent_root_tag_img().equals("")){
            /*
            这是学校溯源
             */
            ImageView imagvIew = holder.getImagvIew(R.id.roots1_head);
            Picasso.with(mContext).load(data.getContent_root_tag_img()).into(imagvIew);
            holder.setText(R.id.roots1_school,data.getContent_root_tag());
            view2.setVisibility(View.GONE);
            view1.setVisibility(View.VISIBLE);

            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(mContext,SchoolRootsActivity.class);
                    mContext.startActivity(intent);
                }
            });

        }else {
            /*
            隐藏学校溯源 显示兴趣溯源
             */
            holder.setText(R.id.roots2_content,data.getContent_root_tag());
            view2.setVisibility(View.VISIBLE);
            view1.setVisibility(View.GONE);
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  LogUtils.e("跳转到兴趣溯源");
                    intent.setClass(mContext,InterestRootsActivity.class);
                    mContext.startActivity(intent);

                }
            });


        }

        /*
        User 评论者内容和头像
         */
        holder.setHeadIamgview(mContext,R.id.hottest_user_head,data.getComment_user_avatar());
        if (!data.getComment_relation().equals("")){
            holder.setText(R.id.hottest_biaoqian,data.getComment_relation());
        }
        holder.setText(R.id.hottest_biaoqian_text,data.getComment_school());
        holder.setText(R.id.new_item_type1_comment,data.getComment());

        holder.setOnClickListener(R.id.new_item_comment_type1_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("-------->");
                listener.OnCommentContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getContent_publish_user_id()));
            }
        });

        holder.setOnClickListener(R.id.hottest_user_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRelationListener(data.getComment_user_id()+"",data.getContent_id()+"",2);
            }
        });

        holder.setOnClickListener(R.id.new_type1_itemview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnPostInfoListener(String.valueOf(data.getContent_id()),1);
            }
        });

    }

}
