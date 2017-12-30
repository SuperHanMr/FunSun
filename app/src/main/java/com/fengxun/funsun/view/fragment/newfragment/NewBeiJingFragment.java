package com.fengxun.funsun.view.fragment.newfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengxun.funsun.R;
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
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.activity.InformationParticularsActivity;
import com.fengxun.funsun.view.activity.RelationCalorieActivity;
import com.fengxun.funsun.view.activity.VideoPlayerActivity;
import com.fengxun.funsun.view.adapter.NewRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.EditTextDialog;
import com.fengxun.funsun.view.views.RecyclerViewNoBugLinearLayoutManager;
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
 * 内容：北京
 * 请求参数 typer city 城市ID city 110
 */

public class NewBeiJingFragment extends BaseNewFragmnet implements NewItemListener {


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
        String asString = aCache.getAsString("city");
        if (asString != null) {
            LogUtils.e("缓存的数据：" + asString);
            Gson gson = new Gson();
            HeadlinesBean headlinesBean = gson.fromJson(asString, HeadlinesBean.class);
            List<HeadlinesBean.DataBean> data = headlinesBean.getData();
            list.addAll(data);
        }
        this.baseNewfragment = baseNewfragment;
        adapter = new NewRecyclerViewAdapter(getContext(), list, true);
        RecyclerViewNoBugLinearLayoutManager manager = new RecyclerViewNoBugLinearLayoutManager(getContext());
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
    public void NetworkData(final boolean isRefresh) {
        LogUtils.e("请求网络");
        HttpParams params = new HttpParams();
        params.put("content_type", "city");
        params.put("city","501");
        String url = SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?RequestUrl.HEADLINES:RequestUrl.NOT_LOGIN_HEADLINES;
        NetworkReuset.getInstance().getHomeNewReuset(url, params, new onCallBack<HeadlinesBean>(this) {
            @Override
            public void onSucceed(HeadlinesBean headlinesBean, Call call, String string) {
                List<HeadlinesBean.DataBean> data = headlinesBean.getData();

                if (data.size()>5){
                    aCache.put("city", string);
                }
                if (isRefresh){
                    adapter.setData(data);
                    baseNewfragment.finishRefresh();
                }else {

                    adapter.setLoadMoreData(data);
                    baseNewfragment.finishLoadmore();
                }



            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                if (isRefresh){
                    baseNewfragment.finishRefresh();
                }else {
                    baseNewfragment.finishLoadmore();
                }

            }
        });
    }


    @Override
    public void OnVideoInfoListener(VideoInfoBean bean) {
        Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(VIDEOINFO,bean);
        intent.putExtras(mBundle);
        getContext().startActivity(intent);
    }

    @Override
    public void OnPostInfoListener(String postId, int type) {
        LogUtils.e("------------>回调");
        Intent intent = new Intent(getContext(), InformationParticularsActivity.class);
        intent.putExtra(BaseNewFragmnet.POSTINFO,postId);
        getActivity().startActivity(intent);
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
