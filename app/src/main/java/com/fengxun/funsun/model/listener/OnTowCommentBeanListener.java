package com.fengxun.funsun.model.listener;

import com.fengxun.funsun.model.bean.CommentInfoBean;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/28.
 * Holle Android
 */

public interface OnTowCommentBeanListener {

    void onTowCommentBeanListenr(CommentInfoBean.DataBean commentBean);
    void onCommentRelationUserId(String userId);

}
