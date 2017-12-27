package com.fengxun.funsun.view.fragment.newfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.HeadlinesBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.listener.NewItemListener;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.ACache;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.activity.RelationCalorieActivity;
import com.fengxun.funsun.view.activity.VideoPlayerActivity;
import com.fengxun.funsun.view.adapter.NewRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.EditTextDialog;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.fengxun.funsun.view.views.refresh.ParallaxPtrFrameLayout;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
 * 内容：短视频 vedio
 */

public class NewVideoFragment extends BaseNewFragmnet implements NewItemListener {
    private List<HeadlinesBean.DataBean> list;
    private RefreshLayout baseNewfragment;
    private NewRecyclerViewAdapter adapter;


    // 记录 第一次使用缓存 剩下的网络请求不要走缓存
    private ACache aCache;
    private EditTextDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void loadData() {
        LogUtils.e("/*====================自动刷新============*/");
        baseNewfragment.autoRefresh();
    }


    /*
        处理 每个模块
         */
    @Override
    protected void initView(RecyclerView views, final RefreshLayout baseNewfragment) {

        list = new ArrayList<>();

        /*
        读取缓存数据
         */
        aCache = ACache.get(getContext());
        String asString = aCache.getAsString("vedio");
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

        baseNewfragment.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                NetworkData(true);
            }
        });

        baseNewfragment.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                NetworkData(false);
            }
        });

        adapter.setNewItemListenet(this);

    }

    /*
    网络请求
    */

    public void NetworkData(final boolean isReresh) {
        LogUtils.e("请求网络");
        HttpParams params = new HttpParams();
        params.put("content_type", "video");
        NetworkReuset.getInstance().getHomeNewReuset(RequestUrl.HEADLINES, params, new onCallBack<HeadlinesBean>(this) {
            @Override
            public void onSucceed(HeadlinesBean headlinesBean, Call call, String string) {
                List<HeadlinesBean.DataBean> data = headlinesBean.getData();


                if (isReresh){
                    if (data.size() != 0||data!=null) {
                        adapter.setData(data);
                        aCache.put("foreign", string);
                    }
                    ToastUtil.massageToast(getContext(),data.size());
                    baseNewfragment.finishRefresh();

                }else {
                    adapter.setLoadMoreData(data);
                    baseNewfragment.finishLoadmore();
                }

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);


                if (isReresh){
                    baseNewfragment.finishRefresh();
                }else{
                    baseNewfragment.finishLoadmore();
                }

                //TODO 现在判断是死的 应该根据 状态吗判断失败原因 目前 只返回网络不好的错误
                new SuperHanDialog(getContext(), "似乎和互联网断开链接~").show();
            }
        });
    }



    @Override
    public void OnVideoInfoListener(VideoInfoBean bean) {
        LogUtils.e(KEY.TAG+"视频条目："+bean.toString());
        Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(VIDEOINFO,bean);
        intent.putExtras(mBundle);
        getContext().startActivity(intent);
    }


    @Override
    public void OnPostInfoListener(String postId, int type) {
        LogUtils.e("------------>回调");
    }

    @Override
    public void OnCommentContentListener(String contentId,String userId) {

        dialog = new EditTextDialog(contentId, userId, new EditTextDialog.SendBackListener() {
            @Override
            public void sendBack() {
                dialog.dismiss();
                ToastUtil.showNormalToast(getContext(),"评论成功");
            }
        });
        dialog.show(getFragmentManager(),"dialog");
    }


    @Override
    public void onRelationListener(String userId, String contentId, int type) {
        RelationInfBean bean = new RelationInfBean(type, userId, contentId);
        Intent intent = new Intent(getContext(), RelationCalorieActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
        intent.putExtras(mBundle);
        getContext().startActivity(intent);
    }
}
