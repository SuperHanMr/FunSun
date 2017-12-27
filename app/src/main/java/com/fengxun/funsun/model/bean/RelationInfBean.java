package com.fengxun.funsun.model.bean;

import java.io.Serializable;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/11.
 * Holle Android
 */

public class RelationInfBean implements Serializable {


    public int type;
    public String userId;
    public String contentId;

    @Override
    public String toString() {
        return "RelationInfBean{" +
                "type=" + type +
                ", userId='" + userId + '\'' +
                ", contentId='" + contentId + '\'' +
                '}';
    }


    public RelationInfBean(int type, String userId, String contentId) {
        this.type = type;
        this.userId = userId;
        this.contentId = contentId;
    }






}
