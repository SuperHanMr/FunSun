package com.fengxun.funsun.model.bean;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/1.
 * Holle Android
 */

public class VideoBean{

    /**
     * msg : 获取数据成功
     * data : {"content_publish_user_avatar":"http://qiniu.funsun.cn/build-boy-avatar%20%2869%29.jpg","content_root_cnt":0,"content_publish_user_id":49513,"is_collect":1,"content_publish_user_relation":"","content_collection_url":null,"content_title":"郑州大学考研族冒严寒在户外背书：醒脑","content_root_tag":"校园","source_type":1,"content_cover_img_url":"http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim","vedio_info":{"vedio_word":{"beginTime":[],"endTime":[],"text":[]},"content_title":"郑州大学考研族冒严寒在户外背书：醒脑","second_root_tag":"趣闻","vedio_url":"http://qiniu.funsun.cn/video_av-ca7e51a7-836d-44dc-a2bd-ae394ec94005","second_root_tag_id":5011,"school_cover_img_url":["http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"],"publish_time":1.512081781E9,"content_id":15277,"publish_user_nick":"丰讯趣闻"},"html":"[{\"url\": \"http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim\", \"word\": \"\", \"size\": \"533:297\"}]","content_type":1,"content_publish_time":1.512081781E9,"content":[{"size":"533:297","word":"","url":"http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"}],"second_root_tag":"趣闻","vedio_word_zh_cn":{"beginTime":[],"endTime":[],"text":[]},"content_vedio_url":"http://qiniu.funsun.cn/video_av-ca7e51a7-836d-44dc-a2bd-ae394ec94005","content_root_tag_id":5005,"second_root_tag_id":5011,"view_cnt":0,"vedio_word":"","school_cover_img_url":"[\"http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim\"]","content_publish_user_nick":"丰讯趣闻","content_publish_user_school_name":"","translate_content":[],"content_root_tag_img":"","share_data":{"share_url":"http://api.funsun.cn/share/v1/?c=15277","share_title":"郑州大学考研族冒严寒在户外背书：醒脑","share_content":"[郑州大学]的戏精第一个分享了这条消息","share_img":"http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"},"content_publish_user_school_id":"","content_id":15277,"comment_cnt":1,"is_collection":0}
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

    public static class DataBean {
        /**
         * content_publish_user_avatar : http://qiniu.funsun.cn/build-boy-avatar%20%2869%29.jpg
         * content_root_cnt : 0
         * content_publish_user_id : 49513
         * is_collect : 1
         * content_publish_user_relation :
         * content_collection_url : null
         * content_title : 郑州大学考研族冒严寒在户外背书：醒脑
         * content_root_tag : 校园
         * source_type : 1
         * content_cover_img_url : http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
         * vedio_info : {"vedio_word":{"beginTime":[],"endTime":[],"text":[]},"content_title":"郑州大学考研族冒严寒在户外背书：醒脑","second_root_tag":"趣闻","vedio_url":"http://qiniu.funsun.cn/video_av-ca7e51a7-836d-44dc-a2bd-ae394ec94005","second_root_tag_id":5011,"school_cover_img_url":["http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"],"publish_time":1.512081781E9,"content_id":15277,"publish_user_nick":"丰讯趣闻"}
         * html : [{"url": "http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim", "word": "", "size": "533:297"}]
         * content_type : 1
         * content_publish_time : 1.512081781E9
         * content : [{"size":"533:297","word":"","url":"http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"}]
         * second_root_tag : 趣闻
         * vedio_word_zh_cn : {"beginTime":[],"endTime":[],"text":[]}
         * content_vedio_url : http://qiniu.funsun.cn/video_av-ca7e51a7-836d-44dc-a2bd-ae394ec94005
         * content_root_tag_id : 5005
         * second_root_tag_id : 5011
         * view_cnt : 0
         * vedio_word :
         * school_cover_img_url : ["http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"]
         * content_publish_user_nick : 丰讯趣闻
         * content_publish_user_school_name :
         * translate_content : []
         * content_root_tag_img :
         * share_data : {"share_url":"http://api.funsun.cn/share/v1/?c=15277","share_title":"郑州大学考研族冒严寒在户外背书：醒脑","share_content":"[郑州大学]的戏精第一个分享了这条消息","share_img":"http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"}
         * content_publish_user_school_id :
         * content_id : 15277
         * comment_cnt : 1
         * is_collection : 0
         */

