package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/12.
 * Holle Android
 */

public class RelationInfoBean implements Serializable {
    /**
     * code : 200
     * data : {"avatar":"http://qiniu.funsun.cn/wx-/790627-85a886e464098572fd00d92f24dd8268","id":"26519","level":"1","meet_count":0,"nick":"清华微生活","photo_list":[{"id":23815,"photo_url":"http://qiniu.funsun.cn/build-girl-avatar%20%2858%29.jpg"}],"relation":"","same_Interest":"","same_like":"","school":"5501","school_img":"http://qiniu.funsun.cn/%E6%B8%85%E5%8D%8E%E5%A4%A7%E5%AD%A6.png","school_name":"清华大学","sex":"0","user_blacklist":0}
     * msg : 查询成功
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
         * avatar : http://qiniu.funsun.cn/wx-/790627-85a886e464098572fd00d92f24dd8268
         * id : 26519
         * level : 1
         * meet_count : 0
         * nick : 清华微生活
         * photo_list : [{"id":23815,"photo_url":"http://qiniu.funsun.cn/build-girl-avatar%20%2858%29.jpg"}]
         * relation :
         * same_Interest :
         * same_like :
         * school : 5501
         * school_img : http://qiniu.funsun.cn/%E6%B8%85%E5%8D%8E%E5%A4%A7%E5%AD%A6.png
         * school_name : 清华大学
         * sex : 0
         * user_blacklist : 0
         */

        private String avatar;
        private String id;
        private String level;
        private int meet_count;
        private String nick;
        private String relation;
        private String same_Interest;
        private String same_like;
        private String school;
        private String school_img;
        private String school_name;
        private String sex;
        private int user_blacklist;
        private List<PhotoListBean> photo_list;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getMeet_count() {
            return meet_count;
        }

        public void setMeet_count(int meet_count) {
            this.meet_count = meet_count;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getSame_Interest() {
            return same_Interest;
        }

        public void setSame_Interest(String same_Interest) {
            this.same_Interest = same_Interest;
        }

        public String getSame_like() {
            return same_like;
        }

        public void setSame_like(String same_like) {
            this.same_like = same_like;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getSchool_img() {
            return school_img;
        }

        public void setSchool_img(String school_img) {
            this.school_img = school_img;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getUser_blacklist() {
            return user_blacklist;
        }

        public void setUser_blacklist(int user_blacklist) {
            this.user_blacklist = user_blacklist;
        }

        public List<PhotoListBean> getPhoto_list() {
            return photo_list;
        }

        public void setPhoto_list(List<PhotoListBean> photo_list) {
            this.photo_list = photo_list;
        }

        public static class PhotoListBean {
            /**
             * id : 23815
             * photo_url : http://qiniu.funsun.cn/build-girl-avatar%20%2858%29.jpg
             */

            private int id;
            private String photo_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }
        }
    }
}
