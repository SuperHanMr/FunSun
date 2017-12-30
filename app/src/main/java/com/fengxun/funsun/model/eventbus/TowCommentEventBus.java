package com.fengxun.funsun.model.eventbus;

import com.fengxun.funsun.model.bean.AtextBean;
import com.fengxun.funsun.model.bean.CommentInfoBean;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/28.
 * Holle Android
 */

public class TowCommentEventBus  {


    private AtextBean.DataBean dataBean;

    private CommentInfoBean.DataBean commentBean;

    public TowCommentEventBus(AtextBean.DataBean dataBean, CommentInfoBean.DataBean commentBean) {
        this.dataBean = dataBean;
        this.commentBean = commentBean;
    }

    public AtextBean.DataBean getDataBean() {
        return dataBean;
    }

    public CommentInfoBean.DataBean getCommentBean() {
        return commentBean;
    }

}
