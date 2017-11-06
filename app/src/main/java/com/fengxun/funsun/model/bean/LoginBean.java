package com.fengxun.funsun.model.bean;

import java.io.Serializable;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/4.
 * Holle Android
 * 内容：实序列化
 */

public class LoginBean implements Serializable{

    /**
     * msg :
     * data : {"expires_in":864000,"user_info":{"nick":"瀚瀚","admission":"2011","school_name":"郑州大学","avatar":"http://qiniu.shujutiyu.com/FpUR7Id47JPn4eEuQikQQiSxRt-C","funsun_id":"SuperHan","school":"5603","sex":1,"level":1,"school_img":"http://qiniu.funsun.cn/%E9%83%91%E5%B7%9E%E5%A4%A7%E5%AD%A6.png","id":49625},"access_token":"1ikeH-4RwJE9iXMCYedel-Yfc7g","refresh_token":"dZE8ZXEfMwAMSoCB9EZATcAngcA"}
     * code : 200
     */

    private String msg;
    private DataBean data;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean implements Serializable {
        /**
         * expires_in : 864000
         * user_info : {"nick":"瀚瀚","admission":"2011","school_name":"郑州大学","avatar":"http://qiniu.shujutiyu.com/FpUR7Id47JPn4eEuQikQQiSxRt-C","funsun_id":"SuperHan","school":"5603","sex":1,"level":1,"school_img":"http://qiniu.funsun.cn/%E9%83%91%E5%B7%9E%E5%A4%A7%E5%AD%A6.png","id":49625}
         * access_token : 1ikeH-4RwJE9iXMCYedel-Yfc7g
         * refresh_token : dZE8ZXEfMwAMSoCB9EZATcAngcA
         */

        private int expires_in;
        private UserInfoBean user_info;
        private String access_token;
        private String refresh_token;

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public static class UserInfoBean implements Serializable {
            /**
             * nick : 瀚瀚
             * admission : 2011
             * school_name : 郑州大学
             * avatar : http://qiniu.shujutiyu.com/FpUR7Id47JPn4eEuQikQQiSxRt-C
             * funsun_id : SuperHan
             * school : 5603
             * sex : 1
             * level : 1
             * school_img : http://qiniu.funsun.cn/%E9%83%91%E5%B7%9E%E5%A4%A7%E5%AD%A6.png
             * id : 49625
             */

            private String nick;
            private String admission;
            private String school_name;
            private String avatar;
            private String funsun_id;
            private String school;
            private int sex;
            private int level;
            private String school_img;
            private int id;

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getAdmission() {
                return admission;
            }

            public void setAdmission(String admission) {
                this.admission = admission;
            }

            public String getSchool_name() {
                return school_name;
            }

            public void setSchool_name(String school_name) {
                this.school_name = school_name;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getFunsun_id() {
                return funsun_id;
            }

            public void setFunsun_id(String funsun_id) {
                this.funsun_id = funsun_id;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getSchool_img() {
                return school_img;
            }

            public void setSchool_img(String school_img) {
                this.school_img = school_img;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
