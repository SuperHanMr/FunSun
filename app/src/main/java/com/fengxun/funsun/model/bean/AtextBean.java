package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/19.
 * Holle Android
 */

public class AtextBean implements Serializable {


    /**
     * code : 200
     * msg : 获取数据成功
     * data : {"content_root_tag_id":5010,"vedio_word":"","content_publish_user_relation":"","content_root_tag_img":"","content_id":35607,"school_cover_img_url":"[\"http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim\"]","comment_cnt":1,"content_publish_user_school_id":"","content_root_cnt":0,"content":"http://qiniu.funsun.cn/auto-e5719a9d-5b31-44d6-81e2-df8d89a3cc9a.html","translate_content":"","is_collection":0,"content_title":"就是好听！欧美歌曲：So Much More Than This","content_vedio_url":"","content_cover_img_url":"http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","content_root_tag":"音乐","translate_content_v2":"","vedio_info":{"vedio_word":{"beginTime":[],"endTime":[],"text":[]},"publish_time":1.513616452E9,"second_root_tag_id":null,"publish_user_nick":"欧美音乐摇滚歌曲","second_root_tag":"","vedio_url":"","content_id":35607,"content_title":"就是好听！欧美歌曲：So Much More Than This","school_cover_img_url":["http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"]},"content_publish_user_avatar":"http://qiniu.funsun.cn/wx-/790627-cd60ed8d30c576506bcf75ff9a50d1fa","content_type":0,"share_data":{"share_img":"http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","share_title":"就是好听！欧美歌曲：So Much More Than This","share_content":"[郑州大学]的666第一个分享了这条消息","share_url":"http://api.funsun.cn/share/v1/?c=35607&u=49625"},"view_cnt":2,"source_type":0,"is_collect":1,"second_root_tag_id":null,"content_publish_user_school_name":"","content_collection_url":null,"content_publish_user_nick":"欧美音乐摇滚歌曲","content_publish_user_id":36875,"vedio_word_zh_cn":{"beginTime":[],"endTime":[],"text":[]},"content_publish_time":1.513616452E9,"html":"http://qiniu.funsun.cn/auto-e5719a9d-5b31-44d6-81e2-df8d89a3cc9a.html","second_root_tag":""}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        @Override
        public String toString() {
            return "DataBean{" +
                    "content_root_tag_id=" + content_root_tag_id +
                    ", vedio_word='" + vedio_word + '\'' +
                    ", content_publish_user_relation='" + content_publish_user_relation + '\'' +
                    ", content_root_tag_img='" + content_root_tag_img + '\'' +
                    ", content_id=" + content_id +
                    ", school_cover_img_url='" + school_cover_img_url + '\'' +
                    ", comment_cnt=" + comment_cnt +
                    ", content_publish_user_school_id='" + content_publish_user_school_id + '\'' +
                    ", content_root_cnt=" + content_root_cnt +
                    ", content='" + content + '\'' +
                    ", translate_content='" + translate_content + '\'' +
                    ", is_collection=" + is_collection +
                    ", content_title='" + content_title + '\'' +
                    ", content_vedio_url='" + content_vedio_url + '\'' +
                    ", content_cover_img_url='" + content_cover_img_url + '\'' +
                    ", content_root_tag='" + content_root_tag + '\'' +
                    ", translate_content_v2='" + translate_content_v2 + '\'' +
                    ", vedio_info=" + vedio_info +
                    ", content_publish_user_avatar='" + content_publish_user_avatar + '\'' +
                    ", content_type=" + content_type +
                    ", share_data=" + share_data +
                    ", view_cnt=" + view_cnt +
                    ", source_type=" + source_type +
                    ", is_collect=" + is_collect +
                    ", second_root_tag_id=" + second_root_tag_id +
                    ", content_publish_user_school_name='" + content_publish_user_school_name + '\'' +
                    ", content_collection_url=" + content_collection_url +
                    ", content_publish_user_nick='" + content_publish_user_nick + '\'' +
                    ", content_publish_user_id=" + content_publish_user_id +
                    ", vedio_word_zh_cn=" + vedio_word_zh_cn +
                    ", content_publish_time=" + content_publish_time +
                    ", html='" + html + '\'' +
                    ", second_root_tag='" + second_root_tag + '\'' +
                    '}';
        }

        /**
         * content_root_tag_id : 5010
         * vedio_word :
         * content_publish_user_relation :
         * content_root_tag_img :
         * content_id : 35607
         * school_cover_img_url : ["http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"]
         * comment_cnt : 1
         * content_publish_user_school_id :
         * content_root_cnt : 0
         * content : http://qiniu.funsun.cn/auto-e5719a9d-5b31-44d6-81e2-df8d89a3cc9a.html
         * translate_content :
         * is_collection : 0
         * content_title : 就是好听！欧美歌曲：So Much More Than This
         * content_vedio_url :
         * content_cover_img_url : http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
         * content_root_tag : 音乐
         * translate_content_v2 :
         * vedio_info : {"vedio_word":{"beginTime":[],"endTime":[],"text":[]},"publish_time":1.513616452E9,"second_root_tag_id":null,"publish_user_nick":"欧美音乐摇滚歌曲","second_root_tag":"","vedio_url":"","content_id":35607,"content_title":"就是好听！欧美歌曲：So Much More Than This","school_cover_img_url":["http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"]}
         * content_publish_user_avatar : http://qiniu.funsun.cn/wx-/790627-cd60ed8d30c576506bcf75ff9a50d1fa
         * content_type : 0
         * share_data : {"share_img":"http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","share_title":"就是好听！欧美歌曲：So Much More Than This","share_content":"[郑州大学]的666第一个分享了这条消息","share_url":"http://api.funsun.cn/share/v1/?c=35607&u=49625"}
         * view_cnt : 2
         * source_type : 0
         * is_collect : 1
         * second_root_tag_id : null
         * content_publish_user_school_name :
         * content_collection_url : null
         * content_publish_user_nick : 欧美音乐摇滚歌曲
         * content_publish_user_id : 36875
         * vedio_word_zh_cn : {"beginTime":[],"endTime":[],"text":[]}
         * content_publish_time : 1.513616452E9
         * html : http://qiniu.funsun.cn/auto-e5719a9d-5b31-44d6-81e2-df8d89a3cc9a.html
         * second_root_tag :
         */

