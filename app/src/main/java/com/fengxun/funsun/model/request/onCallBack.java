package com.fengxun.funsun.model.request;


import android.app.Activity;
import android.support.v4.app.Fragment;


import com.fengxun.funsun.model.eventbus.MainActivityEventBus;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.activity.MainActivity;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by BM-WGX on 2017/3/17.
 */

public abstract class onCallBack<T> extends JsonCallback<T>{
    private Activity activity = null;
    private Fragment fragment = null;

    private boolean isLogdin;

    public Activity getActivity() {
        return activity;
    }



    public onCallBack(Activity mActivity) {
        super();
        this.activity = mActivity;
    }

    public onCallBack(Fragment fragment) {
        super();
        this.fragment = fragment;
        activity = fragment.getActivity();
    }

    public void onFaild(String faild){
        if (activity!=null){
            ToastUtil.showNormalToast(activity,faild);
        }
    }
    public void onNetFaild(IOException e){
        LogUtils.d("------------onNetFaild");
    }

    @Override
    public void onCacheSuccess(T t, Call call) {
        super.onCacheSuccess(t, call);
//        ((BaseActivity)activity).superHanLoginDigloger.dismiss();
    }

    @Override
    public void onSuccess(T t, Call call, Response response) {
        super.onSuccess(t, call, response);

    }


    public abstract void onSucceed(T t, Call call, String string);




    // 网络请求失败
    @Override
    public void onError(Call call, Response response, Exception e) {
       super.onError(call, response, e);
        if (response!=null){
            switch (response.code()){
                case 949:
                    ToastUtil.showNormalToast(activity,"登录失效");
                    SPUtils.clear();
                    EventBus.getDefault().post(new MainActivityEventBus(2));
                    break;
            }

        }

    }

    @Override
    public void onAfter(T t, Exception e) {
        super.onAfter(t, e);

    }

    @Override
    protected void onreSponse401() {
        super.onreSponse401();
    }
}
