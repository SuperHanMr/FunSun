package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.InterestRoostBean;
import com.fengxun.funsun.model.bean.RoostBean;
import com.fengxun.funsun.model.listener.OnMenuItemListener;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.adapter.InterestRootsRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.views.EditTextDialog;
import com.fengxun.funsun.view.views.bottomdialog.BottonDialogList;
import com.fengxun.funsun.view.views.bottomdialog.BottonDialogMenu;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/24.
 * Holle Android
 * 内容：兴趣溯源
 */

public class InterestRootsActivity extends BaseActivity implements InterestRootsRecyclerViewAdapter.OncommentContentListener {


    @BindView(R.id.ac_interest_roots_tv)
    TextView acInterestRootsTv;
    @BindView(R.id.ac_interest_roots_recyclerview)

    RecyclerView acInterestRootsRecyclerview;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private BottonDialogList dialogList;
    private BottonDialogMenu dialogMenu;
    private String roostName;
    private String roostId;
    private String sort = "11"; // 21是热门    11是时间
    private int offset = 1;
    private InterestRootsRecyclerViewAdapter adapter;
    private EditTextDialog dialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_interes_roots;
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

        roostName = getIntent().getStringExtra(KEY.KEY_SCHOOLNAME);
        roostId = getIntent().getStringExtra(KEY.KEY_SCHOOLID);
        setBarRightTv(roostName + " ▼");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        acInterestRootsRecyclerview.setLayoutManager(manager);
        List list = new ArrayList();
        adapter = new InterestRootsRecyclerViewAdapter(this, list, false);
        acInterestRootsRecyclerview.setAdapter(adapter);
        acInterestRootsRecyclerview.addItemDecoration(new SpaceItemDecoration(15));
        adapter.setOnCommentListenr(this);


        List<RoostBean> roostList = new ArrayList<>();
        String[] interestName = {"科技", "影视", "搞笑", "游记", "美食", "校园", "体育", "创就", "留学", "追星",
                "音乐", "游戏", "见闻", "二次元", "时尚", "学习", "运动", "生活"};
        String[] interestId = { "5012","5007", "5020", "5002", "5000", "5011", "5008", "5004", "5016", "5006", "5005", "5021", "5010", "5001", "5003", "5017", "5015", "5009"};
        for (int i = 0; i < interestName.length; i++) {
            RoostBean roostBean = new RoostBean(interestName[i], interestId[i]);
            roostList.add(roostBean);
        }
        dialogList = new BottonDialogList(roostList);
        dialogList.setOnMeunListItem(new OnMenuItemListener() {
            @Override
            public void onMenuItemListener(String interestId,String name) {
                setBarRightTv(name + " ▼");
                roostId = interestId;
                NetworkData(true);
                dialogList.dismiss();
            }
        });

        List<RoostBean> sequenceList = new ArrayList<>();
        String[] sequenceText = {"按时间排序", "按热度排序"};
        String[] sequenceType = {"11", "21"};
        for (int i = 0; i < sequenceText.length; i++) {
            RoostBean bean = new RoostBean(sequenceText[i], sequenceType[i]);
            sequenceList.add(bean);
        }

        dialogMenu = new BottonDialogMenu(sequenceList);
        dialogMenu.setTexTitle("选择排序", true);
        dialogMenu.setOnMeunListItem(new OnMenuItemListener() {
            @Override
            public void onMenuItemListener(String interestId,String name) {
                acInterestRootsTv.setText(name);
                sort = interestId;
                NetworkData(true);
                dialogMenu.dismiss();


            }
        });


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

        // 请求数据
        NetworkData(true);

    }

    private void NetworkData(final boolean isReresh) {

        HttpParams params = new HttpParams();
        params.put("source_tag_id", roostId);
        params.put("sort", sort);
        params.put("offset", offset);

        NetworkReuset.getInstance().getInteresRoost(params, new onCallBack<InterestRoostBean>(this) {
            @Override
            public void onSucceed(InterestRoostBean interestRoostBean, Call call, String string) {
                List<InterestRoostBean.DataBean> data = interestRoostBean.getData();
                if (isReresh){
                    if (data.size()!=0){
                        adapter.setNewData(data);
                        refreshLayout.finishRefresh();
                    }
                }else {
                    adapter.setLoadMoreData(data);
                    refreshLayout.finishLoadmore();
                }
                LogUtils.d("解析成功");
            }
        });
    }


    /*
    回调的ID
     */


    @OnClick({R.id.tooblar_right_text, R.id.ac_interest_roots_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tooblar_right_text:
                ToastUtil.showNormalToast(this, "溯源分类");
                dialogList.show(getSupportFragmentManager());
                break;
            case R.id.ac_interest_roots_rl:
                dialogMenu.show(getSupportFragmentManager());
                ToastUtil.showNormalToast(this, "按照时间排序");
                break;
        }
    }


    @Override
    public void onCommtntContentListener(String contentID, String userID) {

        dialog = new EditTextDialog(contentID, userID, new EditTextDialog.SendBackListener() {
            @Override
            public void sendBack() {
                dialog.dismiss();
                ToastUtil.showNormalToast(InterestRootsActivity.this, "评论成功");
            }
        });
        dialog.show(getSupportFragmentManager(), "dialog");
    }
}
