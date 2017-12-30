package com.fengxun.funsun.view.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.HotSchoolBean;
import com.fengxun.funsun.model.bean.RecommendSchooleBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.ACache;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.view.activity.LoginActivity;
import com.fengxun.funsun.view.activity.SchoolListActivity;
import com.fengxun.funsun.view.activity.SchoolRootsActivity;
import com.fengxun.funsun.view.activity.SearchSchoolActivity;
import com.fengxun.funsun.view.adapter.aroudcampus.AroundCampusHotstopAdapter;
import com.fengxun.funsun.view.adapter.aroudcampus.AroundCampusVicinityAdapter;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.views.FlowLayout;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/3.
 * Holle Android
 * 内容：未登录 逛校园 是一个Fragment 登录之后 会复用这个Activity
 */

public class AroundCampusFragmnet extends BaseFragment {

    @BindView(R.id.status_bar_fix)
    View statusBarFix;
    Unbinder unbinder;
    @BindView(R.id.around_campus_scholl_list)
    RecyclerView aroundCampusSchollList;
    @BindView(R.id.around_campus_scholl_name)
    RecyclerView aroundCampusSchollName;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private ArrayList<String> recommendList;
    private AroundCampusHotstopAdapter adapter;
    private ACache aCache;

    private boolean isLogdin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_around;
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NetworkDatas();
    }

    /*
    初始化一下 RecyclerView
    垂直排列(热门学校) 水平排列(附近学校)
     */
    @Override
    protected void initView() {

        if (isLogdin) {
            setBarLeftIcon(true);
        } else {
            statusBarFix.setLayoutParams(new
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SteBoolarUtil.getStateBarHeight(getActivity())));//填充状态栏
            setBarLeftTv("登录");
        }


        refreshLayout.setEnableLoadmore(false);//是否启用上拉加载功能
        recommendList = new ArrayList<>();

        /*
        垂直
         */
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        aroundCampusSchollList.setLayoutManager(manager);
        adapter = new AroundCampusHotstopAdapter(getContext());
        aroundCampusSchollList.setAdapter(adapter);
        /*
       水平
         */
        LinearLayoutManager vicinityManager = new LinearLayoutManager(getContext());
        vicinityManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        aroundCampusSchollName.setLayoutManager(vicinityManager);
        AroundCampusVicinityAdapter vicinityAdapter = new AroundCampusVicinityAdapter(getContext());
        aroundCampusSchollName.setAdapter(vicinityAdapter);


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                NetworkDatas();
            }
        });
        /*
        列表数据
         */
        aCache = ACache.get(getContext());
        String schoollist = aCache.getAsString("schoollist");
        if (schoollist!=null){
            Gson gson = new Gson();
            HotSchoolBean hotSchoolBean = gson.fromJson(schoollist, HotSchoolBean.class);
            List<HotSchoolBean.DataBean> data = hotSchoolBean.getData();
            adapter.setData(data);
        }


        String tuijian = aCache.getAsString("tuijian");
        if (tuijian!=null){
            Gson gson = new Gson();
            RecommendSchooleBean recommendSchooleBean = gson.fromJson(tuijian, RecommendSchooleBean.class);
            List<RecommendSchooleBean.DataBean> data = recommendSchooleBean.getData();
            processRecommendSchoolData(data);
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tooblar_left_text, R.id.around_campus_rl_search_school, R.id.around_campus_gengduo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tooblar_left_text:
                /*
                跳转登录
                 */
                getActivity().startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.around_campus_gengduo:
                /*
                跳转 更多学校列表
                 */
                getActivity().startActivity(new Intent(getContext(), SchoolListActivity.class));
                break;

            case R.id.around_campus_rl_search_school:

                getActivity().startActivity(new Intent(getContext(), SearchSchoolActivity.class));
                break;
        }
    }


    public void NetworkDatas() {
        NetworkReuset.getInstance().getHotSchool(new onCallBack<HotSchoolBean>(this) {
            @Override
            public void onSucceed(HotSchoolBean hotSchoolBean, Call call, String string) {

                aCache.put("schoollist",string);
                List<HotSchoolBean.DataBean> data = hotSchoolBean.getData();
                adapter.setData(data);

            }
        });


        NetworkReuset.getInstance().getRecommend(new onCallBack<RecommendSchooleBean>(this) {
            @Override
            public void onSucceed(RecommendSchooleBean recommendSchooleBean, Call call, String string) {
                aCache.put("tuijian",string);
                List<RecommendSchooleBean.DataBean> data = recommendSchooleBean.getData();
                flowLayout.removeAllViews();
                processRecommendSchoolData(data);

                refreshLayout.finishRefresh();

            }
        });


    }


    private void processRecommendSchoolData(final List<RecommendSchooleBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            TextView tv = new TextView(getContext());
            tv.setText(data.get(i).getTag_name());
            tv.setTextColor(getContext().getResources().getColor(R.color.colorbText));
            tv.setPadding(15, 10, 10, 15);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(14);
            StateListDrawable stateDrawable = new StateListDrawable();
            Resources resources = getActivity().getResources();
            Drawable drawable = resources.getDrawable(R.drawable.shape_recommend_brea);
            stateDrawable.addState(new int[]{}, drawable);
            tv.setBackground(stateDrawable);

            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),SchoolRootsActivity.class);
                    intent.putExtra(KEY.KEY_SCHOOLID,data.get(finalI).getTag_id()+"");
                    intent.putExtra(KEY.KEY_SCHOOLNAME,data.get(finalI).getTag_name());

                    getActivity().startActivity(intent);
                }
            });

            flowLayout.addView(tv);

        }

    }

}
