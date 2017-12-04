package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/2.
 * Holle Android
 */

public class MeetTheManBean implements Serializable {


    /**
     * code : 200
     * data : [{"content_id":"15698","user_avatar":"http://qiniu.shujutiyu.com/FpbBcgugQy0YU5tutv3S6N2pNopP","user_blacklist":0,"user_comment":"","user_encounters":63,"user_id":48999,"user_nick":"像我这种人在小说里一般都是主角","user_relation":"","user_school":"北京大学"}]
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
         * content_id : 15698
         * user_avatar : http://qiniu.shujutiyu.com/FpbBcgugQy0YU5tutv3S6N2pNopP
         * user_blacklist : 0
         * user_comment :
         * user_encounters : 63
         * user_id : 48999
         * user_nick : 像我这种人在小说里一般都是主角
         * user_relation :
         * user_school : 北京大学
         */

        private String content_id;
        private String user_avatar;
        private int user_blacklist;
        private String user_comment;
        private int user_encounters;
        private int user_id;
        private String user_nick;
        private String user_relation;
        private String user_school;

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public int getUser_blacklist() {
            return user_blacklist;
        }

        public void setUser_blacklist(int user_blacklist) {
            this.user_blacklist = user_blacklist;
        }

        public String getUser_comment() {
            return user_comment;
        }

        public void setUser_comment(String user_comment) {
            this.user_comment = user_comment;
        }

        public int getUser_encounters() {
            return user_encounters;
        }

        public void setUser_encounters(int user_encounters) {
            this.user_encounters = user_encounters;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_nick() {
            return user_nick;
        }

        public void setUser_nick(String user_nick) {
            this.user_nick = user_nick;
        }

        public String getUser_relation() {
            return user_relation;
        }

        public void setUser_relation(String user_relation) {
            this.user_relation = user_relation;
        }

        public String getUser_school() {
            return user_school;
        }

        public void setUser_school(String user_school) {
            this.user_school = user_school;
        }
    }
}
