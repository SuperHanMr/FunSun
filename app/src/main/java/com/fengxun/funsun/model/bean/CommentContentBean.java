package com.fengxun.funsun.model.bean;

import java.io.Serializable;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/2.
 * Holle Android
 */

public class CommentContentBean implements Serializable{
    /**
     * code : 200
     * data : {"comment_content":"22從2","comment_direction":0,"comment_id":16024,"comment_school":"郑州大学","comment_time":1.512198139E9,"comment_user":49625,"comment_user_avatar":"http://qiniu.shujutiyu.com/FoHIjvQF278UgCqRSCevj1AdkdXa","comment_user_nick":"666","content_id":15678,"hot_cnt":0,"like_cnt":0}
     * msg : 添加评论成功
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

    public static class DataBean  implements Serializable{
        /**
         * comment_content : 22從2
         * comment_direction : 0
         * comment_id : 16024
         * comment_school : 郑州大学
         * comment_time : 1.512198139E9
         * comment_user : 49625
         * comment_user_avatar : http://qiniu.shujutiyu.com/FoHIjvQF278UgCqRSCevj1AdkdXa
         * comment_user_nick : 666
         * content_id : 15678
         * hot_cnt : 0
         * like_cnt : 0
         */

        private String comment_content;
        private int comment_direction;
        private int comment_id;
        private String comment_school;
        private double comment_time;
        private int comment_user;
        private String comment_user_avatar;
        private String comment_user_nick;
        private int content_id;
        private int hot_cnt;
        private int like_cnt;

        public String getComment_content() {
            return comment_content;
        }

        public void setComment_content(String comment_content) {
            this.comment_content = comment_content;
        }

        public int getComment_direction() {
            return comment_direction;
        }

        public void setComment_direction(int comment_direction) {
            this.comment_direction = comment_direction;
        }

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public String getComment_school() {
            return comment_school;
        }

        public void setComment_school(String comment_school) {
            this.comment_school = comment_school;
        }

        public double getComment_time() {
            return comment_time;
        }

        public void setComment_time(double comment_time) {
            this.comment_time = comment_time;
        }

        public int getComment_user() {
            return comment_user;
        }

        public void setComment_user(int comment_user) {
            this.comment_user = comment_user;
        }

        public String getComment_user_avatar() {
            return comment_user_avatar;
        }

        public void setComment_user_avatar(String comment_user_avatar) {
            this.comment_user_avatar = comment_user_avatar;
        }

        public String getComment_user_nick() {
            return comment_user_nick;
        }

        public void setComment_user_nick(String comment_user_nick) {
            this.comment_user_nick = comment_user_nick;
        }


        public int getContent_id() {
            return content_id;
        }

        public void setContent_id(int content_id) {
            this.content_id = content_id;
        }

        public int getHot_cnt() {
            return hot_cnt;
        }

        public void setHot_cnt(int hot_cnt) {
            this.hot_cnt = hot_cnt;
        }

        public int getLike_cnt() {
            return like_cnt;
        }

        public void setLike_cnt(int like_cnt) {
            this.like_cnt = like_cnt;
        }
    }
}
