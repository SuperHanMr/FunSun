package com.fengxun.funsun.view.activity;

import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CamPusStorietteBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.bean.RoostBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.listener.NewItemListener;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.adapter.StorietteRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/25.
 * Holle Android
 * 内容 校内溯源
 */

public class CampusLittlestoryActivity extends BaseActivity implements NewItemListener {


    @BindView(R.id.ac_campus_recyclerview)
    RecyclerView acCampusRecyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private StorietteRecyclerViewAdapter storietteAdapter;
    private List<CamPusStorietteBean> storietteList;

    private String interesId;

    private int offset = 1;


    private String rootsID;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_campus_layout;
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
    }

    @Override
    public void initView() {
        super.initView();
        setBarLeftIcon(true);
        RoostBean bean = (RoostBean) getIntent().getSerializableExtra(KEY.KEY_ROOTSTAG);
        setBarRightTv(bean.interestName);
        rootsID = bean.interesId;
        interesId = getIntent().getStringExtra(KEY.KEY_SCHOOLID);


        storietteList = new ArrayList<>();
        acCampusRecyclerview.setLayoutManager(new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        storietteAdapter = new StorietteRecyclerViewAdapter(this,false);
        acCampusRecyclerview.addItemDecoration(new SpaceItemDecoration(10));
        acCampusRecyclerview.setAdapter(storietteAdapter);
        storietteAdapter.setItemListener(this);
        NetworkData(true);



        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                NetworkData(true);
            }
        });



        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                offset++;
                NetworkData(false);
            }
        });


    }



    /*
    school_id,offset,source_tag_id)                                                                                                                     [NSString stringWithFormat:@"%@school_content_source/v1/?content_type=school&sort=%@&school_id=%@&offset=%@&source_tag_id=%@",server,(sort),(school_id),(offset),(source_tag_id)]
     */
    private void NetworkData(final boolean isRefresh) {
        HttpParams params = new HttpParams();
        params.put("content_type","school");
        params.put("offset",offset);
        params.put("school_id",interesId);
        params.put("source_tag_id",rootsID);
        params.put("sort",21);
        LogUtils.e(interesId+"-------"+rootsID);
        NetworkReuset.getInstance().getCamPusXiaoGuShi( params, new onCallBack<CamPusStorietteBean>(this) {
            @Override
            public void onSucceed(CamPusStorietteBean camPusStorietteBean, Call call, String string) {
                LogUtils.e("解析成功");
                List<CamPusStorietteBean.DataBean> data = camPusStorietteBean.getData();
                if (isRefresh){
                    storietteAdapter.setData(data);
                    refreshLayout.finishRefresh();
                }else {
                    storietteAdapter.setLoadData(data);
                    refreshLayout.finishLoadmore();
                }
            }
        });

    }

    @Override
    public void OnVideoInfoListener(VideoInfoBean bean) {
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.VIDEOINFO, bean);
        intent.putExtras(mBundle);
       startActivity(intent);

    }

    @Override
    public void OnPostInfoListener(String postId, int type) {

        Intent intent = new Intent(this, InformationParticularsActivity.class);
        intent.putExtra(BaseNewFragmnet.POSTINFO,postId);
        startActivity(intent);

    }

    @Override
    public void OnCommentContentListener(String contentId, String userId) {


    }

    @Override
    public void onRelationListener(String userId, String contentId, int type) {
        RelationInfBean bean = new RelationInfBean(type, userId, contentId);
        Intent intent = new Intent(this, RelationCalorieActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
        intent.putExtras(mBundle);
        startActivity(intent);

    }
}
