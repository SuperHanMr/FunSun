package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.ToViewListBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.view.adapter.ToViewPromitngAdapter;
import com.fengxun.funsun.view.base.BaseActivity;

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
        setBarLeftIcon(true,R.drawable.dingbuback);
        setTvTitle("查看提示",R.color.colorbBlack);
        initViews();

    }

    private void initViews() {
        final ToViewPromitngAdapter adapter = new ToViewPromitngAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        toviewPromtingRecyclerview.setLayoutManager(manager);
        toviewPromtingRecyclerview.setAdapter(adapter);

        NetworkReuset.getInstance().GetReuset(RequestUrl.TOVIEWPROMTING, new onCallBack<ToViewListBean>(this) {
            @Override
            public void onSucceed(ToViewListBean toViewListBean, Call call, String string) {
                ToViewListBean.DataBeanX data = toViewListBean.getData();
                List<ToViewListBean.DataBeanX.DataBean> mList = data.getData();
                adapter.setData(mList);

            }
        });

    }


}
