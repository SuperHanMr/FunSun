package com.fengxun.funsun.model.request;


import android.app.Activity;
import android.support.v4.app.Fragment;


import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.base.BaseFragment;

import java.io.IOException;

/**
 * Created by BM-WGX on 2017/3/17.
 */

public abstract class onCallBack<T> extends JsonCallback<T>{
    private Activity activity = null;
    private Fragment fragment = null;

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
    public void onAfter(T t, Exception e) {
        super.onAfter(t, e);
        if (fragment!=null){
            //((BaseFragment)fragment).endNetworkData();
            fragment = null;
            return;
        }
        if (activity!=null){
            //((BaseActivity)activity).endNetworkData();
            activity = null;
        }

    }

    @Override
    protected void onreSponse401() {
        super.onreSponse401();
       ToastUtil.showNormalToast(activity,"登录失效");
    }
}
