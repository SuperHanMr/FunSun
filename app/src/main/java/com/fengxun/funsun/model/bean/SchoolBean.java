package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/13.
 * Holle Android
 */

public class SchoolBean implements Serializable {
    /**
     * code : 200
     * data : [{"content_count":25,"hot_cnt":1119,"tag_id":5500,"tag_img":"http://qiniu.funsun.cn/%E5%8C%97%E4%BA%AC%E5%A4%A7%E5%AD%A6.png","tag_name":"北京大学"},{"content_count":0,"hot_cnt":820,"tag_id":12100,"tag_img":"http://qiniu.funsun.cn/University-%E5%9F%83%E5%85%8B%E5%A1%9E%E7%89%B9%E5%A4%A7%E5%AD%A6.png","tag_name":"埃克塞特大学"},{"content_count":54,"hot_cnt":790,"tag_id":5577,"tag_img":"http://qiniu.funsun.cn/%E4%B8%AD%E5%B1%B1%E5%A4%A7%E5%AD%A6.png","tag_name":"中山大学"},{"content_count":0,"hot_cnt":780,"tag_id":12102,"tag_img":"http://qiniu.funsun.cn/University-%E7%BA%BD%E5%8D%A1%E6%96%AF%E5%B0%94%E5%A4%A7%E5%AD%A6.png","tag_name":"纽卡斯尔大学"},{"content_count":1,"hot_cnt":720,"tag_id":12103,"tag_img":"http://qiniu.funsun.cn/University-%E8%8B%8F%E5%A1%9E%E5%85%8B%E6%96%AF%E5%A4%A7%E5%AD%A6.png","tag_name":"苏塞克斯大学"},{"content_count":0,"hot_cnt":720,"tag_id":12101,"tag_img":"http://qiniu.funsun.cn/University-%E6%9D%9C%E4%BC%A6%E5%A4%A7%E5%AD%A6.png","tag_name":"杜伦大学"},{"content_count":0,"hot_cnt":680,"tag_id":12094,"tag_img":"http://qiniu.funsun.cn/University-%E7%BB%B4%E6%8B%89%E8%AF%BA%E7%93%A6%E5%A4%A7%E5%AD%A6.png","tag_name":"维拉诺瓦大学"},{"content_count":1,"hot_cnt":660,"tag_id":12091,"tag_img":"http://qiniu.funsun.cn/University-%E7%90%86%E6%B5%B7%E5%A4%A7%E5%AD%A6.png","tag_name":"理海大学"},{"content_count":7,"hot_cnt":610,"tag_id":5501,"tag_img":"http://qiniu.funsun.cn/%E6%B8%85%E5%8D%8E%E5%A4%A7%E5%AD%A6.png","tag_name":"清华大学"},{"content_count":0,"hot_cnt":600,"tag_id":12093,"tag_img":"http://qiniu.funsun.cn/University-%E8%BF%88%E9%98%BF%E5%AF%86%E5%A4%A7%E5%AD%A6.png","tag_name":"迈阿密大学"},{"content_count":0,"hot_cnt":560,"tag_id":12099,"tag_img":"http://qiniu.funsun.cn/University-%E7%BD%97%E5%88%87%E6%96%AF%E7%89%B9%E7%90%86%E5%B7%A5%E5%AD%A6%E9%99%A2.png","tag_name":"罗切斯特理工学院"},{"content_count":0,"hot_cnt":560,"tag_id":12098,"tag_img":"http://qiniu.funsun.cn/University-%E8%80%B6%E4%BB%80%E5%8D%8E%E5%A4%A7%E5%AD%A6.png","tag_name":"耶什华大学"},{"content_count":68,"hot_cnt":550,"tag_id":5687,"tag_img":"http://qiniu.funsun.cn/%E6%B5%99%E6%B1%9F%E5%A4%A7%E5%AD%A6.png","tag_name":"浙江大学"},{"content_count":1,"hot_cnt":540,"tag_id":12095,"tag_img":"http://qiniu.funsun.cn/University-%E5%BE%B7%E9%9B%B7%E5%A1%9E%E5%B0%94%E5%A4%A7%E5%AD%A6.png","tag_name":"德雷塞尔大学"},{"content_count":1,"hot_cnt":540,"tag_id":12090,"tag_img":"http://qiniu.funsun.cn/University-%E4%BC%A6%E6%96%AF%E5%8B%92%E7%90%86%E5%B7%A5%E5%AD%A6%E9%99%A2.png","tag_name":"伦斯勒理工学院"},{"content_count":1,"hot_cnt":520,"tag_id":12092,"tag_img":"http://qiniu.funsun.cn/University-%E4%BD%A9%E7%8F%80%E4%BB%A3%E5%9B%A0%E5%A4%A7%E5%AD%A6.jpg","tag_name":"佩珀代因大学"},{"content_count":1,"hot_cnt":500,"tag_id":12096,"tag_img":"http://qiniu.funsun.cn/University-%E5%9C%A3%E8%B7%AF%E6%98%93%E6%96%AF%E5%A4%A7%E5%AD%A6.png","tag_name":"圣路易斯大学"},{"content_count":25,"hot_cnt":432,"tag_id":5611,"tag_img":"http://qiniu.funsun.cn/%E5%8D%8E%E4%B8%AD%E5%B8%88%E8%8C%83%E5%A4%A7%E5%AD%A6.png","tag_name":"华中师范大学"},{"content_count":44,"hot_cnt":430,"tag_id":5661,"tag_img":"http://qiniu.funsun.cn/%E5%A4%8D%E6%97%A6%E5%A4%A7%E5%AD%A6.png","tag_name":"复旦大学"},{"content_count":9,"hot_cnt":424,"tag_id":5502,"tag_img":"http://qiniu.funsun.cn/%E4%B8%AD%E5%9B%BD%E4%BA%BA%E6%B0%91%E5%A4%A7%E5%AD%A6.png","tag_name":"中国人民大学"}]
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
