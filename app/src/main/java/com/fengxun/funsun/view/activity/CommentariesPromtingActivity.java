package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CommentPromtingBean;
import com.fengxun.funsun.model.bean.LoginBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.adapter.CommentariesPromtingAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.base.BasePromtingAdapter;
import com.fengxun.funsun.view.base.PromtingViewholder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/10.
 * Holle Android
 * 内容：评论提示列表
 */

public class CommentariesPromtingActivity extends BaseActivity {
    @BindView(R.id.commentaries_recyckerview)
    RecyclerView commentariesRecyckerview;
    private CommentariesPromtingAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commentaries_promting;
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
        setTvTitle("评论提示",R.color.colorbBlack);
        setBarLeftIcon(true,R.drawable.dingbuback);
        initViews();
    }

    /*
       网络请求
     */
    private void initViews() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        commentariesRecyckerview.setLayoutManager(manager);

        adapter = new CommentariesPromtingAdapter(this);
        commentariesRecyckerview.setAdapter(adapter);

        NetworkReuset.getInstance().GetReuset(RequestUrl.COMMENTPROMTING, new onCallBack<CommentPromtingBean>(this) {
            @Override
            public void onSucceed(CommentPromtingBean commentPromtingBean, Call call, String string) {
                List<CommentPromtingBean.DataBeanX.DataBean> data = commentPromtingBean.getData().getData();
                adapter.setData(data);
            }
        });

    }
}
