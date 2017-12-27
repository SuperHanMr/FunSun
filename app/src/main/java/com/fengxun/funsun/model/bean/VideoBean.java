package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/1.
 * Holle Android
 */

public class VideoBean implements Serializable{

    /**
     * data : {"is_collection":0,"content_publish_user_school_name":"清华大学","content_vedio_url":"http://qiniu.funsun.cn/video_av-4a7eb5be-b9e2-4101-950d-2b1b23617bb8","translate_content":"","content_root_tag_img":"http://qiniu.funsun.cn/%E6%B8%85%E5%8D%8E%E5%A4%A7%E5%AD%A6.png","content_collection_url":null,"share_data":{"share_content":"[北京大学]的浅忆👄：小姐姐第一个分享了这条消息","share_img":"http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","share_url":"http://api.funsun.cn/share/v1/?c=39103&u=14648","share_title":"太火！清华RAP神曲《水木道》超燃，每一帧都是大片"},"content_title":"太火！清华RAP神曲《水木道》超燃，每一帧都是大片","view_cnt":0,"content_root_cnt":0,"content_id":39103,"vedio_word_zh_cn":{"beginTime":[],"endTime":[],"text":[]},"vedio_word":"","html":"[{\"size\": \"766:596\", \"word\": \"\", \"url\": \"http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim\"}]","second_root_tag":"音乐厅","content_publish_user_relation":"","content_type":1,"content_root_tag_id":5501,"content_publish_user_school_id":"5501","school_cover_img_url":"[\"http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim\"]","content_publish_user_avatar":"http://qiniu.funsun.cn/build-boy-avatar%20%2867%29.jpg","vedio_info":{"publish_time":1.513733498E9,"content_id":39103,"school_cover_img_url":["http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"],"vedio_word":{"beginTime":[],"endTime":[],"text":[]},"vedio_url":"http://qiniu.funsun.cn/video_av-4a7eb5be-b9e2-4101-950d-2b1b23617bb8","second_root_tag":"音乐厅","content_title":"太火！清华RAP神曲《水木道》超燃，每一帧都是大片","publish_user_nick":"丰讯清华","second_root_tag_id":5032},"content_root_tag":"清华大学","content_publish_user_id":49619,"source_type":1,"content_publish_time":1.513733498E9,"content_cover_img_url":"http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","content":[{"url":"http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","size":"766:596","word":""}],"comment_cnt":0,"is_collect":1,"second_root_tag_id":5032,"content_publish_user_nick":"丰讯清华","translate_content_v2":"0"}
     * msg : 获取数据成功
     * code : 200
     */

