package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.widget.GridView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.SchoolBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.view.adapter.SchooleListAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/13.
 * Holle Android
 */

public class SchoolListActivity extends BaseActivity {


//    @BindView(R.id.ac_schoole_gridview)
    GridView acSchooleGridview;
    private SchooleListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_school_list;
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
        setTvTitle("学校",R.color.colorbBlack);
        NetworkSchoolList();

    }

    @Override
    public void initView() {
        super.initView();

        acSchooleGridview = (GridView) findViewById(R.id.ac_schoole_gridview);
        adapter = new SchooleListAdapter(this);
        acSchooleGridview.setAdapter(adapter);




    }

    private void NetworkSchoolList() {
        HttpParams params = new HttpParams();
        params.put("limit",20);
        params.put("offset",1);
        NetworkReuset.getInstance().getSchoolList(params, new onCallBack<SchoolBean>(this) {
            @Override
            public void onSucceed(SchoolBean schoolBean, Call call, String string) {
                List<SchoolBean.DataBean> data = schoolBean.getData();

                adapter.setList(data);
            }
        });

    }




}
