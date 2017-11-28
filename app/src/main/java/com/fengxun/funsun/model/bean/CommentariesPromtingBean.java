package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/14.
 * Holle Android
 */

public class CommentariesPromtingBean implements Serializable{
    /**
     * data : {"data":[{"second_comment_content":"看来我的务实的和你们相比弱爆了 都怪我选择了北影 考颜值和实力取胜 上学期间有一次的实习机会让我有幸去了华谊兄弟公司实习。不说了，我要和我学妹关晓彤对剧本去了！👻👻👻","comment_user_avatar":"http://qiniu.funsun.cn/build-boy-avatar%20%2832%29.jpg","comment_id":"7699","is_collection":"0","parent_comment_id":"7699","comment_user_nick":"Ye","comment_evaluation":"like","comment_content":"大家努力就好，有时候选择比能力更重要。去年我们校招，我承认我不是班里学习最好的，但是我是最勤奋的，我始终相信笨鸟先飞，刚毕业我就是班里第一个找个工作的人拿到了美团的offer，组长对新人特别照顾，给我配置了电动车和送餐箱。不说了，刚刚又接了一单","hot_cnt":0,"content_publish_user_relation":"","comment_school":"北京电影学院","like_cnt":1,"content_publish_user_id":26421,"content_id":"8995","content_publish_user_school_name":"北京大学","share_data":{"share_img":"http://qiniu.funsun.cn/wx-/790627-1e18c15bed98efd1b78a868f03a27271.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","share_content":"[郑州大学]的55555第一个分享了这条消息","share_url":"http://api.funsun.cn/share/v1/?c=8995","share_title":"活动 | 2017北京大学秋季求职交流会"},"update_time":1.510550567E9,"content_publish_user_school_id":"5500","comment_time":1.510549388E9,"comment_user":49625,"comment_relation":""}],"page":"1"}
     * code : 200
     * msg : 查询成功
     */

    private DataBeanX data;
    private int code;
    private String msg;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

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

    public static class DataBeanX {
        /**
         * data : [{"second_comment_content":"看来我的务实的和你们相比弱爆了 都怪我选择了北影 考颜值和实力取胜 上学期间有一次的实习机会让我有幸去了华谊兄弟公司实习。不说了，我要和我学妹关晓彤对剧本去了！👻👻👻","comment_user_avatar":"http://qiniu.funsun.cn/build-boy-avatar%20%2832%29.jpg","comment_id":"7699","is_collection":"0","parent_comment_id":"7699","comment_user_nick":"Ye","comment_evaluation":"like","comment_content":"大家努力就好，有时候选择比能力更重要。去年我们校招，我承认我不是班里学习最好的，但是我是最勤奋的，我始终相信笨鸟先飞，刚毕业我就是班里第一个找个工作的人拿到了美团的offer，组长对新人特别照顾，给我配置了电动车和送餐箱。不说了，刚刚又接了一单","hot_cnt":0,"content_publish_user_relation":"","comment_school":"北京电影学院","like_cnt":1,"content_publish_user_id":26421,"content_id":"8995","content_publish_user_school_name":"北京大学","share_data":{"share_img":"http://qiniu.funsun.cn/wx-/790627-1e18c15bed98efd1b78a868f03a27271.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","share_content":"[郑州大学]的55555第一个分享了这条消息","share_url":"http://api.funsun.cn/share/v1/?c=8995","share_title":"活动 | 2017北京大学秋季求职交流会"},"update_time":1.510550567E9,"content_publish_user_school_id":"5500","comment_time":1.510549388E9,"comment_user":49625,"comment_relation":""}]
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
             * second_comment_content : 看来我的务实的和你们相比弱爆了 都怪我选择了北影 考颜值和实力取胜 上学期间有一次的实习机会让我有幸去了华谊兄弟公司实习。不说了，我要和我学妹关晓彤对剧本去了！👻👻👻
             * comment_user_avatar : http://qiniu.funsun.cn/build-boy-avatar%20%2832%29.jpg
             * comment_id : 7699
             * is_collection : 0
             * parent_comment_id : 7699
             * comment_user_nick : Ye
             * comment_evaluation : like
             * comment_content : 大家努力就好，有时候选择比能力更重要。去年我们校招，我承认我不是班里学习最好的，但是我是最勤奋的，我始终相信笨鸟先飞，刚毕业我就是班里第一个找个工作的人拿到了美团的offer，组长对新人特别照顾，给我配置了电动车和送餐箱。不说了，刚刚又接了一单
             * hot_cnt : 0
             * content_publish_user_relation :
             * comment_school : 北京电影学院
             * like_cnt : 1
             * content_publish_user_id : 26421
             * content_id : 8995
             * content_publish_user_school_name : 北京大学
             * share_data : {"share_img":"http://qiniu.funsun.cn/wx-/790627-1e18c15bed98efd1b78a868f03a27271.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","share_content":"[郑州大学]的55555第一个分享了这条消息","share_url":"http://api.funsun.cn/share/v1/?c=8995","share_title":"活动 | 2017北京大学秋季求职交流会"}
             * update_time : 1.510550567E9
             * content_publish_user_school_id : 5500
             * comment_time : 1.510549388E9
             * comment_user : 49625
             * comment_relation :
             */