        private int content_root_tag_id;
        private String vedio_word;
        private String content_publish_user_relation;
        private String content_root_tag_img;
        private int content_id;
        private String school_cover_img_url;
        private int comment_cnt;
        private String content_publish_user_school_id;
        private int content_root_cnt;
        private String content;
        private String translate_content;
        private int is_collection;
        private String content_title;
        private String content_vedio_url;
        private String content_cover_img_url;
        private String content_root_tag;
        private String translate_content_v2;
        private VedioInfoBean vedio_info;
        private String content_publish_user_avatar;
        private int content_type;
        private ShareDataBean share_data;
        private int view_cnt;
        private int source_type;
        private int is_collect;
        private Object second_root_tag_id;
        private String content_publish_user_school_name;
        private Object content_collection_url;
        private String content_publish_user_nick;
        private int content_publish_user_id;
        private VedioWordZhCnBean vedio_word_zh_cn;
        private double content_publish_time;
        private String html;
        private String second_root_tag;

        public int getContent_root_tag_id() {
            return content_root_tag_id;
        }

        public void setContent_root_tag_id(int content_root_tag_id) {
            this.content_root_tag_id = content_root_tag_id;
        }

        public String getVedio_word() {
            return vedio_word;
        }

        public void setVedio_word(String vedio_word) {
            this.vedio_word = vedio_word;
        }

        public String getContent_publish_user_relation() {
            return content_publish_user_relation;
        }

        public void setContent_publish_user_relation(String content_publish_user_relation) {
            this.content_publish_user_relation = content_publish_user_relation;
        }

        public String getContent_root_tag_img() {
            return content_root_tag_img;
        }

        public void setContent_root_tag_img(String content_root_tag_img) {
            this.content_root_tag_img = content_root_tag_img;
        }

        public int getContent_id() {
            return content_id;
        }

        public void setContent_id(int content_id) {
            this.content_id = content_id;
        }

        public String getSchool_cover_img_url() {
            return school_cover_img_url;
        }

        public void setSchool_cover_img_url(String school_cover_img_url) {
            this.school_cover_img_url = school_cover_img_url;
        }

        public int getComment_cnt() {
            return comment_cnt;
        }

        public void setComment_cnt(int comment_cnt) {
            this.comment_cnt = comment_cnt;
        }

