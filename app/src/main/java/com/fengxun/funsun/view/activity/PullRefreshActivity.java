package com.fengxun.funsun.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.base.BasePromtingAdapter;

import com.fengxun.funsun.view.views.refresh.PullRefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/15.
 * Holle Android
 */

public class PullRefreshActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_rull_refresh);
        recyclerView = (RecyclerView) findViewById(R.id.demo_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        final MyDemoRecyclerViewAdapter adapter = new MyDemoRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        RefreshLayout   refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setEnableAutoLoadmore(false);//开启自动加载功能（非必须）


        /*
        下拉加载
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {



            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {

                Log.e("------------->","0");
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setList(initData());
                        refreshlayout.finishRefresh();
//                        refreshlayout.resetNoMoreData();//恢复上拉状态 报错
                    }
                }, 1000);
            }

        });


        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                Log.e("------------->","1");
                refreshlayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                       if (30>adapter.getItemCount()){
                           adapter.setLoadList(initData());
                           refreshlayout.finishLoadmore();
                       }else {
                           refreshlayout.finishLoadmore();
                       }


                    }
                }, 1000);
            }
        });

        refreshLayout.autoRefresh();

    }

    private List initData() {

        for (int i= 0;i<12;i++){
            list.add(String.valueOf(i));
        }

        return list;
    }


    class MyDemoRecyclerViewAdapter extends BasePromtingAdapter{


        private List<String> list;

        public MyDemoRecyclerViewAdapter(Context context) {
            super(context);
            list =new ArrayList<>();
        }

        @Override
        public int layoutId() {
            return R.layout.text_demo;
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setList(List<String> addList) {
            list.clear();
            list.addAll(addList);
            notifyDataSetChanged();
        }

        public void setLoadList(List<String> loadListlist){
            this.list.addAll(list.size(),loadListlist);
            notifyDataSetChanged();
        }
    }



}
