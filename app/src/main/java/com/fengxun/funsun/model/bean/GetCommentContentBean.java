package com.fengxun.funsun.model.bean;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/2.
 * Holle Android
 */

public class GetCommentContentBean {
    /**
     * code : 200
     * data : [{"comment_content":"刚po了一条，大家快来围观","comment_direction":0,"comment_evaluation":"","comment_id":16003,"comment_relation":"","comment_school":"","comment_time":1.51218799E9,"comment_user":49020,"comment_user_avatar":"http://qiniu.funsun.cn/qq_coverimg-cec507de-560f-4d11-9ea2-e7079f0cbaf5","comment_user_nick":"四叶草影视","hot_cnt":0,"latest_child_comment":"","latest_child_user":"","latest_child_user_avatar":"","latest_child_user_nick":"","like_cnt":0}]
     * msg : 获取数据成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * comment_content : 刚po了一条，大家快来围观
         * comment_direction : 0
         * comment_evaluation :
         * comment_id : 16003
         * comment_relation :
         * comment_school :
         * comment_time : 1.51218799E9
         * comment_user : 49020
         * comment_user_avatar : http://qiniu.funsun.cn/qq_coverimg-cec507de-560f-4d11-9ea2-e7079f0cbaf5
         * comment_user_nick : 四叶草影视
         * hot_cnt : 0
         * latest_child_comment :
         * latest_child_user :
         * latest_child_user_avatar :
         * latest_child_user_nick :
         * like_cnt : 0
         */

        private String comment_content;
        private int comment_direction;
        private String comment_evaluation;
        private int comment_id;
        private String comment_relation;
        private String comment_school;
        private double comment_time;
        private int comment_user;
        private String comment_user_avatar;
        private String comment_user_nick;
        private int hot_cnt;
        private String latest_child_comment;
        private String latest_child_user;
        private String latest_child_user_avatar;
        private String latest_child_user_nick;
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

        public String getComment_evaluation() {
            return comment_evaluation;
        }

        public void setComment_evaluation(String comment_evaluation) {
            this.comment_evaluation = comment_evaluation;
        }

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public String getComment_relation() {
            return comment_relation;
        }

        public void setComment_relation(String comment_relation) {
            this.comment_relation = comment_relation;
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

        public int getHot_cnt() {
            return hot_cnt;
        }

        public void setHot_cnt(int hot_cnt) {
            this.hot_cnt = hot_cnt;
        }

        public String getLatest_child_comment() {
            return latest_child_comment;
        }

        public void setLatest_child_comment(String latest_child_comment) {
            this.latest_child_comment = latest_child_comment;
        }

        public String getLatest_child_user() {
            return latest_child_user;
        }

        public void setLatest_child_user(String latest_child_user) {
            this.latest_child_user = latest_child_user;
        }

        public String getLatest_child_user_avatar() {
            return latest_child_user_avatar;
        }

        public void setLatest_child_user_avatar(String latest_child_user_avatar) {
            this.latest_child_user_avatar = latest_child_user_avatar;
        }

        public String getLatest_child_user_nick() {
            return latest_child_user_nick;
        }

        public void setLatest_child_user_nick(String latest_child_user_nick) {
            this.latest_child_user_nick = latest_child_user_nick;
        }

        public int getLike_cnt() {
            return like_cnt;
        }

        public void setLike_cnt(int like_cnt) {
            this.like_cnt = like_cnt;
        }
    }
}
