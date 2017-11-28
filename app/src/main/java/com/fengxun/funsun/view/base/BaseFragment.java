package com.fengxun.funsun.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 程序员：韩永辉
 * 时间：2017/11/2
 * 联系方式：17710558669
 * Hello Android！
 * 内容 抽取ToolBar
 * Fragment懒加载
 * 抽取下拉刷新
 */

public abstract class BaseFragment extends Fragment {

    private Toolbar mToolbar;
    private RelativeLayout barLeftIcon;
    private TextView barLeftTv;
    private ImageView barRightIcon;
    private TextView barRightTv;
    private Unbinder bind;

    //是否可见
    protected boolean isVisble;
    // 标志位，标志Fragment已经初始化完成。
    public boolean isPrepared = false;
    public SuperHanLoginDiglog loginDiglog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        mToolbar = (Toolbar) view.findViewById(R.id.tooblar);
        barLeftIcon = (RelativeLayout) view.findViewById(R.id.tooblar_left_icon);
        barLeftTv = (TextView) view.findViewById(R.id.tooblar_left_text);
        barRightIcon = (ImageView) view.findViewById(R.id.tooblar_right_icon);
        barRightTv = (TextView) view.findViewById(R.id.tooblar_right_text);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();


    protected abstract int getLayoutId();

    public void setBarLeftTv(String string){
        barLeftTv.setText(string);
    }


    /**
     * 实现Fragment数据的缓加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisble = true;
            /*
            不可见的时候调用
             */
            NetworkData();
        } else {
            isVisble = false;
        }
    }

    public void NetworkData(){

    }


    public SuperHanLoginDiglog diglog(Context context){
        if (loginDiglog ==null){
            loginDiglog = new SuperHanLoginDiglog(context);
        }
        return loginDiglog;
    }

}
