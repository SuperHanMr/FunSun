package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.InterestRoostBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.bean.RoostBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.activity.InformationParticularsActivity;
import com.fengxun.funsun.view.activity.RelationCalorieActivity;
import com.fengxun.funsun.view.activity.TowInterRootsActvity;
import com.fengxun.funsun.view.activity.VideoPlayerActivity;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.base.MultiBaseAdapter;
import com.fengxun.funsun.view.base.ViewHolder;
import com.fengxun.funsun.view.views.FlowLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/24.
 * Holle Android
 */

public class InterestRootsRecyclerViewAdapter extends MultiBaseAdapter<InterestRoostBean.DataBean> {


    private final int NEWITEM_TYPE1 = 2;//第一种大图 标题帖子item
    private final int NEWITEM_TYPE2 = 1;//第二种 视频大图 标题item
    private final int NEWITEM_TYPE3 = 0;//第三种 标题左侧 小图右侧item
    private final int NEWITEM_TYPE4 = 3;//标题居中 小图并列排成一排;



    public InterestRootsRecyclerViewAdapter(Context context, List<InterestRoostBean.DataBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);

    }




    @Override
    protected void convert(ViewHolder holder, InterestRoostBean.DataBean data, int position, int viewType) {

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
    =========================设置数据=================================
     */
    /*
    二级溯源 显示 有两种方式
    第一：找出每个溯源TextView 添加到集合里面
    根据 服务器 返回的 数组 遍历集合进行设置
    第二种：遍历父容器 获取每个子View 设置对应的文本 推荐第二种
     */

    private void setType4ItemData(ViewHolder holder, final InterestRoostBean.DataBean data) {

         /*
           设置标题
         */
        holder.setText(R.id.new_item_type3_title,data.getContent_title());
        /*
        设置三图
         */
        ArrayList<RoundedImageView> views = new ArrayList<>();
        views.add(holder.getRoundedImageView(R.id.roots_item_type4_imagview1));
        views.add(holder.getRoundedImageView(R.id.roots_item_type4_imagview2));
        views.add(holder.getRoundedImageView(R.id.roots_item_type4_imagview3));
        for (int i = 0; i < views.size(); i++) {
            Picasso.with(mContext).load(data.getContent_cover_img_url_v2().get(i)).into(views.get(i));
        }

           /*
        设置 推荐标签 发帖用户姓名 发帖时间
         */
        holder.setText(R.id.roots_item_type4_tv_name,data.getContent_publish_user_nick());
        holder.setText(R.id.roots_item_type4_tv_time, TimeUtils.getTimeFormatText(String.valueOf((int)data.getContent_publish_time())));

        /*
        二级溯源
         */
       AutoLinearLayout layout = holder.getView(R.id.roots_tag_ll);
       final List<InterestRoostBean.DataBean.TagsBean> tags = data.getTags();
        for (int i = 0; i < tags.size(); i++) {
            TextView childAt = (TextView) layout.getChildAt(i);
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(tags.get(i).getTag());
            final int finalI = i;
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RoostBean roostBean = new RoostBean(tags.get(finalI).getTag(),tags.get(finalI).getTag_id()+"");
                    Intent intent = new Intent(mContext, TowInterRootsActvity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(KEY.KEY_ROOTSTAG, roostBean);
                    intent.putExtras(mBundle);
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

        holder.setOnClickListener(R.id.roots_item_type4_iv_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentListener.onCommtntContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getContent_publish_user_id()));
            }
        });

        /*
        关系卡
         */
        holder.setOnClickListener(R.id.comment_user_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelationInfBean bean = new RelationInfBean(1, data.getComment_user_id()+"", data.getContent_id()+"");
                Intent intent = new Intent(mContext, RelationCalorieActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
            }
        });


        holder.setOnClickListener(R.id.roots_type4_itemview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InformationParticularsActivity.class);
                intent.putExtra(BaseNewFragmnet.POSTINFO,data.getContent_id()+"");
                mContext.startActivity(intent);
            }
        });



    }

    private void setType3ItemData(ViewHolder holder, final InterestRoostBean.DataBean data) {

           /*
        标题左边的 圆角图片
         */
        RoundedImageView bg = holder.getRoundedImageView(R.id.roots_item_type3_imagview);
        Picasso.with(mContext).load(data.getContent_cover_img_url_v2().get(0)).into(bg);

        /*
        标题右边的 标题 发帖用户以及发帖时间
         */
        if (data.getContent_title()!=null){
            holder.setText(R.id.roots_item_type3_tv_biaoti,data.getContent_title());
        }else {
            TextView tvTilet = holder.getView(R.id.roots_item_type3_tv_biaoti);
            tvTilet.setVisibility(View.GONE);
        }

        holder.setText(R.id.roots_item_type3_tv_tiezi_user_name,data.getContent_publish_user_nick());
        holder.setText(R.id.roots_item_type3_tv_time,TimeUtils.getTimeFormatText(String.valueOf((int)data.getContent_publish_time())));

          /*
        二级溯源
         */
        AutoLinearLayout layout = holder.getView(R.id.roots_tag_ll);
        final List<InterestRoostBean.DataBean.TagsBean> tags = data.getTags();
        for (int i = 0; i < tags.size(); i++) {
            TextView childAt = (TextView) layout.getChildAt(i);
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(tags.get(i).getTag());
            final int finalI = i;
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RoostBean roostBean = new RoostBean(tags.get(finalI).getTag(),tags.get(finalI).getTag_id()+"");
                    Intent intent = new Intent(mContext, TowInterRootsActvity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(KEY.KEY_ROOTSTAG, roostBean);
                    intent.putExtras(mBundle);
                    mContext.startActivity(intent);
                }
            });
        }


        holder.setHeadIamgview(mContext,R.id.comment_user_head,data.getComment_user_avatar());
        holder.setText(R.id.comment_tv_biaoqian,data.getComment_user_nick());
        holder.setText(R.id.comment_tv_content,data.getComment());
        if (!data.getComment_relation().equals("")){
            holder.setText(R.id.comment_tv_zuire,data.getComment_relation());
        }

        holder.setOnClickListener(R.id.roost_item_type3_iv_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentListener.onCommtntContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getContent_publish_user_id()));
            }
        });


          /*
        关系卡
         */
        holder.setOnClickListener(R.id.comment_user_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelationInfBean bean = new RelationInfBean(1, data.getComment_user_id()+"", data.getContent_id()+"");
                Intent intent = new Intent(mContext, RelationCalorieActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
            }
        });


        holder.setOnClickListener(R.id.roots_type3_itemview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InformationParticularsActivity.class);
                intent.putExtra(BaseNewFragmnet.POSTINFO,data.getContent_id()+"");
                mContext.startActivity(intent);
            }
        });



    }

    private void setType2ItemData(ViewHolder holder, final InterestRoostBean.DataBean data) {

           /*
        背景圆角大图
         */
        RoundedImageView roundedImageView = holder.getRoundedImageView(R.id.roots_item_type2_bg);
        Picasso.with(mContext).load(data.getContent_cover_img_url_v2().get(0)).into(roundedImageView);

        if (data.getContent_title()!=null){
            holder.setText(R.id.roots_item_type2_biaoti,data.getContent_title());
        }else {
            TextView tvTilet = holder.getView(R.id.roots_item_type2_biaoti);
            tvTilet.setVisibility(View.GONE);
        }
        holder.setText(R.id.roots_type2_item_school,data.getContent_publish_user_nick());
        holder.setText(R.id.roots_item_type2_time, TimeUtils.getTimeFormatText(String.valueOf((int)data.getContent_publish_time())));

        /*
        二级溯源标签
         */
        AutoLinearLayout layout = holder.getView(R.id.roots_tag_ll);
        final List<InterestRoostBean.DataBean.TagsBean> tags = data.getTags();
        for (int i = 0; i < tags.size(); i++) {
            TextView childAt = (TextView) layout.getChildAt(i);
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(tags.get(i).getTag());
            final int finalI = i;
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RoostBean roostBean = new RoostBean(tags.get(finalI).getTag(),tags.get(finalI).getTag_id()+"");
                    Intent intent = new Intent(mContext, TowInterRootsActvity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(KEY.KEY_ROOTSTAG, roostBean);
                    intent.putExtras(mBundle);
                    mContext.startActivity(intent);
                }
            });
        }



        /*
        热评的用户的信息
         */
        holder.setHeadIamgview(mContext,R.id.comment_user_head,data.getComment_user_avatar());
        holder.setText(R.id.comment_tv_biaoqian,data.getComment_user_nick());
        holder.setText(R.id.comment_tv_content,data.getComment());
        if (!data.getComment_relation().equals("")){
            holder.setText(R.id.comment_tv_zuire,data.getComment_relation());
        }


        holder.setOnClickListener(R.id.roots_item_comment_type2_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentListener.onCommtntContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getContent_publish_user_id()));
            }
        });


          /*
        关系卡
         */
        holder.setOnClickListener(R.id.comment_user_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelationInfBean bean = new RelationInfBean(1, data.getComment_user_id()+"", data.getContent_id()+"");
                Intent intent = new Intent(mContext, RelationCalorieActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
            }
        });


         /*
        视频跳转
         */
        holder.setOnClickListener(R.id.roots_type2_itemview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int videoID = data.getVedio_info().getContent_id();
                String cover = data.getVedio_info().getSchool_cover_img_url().get(0);
                String vedio_url = data.getVedio_info().getVedio_url();
                VideoInfoBean bean =  new VideoInfoBean(vedio_url,cover,videoID);
                Intent intent = new Intent(mContext, VideoPlayerActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(BaseNewFragmnet.VIDEOINFO,bean);
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
            }
        });



        holder.setOnClickListener(R.id.roots_item_comment_type2_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentListener.onCommtntContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getContent_publish_user_id()));
            }
        });






    }

    private void setType1ItemData(ViewHolder holder, final InterestRoostBean.DataBean data) {

         /*
        Item 背景大图
         */
        RoundedImageView bg = holder.getRoundedImageView(R.id.roots_item_type1_bg);
        Picasso.with(mContext).load(data.getContent_cover_img_url_v2().get(0)).into(bg);
        /*
        推荐 标题 推荐标签 发帖用户名 以及发帖时间
         */
        if (data.getContent_title()!=null){
            holder.setText(R.id.roots_item_type1_biaoti,data.getContent_title());
        }else {
            TextView tvTilet = holder.getView(R.id.roots_item_type1_biaoti);
            tvTilet.setVisibility(View.GONE);
        }

        holder.setText(R.id.roots_china_item_school,data.getContent_publish_user_nick());
        holder.setText(R.id.roots_item_type1_time, TimeUtils.getTimeFormatText(String.valueOf((int)data.getContent_publish_time())));


         /*
        二级溯源标签
         */
        AutoLinearLayout layout = holder.getView(R.id.roots_tag_ll);
        final List<InterestRoostBean.DataBean.TagsBean> tags = data.getTags();
        for (int i = 0; i < tags.size(); i++) {
            TextView childAt = (TextView) layout.getChildAt(i);
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(tags.get(i).getTag());
            final int finalI = i;
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RoostBean roostBean = new RoostBean(tags.get(finalI).getTag(),tags.get(finalI).getTag_id()+"");
                    Intent intent = new Intent(mContext, TowInterRootsActvity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(KEY.KEY_ROOTSTAG, roostBean);
                    intent.putExtras(mBundle);
                    mContext.startActivity(intent);
                }
            });
        }


        /*
        热评的用户的信息
         */
        holder.setHeadIamgview(mContext,R.id.comment_user_head,data.getComment_user_avatar());
        holder.setText(R.id.comment_tv_biaoqian,data.getComment_user_nick());
        holder.setText(R.id.comment_tv_content,data.getComment());
        if (!data.getComment_relation().equals("")){
            holder.setText(R.id.comment_tv_zuire,data.getComment_relation());
        }

             /*
        关系卡
         */
        holder.setOnClickListener(R.id.comment_user_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelationInfBean bean = new RelationInfBean(1, data.getComment_user_id()+"", data.getContent_id()+"");
                Intent intent = new Intent(mContext, RelationCalorieActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
            }
        });

        holder.setOnClickListener(R.id.roots_item_comment_type1_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentListener.onCommtntContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getContent_publish_user_id()));
            }
        });


        holder.setOnClickListener(R.id.roots_type1_itemview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InformationParticularsActivity.class);
                intent.putExtra(BaseNewFragmnet.POSTINFO,data.getContent_id()+"");
                mContext.startActivity(intent);
            }
        });




    }



    public interface OncommentContentListener{
        void onCommtntContentListener(String contentID,String userID);
    }

    private OncommentContentListener contentListener;
    public void setOnCommentListenr(OncommentContentListener contentListener){
        this.contentListener = contentListener;
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        switch (viewType){
            case NEWITEM_TYPE1:
                return R.layout.item_roosttype1;
            case NEWITEM_TYPE2:
                return R.layout.item_roosttype2;
            case NEWITEM_TYPE3:
                return R.layout.item_roosttype3;
            case NEWITEM_TYPE4:
                return R.layout.item_roosttype4;
        }
        return 0;
    }

    @Override
    protected int getViewType(int position, InterestRoostBean.DataBean dataBean) {
           /*
        data 就是数据源 然后 根据数据源的的类型去判断 展示什么样的item
         */


        switch (dataBean.getContent_type()){
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
}
