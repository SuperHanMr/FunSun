package com.fengxun.funsun.view.base;

import java.io.Serializable;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/4.
 * Holle Android
 */

public class FunSunResponseBean<T> implements Serializable {



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;

}