        public String getContent_publish_user_school_id() {
            return content_publish_user_school_id;
        }

        public void setContent_publish_user_school_id(String content_publish_user_school_id) {
            this.content_publish_user_school_id = content_publish_user_school_id;
        }

        public int getContent_root_cnt() {
            return content_root_cnt;
        }

        public void setContent_root_cnt(int content_root_cnt) {
            this.content_root_cnt = content_root_cnt;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTranslate_content() {
            return translate_content;
        }

        public void setTranslate_content(String translate_content) {
            this.translate_content = translate_content;
        }

        public int getIs_collection() {
            return is_collection;
        }

        public void setIs_collection(int is_collection) {
            this.is_collection = is_collection;
        }

        public String getContent_title() {
            return content_title;
        }

        public void setContent_title(String content_title) {
            this.content_title = content_title;
        }

        public String getContent_vedio_url() {
            return content_vedio_url;
        }

        public void setContent_vedio_url(String content_vedio_url) {
            this.content_vedio_url = content_vedio_url;
        }

        public String getContent_cover_img_url() {
            return content_cover_img_url;
        }

        public void setContent_cover_img_url(String content_cover_img_url) {
            this.content_cover_img_url = content_cover_img_url;
        }

        public String getContent_root_tag() {
            return content_root_tag;
        }

        public void setContent_root_tag(String content_root_tag) {
            this.content_root_tag = content_root_tag;
        }

        public String getTranslate_content_v2() {
            return translate_content_v2;
        }

        public void setTranslate_content_v2(String translate_content_v2) {
            this.translate_content_v2 = translate_content_v2;
        }

        public VedioInfoBean getVedio_info() {
            return vedio_info;
        }

        public void setVedio_info(VedioInfoBean vedio_info) {
            this.vedio_info = vedio_info;
        }

        public String getContent_publish_user_avatar() {
            return content_publish_user_avatar;
        }

        public void setContent_publish_user_avatar(String content_publish_user_avatar) {
            this.content_publish_user_avatar = content_publish_user_avatar;
        }

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }

        public ShareDataBean getShare_data() {
            return share_data;
        }

        public void setShare_data(ShareDataBean share_data) {
            this.share_data = share_data;
        }

        public int getView_cnt() {
            return view_cnt;
        }

        public void setView_cnt(int view_cnt) {
            this.view_cnt = view_cnt;
        }

        public int getSource_type() {
            return source_type;
        }

