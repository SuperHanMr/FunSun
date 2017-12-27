package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/13.
 * Holle Android
 */

public class RecommendSchooleBean implements Serializable {
    /**
     * code : 200
     * data : [{"tag_id":12100,"tag_img":"http://qiniu.funsun.cn/University-%E5%9F%83%E5%85%8B%E5%A1%9E%E7%89%B9%E5%A4%A7%E5%AD%A6.png","tag_name":"埃克塞特大学"},{"tag_id":12102,"tag_img":"http://qiniu.funsun.cn/University-%E7%BA%BD%E5%8D%A1%E6%96%AF%E5%B0%94%E5%A4%A7%E5%AD%A6.png","tag_name":"纽卡斯尔大学"},{"tag_id":12101,"tag_img":"http://qiniu.funsun.cn/University-%E6%9D%9C%E4%BC%A6%E5%A4%A7%E5%AD%A6.png","tag_name":"杜伦大学"},{"tag_id":12103,"tag_img":"http://qiniu.funsun.cn/University-%E8%8B%8F%E5%A1%9E%E5%85%8B%E6%96%AF%E5%A4%A7%E5%AD%A6.png","tag_name":"苏塞克斯大学"},{"tag_id":12094,"tag_img":"http://qiniu.funsun.cn/University-%E7%BB%B4%E6%8B%89%E8%AF%BA%E7%93%A6%E5%A4%A7%E5%AD%A6.png","tag_name":"维拉诺瓦大学"},{"tag_id":12091,"tag_img":"http://qiniu.funsun.cn/University-%E7%90%86%E6%B5%B7%E5%A4%A7%E5%AD%A6.png","tag_name":"理海大学"},{"tag_id":12093,"tag_img":"http://qiniu.funsun.cn/University-%E8%BF%88%E9%98%BF%E5%AF%86%E5%A4%A7%E5%AD%A6.png","tag_name":"迈阿密大学"},{"tag_id":12098,"tag_img":"http://qiniu.funsun.cn/University-%E8%80%B6%E4%BB%80%E5%8D%8E%E5%A4%A7%E5%AD%A6.png","tag_name":"耶什华大学"},{"tag_id":12099,"tag_img":"http://qiniu.funsun.cn/University-%E7%BD%97%E5%88%87%E6%96%AF%E7%89%B9%E7%90%86%E5%B7%A5%E5%AD%A6%E9%99%A2.png","tag_name":"罗切斯特理工学院"},{"tag_id":12090,"tag_img":"http://qiniu.funsun.cn/University-%E4%BC%A6%E6%96%AF%E5%8B%92%E7%90%86%E5%B7%A5%E5%AD%A6%E9%99%A2.png","tag_name":"伦斯勒理工学院"},{"tag_id":12095,"tag_img":"http://qiniu.funsun.cn/University-%E5%BE%B7%E9%9B%B7%E5%A1%9E%E5%B0%94%E5%A4%A7%E5%AD%A6.png","tag_name":"德雷塞尔大学"},{"tag_id":12092,"tag_img":"http://qiniu.funsun.cn/University-%E4%BD%A9%E7%8F%80%E4%BB%A3%E5%9B%A0%E5%A4%A7%E5%AD%A6.jpg","tag_name":"佩珀代因大学"},{"tag_id":12096,"tag_img":"http://qiniu.funsun.cn/University-%E5%9C%A3%E8%B7%AF%E6%98%93%E6%96%AF%E5%A4%A7%E5%AD%A6.png","tag_name":"圣路易斯大学"},{"tag_id":12104,"tag_img":"http://qiniu.funsun.cn/University-%E7%BA%A6%E5%85%8B%E5%A4%A7%E5%AD%A6.png","tag_name":"约克大学"},{"tag_id":5727,"tag_img":"http://qiniu.funsun.cn/Harvard-University.png","tag_name":"哈佛大学"},{"tag_id":5728,"tag_img":"http://qiniu.funsun.cn/Massachusetts-Institute-of-Technology.png","tag_name":"麻省理工学院"},{"tag_id":5729,"tag_img":"http://qiniu.funsun.cn/Stanford-University.png","tag_name":"斯坦福大学"},{"tag_id":5792,"tag_img":"http://qiniu.funsun.cn/The-University-of-New-South-Wales.png","tag_name":"新南威尔士大学"},{"tag_id":5799,"tag_img":"http://qiniu.funsun.cn/University-of-Virginia.png","tag_name":"弗吉尼亚大学"},{"tag_id":5798,"tag_img":"http://qiniu.funsun.cn/The-University-of-Western-Australia.png","tag_name":"西澳大学"}]
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
         * tag_id : 12100
         * tag_img : http://qiniu.funsun.cn/University-%E5%9F%83%E5%85%8B%E5%A1%9E%E7%89%B9%E5%A4%A7%E5%AD%A6.png
         * tag_name : 埃克塞特大学
         */

        private int tag_id;
        private String tag_img;
        private String tag_name;

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
