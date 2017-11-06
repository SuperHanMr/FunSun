package com.fengxun.funsun.model.request;

import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.LoginBean;
import com.fengxun.funsun.utils.SPUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import java.lang.reflect.Type;
import java.util.List;

/**
 内容：封装请求
 */

public class NetworkReuset {

    private static NetworkReuset  instance = null;

    public static synchronized NetworkReuset getInstance(){
        if (instance==null){
            instance = new NetworkReuset();
        }
        return instance;
    }

    /**
     * @param url 请求地址
     * @param params 请求参数
     * @param onCallBack 请求回调
     */
    public void PostReuset(String url,HttpParams params,onCallBack onCallBack){
        OkGo.post(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN)) // 添加token
                .params(params)
                .execute(onCallBack);
    }

    /**
     * @param url
     * @param jsonCallback
     */
//    public void GetReuset(String url,JsonCallback jsonCallback){
//        OkGo.post(url)
//                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
//                .execute(jsonCallback);
//    }


}