        public void setSource_type(int source_type) {
            this.source_type = source_type;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public Object getSecond_root_tag_id() {
            return second_root_tag_id;
        }

        public void setSecond_root_tag_id(Object second_root_tag_id) {
            this.second_root_tag_id = second_root_tag_id;
        }

        public String getContent_publish_user_school_name() {
            return content_publish_user_school_name;
        }

        public void setContent_publish_user_school_name(String content_publish_user_school_name) {
            this.content_publish_user_school_name = content_publish_user_school_name;
        }

        public Object getContent_collection_url() {
            return content_collection_url;
        }

        public void setContent_collection_url(Object content_collection_url) {
            this.content_collection_url = content_collection_url;
        }

        public String getContent_publish_user_nick() {
            return content_publish_user_nick;
        }

        public void setContent_publish_user_nick(String content_publish_user_nick) {
            this.content_publish_user_nick = content_publish_user_nick;
        }

        public int getContent_publish_user_id() {
            return content_publish_user_id;
        }

        public void setContent_publish_user_id(int content_publish_user_id) {
            this.content_publish_user_id = content_publish_user_id;
        }

        public VedioWordZhCnBean getVedio_word_zh_cn() {
            return vedio_word_zh_cn;
        }

        public void setVedio_word_zh_cn(VedioWordZhCnBean vedio_word_zh_cn) {
            this.vedio_word_zh_cn = vedio_word_zh_cn;
        }

        public double getContent_publish_time() {
            return content_publish_time;
        }

        public void setContent_publish_time(double content_publish_time) {
            this.content_publish_time = content_publish_time;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getSecond_root_tag() {
            return second_root_tag;
        }

        public void setSecond_root_tag(String second_root_tag) {
            this.second_root_tag = second_root_tag;
        }

        public static class VedioInfoBean implements Serializable {
            /**
             * vedio_word : {"beginTime":[],"endTime":[],"text":[]}
             * publish_time : 1.513616452E9
             * second_root_tag_id : null
             * publish_user_nick : 欧美音乐摇滚歌曲
             * second_root_tag :
             * vedio_url :
             * content_id : 35607
             * content_title : 就是好听！欧美歌曲：So Much More Than This
             * school_cover_img_url : ["http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"]
             */

            private VedioWordBean vedio_word;
            private double publish_time;
            private Object second_root_tag_id;
            private String publish_user_nick;
            private String second_root_tag;
            private String vedio_url;
            private int content_id;
            private String content_title;
            private List<String> school_cover_img_url;

            public VedioWordBean getVedio_word() {
                return vedio_word;
            }

            public void setVedio_word(VedioWordBean vedio_word) {
                this.vedio_word = vedio_word;
            }

            public double getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(double publish_time) {
                this.publish_time = publish_time;
            }

            public Object getSecond_root_tag_id() {
                return second_root_tag_id;
            }

            public void setSecond_root_tag_id(Object second_root_tag_id) {
                this.second_root_tag_id = second_root_tag_id;
            }

            public String getPublish_user_nick() {
                return publish_user_nick;
            }

            public void setPublish_user_nick(String publish_user_nick) {
                this.publish_user_nick = publish_user_nick;
            }

            public String getSecond_root_tag() {
                return second_root_tag;
            }

            public void setSecond_root_tag(String second_root_tag) {
                this.second_root_tag = second_root_tag;
            }

            public String getVedio_url() {
                return vedio_url;
            }

            public void setVedio_url(String vedio_url) {
                this.vedio_url = vedio_url;
            }

            public int getContent_id() {
                return content_id;
            }

            public void setContent_id(int content_id) {
                this.content_id = content_id;
            }

            public String getContent_title() {
                return content_title;
            }

            public void setContent_title(String content_title) {
                this.content_title = content_title;
            }

            public List<String> getSchool_cover_img_url() {
                return school_cover_img_url;
            }

            public void setSchool_cover_img_url(List<String> school_cover_img_url) {
                this.school_cover_img_url = school_cover_img_url;
            }

            public static class VedioWordBean implements Serializable {
                private List<?> beginTime;
                private List<?> endTime;
                private List<?> text;

                public List<?> getBeginTime() {
                    return beginTime;
                }

                public void setBeginTime(List<?> beginTime) {
                    this.beginTime = beginTime;
                }

                public List<?> getEndTime() {
                    return endTime;
                }

                public void setEndTime(List<?> endTime) {
                    this.endTime = endTime;
                }

                public List<?> getText() {
                    return text;
                }

                public void setText(List<?> text) {
                    this.text = text;
                }
            }
        }

        public static class ShareDataBean implements Serializable {

            /**
             * share_img : http://qiniu.funsun.cn/wx-/790627-8856d2f6d48e0fe9aa0bc8412f0e5b0b.jpeg?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
             * share_title : 就是好听！欧美歌曲：So Much More Than This
             * share_content : [郑州大学]的666第一个分享了这条消息
             * share_url : http://api.funsun.cn/share/v1/?c=35607&u=49625
             */

            private String share_img;
            private String share_title;
            private String share_content;
            private String share_url;

            public String getShare_img() {
                return share_img;
            }

            public void setShare_img(String share_img) {
                this.share_img = share_img;
            }

            public String getShare_title() {
                return share_title;
            }

            public void setShare_title(String share_title) {
                this.share_title = share_title;
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

            @Override
            public String toString() {
                return "ShareDataBean{" +
                        "share_img='" + share_img + '\'' +
                        ", share_title='" + share_title + '\'' +
                        ", share_content='" + share_content + '\'' +
                        ", share_url='" + share_url + '\'' +
                        '}';
            }
        }

        public static class VedioWordZhCnBean implements Serializable {
            private List<?> beginTime;
            private List<?> endTime;
            private List<?> text;

            public List<?> getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(List<?> beginTime) {
                this.beginTime = beginTime;
            }

            public List<?> getEndTime() {
                return endTime;
            }

            public void setEndTime(List<?> endTime) {
                this.endTime = endTime;
            }

            public List<?> getText() {
                return text;
            }

            public void setText(List<?> text) {
                this.text = text;
            }
        }
    }
}