    private DataBean data;
    private String msg;
    private int code;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean implements Serializable {
        /**
         * is_collection : 0
         * content_publish_user_school_name : 清华大学
         * content_vedio_url : http://qiniu.funsun.cn/video_av-4a7eb5be-b9e2-4101-950d-2b1b23617bb8
         * translate_content :
         * content_root_tag_img : http://qiniu.funsun.cn/%E6%B8%85%E5%8D%8E%E5%A4%A7%E5%AD%A6.png
         * content_collection_url : null
         * share_data : {"share_content":"[北京大学]的浅忆👄：小姐姐第一个分享了这条消息","share_img":"http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","share_url":"http://api.funsun.cn/share/v1/?c=39103&u=14648","share_title":"太火！清华RAP神曲《水木道》超燃，每一帧都是大片"}
         * content_title : 太火！清华RAP神曲《水木道》超燃，每一帧都是大片
         * view_cnt : 0
         * content_root_cnt : 0
         * content_id : 39103
         * vedio_word_zh_cn : {"beginTime":[],"endTime":[],"text":[]}
         * vedio_word :
         * html : [{"size": "766:596", "word": "", "url": "http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"}]
         * second_root_tag : 音乐厅
         * content_publish_user_relation :
         * content_type : 1
         * content_root_tag_id : 5501
         * content_publish_user_school_id : 5501
         * school_cover_img_url : ["http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"]
         * content_publish_user_avatar : http://qiniu.funsun.cn/build-boy-avatar%20%2867%29.jpg
         * vedio_info : {"publish_time":1.513733498E9,"content_id":39103,"school_cover_img_url":["http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"],"vedio_word":{"beginTime":[],"endTime":[],"text":[]},"vedio_url":"http://qiniu.funsun.cn/video_av-4a7eb5be-b9e2-4101-950d-2b1b23617bb8","second_root_tag":"音乐厅","content_title":"太火！清华RAP神曲《水木道》超燃，每一帧都是大片","publish_user_nick":"丰讯清华","second_root_tag_id":5032}
         * content_root_tag : 清华大学
         * content_publish_user_id : 49619
         * source_type : 1
         * content_publish_time : 1.513733498E9
         * content_cover_img_url : http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
         * content : [{"url":"http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","size":"766:596","word":""}]
         * comment_cnt : 0
         * is_collect : 1
         * second_root_tag_id : 5032
         * content_publish_user_nick : 丰讯清华
         * translate_content_v2 : 0
         */

        private int is_collection;
        private String content_publish_user_school_name;
        private String content_vedio_url;
        private String translate_content;
        private String content_root_tag_img;
        private Object content_collection_url;
        private ShareDataBean share_data;
        private String content_title;
        private int view_cnt;
        private int content_root_cnt;
        private int content_id;
        private VedioWordZhCnBean vedio_word_zh_cn;
        private String vedio_word;
        private String html;
        private String second_root_tag;
        private String content_publish_user_relation;
        private int content_type;
        private int content_root_tag_id;
        private String content_publish_user_school_id;
        private String school_cover_img_url;
        private String content_publish_user_avatar;
        private VedioInfoBean vedio_info;
        private String content_root_tag;
        private int content_publish_user_id;
        private int source_type;
        private double content_publish_time;
        private String content_cover_img_url;
        private int comment_cnt;
        private int is_collect;
        private int second_root_tag_id;
        private String content_publish_user_nick;
        private String translate_content_v2;
        private List<ContentBean> content;

        public int getIs_collection() {
            return is_collection;
        }

        public void setIs_collection(int is_collection) {
            this.is_collection = is_collection;
        }

        public String getContent_publish_user_school_name() {
            return content_publish_user_school_name;
        }

        public void setContent_publish_user_school_name(String content_publish_user_school_name) {
            this.content_publish_user_school_name = content_publish_user_school_name;
        }

        public String getContent_vedio_url() {
            return content_vedio_url;
        }

        public void setContent_vedio_url(String content_vedio_url) {
            this.content_vedio_url = content_vedio_url;
        }

        public String getTranslate_content() {
            return translate_content;
        }

        public void setTranslate_content(String translate_content) {
            this.translate_content = translate_content;
        }

        public String getContent_root_tag_img() {
            return content_root_tag_img;
        }

        public void setContent_root_tag_img(String content_root_tag_img) {
            this.content_root_tag_img = content_root_tag_img;
        }

        public Object getContent_collection_url() {
            return content_collection_url;
        }

        public void setContent_collection_url(Object content_collection_url) {
            this.content_collection_url = content_collection_url;
        }

        public ShareDataBean getShare_data() {
            return share_data;
        }

        public void setShare_data(ShareDataBean share_data) {
            this.share_data = share_data;
        }

        public String getContent_title() {
            return content_title;
        }

        public void setContent_title(String content_title) {
            this.content_title = content_title;
        }

        public int getView_cnt() {
            return view_cnt;
        }

        public void setView_cnt(int view_cnt) {
            this.view_cnt = view_cnt;
        }

        public int getContent_root_cnt() {
            return content_root_cnt;
        }

        public void setContent_root_cnt(int content_root_cnt) {
            this.content_root_cnt = content_root_cnt;
        }

        public int getContent_id() {
            return content_id;
        }

        public void setContent_id(int content_id) {
            this.content_id = content_id;
        }

        public VedioWordZhCnBean getVedio_word_zh_cn() {
            return vedio_word_zh_cn;
        }

        public void setVedio_word_zh_cn(VedioWordZhCnBean vedio_word_zh_cn) {
            this.vedio_word_zh_cn = vedio_word_zh_cn;
        }

        public String getVedio_word() {
            return vedio_word;
        }

        public void setVedio_word(String vedio_word) {
            this.vedio_word = vedio_word;
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

        public String getContent_publish_user_relation() {
            return content_publish_user_relation;
        }

        public void setContent_publish_user_relation(String content_publish_user_relation) {
            this.content_publish_user_relation = content_publish_user_relation;
        }

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }

        public int getContent_root_tag_id() {
            return content_root_tag_id;
        }

        public void setContent_root_tag_id(int content_root_tag_id) {
            this.content_root_tag_id = content_root_tag_id;
        }

        public String getContent_publish_user_school_id() {
            return content_publish_user_school_id;
        }

        public void setContent_publish_user_school_id(String content_publish_user_school_id) {
            this.content_publish_user_school_id = content_publish_user_school_id;
        }

        public String getSchool_cover_img_url() {
            return school_cover_img_url;
        }

        public void setSchool_cover_img_url(String school_cover_img_url) {
            this.school_cover_img_url = school_cover_img_url;
        }

        public String getContent_publish_user_avatar() {
            return content_publish_user_avatar;
        }

        public void setContent_publish_user_avatar(String content_publish_user_avatar) {
            this.content_publish_user_avatar = content_publish_user_avatar;
        }

        public VedioInfoBean getVedio_info() {
            return vedio_info;
        }

        public void setVedio_info(VedioInfoBean vedio_info) {
            this.vedio_info = vedio_info;
        }

        public String getContent_root_tag() {
            return content_root_tag;
        }

        public void setContent_root_tag(String content_root_tag) {
            this.content_root_tag = content_root_tag;
        }

        public int getContent_publish_user_id() {
            return content_publish_user_id;
        }

        public void setContent_publish_user_id(int content_publish_user_id) {
            this.content_publish_user_id = content_publish_user_id;
        }

        public int getSource_type() {
            return source_type;
        }

        public void setSource_type(int source_type) {
            this.source_type = source_type;
        }

        public double getContent_publish_time() {
            return content_publish_time;
        }

        public void setContent_publish_time(double content_publish_time) {
            this.content_publish_time = content_publish_time;
        }

        public String getContent_cover_img_url() {
            return content_cover_img_url;
        }

        public void setContent_cover_img_url(String content_cover_img_url) {
            this.content_cover_img_url = content_cover_img_url;
        }

        public int getComment_cnt() {
            return comment_cnt;
        }

        public void setComment_cnt(int comment_cnt) {
            this.comment_cnt = comment_cnt;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public int getSecond_root_tag_id() {
            return second_root_tag_id;
        }

        public void setSecond_root_tag_id(int second_root_tag_id) {
            this.second_root_tag_id = second_root_tag_id;
        }

        public String getContent_publish_user_nick() {
            return content_publish_user_nick;
        }

        public void setContent_publish_user_nick(String content_publish_user_nick) {
            this.content_publish_user_nick = content_publish_user_nick;
        }

        public String getTranslate_content_v2() {
            return translate_content_v2;
        }

        public void setTranslate_content_v2(String translate_content_v2) {
            this.translate_content_v2 = translate_content_v2;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ShareDataBean implements Serializable{
            /**
             * share_content : [北京大学]的浅忆👄：小姐姐第一个分享了这条消息
             * share_img : http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
             * share_url : http://api.funsun.cn/share/v1/?c=39103&u=14648
             * share_title : 太火！清华RAP神曲《水木道》超燃，每一帧都是大片
             */

            private String share_content;
            private String share_img;
            private String share_url;
            private String share_title;

            public String getShare_content() {
                return share_content;
            }

            public void setShare_content(String share_content) {
                this.share_content = share_content;
            }

            public String getShare_img() {
                return share_img;
            }

            public void setShare_img(String share_img) {
                this.share_img = share_img;
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

        public static class VedioWordZhCnBean  implements Serializable{
            private List<Integer> beginTime;
            private List<Integer> endTime;
            private List<String> text;

            public List<Integer> getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(List<Integer> beginTime) {
                this.beginTime = beginTime;
            }

            public List<Integer> getEndTime() {
                return endTime;
            }

            public void setEndTime(List<Integer> endTime) {
                this.endTime = endTime;
            }

            public List<String> getText() {
                return text;
            }

            public void setText(List<String> text) {
                this.text = text;
            }
        }

        public static class VedioInfoBean implements Serializable {
            /**
             * publish_time : 1.513733498E9
             * content_id : 39103
             * school_cover_img_url : ["http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"]
             * vedio_word : {"beginTime":[],"endTime":[],"text":[]}
             * vedio_url : http://qiniu.funsun.cn/video_av-4a7eb5be-b9e2-4101-950d-2b1b23617bb8
             * second_root_tag : 音乐厅
             * content_title : 太火！清华RAP神曲《水木道》超燃，每一帧都是大片
             * publish_user_nick : 丰讯清华
             * second_root_tag_id : 5032
             */

            private double publish_time;
            private int content_id;
            private VedioWordBean vedio_word;
            private String vedio_url;
            private String second_root_tag;
            private String content_title;
            private String publish_user_nick;
            private int second_root_tag_id;
            private List<String> school_cover_img_url;

            public double getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(double publish_time) {
                this.publish_time = publish_time;
            }

            public int getContent_id() {
                return content_id;
            }

            public void setContent_id(int content_id) {
                this.content_id = content_id;
            }

            public VedioWordBean getVedio_word() {
                return vedio_word;
            }

            public void setVedio_word(VedioWordBean vedio_word) {
                this.vedio_word = vedio_word;
            }

            public String getVedio_url() {
                return vedio_url;
            }

            public void setVedio_url(String vedio_url) {
                this.vedio_url = vedio_url;
            }

            public String getSecond_root_tag() {
                return second_root_tag;
            }

            public void setSecond_root_tag(String second_root_tag) {
                this.second_root_tag = second_root_tag;
            }

            public String getContent_title() {
                return content_title;
            }

            public void setContent_title(String content_title) {
                this.content_title = content_title;
            }

            public String getPublish_user_nick() {
                return publish_user_nick;
            }

            public void setPublish_user_nick(String publish_user_nick) {
                this.publish_user_nick = publish_user_nick;
            }

            public int getSecond_root_tag_id() {
                return second_root_tag_id;
            }

            public void setSecond_root_tag_id(int second_root_tag_id) {
                this.second_root_tag_id = second_root_tag_id;
            }

            public List<String> getSchool_cover_img_url() {
                return school_cover_img_url;
            }

            public void setSchool_cover_img_url(List<String> school_cover_img_url) {
                this.school_cover_img_url = school_cover_img_url;
            }

            public static class VedioWordBean implements Serializable{
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

        public static class ContentBean implements Serializable {
            /**
             * url : http://qiniu.funsun.cn/act_img-814b5771-2564-422f-b088-ef2cabce8a9c?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
             * size : 766:596
             * word :
             */

            private String url;
            private String size;
            private String word;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }
        }
    }
}
