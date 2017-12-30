package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.MeetTheManBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.view.adapter.MeetManAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/22.
 * Holle Android
 * 内容：相遇的人
 */

public class MeetActivity extends BaseActivity {


    @BindView(R.id.ac_ac_meet_recyclerview)
    RecyclerView acAcMeetRecyclerview;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private String contentId;
    private int offset = 1;
    private MeetManAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mett_man;
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
        contentId = getIntent().getStringExtra("contentID");
        NetworkData(true);
    }

    private void NetworkData(final boolean isReresh) {
        /*
        获取相遇的人
         */
        HttpParams params = new HttpParams();
        params.put("offset",offset);
        NetworkReuset.getInstance().getMeetTheMan(String.valueOf(contentId), new onCallBack<MeetTheManBean>(this) {
            @Override
            public void onSucceed(MeetTheManBean meetTheManBean, Call call, String string) {
                List<MeetTheManBean.DataBean> data = meetTheManBean.getData();
                if (isReresh){
                    adapter.setData(data);
                    refreshLayout.finishRefresh();
                }else {
                    if (data.size()!=0){
                        adapter.setLoadMoreData(data);
                        refreshLayout.finishLoadmore();

                    }

                }

            }
        });

    }

    @Override
    public void initView() {
        super.initView();
        setBarLeftIcon(true, R.drawable.dingbuback);
        setCenterIamgView();
        refreshLayout.setEnableAutoLoadmore(false);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        acAcMeetRecyclerview.setLayoutManager(manager);
        adapter = new MeetManAdapter(this);
        acAcMeetRecyclerview.setAdapter(adapter);


        /*
        智能下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                offset = 1;
                NetworkData(true);

            }
        });




        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                offset++;
                NetworkData(false);
            }
        });

    }



}
