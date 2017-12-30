package com.fengxun.funsun;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.request.LoggerInterceptor;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.view.views.refresh.PullRefreshHeader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;

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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
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

        /*
        6eb441cedc33fb689d928d54e645e2cb
        6eb441cedc33fb689d928d54e645e2cb
         */


        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, null, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        //开启ShareSDK debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;


        // CacheMode.IF_NONE_CACHE_REQUEST;// 如果缓存不在 再请求网络
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new PullRefreshHeader(context);
            }
        });


        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context).setPrimaryColor(getResources().getColor(R.color.colorbWhite));
            }
        });

    }

    {

        PlatformConfig.setWeixin("wx57116b6d92b453ce", "4a83f517221a77a41a3387341805b974");

        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

        PlatformConfig.setSinaWeibo("3096450733", "22c7b3c3da764525703d64fdf8e593c0", "http://sns.whalecloud.com");


    }


}
