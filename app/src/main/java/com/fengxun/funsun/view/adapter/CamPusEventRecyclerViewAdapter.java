package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.view.View;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CamPusEventBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.listener.NewItemListener;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.view.base.MultiBaseAdapter;
import com.fengxun.funsun.view.base.ViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/7.
 * Holle Android
 * 大事件 多item
 * 0是大图标题
 * 1是大图视频
 */

public class CamPusEventRecyclerViewAdapter extends MultiBaseAdapter<CamPusEventBean.DataBean> {

    private final int CAMPUSCONTENTTYPE_TUEN = 0;
    private final int CAMPUSCONTENTTYPE_VIDEO = 1;



    public CamPusEventRecyclerViewAdapter(Context context, List<CamPusEventBean.DataBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);

    }


    @Override
    protected void convert(ViewHolder holder, CamPusEventBean.DataBean data, int position, int viewType) {

        switch (viewType){
            case CAMPUSCONTENTTYPE_TUEN:
                /*
                图文帖子
                 */

                processData(holder,data,false);
                break;
            case CAMPUSCONTENTTYPE_VIDEO:
                /*
                视频帖子
                 */
                processData(holder,data,true);

                break;
        }
    }

    private void processData(ViewHolder holder, final CamPusEventBean.DataBean data, boolean isVideo) {

        RoundedImageView roundedImageView = holder.getRoundedImageView(R.id.campus_event_iv_bg);
        Picasso.with(mContext).load(data.getSchool_cover_img_url().get(0)).into(roundedImageView);
        holder.setText(R.id.campus_event_tv_biaoti,data.getContent_title());
        holder.setText(R.id.campus_event_tv_num,String.valueOf(data.getHot_cnt()));
        holder.setText(R.id.campus_event_tv_school,data.getPublish_user_nick());
        holder.setText(R.id.campus_event_tv_time, TimeUtils.getTimeFormatText(String.valueOf((int)data.getPublish_time())));


        if (data.getVedio_url().equals("")){
            holder.getView(R.id.campus_event_iv_bofang).setVisibility(View.GONE);

        }else {

            holder.getView(R.id.campus_event_iv_bofang).setVisibility(View.VISIBLE);
        }


        holder.setOnClickListener(R.id.campus_event_item_fl, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (data.getVedio_url().equals("")){
                    listener.OnPostInfoListener(String.valueOf(data.getContent_id()),data.getContent_type());
                }else {
                    VideoInfoBean bean = new VideoInfoBean(data.getVedio_url(),data.getSchool_cover_img_url().get(0),data.getContent_id());
                    listener.OnVideoInfoListener(bean);
                }

            }
        });

        holder.setOnClickListener(R.id.new_item_comment_type1_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnCommentContentListener(String.valueOf(data.getContent_id()),String.valueOf(data.getPublish_user()));
            }
        });




        holder.setText(R.id.campus_event_tv_text,data.getComment_school());
        holder.setText(R.id.campus_event_tv_comment_name,data.getComment_user_nick());
        holder.setText(R.id.campus_event_tv_comment_content,data.getComment());


        CircleImageView head = holder.getView(R.id.campus_event_iv_head);
        Picasso.with(mContext).load(data.getComment_user_avatar()).into(head);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRelationListener(String.valueOf(data.getComment_user_id()),String.valueOf(data.getContent_id()),2);
            }
        });





    }

    @Override
    protected int getItemLayoutId(int viewType) {
        switch (viewType){
            case CAMPUSCONTENTTYPE_TUEN:
                return R.layout.campus_eventbus;
            case CAMPUSCONTENTTYPE_VIDEO:
                return  R.layout.campus_eventbus;
        }

        return 0;
    }


    @Override
    protected int getViewType(int position, CamPusEventBean.DataBean data) {
        switch (data.getContent_type()){
            case CAMPUSCONTENTTYPE_TUEN:
                return CAMPUSCONTENTTYPE_TUEN;
            case CAMPUSCONTENTTYPE_VIDEO:
                return CAMPUSCONTENTTYPE_VIDEO;
        }

        return 0;
    }

    private NewItemListener listener;
    public void setItemListener(NewItemListener listener){
        this.listener = listener;
    }

}
