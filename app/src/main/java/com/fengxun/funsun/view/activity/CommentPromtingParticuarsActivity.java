package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.view.adapter.CommentariesPromtingParticuarsAdapter;
import com.fengxun.funsun.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/14.
 * Holle Android
 */

public class CommentPromtingParticuarsActivity extends BaseActivity {

    @BindView(R.id.commentprmoting_particulars_recyclerview)
    RecyclerView commentprmotingParticularsRecyclerview;

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
        setBarLeftIcon(true,R.drawable.dingbuback);
        initViews();
        logData();

    }

    private void initViews() {

        CommentariesPromtingParticuarsAdapter adapter = new CommentariesPromtingParticuarsAdapter(this);
        LinearLayoutManager manage = new LinearLayoutManager(this);
        manage.setOrientation(LinearLayoutManager.VERTICAL);
        commentprmotingParticularsRecyclerview.setLayoutManager(manage);
        commentprmotingParticularsRecyclerview.setAdapter(adapter);

    }

    private void logData() {




    }





}
