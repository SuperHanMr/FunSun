package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/13.
 * Holle Android
 */

public class MeCenterMessageBean implements Serializable {
    /**
     * code : 200
     * data : [{"comment_data":{"content":"北京电影学院的Ye评论了你!","is_update":"0","timestamp":1.510550567E9},"system_data":{"content":"","is_update":"0","timestamp":""},"visit_data":{"content":"北京大学的小事儿-撩了你一下!","is_update":"0","timestamp":1.510550729E9}}]
     * msg : 201
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

    public static class DataBean implements Serializable {
        /**
         * comment_data : {"content":"北京电影学院的Ye评论了你!","is_update":"0","timestamp":1.510550567E9}
         * system_data : {"content":"","is_update":"0","timestamp":""}
         * visit_data : {"content":"北京大学的小事儿-撩了你一下!","is_update":"0","timestamp":1.510550729E9}
         */

        private CommentDataBean comment_data;
        private SystemDataBean system_data;
        private VisitDataBean visit_data;

        public CommentDataBean getComment_data() {
            return comment_data;
        }

        public void setComment_data(CommentDataBean comment_data) {
            this.comment_data = comment_data;
        }

        public SystemDataBean getSystem_data() {
            return system_data;
        }

        public void setSystem_data(SystemDataBean system_data) {
            this.system_data = system_data;
        }

        public VisitDataBean getVisit_data() {
            return visit_data;
        }

        public void setVisit_data(VisitDataBean visit_data) {
            this.visit_data = visit_data;
        }

        public static class CommentDataBean implements Serializable {
            /**
             * content : 北京电影学院的Ye评论了你!
             * is_update : 0
             * timestamp : 1.510550567E9
             */

            private String content;
            private String is_update;
            private String timestamp;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIs_update() {
                return is_update;
            }

            public void setIs_update(String is_update) {
                this.is_update = is_update;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }

        public static class SystemDataBean implements Serializable {
            /**
             * content :
             * is_update : 0
             * timestamp :
             */

            private String content;
            private String is_update;
            private String timestamp;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIs_update() {
                return is_update;
            }

            public void setIs_update(String is_update) {
                this.is_update = is_update;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }

        public static class VisitDataBean implements Serializable{
            /**
             * content : 北京大学的小事儿-撩了你一下!
             * is_update : 0
             * timestamp : 1.510550729E9
             */

            private String content;
            private String is_update;
            private String timestamp;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIs_update() {
                return is_update;
            }

            public void setIs_update(String is_update) {
                this.is_update = is_update;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}
