package com.fengxun.funsun.model.bean;

import java.io.Serializable;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/21.
 * Holle Android
 */

public class LikeBean implements Serializable {
    /**
     * code : 200
     * data : {"like_cnt":1}
     * msg : 点赞成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * like_cnt : 1
         */

        private int like_cnt;

        public int getLike_cnt() {
            return like_cnt;
        }

        public void setLike_cnt(int like_cnt) {
            this.like_cnt = like_cnt;
        }
    }
}
