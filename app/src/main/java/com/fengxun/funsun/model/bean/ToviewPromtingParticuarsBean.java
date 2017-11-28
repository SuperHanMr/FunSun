package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/14.
 * Holle Android
 */

public class ToviewPromtingParticuarsBean implements Serializable{
    /**
     * code : 200
     * data : {"data":[{"content_id":"9006","content_publish_user_avatar":"http://qiniu.funsun.cn/qq_coverimg-278f03a1-e03e-4085-92de-fce4bfd025b0","content_publish_user_nick":"科技美学","content_publish_user_relation":"","content_publish_user_school_name":"","content_time":1.510545458E9,"content_title":"iPhone X/8 Plus产能大增长","content_type":0,"content_url":"http://qiniu.funsun.cn/wx-/790627-f40363a3a20c65a297323d93ea7ca256.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","friend":48995,"friend_avatar":"http://qiniu.shujutiyu.com/FqG4tnhG4AKaYOBVPwv7a-MB1J1-","friend_nick":"小事儿-","friend_relation":"","friend_school":"北京大学","source_visit":1,"update_time":1.510550729E9}],"page":"1"}
     * msg : 查询成功
     */

    private int code;
    private DataBeanX data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBeanX {
        /**
         * data : [{"content_id":"9006","content_publish_user_avatar":"http://qiniu.funsun.cn/qq_coverimg-278f03a1-e03e-4085-92de-fce4bfd025b0","content_publish_user_nick":"科技美学","content_publish_user_relation":"","content_publish_user_school_name":"","content_time":1.510545458E9,"content_title":"iPhone X/8 Plus产能大增长","content_type":0,"content_url":"http://qiniu.funsun.cn/wx-/790627-f40363a3a20c65a297323d93ea7ca256.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","friend":48995,"friend_avatar":"http://qiniu.shujutiyu.com/FqG4tnhG4AKaYOBVPwv7a-MB1J1-","friend_nick":"小事儿-","friend_relation":"","friend_school":"北京大学","source_visit":1,"update_time":1.510550729E9}]
         * page : 1
         */

        private String page;
        private List<DataBean> data;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content_id : 9006
             * content_publish_user_avatar : http://qiniu.funsun.cn/qq_coverimg-278f03a1-e03e-4085-92de-fce4bfd025b0
             * content_publish_user_nick : 科技美学
             * content_publish_user_relation :
             * content_publish_user_school_name :
             * content_time : 1.510545458E9
             * content_title : iPhone X/8 Plus产能大增长
             * content_type : 0
             * content_url : http://qiniu.funsun.cn/wx-/790627-f40363a3a20c65a297323d93ea7ca256.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
             * friend : 48995
             * friend_avatar : http://qiniu.shujutiyu.com/FqG4tnhG4AKaYOBVPwv7a-MB1J1-
             * friend_nick : 小事儿-
             * friend_relation :
             * friend_school : 北京大学
             * source_visit : 1
             * update_time : 1.510550729E9
             */

            private String content_id;
            private String content_publish_user_avatar;
            private String content_publish_user_nick;
            private String content_publish_user_relation;
            private String content_publish_user_school_name;
            private double content_time;
            private String content_title;
            private int content_type;
            private String content_url;
            private int friend;
            private String friend_avatar;
            private String friend_nick;
            private String friend_relation;
            private String friend_school;
            private int source_visit;
            private double update_time;

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getContent_publish_user_avatar() {
                return content_publish_user_avatar;
            }

            public void setContent_publish_user_avatar(String content_publish_user_avatar) {
                this.content_publish_user_avatar = content_publish_user_avatar;
            }

            public String getContent_publish_user_nick() {
                return content_publish_user_nick;
            }

            public void setContent_publish_user_nick(String content_publish_user_nick) {
                this.content_publish_user_nick = content_publish_user_nick;
            }

            public String getContent_publish_user_relation() {
                return content_publish_user_relation;
            }

            public void setContent_publish_user_relation(String content_publish_user_relation) {
                this.content_publish_user_relation = content_publish_user_relation;
            }

            public String getContent_publish_user_school_name() {
                return content_publish_user_school_name;
            }

            public void setContent_publish_user_school_name(String content_publish_user_school_name) {
                this.content_publish_user_school_name = content_publish_user_school_name;
            }

            public double getContent_time() {
                return content_time;
            }

            public void setContent_time(double content_time) {
                this.content_time = content_time;
            }

            public String getContent_title() {
                return content_title;
            }

            public void setContent_title(String content_title) {
                this.content_title = content_title;
            }

            public int getContent_type() {
                return content_type;
            }

            public void setContent_type(int content_type) {
                this.content_type = content_type;
            }

            public String getContent_url() {
                return content_url;
            }

            public void setContent_url(String content_url) {
                this.content_url = content_url;
            }

            public int getFriend() {
                return friend;
            }

            public void setFriend(int friend) {
                this.friend = friend;
            }

            public String getFriend_avatar() {
                return friend_avatar;
            }

            public void setFriend_avatar(String friend_avatar) {
                this.friend_avatar = friend_avatar;
            }

            public String getFriend_nick() {
                return friend_nick;
            }

            public void setFriend_nick(String friend_nick) {
                this.friend_nick = friend_nick;
            }

            public String getFriend_relation() {
                return friend_relation;
            }

            public void setFriend_relation(String friend_relation) {
                this.friend_relation = friend_relation;
            }

            public String getFriend_school() {
                return friend_school;
            }

            public void setFriend_school(String friend_school) {
                this.friend_school = friend_school;
            }

            public int getSource_visit() {
                return source_visit;
            }

            public void setSource_visit(int source_visit) {
                this.source_visit = source_visit;
            }

            public double getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(double update_time) {
                this.update_time = update_time;
            }
        }
    }
}
