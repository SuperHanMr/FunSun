package com.fengxun.funsun.model.bean;

import java.io.Serializable;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/7.
 * Holle Android
 */

public class CodeBean implements Serializable {
    /**
     * msg : 短信发送成功
     * code : 200
     */

    private String msg;
    private int code;

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
}
