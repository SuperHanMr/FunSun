package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.ToviewPromtingParticuarsBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.view.adapter.ToviewPromtingParticuarsAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/14.
 * Holle Android
 */

public class ToviewPromtingParticuarsActivity extends BaseActivity {


    @BindView(R.id.toview_promting_paitcuars_recyclerview)
    RecyclerView toviewPromtingPaitcuarsRecyclerview;
    private int userid;
    private ToviewPromtingParticuarsAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toview_promtingparitcuars;
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
        userid = getIntent().getIntExtra("userid", 1);
        setBarLeftIcon(true,R.drawable.dingbuback);
        initViews();
        LoData();
    }

    private void initViews() {
        adapter = new ToviewPromtingParticuarsAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        toviewPromtingPaitcuarsRecyclerview.setLayoutManager(manager);
        toviewPromtingPaitcuarsRecyclerview.setAdapter(adapter);
    }

    private void LoData() {
        HttpParams params = new HttpParams();
        params.put("friend_id",userid);
        params.put("page",1); // page 可以改变 添加刷新
        NetworkReuset.getInstance().GetReuset(RequestUrl.TOVIEWDATAIL, params, new onCallBack<ToviewPromtingParticuarsBean>(this) {
            @Override
            public void onSucceed(ToviewPromtingParticuarsBean toviewPromtingParticuarsBean, Call call, String string) {
                List<ToviewPromtingParticuarsBean.DataBeanX.DataBean> data = toviewPromtingParticuarsBean.getData().getData();
                adapter.setData(data);
            }
        });


    }
}
