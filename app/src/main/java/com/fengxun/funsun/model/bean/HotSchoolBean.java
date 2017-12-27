package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/13.
 * Holle Android
 */

public class HotSchoolBean implements Serializable{
    /**
     * code : 200
     * data : [{"content_count":25,"hot_cnt":1119,"tag_id":5500,"tag_img":"http://qiniu.funsun.cn/%E5%8C%97%E4%BA%AC%E5%A4%A7%E5%AD%A6.png","tag_name":"北京大学"},{"content_count":37,"hot_cnt":790,"tag_id":5577,"tag_img":"http://qiniu.funsun.cn/%E4%B8%AD%E5%B1%B1%E5%A4%A7%E5%AD%A6.png","tag_name":"中山大学"},{"content_count":7,"hot_cnt":610,"tag_id":5501,"tag_img":"http://qiniu.funsun.cn/%E6%B8%85%E5%8D%8E%E5%A4%A7%E5%AD%A6.png","tag_name":"清华大学"},{"content_count":56,"hot_cnt":550,"tag_id":5687,"tag_img":"http://qiniu.funsun.cn/%E6%B5%99%E6%B1%9F%E5%A4%A7%E5%AD%A6.png","tag_name":"浙江大学"},{"content_count":25,"hot_cnt":432,"tag_id":5611,"tag_img":"http://qiniu.funsun.cn/%E5%8D%8E%E4%B8%AD%E5%B8%88%E8%8C%83%E5%A4%A7%E5%AD%A6.png","tag_name":"华中师范大学"},{"content_count":19,"hot_cnt":430,"tag_id":5661,"tag_img":"http://qiniu.funsun.cn/%E5%A4%8D%E6%97%A6%E5%A4%A7%E5%AD%A6.png","tag_name":"复旦大学"},{"content_count":9,"hot_cnt":424,"tag_id":5502,"tag_img":"http://qiniu.funsun.cn/%E4%B8%AD%E5%9B%BD%E4%BA%BA%E6%B0%91%E5%A4%A7%E5%AD%A6.png","tag_name":"中国人民大学"},{"content_count":4,"hot_cnt":392,"tag_id":5536,"tag_img":"http://qiniu.funsun.cn/%E4%B8%AD%E5%9B%BD%E4%BC%A0%E5%AA%92%E5%A4%A7%E5%AD%A6.png","tag_name":"中国传媒大学"},{"content_count":11,"hot_cnt":354,"tag_id":5609,"tag_img":"http://qiniu.funsun.cn/%E6%AD%A6%E6%B1%89%E5%A4%A7%E5%AD%A6.png","tag_name":"武汉大学"},{"content_count":15,"hot_cnt":354,"tag_id":5647,"tag_img":"http://qiniu.funsun.cn/%E5%B1%B1%E4%B8%9C%E5%A4%A7%E5%AD%A6.png","tag_name":"山东大学"},{"content_count":2,"hot_cnt":332,"tag_id":5511,"tag_img":"http://qiniu.funsun.cn/%E5%8C%97%E4%BA%AC%E9%82%AE%E7%94%B5%E5%A4%A7%E5%AD%A6.png","tag_name":"北京邮电大学"},{"content_count":9,"hot_cnt":332,"tag_id":5515,"tag_img":"http://qiniu.funsun.cn/%E5%8C%97%E4%BA%AC%E6%9E%97%E4%B8%9A%E5%A4%A7%E5%AD%A6.png","tag_name":"北京林业大学"},{"content_count":34,"hot_cnt":324,"tag_id":5569,"tag_img":"http://qiniu.funsun.cn/%E5%8E%A6%E9%97%A8%E5%A4%A7%E5%AD%A6.png","tag_name":"厦门大学"},{"content_count":12,"hot_cnt":320,"tag_id":5503,"tag_img":"http://qiniu.funsun.cn/%E5%8C%97%E4%BA%AC%E5%B8%88%E8%8C%83%E5%A4%A7%E5%AD%A6.png","tag_name":"北京师范大学"},{"content_count":24,"hot_cnt":318,"tag_id":5574,"tag_img":"http://qiniu.funsun.cn/%E5%85%B0%E5%B7%9E%E5%A4%A7%E5%AD%A6.png","tag_name":"兰州大学"},{"content_count":8,"hot_cnt":312,"tag_id":5520,"tag_img":"http://qiniu.funsun.cn/%E5%8D%8E%E5%8C%97%E7%94%B5%E5%8A%9B%E5%A4%A7%E5%AD%A6.png","tag_name":"华北电力大学"},{"content_count":4,"hot_cnt":310,"tag_id":5506,"tag_img":"http://qiniu.funsun.cn/%E5%8C%97%E4%BA%AC%E7%90%86%E5%B7%A5%E5%A4%A7%E5%AD%A6.png","tag_name":"北京理工大学"},{"content_count":33,"hot_cnt":304,"tag_id":5692,"tag_img":"http://qiniu.funsun.cn/%E9%87%8D%E5%BA%86%E5%A4%A7%E5%AD%A6.png","tag_name":"重庆大学"},{"content_count":9,"hot_cnt":290,"tag_id":5519,"tag_img":"http://qiniu.funsun.cn/%E4%B8%AD%E5%A4%AE%E8%B4%A2%E7%BB%8F%E5%A4%A7%E5%AD%A6.png","tag_name":"中央财经大学"},{"content_count":29,"hot_cnt":270,"tag_id":5679,"tag_img":"http://qiniu.funsun.cn/%E5%A4%A9%E6%B4%A5%E5%A4%A7%E5%AD%A6.png","tag_name":"天津大学"}]
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

    public static class DataBean implements Serializable {
        /**
         * content_count : 25
         * hot_cnt : 1119
         * tag_id : 5500
         * tag_img : http://qiniu.funsun.cn/%E5%8C%97%E4%BA%AC%E5%A4%A7%E5%AD%A6.png
         * tag_name : 北京大学
         */

        private int content_count;
        private int hot_cnt;
        private int tag_id;
        private String tag_img;
        private String tag_name;

        public int getContent_count() {
            return content_count;
        }

        public void setContent_count(int content_count) {
            this.content_count = content_count;
        }

        public int getHot_cnt() {
            return hot_cnt;
        }

        public void setHot_cnt(int hot_cnt) {
            this.hot_cnt = hot_cnt;
        }

        public int getTag_id() {
            return tag_id;
        }

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }

        public String getTag_img() {
            return tag_img;
        }

        public void setTag_img(String tag_img) {
            this.tag_img = tag_img;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }
    }
}
