package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.BlackListBean;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.adapter.BlackListAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/15.
 * Holle Android
 */

public class BlackListActivity extends BaseActivity {
    @BindView(R.id.ac_blacklist_recyclerview)
    RecyclerView acBlacklistRecyclerview;
    private BlackListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_blacklist;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorbWhite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTextColocr();
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        setTvTitle("黑名单",R.color.colorbBlack);
        setBarLeftIcon(true,R.drawable.dingbuback);
        initViews();
        logData();
    }

    private void initViews() {


        adapter = new BlackListAdapter(this);
        LinearLayoutManager manage = new LinearLayoutManager(this);
        manage.setOrientation(LinearLayoutManager.VERTICAL);
        acBlacklistRecyclerview.setLayoutManager(manage);
        acBlacklistRecyclerview.setAdapter(adapter);

        adapter.setBlackListener(new BlackListAdapter.BlackListener() {
            @Override
            public void onBlackListener(int id) {
                LogUtils.d(id+"----->");

                deleteBlackList(id);


            }
        });





    }

    private void deleteBlackList(int id) {
        diaLogin(this).show();
        HttpParams params = new HttpParams();
        params.put("action","delete");
        params.put("black_user_id",id);
        NetworkReuset.getInstance().PostReuset(RequestUrl.HEIMINGDAN, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean o, Call call, String string) {
                diaLogin(BlackListActivity.this).dismiss();
                if (o.getCode()==200){
                    ToastUtil.showNormalToast(BlackListActivity.this,"删除成功");
                }else {
                    new SuperHanDialog(BlackListActivity.this,o.getMsg()).show();
                }


            }
        });



    }

    public void logData(){
        diaLogin(this).show();
        NetworkReuset.getInstance().GetReuset(RequestUrl.HEIMINGDAN, new onCallBack<BlackListBean>(this) {
            @Override
            public void onSucceed(BlackListBean blackListBean, Call call, String string) {

                if (blackListBean.getCode()==200){
                    List<BlackListBean.DataBean> data = blackListBean.getData();
                    adapter.setData(data);
                }
                diaLogin(BlackListActivity.this).dismiss();

            }
        });
    }


}
