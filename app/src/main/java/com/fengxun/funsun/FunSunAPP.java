package com.fengxun.funsun;

import android.app.Application;
import android.content.Context;

import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.request.LoggerInterceptor;
import com.fengxun.funsun.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;

/**
 * 程序员：韩永辉
 * 时间：2017/11/2
 * 联系方式：17710558669
 * Hello Android！
 * 内容：全局初始化
 */

public class FunSunAPP extends Application {

    private static Context context;
    private static boolean  isLogin;

    public static Context getInstance(){
        return  context;
    }

    public static boolean getIsLogin(){
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        OkGo.getInstance().init(this);
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept","application/json");
        OkGo.getInstance()
                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .setRetryCount(3)
                .addCommonHeaders(headers)
                .addInterceptor(new LoggerInterceptor());


       // CacheMode.IF_NONE_CACHE_REQUEST;// 如果缓存不在 再请求网络
    }


}
