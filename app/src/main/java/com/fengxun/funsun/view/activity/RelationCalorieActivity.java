package com.fengxun.funsun.view.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.QuotationBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.bean.RelationInfoBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.adapter.RelationAdapter;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.BlurTransformation;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;


/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/11.
 * Holle Android
 */

public class RelationCalorieActivity extends AppCompatActivity {
    @BindView(R.id.relation_recyclerview)
    RecyclerView relationRecyclerview;
    @BindView(R.id.relation_iv_head)


    CircleImageView relationIvHead;
    @BindView(R.id.relation_tv_name)
    TextView relationTvName;
    @BindView(R.id.relation_tv_xingqu)
    TextView relationTvXingqu;
    @BindView(R.id.relation_tv_xiangyv)
    TextView relationTvXiangyv;
    @BindView(R.id.iv_zoom)
    ImageView ivZoom;
    @BindView(R.id.relation_iv_back)
    ImageView relationIvBack;
    @BindView(R.id.relation_iv_jvbao)
    ImageView relationIvJvbao;
    @BindView(R.id.relation_school_name)
    TextView relationSchoolName;

    @BindView(R.id.relation_refreshLayout)
    RefreshLayout relationRefreshLayout;

    //
    private List<String> list;
    private RelationInfBean bean;
    private RelationAdapter adapter;
    private int pager = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation_calorie);
        ButterKnife.bind(this);
        transparencyBar(this);
        intView();
        NetworkData();
        NetworkQuotations(true);

    }


    private void intView() {
        bean = (RelationInfBean) getIntent().getSerializableExtra(BaseNewFragmnet.RELATION);

        LogUtils.e(bean.toString());
        list = new ArrayList<>();
        if (relationRecyclerview != null) {
            adapter = new RelationAdapter(this);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            relationRecyclerview.setLayoutManager(manager);
            relationRecyclerview.setAdapter(adapter);
            relationRecyclerview.setHasFixedSize(true);
            relationRecyclerview.setNestedScrollingEnabled(false);
        }

        relationRefreshLayout.setEnableAutoLoadmore(false);
        relationRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });

        relationRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pager++;
                NetworkQuotations(false);
            }
        });

    }


    /*
    分两个接口
    一个请求个人信息 一个请求 语录
     */
    private void NetworkData() {

        HttpParams params = new HttpParams();
        params.put("friend_id", bean.userId);
        params.put("source", 2);
        params.put("content_id", bean.contentId);
        NetworkReuset.getInstance().getRelationCard(RequestUrl.RELATIONCIDE, params, new onCallBack<RelationInfoBean>(this) {
            @Override
            public void onSucceed(RelationInfoBean relationInfoBean, Call call, String string) {
                processInfo(relationInfoBean);
            }
        });
    }



      /*
    关系卡的用户语录
     */

    private void NetworkQuotations(final boolean isRefesh) {

        // TODO 这个地方需要 上拉刷新的逻辑判断
        HttpParams params = new HttpParams();
        params.put("user_id", bean.userId);
        params.put("page", pager);
        NetworkReuset.getInstance().getRelationCard(RequestUrl.QUOTATIONS, params, new onCallBack<QuotationBean>(this) {
            @Override
            public void onSucceed(QuotationBean quotationBean, Call call, String string) {
                List<QuotationBean.DataBean.RetBean> retBeanList = quotationBean.getData().getRet();
                if (isRefesh){
                    adapter.setData(retBeanList);
                }else {
                    adapter.setLoadData(retBeanList);
                    relationRefreshLayout.finishLoadmore();
                }

            }
        });

    }


    /*
    关系卡的用户信息
     */
    private void processInfo(RelationInfoBean relationInfoBean) {
        Picasso.with(this).load(relationInfoBean.getData().getAvatar()).transform(new BlurTransformation(this)).into(ivZoom);
        Picasso.with(this).load(relationInfoBean.getData().getAvatar()).into(relationIvHead);
        relationTvName.setText(relationInfoBean.getData().getNick());
        relationTvXingqu.setText(relationInfoBean.getData().getMeet_count() == 0 ? "无" : String.valueOf(relationInfoBean.getData().getMeet_count()));
        relationTvXiangyv.setText(String.valueOf(relationInfoBean.getData().getLevel()));
        relationSchoolName.setText(relationInfoBean.getData().getSchool_name());
    }


    @OnClick({R.id.relation_iv_back, R.id.relation_iv_jvbao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relation_iv_back:
                finish();
                break;
            case R.id.relation_iv_jvbao:
                /*
                举报
                 */
                break;
        }
    }


    /**
     * 修改状态栏为全透明
     *
     * @param activity
     */
    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
