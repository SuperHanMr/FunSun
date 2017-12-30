package com.fengxun.funsun.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.HeadlinesBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.bean.RoostBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.listener.NewItemListener;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.adapter.NewRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.EditTextDialog;
import com.fengxun.funsun.view.views.RecyclerViewNoBugLinearLayoutManager;
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
 * 创建日期：on 2017/12/24.
 * Holle Android
 * 内容：二级兴趣溯源
 */

public class TowInterRootsActvity extends BaseActivity implements NewItemListener {

    @BindView(R.id.tow_intenr_roots_tv_title)
    TextView towIntenrRootsTvTitle;
    @BindView(R.id.tow_intenr_roots_recyclerview)
    RecyclerView towIntenrRootsRecyclerview;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private String interesid;
    private int offset = 1;

    private List<HeadlinesBean.DataBean> list;
    private NewRecyclerViewAdapter adapter;
    private EditTextDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.tow_interes_roots;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorbWhite;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        setStatusBarTextColocr();
    }

    @Override
    public void initView() {
        super.initView();
        setBarLeftIcon(true,R.drawable.dingbuback);
        list = new ArrayList<>();
        RoostBean bean = (RoostBean) getIntent().getSerializableExtra(KEY.KEY_ROOTSTAG);
        interesid = bean.interesId;
        towIntenrRootsTvTitle.setText(bean.interestName + "的搜索结果");
        adapter = new NewRecyclerViewAdapter(this, list, true);
        RecyclerViewNoBugLinearLayoutManager manager = new RecyclerViewNoBugLinearLayoutManager(this);
        towIntenrRootsRecyclerview.setLayoutManager(manager);
        towIntenrRootsRecyclerview.setAdapter(adapter);
        towIntenrRootsRecyclerview.addItemDecoration(new SpaceItemDecoration(15));

         /*
        =====================下拉刷新====================
         */
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

        NetworkData(true);
        adapter.setNewItemListenet(this);

    }

    private void NetworkData(final boolean isReresh) {

        HttpParams params = new HttpParams();
        params.put("source_tag_id", interesid);
        params.put("sort", "11");
        params.put("offset", offset);

        NetworkReuset.getInstance().getInteresRoost(params, new onCallBack<HeadlinesBean>(this) {
            @Override
            public void onSucceed(HeadlinesBean interestRoostBean, Call call, String string) {
                List<HeadlinesBean.DataBean> data = interestRoostBean.getData();
                LogUtils.e("解析成功");
                if (isReresh) {
                    if (data.size() != 0) {
                        adapter.setData(data);
                        refreshLayout.finishRefresh();
                    }
                } else {
                    adapter.setLoadMoreData(data);
                    refreshLayout.finishLoadmore();
                }

            }
        });

    }

    @Override
    public void OnVideoInfoListener(VideoInfoBean bean) {

        Intent intent = new Intent(this, VideoPlayerActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.VIDEOINFO,bean);
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
        dialog = new EditTextDialog(contentId, userId, new EditTextDialog.SendBackListener() {
            @Override
            public void sendBack() {
                ToastUtil.showNormalToast(TowInterRootsActvity.this,"评论成功");
                dialog.dismiss();
            }
        });
        dialog.show(getSupportFragmentManager(),"dialog");

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
