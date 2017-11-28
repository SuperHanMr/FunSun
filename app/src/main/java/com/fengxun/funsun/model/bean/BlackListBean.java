package com.fengxun.funsun.model.bean;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/15.
 * Holle Android
 */

public class BlackListBean  {
    /**
     * code : 200
     * data : [{"user_avatar":"http://qiniu.funsun.cn/wx-/790627-488a8d97d844969398f8aa87609ce9b3","user_id":27393,"user_nick":"北京外国语大学","user_relation":"","user_school":"北京外国语大学"}]
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
         * user_avatar : http://qiniu.funsun.cn/wx-/790627-488a8d97d844969398f8aa87609ce9b3
         * user_id : 27393
         * user_nick : 北京外国语大学
         * user_relation :
         * user_school : 北京外国语大学
         */

        private String user_avatar;
        private int user_id;
        private String user_nick;
        private String user_relation;
        private String user_school;

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
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
