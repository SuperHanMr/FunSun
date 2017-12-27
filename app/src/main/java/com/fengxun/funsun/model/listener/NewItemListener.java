package com.fengxun.funsun.model.listener;

import com.fengxun.funsun.model.bean.VideoInfoBean;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/30.
 * Holle Android
 */

public interface NewItemListener {

    /*
    跳转携带 视频信息
     */
    void OnVideoInfoListener(VideoInfoBean bean);

    /*
    跳转 携带 图文帖子ID 和图文type 原生/web
     */
    void OnPostInfoListener(String postId,int type);



    /*
    用户评论信息
     */
    void OnCommentContentListener(String contentId,String userId);


    /*
    关系卡 ID 资讯的ID 类型 2
     */

    void onRelationListener(String userId,String contentId,int type);


}
