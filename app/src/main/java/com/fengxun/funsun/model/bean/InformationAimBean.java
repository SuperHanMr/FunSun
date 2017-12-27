package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/19.
 * Holle Android
 */

public class InformationAimBean implements Serializable {
    /**
     * code : 200
     * data : {"snapshot":"6f84a7d3-96ac-4da0-a26b-3f6dbeb6c3ce","users":[{"user_avatar":"http://qiniu.shujutiyu.com/FvOa8WvbqxkeAufMuIrJc_1eaTlP","user_id":14648,"user_nick":"浅忆👄：小姐姐"}]}
     * msg : 获取数据成功
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
         * snapshot : 6f84a7d3-96ac-4da0-a26b-3f6dbeb6c3ce
         * users : [{"user_avatar":"http://qiniu.shujutiyu.com/FvOa8WvbqxkeAufMuIrJc_1eaTlP","user_id":14648,"user_nick":"浅忆👄：小姐姐"}]
         */

        private String snapshot;
        private List<UsersBean> users;

        public String getSnapshot() {
            return snapshot;
        }

        public void setSnapshot(String snapshot) {
            this.snapshot = snapshot;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * user_avatar : http://qiniu.shujutiyu.com/FvOa8WvbqxkeAufMuIrJc_1eaTlP
             * user_id : 14648
             * user_nick : 浅忆👄：小姐姐
             */

            private String user_avatar;
            private int user_id;
            private String user_nick;

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
        }
    }
}