            private String second_comment_content;
            private String comment_user_avatar;
            private String comment_id;
            private String is_collection;
            private String parent_comment_id;
            private String comment_user_nick;
            private String comment_evaluation;
            private String comment_content;
            private int hot_cnt;
            private String content_publish_user_relation;
            private String comment_school;
            private int like_cnt;
            private int content_publish_user_id;
            private String content_id;
            private String content_publish_user_school_name;
            private ShareDataBean share_data;
            private double update_time;
            private String content_publish_user_school_id;
            private double comment_time;
            private int comment_user;
            private String comment_relation;

            public String getSecond_comment_content() {
                return second_comment_content;
            }

            public void setSecond_comment_content(String second_comment_content) {
                this.second_comment_content = second_comment_content;
            }

            public String getComment_user_avatar() {
                return comment_user_avatar;
            }

            public void setComment_user_avatar(String comment_user_avatar) {
                this.comment_user_avatar = comment_user_avatar;
            }

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
            }

            public String getIs_collection() {
                return is_collection;
            }

            public void setIs_collection(String is_collection) {
                this.is_collection = is_collection;
            }

            public String getParent_comment_id() {
                return parent_comment_id;
            }

            public void setParent_comment_id(String parent_comment_id) {
                this.parent_comment_id = parent_comment_id;
            }

            public String getComment_user_nick() {
                return comment_user_nick;
            }

            public void setComment_user_nick(String comment_user_nick) {
                this.comment_user_nick = comment_user_nick;
            }

            public String getComment_evaluation() {
                return comment_evaluation;
            }

            public void setComment_evaluation(String comment_evaluation) {
                this.comment_evaluation = comment_evaluation;
            }

            public String getComment_content() {
                return comment_content;
            }

            public void setComment_content(String comment_content) {
                this.comment_content = comment_content;
            }

            public int getHot_cnt() {
                return hot_cnt;
            }

            public void setHot_cnt(int hot_cnt) {
                this.hot_cnt = hot_cnt;
            }

            public String getContent_publish_user_relation() {
                return content_publish_user_relation;
            }

            public void setContent_publish_user_relation(String content_publish_user_relation) {
                this.content_publish_user_relation = content_publish_user_relation;
            }

            public String getComment_school() {
                return comment_school;
            }

            public void setComment_school(String comment_school) {
                this.comment_school = comment_school;
            }

            public int getLike_cnt() {
                return like_cnt;
            }

            public void setLike_cnt(int like_cnt) {
                this.like_cnt = like_cnt;
            }

            public int getContent_publish_user_id() {
                return content_publish_user_id;
            }

            public void setContent_publish_user_id(int content_publish_user_id) {
                this.content_publish_user_id = content_publish_user_id;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getContent_publish_user_school_name() {
                return content_publish_user_school_name;
            }

            public void setContent_publish_user_school_name(String content_publish_user_school_name) {
                this.content_publish_user_school_name = content_publish_user_school_name;
            }

            public ShareDataBean getShare_data() {
                return share_data;
            }

            public void setShare_data(ShareDataBean share_data) {
                this.share_data = share_data;
            }

            public double getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(double update_time) {
                this.update_time = update_time;
            }

            public String getContent_publish_user_school_id() {
                return content_publish_user_school_id;
            }

            public void setContent_publish_user_school_id(String content_publish_user_school_id) {
                this.content_publish_user_school_id = content_publish_user_school_id;
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

            public String getComment_relation() {
                return comment_relation;
            }

            public void setComment_relation(String comment_relation) {
                this.comment_relation = comment_relation;
            }

            public static class ShareDataBean {
                /**
                 * share_img : http://qiniu.funsun.cn/wx-/790627-1e18c15bed98efd1b78a868f03a27271.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
                 * share_content : [郑州大学]的55555第一个分享了这条消息
                 * share_url : http://api.funsun.cn/share/v1/?c=8995
                 * share_title : 活动 | 2017北京大学秋季求职交流会
                 */

                private String share_img;
                private String share_content;
                private String share_url;
                private String share_title;

                public String getShare_img() {
                    return share_img;
                }

                public void setShare_img(String share_img) {
                    this.share_img = share_img;
                }

                public String getShare_content() {
                    return share_content;
                }

                public void setShare_content(String share_content) {
                    this.share_content = share_content;
                }

                public String getShare_url() {
                    return share_url;
                }

                public void setShare_url(String share_url) {
                    this.share_url = share_url;
                }

                public String getShare_title() {
                    return share_title;
                }

                public void setShare_title(String share_title) {
                    this.share_title = share_title;
                }
            }
        }
    }
}
