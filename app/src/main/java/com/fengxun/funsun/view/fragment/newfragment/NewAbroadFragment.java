package com.fengxun.funsun.view.fragment.newfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.HeadlinesBean;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.ACache;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.adapter.NewRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.fengxun.funsun.view.views.refresh.ParallaxPtrFrameLayout;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/20.
 * Holle Android
 * 内容：全球
 *  请求参数 global
 */

public class NewAbroadFragment extends BaseNewFragmnet {

    private List<HeadlinesBean.DataBean> list;
    private ParallaxPtrFrameLayout baseNewfragment;
    private NewRecyclerViewAdapter adapter;


    // 记录 第一次使用缓存 剩下的网络请求不要走缓存
    private ACache aCache;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void loadData() {
        LogUtils.e("/*====================自动刷新============*/");
            baseNewfragment.postDelayed(new Runnable() {
                @Override
                public void run() {
                    baseNewfragment.autoRefresh();
                }
            },250);

    }


    /*
        处理 每个模块
         */
    @Override
    protected void initView(RecyclerView views, final ParallaxPtrFrameLayout baseNewfragment) {
        /*
        缓存类
         */
        aCache = ACache.get(getContext());
        list = new ArrayList<>();
        /*
        读取缓存数据
         */
        String asString = aCache.getAsString("global");
        if (asString != null) {
            LogUtils.e("缓存的数据：" + asString);
            Gson gson = new Gson();
            HeadlinesBean headlinesBean = gson.fromJson(asString, HeadlinesBean.class);
            List<HeadlinesBean.DataBean> data = headlinesBean.getData();
            list.addAll(data);
        }

        this.baseNewfragment = baseNewfragment;
        adapter = new NewRecyclerViewAdapter(getContext(), list, true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        views.setLayoutManager(manager);
        views.addItemDecoration(new SpaceItemDecoration(10));
        views.setAdapter(adapter);


        /*
        =====================下拉刷新====================
         */
        baseNewfragment.setPullToRefresh(true);
        baseNewfragment.disableWhenHorizontalMove(true);
        baseNewfragment.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                NetworkData();
            }
        });

    }


    /*
    网络请求
    */

    public void NetworkData() {
        LogUtils.e("请求网络");
        HttpParams params = new HttpParams();
        params.put("content_type", "global");
        NetworkReuset.getInstance().getHomeNewReuset(RequestUrl.HEADLINES, params, new onCallBack<HeadlinesBean>(this) {
            @Override
            public void onSucceed(HeadlinesBean headlinesBean, Call call, String string) {
                List<HeadlinesBean.DataBean> data = headlinesBean.getData();
                if (data.size() != 0) {
                    adapter.setData(data);
                    aCache.put("global", string);
                }
                baseNewfragment.refreshComplete();
            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                baseNewfragment.refreshComplete();
                //TODO 现在判断是死的 应该根据 状态吗判断失败原因 目前 只返回网络不好的错误
                new SuperHanDialog(getContext(), "似乎和互联网断开链接~").show();
            }
        });
    }
}
