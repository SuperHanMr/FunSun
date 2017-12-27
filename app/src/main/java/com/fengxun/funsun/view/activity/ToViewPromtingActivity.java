package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.ToViewListBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.adapter.ToViewPromitngAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/12.
 * Holle Android
 */

public class ToViewPromtingActivity extends BaseActivity {
    @BindView(R.id.toview_promting_recyclerview)
    RecyclerView toviewPromtingRecyclerview;
    @BindView(R.id.ac_toview_refresh)
    RefreshLayout acToviewRefresh;
    private ToViewPromitngAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_toview_promting;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorbWhite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setStatusBarTextColocr();
        ButterKnife.bind(this);
        setBarLeftIcon(true, R.drawable.dingbuback);
        setTvTitle("查看提示", R.color.colorbBlack);
        initViews();
        NetworkData(true);

    }

    private void initViews() {
        adapter = new ToViewPromitngAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        toviewPromtingRecyclerview.setLayoutManager(manager);
        toviewPromtingRecyclerview.setAdapter(adapter);
        acToviewRefresh.setEnableAutoLoadmore(false);

        acToviewRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                NetworkData(false);
            }
        });

    }


    private void NetworkData(final boolean isReresh){

        NetworkReuset.getInstance().getPromting(RequestUrl.TOVIEWPROMTING, new onCallBack<ToViewListBean>(this) {
            @Override
            public void onSucceed(ToViewListBean toViewListBean, Call call, String string) {
                ToViewListBean.DataBeanX data = toViewListBean.getData();
                List<ToViewListBean.DataBeanX.DataBean> mList = data.getData();
                if (isReresh){
                    adapter.setData(mList);

                }else {
//                    adapter.setLoadData(mList);
                    acToviewRefresh.finishLoadmore();
                }


                LogUtils.e("我是网络回调");
            }

            @Override
            public void onCacheSuccess(ToViewListBean toViewListBean, Call call) {
                super.onCacheSuccess(toViewListBean, call);
                ToViewListBean.DataBeanX data = toViewListBean.getData();
                List<ToViewListBean.DataBeanX.DataBean> mList = data.getData();
                adapter.setData(mList);
                LogUtils.e("我是缓存回调");
            }
        });
    }


}
