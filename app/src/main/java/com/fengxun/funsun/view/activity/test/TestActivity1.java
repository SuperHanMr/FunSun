package com.fengxun.funsun.view.activity.test;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.AtextBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.view.adapter.CommentItemAdapter;
import com.fengxun.funsun.view.adapter.InformationAimAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.RecyclerViewHeader;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/20.
 * Holle Android
 * 这个类 备用类 写评论布局的 现在 问题是评论item过多怎么造成OOM异常
 *
 */

public class TestActivity1 extends BaseActivity {



    @BindView(R.id.ac_information_content_recyclerview) //content列表
    RecyclerView acInformationContentRecyclerview;
    @BindView(R.id.ac_information_recyclerview_head) // HeadLayout
    RecyclerViewHeader acInformationRecyclerviewHead;



    /*
    内容详情
     */
    @BindView(R.id.ac_information_webview)
    WebView acInformationWebview; //WebView


    /*
    学校溯源
     */
    @BindView(R.id.roots1_head)
    CircleImageView roots1Head;
    @BindView(R.id.roots1_school)
    TextView roots1School;
    @BindView(R.id.new_roots1)
    AutoRelativeLayout newRoots1;


    /*
   兴趣溯源
   */
    @BindView(R.id.roots2_content)
    TextView roots2Content;
    @BindView(R.id.new_roots2)
    AutoRelativeLayout newRoots2;


    @BindView(R.id.comment_iv_collect)
    CheckBox commentIvCollect;


    private String contentId;
    private String snapshot;
    private InformationAimAdapter adapter;
    private CommentItemAdapter commentItemAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_information;
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
        contentId = getIntent().getStringExtra(BaseNewFragmnet.POSTINFO);
        NetworkData();
    }


    private void NetworkData() {
        NetworkReuset.getInstance().getInformationData(RequestUrl.INFORMATIONDATA, contentId, new onCallBack<AtextBean>(this) {
            @Override
            public void onSucceed(AtextBean atextBean, Call call, String string) {
                AtextBean.DataBean data = atextBean.getData();
                processInfoPartion(data);
            }
        });

    }

    private void processInfoPartion(final AtextBean.DataBean data) {

            /*
        如果 data.getContent_root_tag_img()为"",是兴趣溯源 不为"" 是学校溯源
        根据判断 去显示对应的溯源类型
         */
        if (data.getContent_root_tag_img().equals("")) {
            newRoots1.setVisibility(View.GONE);
            newRoots2.setVisibility(View.VISIBLE);
            roots2Content.setText(data.getContent_root_tag());
        } else {
            newRoots1.setVisibility(View.VISIBLE);
            newRoots2.setVisibility(View.GONE);
            roots1School.setText(data.getContent_root_tag());
            Picasso.with(this).load(data.getContent_root_tag_img()).into(roots1Head);
        }


           /*
        WebView 自适应
         */
        WebSettings webSettings = acInformationWebview.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);


          /*
        =====================播放视频==================
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setPluginState(WebSettings.PluginState.ON);
        acInformationWebview.setWebChromeClient(new WebChromeClient());

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        acInformationWebview.loadUrl(data.getContent());


        //设置不用系统浏览器打开,直接显示在当前Webview
        acInformationWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals(data.getContent())) {
                    view.loadUrl(url);
                } else {

                    /*
                    这个位置 捕获到js监听事件
                     */
//                    AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    acInformationWebview.setLayoutParams(params);

//                    commentItemAdapter.notifyDataSetChanged();
//                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    acInformationWebview.setLayoutParams(lp);

                }
                return true;
            }
        });

    }







    @Override
    public void initView() {
        super.initView();
//        List<String> list = new ArrayList<>();
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        acInformationContentRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        commentItemAdapter = new CommentItemAdapter(this,list,false);
//        acInformationContentRecyclerview.setAdapter(commentItemAdapter);
//
//        acInformationRecyclerviewHead.attachTo(acInformationContentRecyclerview);
    }
}
