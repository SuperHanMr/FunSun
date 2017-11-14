package com.fengxun.funsun.model.bean;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/13.
 * Holle Android
 */

public class ToViewListBean {
    /**
     * code : 200
     * data : {"data":[{"friend_avatar":"http://qiniu.shujutiyu.com/FqG4tnhG4AKaYOBVPwv7a-MB1J1-","friend_id":48995,"friend_nick":"小事儿-","friend_relation":"","friend_school":"北京大学","update_time":1.510550729E9,"visit_count":1}],"page":1}
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
         * data : [{"friend_avatar":"http://qiniu.shujutiyu.com/FqG4tnhG4AKaYOBVPwv7a-MB1J1-","friend_id":48995,"friend_nick":"小事儿-","friend_relation":"","friend_school":"北京大学","update_time":1.510550729E9,"visit_count":1}]
         * page : 1
         */

        private int page;
        private List<DataBean> data;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
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
             * friend_avatar : http://qiniu.shujutiyu.com/FqG4tnhG4AKaYOBVPwv7a-MB1J1-
             * friend_id : 48995
             * friend_nick : 小事儿-
             * friend_relation :
             * friend_school : 北京大学
             * update_time : 1.510550729E9
             * visit_count : 1
             */

            private String friend_avatar;
            private int friend_id;
            private String friend_nick;
            private String friend_relation;
            private String friend_school;
            private double update_time;
            private int visit_count;

            public String getFriend_avatar() {
                return friend_avatar;
            }

            public void setFriend_avatar(String friend_avatar) {
                this.friend_avatar = friend_avatar;
            }

            public int getFriend_id() {
                return friend_id;
            }

            public void setFriend_id(int friend_id) {
                this.friend_id = friend_id;
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

            public double getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(double update_time) {
                this.update_time = update_time;
            }

            public int getVisit_count() {
                return visit_count;
            }

            public void setVisit_count(int visit_count) {
                this.visit_count = visit_count;
            }
        }
    }
}
