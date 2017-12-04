package com.fengxun.funsun.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.LogUtils;

import com.fengxun.funsun.view.views.refresh.ParallaxPtrFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/20.
 * Holle Android
 * 内容：头条所有的Fragment父类
 * 子类只需继承此类 设置Adapter即可
 */

public abstract class BaseNewFragmnet extends Fragment {

    @BindView(R.id.base_newfragment)
    ParallaxPtrFrameLayout baseNewfragment;
    Unbinder unbinder;
    private RecyclerView recyclerView;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;

    // 视频信息
    public static final String VIDEOINFO = "videoinfo";

    //帖子信息
    public static final String POSTINFO = "postinfo";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_newfragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.new_recyclerview);
        unbinder = ButterKnife.bind(this, view);
        LogUtils.e("加载了");

        if (recyclerView!=null){
            initView(recyclerView,baseNewfragment);
        }else {
            LogUtils.d("---------------->趣闻的RecyclerView为空");
        }

        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }


    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }


    protected abstract void loadData();
    protected abstract void initView(RecyclerView views, ParallaxPtrFrameLayout baseNewfragment);


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
