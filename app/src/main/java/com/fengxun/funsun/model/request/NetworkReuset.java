package com.fengxun.funsun.model.request;

import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.LoginBean;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import java.lang.reflect.Type;
import java.util.List;

/**
 内容：封装请求
 */

public class NetworkReuset {


    /*
    五种缓存模式
        1.) 无缓存模式 CacheMode.NO_CACHE
        2.) 默认缓存模式,遵循304头 CacheMode.DEFAULT
        3.) 请求网络失败后读取缓存 CacheMode.REQUEST_FAILED_READ_CACHE
        4.) 如果缓存不存在才请求网络，否则使用缓存 CacheMode.IF_NONE_CACHE_REQUEST
        5.) 先使用缓存，不管是否存在，仍然请求网络 CacheMode.FIRST_CACHE_THEN_REQUEST
     */

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
     * @param url 缓存 UserI信息
     * @param onCallBack
     */

    public void PostLogdingReuset(String url,HttpParams params,onCallBack onCallBack){
        OkGo.post(url)
                .cacheKey("user_info")
                .params(params)
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(onCallBack);
    }


    public void getLogdingReuset(String url,onCallBack onCallBack){
        OkGo.post(url)
                .cacheKey("user_info")
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(onCallBack);
    }

    /**
     * @param url 请求地址
     * @param onCallBack 回调
     */
    public void GetReuset(String url,onCallBack onCallBack ){
        OkGo.get(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .execute(onCallBack);
    }




    /**
     * @param url 请求地址
     * @param onCallBack 回调
     */

    public void GetReuset(String url,HttpParams params,onCallBack onCallBack ){
        OkGo.get(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .params(params)
                .execute(onCallBack);
    }


    public void getMeReuset(String url,HttpParams params,onCallBack onCallBack){
        OkGo.get(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .cacheKey("me_massage")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .params(params)
                .execute(onCallBack);

    }


    public void getPromting(String url,onCallBack onCallBack){
        OkGo.get(url)
                .cacheKey("tishilist")
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(onCallBack);
    }

    public void getCommentPromting(String url,onCallBack onCallBack){
        OkGo.get(url)
                .cacheKey("commenttishi")
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(onCallBack);

    }

    /*

     */
    public void getHeadlinesReuset(String url,HttpParams params,onCallBack onCallBack){
        LogUtils.e(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous"+"======"+SPUtils.getString(KEY.KEY_USERTOKEN));
        OkGo.get(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .cacheKey("headlines")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .params(params)
                .execute(onCallBack);
    }


    /*
    请求国内趣闻
     */
    public void getHomeNewReuset(String url,HttpParams params,onCallBack onCallBack){
        OkGo.get(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .cacheMode(CacheMode.NO_CACHE)
                .params(params)
                .execute(onCallBack);
    }

    /*
    请求北京趣闻
     */
    public void getBeiJingNewReuset(String url,HttpParams params,onCallBack onCallBack){
        OkGo.get(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .cacheKey("beijing")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .params(params)
                .execute(onCallBack);
    }

    /*
    请求短视频趣闻
     */
    public void getVideoNewReuset(String url,HttpParams params,onCallBack onCallBack){
        OkGo.get(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .cacheKey("video")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .params(params)
                .execute(onCallBack);
    }

    /*
    请求 全球趣闻
     */
    public void getGlobalNewReuset(String url,HttpParams params,onCallBack onCallBack){
        OkGo.get(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .cacheKey("global")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .params(params)
                .execute(onCallBack);
    }



    /*
    请求 国外趣闻
     */
    public void getForeignNewReuset(String url,HttpParams params,onCallBack onCallBack){
        OkGo.get(url)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .cacheKey("foreign")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .params(params)
                .execute(onCallBack);
    }


}
