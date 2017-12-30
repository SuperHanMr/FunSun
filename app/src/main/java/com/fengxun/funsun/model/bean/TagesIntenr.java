package com.fengxun.funsun.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/29.
 * Holle Android
 */

public class TagesIntenr implements Serializable {

    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