        private String content_publish_user_avatar;
        private int content_root_cnt;
        private int content_publish_user_id;
        private int is_collect;
        private String content_publish_user_relation;
        private Object content_collection_url;
        private String content_title;
        private String content_root_tag;
        private int source_type;
        private String content_cover_img_url;
        private VedioInfoBean vedio_info;
        private String html;
        private int content_type;
        private double content_publish_time;
        private String second_root_tag;
        private VedioWordZhCnBean vedio_word_zh_cn;
        private String content_vedio_url;
        private int content_root_tag_id;
        private int second_root_tag_id;
        private int view_cnt;
        private String vedio_word;
        private String school_cover_img_url;
        private String content_publish_user_nick;
        private String content_publish_user_school_name;
        private String content_root_tag_img;
        private ShareDataBean share_data;
        private String content_publish_user_school_id;
        private int content_id;
        private int comment_cnt;
        private int is_collection;
        private List<ContentBean> content;
        private List<?> translate_content;

        public String getContent_publish_user_avatar() {
            return content_publish_user_avatar;
        }

        public void setContent_publish_user_avatar(String content_publish_user_avatar) {
            this.content_publish_user_avatar = content_publish_user_avatar;
        }

        public int getContent_root_cnt() {
            return content_root_cnt;
        }

        public void setContent_root_cnt(int content_root_cnt) {
            this.content_root_cnt = content_root_cnt;
        }

        public int getContent_publish_user_id() {
            return content_publish_user_id;
        }

