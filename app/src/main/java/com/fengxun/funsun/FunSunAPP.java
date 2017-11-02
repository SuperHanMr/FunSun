package com.fengxun.funsun;

import android.app.Application;
import android.content.Context;

import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.request.LoggerInterceptor;
import com.fengxun.funsun.utils.SPUtils;
import com.lzy.okgo.OkGo;
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
    public static Context getInstance(){
        return  context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        OkGo.init(this);
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept","application/json");

        switch (SPUtils.getInt(KEY.KEY_RECORD)){
            case 1:
                headers.put("X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN));  // 请求头 匿名用户Token
                break;
            case 2:
                headers.put("X-Fo-Access-Token",SPUtils.getString(KEY.KEY_USERTOKEN)); // 请求头UserToken
                break;
        }

        OkGo.getInstance().addCommonHeaders(headers).addInterceptor(new LoggerInterceptor());
       // CacheMode.IF_NONE_CACHE_REQUEST;// 如果缓存不在 再请求网络
    }


}
