package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CommentariesPromtingBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.view.adapter.CommentariesPromtingParticuarsAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/14.
 * Holle Android
 */

public class CommentPromtingParticuarsActivity extends BaseActivity {

    @BindView(R.id.commentprmoting_particulars_recyclerview)
    RecyclerView commentprmotingParticularsRecyclerview;
    @BindView(R.id.ac_comment_refresh)
    RefreshLayout acCommentRefresh;
    private int userid;
    private CommentariesPromtingParticuarsAdapter adapter;

    private int pager = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commnetpromting_particulars;
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
        userid = getIntent().getIntExtra("userid", 1);
        initViews();
        logData(true);

    }

    private void initViews() {

        adapter = new CommentariesPromtingParticuarsAdapter(this);
        LinearLayoutManager manage = new LinearLayoutManager(this);
        manage.setOrientation(LinearLayoutManager.VERTICAL);
        commentprmotingParticularsRecyclerview.setLayoutManager(manage);
        commentprmotingParticularsRecyclerview.setAdapter(adapter);
        acCommentRefresh.setEnableAutoLoadmore(false);

        acCommentRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pager++;
                logData(false);
            }
        });

    }


    private void logData(final boolean isRefresh) {
        HttpParams params = new HttpParams();
        params.put("comment_user", userid);
        params.put("page", pager);
        NetworkReuset.getInstance().GetReuset(RequestUrl.COMMENTDATAIL, params, new onCallBack<CommentariesPromtingBean>(this) {

            @Override
            public void onSucceed(CommentariesPromtingBean commentariesPromtingBean, Call call, String string) {
                CommentariesPromtingBean.DataBeanX data = commentariesPromtingBean.getData();
                List<CommentariesPromtingBean.DataBeanX.DataBean> data1 = data.getData();

                if (isRefresh) {
                    adapter.setData(data1);
                } else {
                    adapter.setLoadData(data1);
                    acCommentRefresh.finishLoadmore();
                }

            }
        });


    }


}