        public void setContent_publish_user_id(int content_publish_user_id) {
            this.content_publish_user_id = content_publish_user_id;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public String getContent_publish_user_relation() {
            return content_publish_user_relation;
        }

        public void setContent_publish_user_relation(String content_publish_user_relation) {
            this.content_publish_user_relation = content_publish_user_relation;
        }

        public Object getContent_collection_url() {
            return content_collection_url;
        }

        public void setContent_collection_url(Object content_collection_url) {
            this.content_collection_url = content_collection_url;
        }

        public String getContent_title() {
            return content_title;
        }

        public void setContent_title(String content_title) {
            this.content_title = content_title;
        }

        public String getContent_root_tag() {
            return content_root_tag;
        }

        public void setContent_root_tag(String content_root_tag) {
            this.content_root_tag = content_root_tag;
        }

        public int getSource_type() {
            return source_type;
        }

        public void setSource_type(int source_type) {
            this.source_type = source_type;
        }

        public String getContent_cover_img_url() {
            return content_cover_img_url;
        }

        public void setContent_cover_img_url(String content_cover_img_url) {
            this.content_cover_img_url = content_cover_img_url;
        }

        public VedioInfoBean getVedio_info() {
            return vedio_info;
        }

        public void setVedio_info(VedioInfoBean vedio_info) {
            this.vedio_info = vedio_info;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }

        public double getContent_publish_time() {
            return content_publish_time;
        }

        public void setContent_publish_time(double content_publish_time) {
            this.content_publish_time = content_publish_time;
        }

        public String getSecond_root_tag() {
            return second_root_tag;
        }

        public void setSecond_root_tag(String second_root_tag) {
            this.second_root_tag = second_root_tag;
        }

        public VedioWordZhCnBean getVedio_word_zh_cn() {
            return vedio_word_zh_cn;
        }

        public void setVedio_word_zh_cn(VedioWordZhCnBean vedio_word_zh_cn) {
            this.vedio_word_zh_cn = vedio_word_zh_cn;
        }

        public String getContent_vedio_url() {
            return content_vedio_url;
        }

        public void setContent_vedio_url(String content_vedio_url) {
            this.content_vedio_url = content_vedio_url;
        }

        public int getContent_root_tag_id() {
            return content_root_tag_id;
        }

        public void setContent_root_tag_id(int content_root_tag_id) {
            this.content_root_tag_id = content_root_tag_id;
        }

        public int getSecond_root_tag_id() {
            return second_root_tag_id;
        }

        public void setSecond_root_tag_id(int second_root_tag_id) {
            this.second_root_tag_id = second_root_tag_id;
        }

        public int getView_cnt() {
            return view_cnt;
        }

        public void setView_cnt(int view_cnt) {
            this.view_cnt = view_cnt;
        }

        public String getVedio_word() {
            return vedio_word;
        }

        public void setVedio_word(String vedio_word) {
            this.vedio_word = vedio_word;
        }

        public String getSchool_cover_img_url() {
            return school_cover_img_url;
        }

        public void setSchool_cover_img_url(String school_cover_img_url) {
            this.school_cover_img_url = school_cover_img_url;
        }

        public String getContent_publish_user_nick() {
            return content_publish_user_nick;
        }

        public void setContent_publish_user_nick(String content_publish_user_nick) {
            this.content_publish_user_nick = content_publish_user_nick;
        }

        public String getContent_publish_user_school_name() {
            return content_publish_user_school_name;
        }

        public void setContent_publish_user_school_name(String content_publish_user_school_name) {
            this.content_publish_user_school_name = content_publish_user_school_name;
        }

        public String getContent_root_tag_img() {
            return content_root_tag_img;
        }

        public void setContent_root_tag_img(String content_root_tag_img) {
            this.content_root_tag_img = content_root_tag_img;
        }

        public ShareDataBean getShare_data() {
            return share_data;
        }

        public void setShare_data(ShareDataBean share_data) {
            this.share_data = share_data;
        }

        public String getContent_publish_user_school_id() {
            return content_publish_user_school_id;
        }

        public void setContent_publish_user_school_id(String content_publish_user_school_id) {
            this.content_publish_user_school_id = content_publish_user_school_id;
        }

        public int getContent_id() {
            return content_id;
        }

        public void setContent_id(int content_id) {
            this.content_id = content_id;
        }

        public int getComment_cnt() {
            return comment_cnt;
        }

        public void setComment_cnt(int comment_cnt) {
            this.comment_cnt = comment_cnt;
        }

        public int getIs_collection() {
            return is_collection;
        }

        public void setIs_collection(int is_collection) {
            this.is_collection = is_collection;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<?> getTranslate_content() {
            return translate_content;
        }

        public void setTranslate_content(List<?> translate_content) {
            this.translate_content = translate_content;
        }

        public static class VedioInfoBean {
            /**
             * vedio_word : {"beginTime":[],"endTime":[],"text":[]}
             * content_title : 郑州大学考研族冒严寒在户外背书：醒脑
             * second_root_tag : 趣闻
             * vedio_url : http://qiniu.funsun.cn/video_av-ca7e51a7-836d-44dc-a2bd-ae394ec94005
             * second_root_tag_id : 5011
             * school_cover_img_url : ["http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim"]
             * publish_time : 1.512081781E9
             * content_id : 15277
             * publish_user_nick : 丰讯趣闻
             */

            private VedioWordBean vedio_word;
            private String content_title;
            private String second_root_tag;
            private String vedio_url;
            private int second_root_tag_id;
            private double publish_time;
            private int content_id;
            private String publish_user_nick;
            private List<String> school_cover_img_url;

            public VedioWordBean getVedio_word() {
                return vedio_word;
            }

            public void setVedio_word(VedioWordBean vedio_word) {
                this.vedio_word = vedio_word;
            }

            public String getContent_title() {
                return content_title;
            }

            public void setContent_title(String content_title) {
                this.content_title = content_title;
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

            public int getSecond_root_tag_id() {
                return second_root_tag_id;
            }

            public void setSecond_root_tag_id(int second_root_tag_id) {
                this.second_root_tag_id = second_root_tag_id;
            }

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

            public String getPublish_user_nick() {
                return publish_user_nick;
            }

            public void setPublish_user_nick(String publish_user_nick) {
                this.publish_user_nick = publish_user_nick;
            }

            public List<String> getSchool_cover_img_url() {
                return school_cover_img_url;
            }

            public void setSchool_cover_img_url(List<String> school_cover_img_url) {
                this.school_cover_img_url = school_cover_img_url;
            }

            public static class VedioWordBean {
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

        public static class VedioWordZhCnBean {
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

        public static class ShareDataBean {
            /**
             * share_url : http://api.funsun.cn/share/v1/?c=15277
             * share_title : 郑州大学考研族冒严寒在户外背书：醒脑
             * share_content : [郑州大学]的戏精第一个分享了这条消息
             * share_img : http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
             */

            private String share_url;
            private String share_title;
            private String share_content;
            private String share_img;

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
        }

        public static class ContentBean {
            /**
             * size : 533:297
             * word :
             * url : http://qiniu.funsun.cn/act_img-bf2dff5f-906a-4628-ac0f-d09cdaf36c4b?imageMogr2/auto-orient/thumbnail/500x500%3E/blur/1x0/quality/75%7Cimageslim
             */

            private String size;
            private String word;
            private String url;

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
