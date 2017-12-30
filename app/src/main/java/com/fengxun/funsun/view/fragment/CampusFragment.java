package com.fengxun.funsun.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CamPusEventBean;
import com.fengxun.funsun.model.bean.CamPusStorietteBean;
import com.fengxun.funsun.model.bean.CampusXiaoGuShiBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.listener.NewItemListener;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.ACache;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.activity.AroundCampusActivity;
import com.fengxun.funsun.view.activity.InformationParticularsActivity;
import com.fengxun.funsun.view.activity.RelationCalorieActivity;
import com.fengxun.funsun.view.activity.VideoPlayerActivity;
import com.fengxun.funsun.view.adapter.CamPusAdapetr;
import com.fengxun.funsun.view.adapter.CamPusEventRecyclerViewAdapter;
import com.fengxun.funsun.view.adapter.StorietteRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.EditTextDialog;
import com.fengxun.funsun.view.views.MYStaggeredGridLayoutManager;
import com.fengxun.funsun.view.views.SlidingTabLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/3.
 * Holle Android
 * 校园 大事件 小故事 这个页面作为学校溯源 需要复用
 **/

public class CampusFragment extends BaseFragment implements NewItemListener, ViewPager.OnPageChangeListener {


    @BindView(R.id.status_bar_fix)
    View statusBarFix;
    Unbinder unbinder;

    @BindView(R.id.campus_vp)
    ViewPager campusVp;
    @BindView(R.id.campus_tab)
    SlidingTabLayout campusTab;
    @BindView(R.id.campus_tv_school_name)
    TextView campusTvSchoolName;
    @BindView(R.id.campus_tv_guangxiaoyuan)
    TextView campusTvGuangxiaoyuan;
    @BindView(R.id.campus_left_back)
    RelativeLayout campusLeftBack;



    private RefreshLayout refreshLayout;

    private List<RecyclerView> recyclerViews = new ArrayList<>();


    private int type;
    private boolean isRefresh = true;

    private List<CamPusEventBean.DataBean> list;
    private CamPusEventRecyclerViewAdapter adapter;


    private List<CamPusStorietteBean.DataBean> storietteList;
    private StorietteRecyclerViewAdapter storietteAdapter;
    private EditTextDialog dialog;

    private int pager = 1;

    private int storiettePager = 1;

    private String schollId; // 学校ID
    private String schoolName; // 学校名字
    private boolean isRoots;
    private ACache aCache;


    /*
    空构造
     */
    public CampusFragment() {

    }

    public CampusFragment(String schollId, String schoolName, boolean type) {
        this.schollId = schollId;
        this.schoolName = schoolName;
        this.isRoots = type;
    }

    @Override
    protected void initView() {

        aCache = ACache.get(getContext());
        /*
        初始化这个页面的 时候 判断学校id和SP保存是否一样 是校内 还是跳转过来
         */
        if (isRoots) {
            campusTvSchoolName.setVisibility(View.GONE);
            campusLeftBack.setVisibility(View.VISIBLE);
            campusTvGuangxiaoyuan.setText(schoolName);
            campusLeftBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });


        } else {
            campusTvSchoolName.setText(schoolName);
            campusTvSchoolName.getPaint().setFakeBoldText(true);
            statusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SteBoolarUtil.getStateBarHeight(getActivity())));//填充状态栏
        }

        campusTvGuangxiaoyuan.getPaint().setFakeBoldText(true);
        String title[] = {"大事件", "小故事"};


