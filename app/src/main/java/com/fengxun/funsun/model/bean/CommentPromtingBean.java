package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/12.
 * Holle Android
 */

public class CommentPromtingBean implements Serializable {
    /**
     * code : 200
     * data : {"data":[{"comment_content":"测试12345678","comment_relation":"","comment_school":"","comment_time":1.505807347E9,"comment_user":2,"comment_user_avatar":"123455","comment_user_nick":"123"}],"page":1}
     * msg : 获取数据成功
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
         * data : [{"comment_content":"测试12345678","comment_relation":"","comment_school":"","comment_time":1.505807347E9,"comment_user":2,"comment_user_avatar":"123455","comment_user_nick":"123"}]
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
             * comment_content : 测试12345678
             * comment_relation :
             * comment_school :
             * comment_time : 1.505807347E9
             * comment_user : 2
             * comment_user_avatar : 123455
             * comment_user_nick : 123
             */

            private String comment_content;
            private String comment_relation;
            private String comment_school;
            private double comment_time;
            private int comment_user;
            private String comment_user_avatar;
            private String comment_user_nick;

            public String getComment_content() {
                return comment_content;
            }

            public void setComment_content(String comment_content) {
                this.comment_content = comment_content;
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
        }
    }
}
