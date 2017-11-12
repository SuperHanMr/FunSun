package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hanyonghui.refreshlibrary.PtrDefaultHandler;
import com.example.hanyonghui.refreshlibrary.PtrFrameLayout;
import com.example.hanyonghui.refreshlibrary.PtrHandler;
import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.adapter.LocationSchoolListAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.refresh.ParallaxPtrFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/9.
 * Holle Android
 * <p>
 * 内容:语录
 */

public class QuotationsActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    ParallaxPtrFrameLayout refreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.quotation_activity;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorBooblar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);



        refreshLayout.setPtrHandler(mPtrHandler);
        refreshLayout.setPullToRefresh(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        LocationSchoolListAdapter adapter = new LocationSchoolListAdapter(this);
        recyclerView.setAdapter(adapter);

        // 由于PtrFrameLayout的自动刷新需要在onWindowFocusChanged(boolean)之后调用，所以这里延时250ms.
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.refreshComplete();
            }
        }, 250);
    }

    /**
     * 刷新监听。
     */
    private PtrHandler mPtrHandler = new PtrDefaultHandler() {
        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            // 这里延时2000ms，模拟网络请求。
            refreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.refreshComplete();
                    LogUtils.e("---->刷新完毕");
                }
            }, 2000);
        }
    };

}