//
        /*
        大事件 RecyclerView
         */
        list = new ArrayList<>();
        final RecyclerView bigEventRecyclerView = new RecyclerView(getContext());
        // 大事件 复用趣闻模块的条目
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        bigEventRecyclerView.setLayoutManager(manager);
        adapter = new CamPusEventRecyclerViewAdapter(getContext(), list, true);
        bigEventRecyclerView.setLayoutManager(manager);
        bigEventRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        bigEventRecyclerView.setAdapter(adapter);
        recyclerViews.add(bigEventRecyclerView);
        adapter.setItemListener(this);


        /*
        小故事RecyclerView
         */
        storietteList = new ArrayList<>();
        final RecyclerView storietteRecyclerView = new RecyclerView(getContext());
        storietteRecyclerView.setLayoutManager(new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        storietteAdapter = new StorietteRecyclerViewAdapter(getActivity(), true);
        storietteRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        storietteRecyclerView.setAdapter(storietteAdapter);
        recyclerViews.add(storietteRecyclerView);
        storietteAdapter.setItemListener(this);

        CamPusAdapetr adapetr = new CamPusAdapetr(recyclerViews, title);
        campusVp.setAdapter(adapetr);
        campusTab.setViewPager(campusVp);

           /*
//        读取缓存数据
//         */


        String toriette = aCache.getAsString("toriette");

        String data = aCache.getAsString("eventbus");

        if (data!=null){
            Gson gson = new Gson();
            CamPusEventBean camPusEventBean = gson.fromJson(data, CamPusEventBean.class);
            List<CamPusEventBean.DataBean> data1 = camPusEventBean.getData();
            list = data1;
            adapter.setData(data1);
        }





        if (toriette!=null){
            Gson gson = new Gson();
            CamPusStorietteBean camPusStorietteBean = gson.fromJson(toriette, CamPusStorietteBean.class);
            List<CamPusStorietteBean.DataBean> data1 = camPusStorietteBean.getData();
            storietteList = data1;
            storietteAdapter.setData(storietteList);
        }




            /*
        ViewPager 设置监听
         */
        campusVp.setOnPageChangeListener(this);



        /*
        智能下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (type == 0) {
                    NetworkDatas(true);
                } else {
                    NetwokStorietteData(true);
                }
            }
        });

        /*
        智能上拉加载更多
         */
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (type == 0) {
                    pager++;
                    NetworkDatas(false);
                } else {
                    storiettePager++;
                    NetwokStorietteData(false);
                }
            }

        });

    }
    /*
    这个页面 不需要每次都请求 数据
     */
    public void NetworkDatas(final boolean isRefresh) {
        LogUtils.e("--------->请求校内大事件数据：当前状态："+SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)+"当前toekn："+SPUtils.getString(KEY.KEY_USERTOKEN));
        HttpParams params = new HttpParams();
        params.put("offset", pager); // 请求页数
        params.put("order", 0); // 按时间 0 按热度 1
        NetworkReuset.getInstance().getCamPusEventbus(schollId, params, new onCallBack<CamPusEventBean>(this) {
            @Override
            public void onSucceed(CamPusEventBean headlinesBean, Call call, String string) {
                if (isRefresh) {
                    adapter.setData(headlinesBean.getData());
                    aCache.put("eventbus",string);
                    refreshLayout.finishRefresh();
                } else {
                    adapter.setLoadMoreData(headlinesBean.getData());
                    refreshLayout.finishLoadmore();
                }
                LogUtils.e("解析成功！！！！！！");
            }

        });

    }

    private void NetwokStorietteData(final boolean isRefresh) {
        HttpParams params = new HttpParams();
        params.put("offset", storiettePager);
        /*
         这个位置
         */
        NetworkReuset.getInstance().GetReuset(RequestUrl.XIAOGUSHI.replace("{school_id}", String.valueOf(schollId)), new onCallBack<CamPusStorietteBean>(this) {
            @Override
            public void onSucceed(CamPusStorietteBean camPusStorietteBean, Call call, String string) {
                List<CamPusStorietteBean.DataBean> data = camPusStorietteBean.getData();
                if (isRefresh) {
                    storietteAdapter.setData(data);
                    aCache.put("toriette", string);
                    refreshLayout.finishRefresh();
                } else {
                    storietteAdapter.setLoadData(data);
                    refreshLayout.finishLoadmore();
                }
            }
        });

    }











    @Override
    protected int getLayoutId() {
        return R.layout.fragment_campus;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        refreshLayout = (RefreshLayout) rootView.findViewById(R.id.campus_refreshLayout);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NetworkDatas(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void OnVideoInfoListener(VideoInfoBean bean) {
        Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.VIDEOINFO, bean);
        intent.putExtras(mBundle);
        getContext().startActivity(intent);
    }

    @Override
    public void OnPostInfoListener(String postId, int type) {
        Intent intent = new Intent(getContext(), InformationParticularsActivity.class);
        intent.putExtra(BaseNewFragmnet.POSTINFO, postId);
        getActivity().startActivity(intent);

    }

    @Override
    public void OnCommentContentListener(String contentId, String userId) {
        dialog = new EditTextDialog(contentId, userId, new EditTextDialog.SendBackListener() {
            @Override
            public void sendBack() {
                ToastUtil.showNormalToast(getContext(), "评论成功");
                dialog.dismiss();
            }
        });
        dialog.show(getFragmentManager(), "dialog");
    }


    @Override
    public void onRelationListener(String userId, String contentId, int type) {
        RelationInfBean bean = new RelationInfBean(type, userId, contentId);
        Intent intent = new Intent(getContext(), RelationCalorieActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
        intent.putExtras(mBundle);
        getContext().startActivity(intent);
    }


    @OnClick(R.id.campus_right)
    public void onViewClicked() {
        getActivity().startActivity(new Intent(getContext(), AroundCampusActivity.class));

    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
                CampusFragment.this.type = position;
                if (isRefresh) {
                    refreshLayout.autoRefresh();
                    isRefresh = false;
                }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
