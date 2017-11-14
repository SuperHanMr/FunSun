package com.fengxun.funsun.model.eventbus;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/13.
 * Holle Android
 */

public class MainActivityEventBus {

    private int refresh;

    public MainActivityEventBus(int refresh){
        this.refresh = refresh;
    }

    public int getMainActivityEventBus(){
        return refresh;
    }

}
