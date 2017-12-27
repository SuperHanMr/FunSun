package com.fengxun.funsun.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanyonghui.mylibrary.BottomMenuFragment;
import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.AtextBean;
import com.fengxun.funsun.model.bean.CommentContentBean;
import com.fengxun.funsun.model.bean.CommentInfoBean;
import com.fengxun.funsun.model.bean.InformationAimBean;
import com.fengxun.funsun.model.bean.LikeBean;
import com.fengxun.funsun.model.bean.MeetTheManBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.listener.OnIikeListener;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.adapter.CommentItemAdapter;
import com.fengxun.funsun.view.adapter.InformationAimAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.EmojiKeyboard;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.rockerhieu.emojicon.EmojiconEditText;
import io.github.rockerhieu.emojicon.EmojiconGridFragment;
import io.github.rockerhieu.emojicon.EmojiconsFragment;
import io.github.rockerhieu.emojicon.emoji.Emojicon;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/18.
 * Holle Android
 */

public class InformationParticularsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, OnIikeListener, RadioGroup.OnCheckedChangeListener, TextView.OnEditorActionListener,
        EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener {


    @BindView(R.id.ac_information_tv_title)
    TextView acInformationTvTitle; // 内容 Title

    @BindView(R.id.ac_information_iv_head)
    CircleImageView acInformationIvHead; // 发帖用户头像

    @BindView(R.id.ac_information_tv_user)
    TextView acInformationTvUser; // 发帖的用户姓名

    @BindView(R.id.ac_information_webview)
    WebView acInformationWebview; //WebView

    @BindView(R.id.ac_information_tv_root)
    TextView acInformationTvRoot; // 文章来源在哪

    @BindView(R.id.ac_information_collect)
    CheckBox acInformationCollect; // 收藏按钮

    @BindView(R.id.ac_information_tv_time) // 时间
            TextView acInformationTvTime;


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


    @BindView(R.id.ac_information_aim_recycler)
    RecyclerView acInformationAimRecycler;

    @BindView(R.id.ac_information_comment_recyclerview)
    RecyclerView acInformationCommentRecyclerview;
    @BindView(R.id.ac_information_tv_miaoguo_number)
    TextView acInformationTvMiaoguoNumber;
    @BindView(R.id.ac_information_tv_comment_number)
    TextView acInformationTvCommentNumber;
    /*
    获取相遇的人
     */
    @BindView(R.id.comment_iv_nomeet)
    ImageView commentIvNomeet;
    @BindView(R.id.comment_iv_meet_head)
    CircleImageView commentIvMeetHead;
    @BindView(R.id.comment_rl_meet)
    AutoRelativeLayout commentRlMeet;


    /*
    评论EditText
     */
    @BindView(R.id.comment_ed_content)
    EmojiconEditText commentEdContent;


    /*
    切换表情
     */
    @BindView(R.id.comment_iv_emoji)
    ImageView commentIvEmoji;

    @BindView(R.id.ac_information_ll)
    AutoLinearLayout acInformationLl;


    /*
    右评RadioButton
     */
    @BindView(R.id.comment_iv_right_rb)
    RadioButton commentIvRightRb;

    /*
    左右评论的父容器
     */
    @BindView(R.id.comment_rb)
    RadioGroup commentRb;


    /*
    上拉加载更多
     */
    @BindView(R.id.relation_refreshLayout)
    RefreshLayout relationRefreshLayout;
    @BindView(R.id.rg_text)
    RadioGroup rgText;

    @BindView(R.id.ac_information_fragment)
    AutoFrameLayout acInformationFragment;
    @BindView(R.id.ac_information_ll_layout)
    AutoLinearLayout acInformationLlLayout;
    @BindView(R.id.tanslation_chekbox)
    CheckBox tanslationChekbox;



    private String contentId;
    private String snapshot;
    private InformationAimAdapter adapter;

    private SuperHanLoginDiglog diglog;

    private int commentType = 1;
    private String type = "all"; // 按时间显示 评论 或者 按 热度显示评论
    private List<CommentInfoBean.DataBean> commentList;
    private CommentItemAdapter commentItemAdapter;
    private String contentUserId;
    private int offset = 1; // 评论数 从第一页
    private AtextBean.DataBean dataBean;


    private EmojiKeyboard emojiKeyboard;


    private String player;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_information_particulars;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorbWhite;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTextColocr();
        ButterKnife.bind(this);
        contentId = getIntent().getStringExtra(BaseNewFragmnet.POSTINFO);
        NetworkData();
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {
        super.initView();
        setBarLeftIcon(true, R.drawable.dingbuback);
        getBarRightIcon().setImageResource(R.drawable.content_jubao);
        getBarRightIcon().setVisibility(View.VISIBLE);
        diglog = new SuperHanLoginDiglog(this);
        rgText.setOnCheckedChangeListener(this);
        commentIvRightRb.setChecked(true);
        commentRb.setOnCheckedChangeListener(this);
        commentEdContent.setOnEditorActionListener(this);
        relationRefreshLayout.setEnableAutoLoadmore(false);
        acInformationCommentRecyclerview.setHasFixedSize(true);
        acInformationCommentRecyclerview.setNestedScrollingEnabled(false);


        /*
        翻译
         */

        tanslationChekbox.setVisibility(View.VISIBLE);
        tanslationChekbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    LogUtils.e("" + isChecked);
                    acInformationWebview.loadUrl("javascript:showTrans()");
                } else {
                    acInformationWebview.loadUrl("javascript:hideTrans()");
                    LogUtils.e("" + isChecked);
                }
            }
        });



        /*
        表情键盘
         */

        emojiKeyboard = new EmojiKeyboard(this, commentEdContent, acInformationFragment, commentIvEmoji, acInformationLl);
        setEmojiconFragment(false);




        /*
        瞄过列表
         */
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (acInformationAimRecycler != null) {
            acInformationAimRecycler.setLayoutManager(manager);
            adapter = new InformationAimAdapter(this);
            acInformationAimRecycler.setAdapter(adapter);
        }

        /*
        评论列表
         */
        commentList = new ArrayList();
        LinearLayoutManager commentManager = new LinearLayoutManager(this);
        commentManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (acInformationCommentRecyclerview != null) {
            acInformationCommentRecyclerview.setLayoutManager(commentManager);
            commentItemAdapter = new CommentItemAdapter(this, commentList, false);
            acInformationCommentRecyclerview.setAdapter(commentItemAdapter);
            acInformationCommentRecyclerview.addItemDecoration(new SpaceItemDecoration(10));
            commentItemAdapter.setOnIikeListener(this);
        }
        relationRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                offset++;
                getCommentIndo(false);
            }
        });


    }





    /*
    一共请求4个接口 都是用 ContentID 去请求
    第一个：资讯详情 发帖用户的基本信息
    第二个：瞄过
    第三个：评论列表 只有 二级评论
    第四个：相遇的人列表


     */

    private void NetworkData() {

        if (contentId == null) {
            return;
        }

        NetworkReuset.getInstance().getInformationData(RequestUrl.INFORMATIONDATA, contentId, new onCallBack<AtextBean>(this) {
            @Override
            public void onSucceed(AtextBean atextBean, Call call, String string) {
                dataBean = atextBean.getData();
                processInfoPartion(dataBean);
            }
        });

        /*
        #define _content_view(content_id,page,snapshot)
          [NSString stringWithFormat:@"%@content_view/v1/%@/?page=%@&snapshot=%@",server,(content_id),(page),(snapshot)]
         */
//        // 接口二 获取瞄过
        HttpParams params = new HttpParams();
        params.put("page", 1);
        params.put("snapshot", "");
        NetworkReuset.getInstance().getPostAimNumber(contentId, params, new onCallBack<InformationAimBean>(this) {
            @Override
            public void onSucceed(InformationAimBean informationAimBean, Call call, String string) {
                InformationAimBean.DataBean data = informationAimBean.getData();
                snapshot = data.getSnapshot();
                List<InformationAimBean.DataBean.UsersBean> users = data.getUsers();
                adapter.setHeadList(users);
                acInformationTvMiaoguoNumber.setText(String.valueOf(users.size()));
            }
        });
        /*
        获取相遇的人
         */
        NetworkReuset.getInstance().getMeetTheMan(String.valueOf(contentId), new onCallBack<MeetTheManBean>(this) {
            @Override
            public void onSucceed(MeetTheManBean meetTheManBean, Call call, String string) {
                List<MeetTheManBean.DataBean> data1 = meetTheManBean.getData();
                if (data1.size() != 0) {
                    MeetTheManBean.DataBean dataBean = data1.get(0);
                    String user_avatar = dataBean.getUser_avatar();
                    commentIvNomeet.setVisibility(View.GONE);
                    commentRlMeet.setVisibility(View.VISIBLE);
                    Picasso.with(InformationParticularsActivity.this).load(user_avatar).into(commentIvMeetHead);
                    //TODO 点击跳转 相遇人列表
                } else {
                    commentIvNomeet.setVisibility(View.VISIBLE);
                    commentRlMeet.setVisibility(View.GONE);
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        getCommentIndo(true);
        LogUtils.e(player);
        acInformationWebview.loadUrl("javascript: (function () {" +
                "                var srcs = '" + player + "';" +
                "                srcs = srcs.split(',');" +
                "                var embeds = document.getElementsByTagName('iframe');" +
                "                for (var i = 0; i < embeds.length; i++) {" +
                "                    embeds[i].src = srcs[i];" +
                "                }" +
                "            return srcs;" +
                "            })()");
    }

    /*
        这里加载 WebView
         */
    private void processInfoPartion(final AtextBean.DataBean data) {

        contentUserId = String.valueOf(data.getContent_publish_user_id());
        /*
        如果 data.getContent_root_tag_img()为"",是兴趣溯源 不为"" 是学校溯源
        根据判断 去显示对应的溯源类型
         */

        final Intent intent = new Intent();
        intent.putExtra(KEY.KEY_SCHOOLID, String.valueOf(data.getContent_root_tag_id()));
        intent.putExtra(KEY.KEY_SCHOOLNAME, data.getContent_root_tag());

        if (data.getContent_root_tag_img().equals("")) {
            newRoots1.setVisibility(View.GONE);
            newRoots2.setVisibility(View.VISIBLE);
            roots2Content.setText(data.getContent_root_tag());
            newRoots2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(InformationParticularsActivity.this,InterestRootsActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            newRoots1.setVisibility(View.VISIBLE);
            newRoots2.setVisibility(View.GONE);
            roots1School.setText(data.getContent_root_tag());
            Picasso.with(this).load(data.getContent_root_tag_img()).into(roots1Head);
            newRoots1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(InformationParticularsActivity.this,SchoolRootsActivity.class);
                    startActivity(intent);
                }
            });
        }










        acInformationTvTitle.setText(data.getContent_title());
        Picasso.with(this).load(data.getContent_publish_user_avatar()).into(acInformationIvHead);
        acInformationTvUser.setText(data.getContent_publish_user_nick());
        acInformationTvTime.setText(TimeUtils.getTimeFormatText(String.valueOf((int) data.getContent_publish_time())));

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

        acInformationWebview.addJavascriptInterface(new JsToJava(), "androidShare");

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
                    AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    acInformationWebview.setLayoutParams(params);

                }
                return true;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });


        if (data.getIs_collection() != 0) {
            acInformationTvRoot.setVisibility(View.GONE);
        }

        /*
        ========= 收藏按钮  ========
         */
        acInformationCollect.setOnCheckedChangeListener(this);
        commentIvCollect.setOnCheckedChangeListener(this);
        if (data.getIs_collection() == 0) {
            acInformationCollect.setChecked(false);
            commentIvCollect.setChecked(false);
        } else {
            acInformationCollect.setChecked(true);
            commentIvCollect.setChecked(true);
        }

    }

    /*
    点击事件
     */

    @OnClick({R.id.tooblar_right_icon, R.id.ac_information_iv_head, R.id.comment_rl_meet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tooblar_right_icon:

                /*
                举报
                 */
                reportContnet();
                break;
            case R.id.ac_information_iv_head:

                /*
                跳转关系卡
                 */
                RelationInfBean bean = new RelationInfBean(1, String.valueOf(dataBean.getContent_publish_user_id()), contentId);
                relationStaActivity(bean);
                break;

            case R.id.comment_rl_meet:

                /*
                跳转相遇的人
                 */

                startActivity(new Intent(this, MeetActivity.class).putExtra("contentID", contentId));
                break;
        }
    }


    // TODO 这个位置 需要网络请求 举报内容
    private void reportContnet() {

        String[] items = {"举报内容问题", "广告", "重复 旧闻", "低俗色情", "违法犯罪", "标题夸张", "与事实不符", "内容质量差", "疑似抄袭", "其他问题，我要吐槽"};
        final BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        bottomMenuFragment.setItems(items);
        bottomMenuFragment.show(getFragmentManager(), "BottomMenuFragment");
        bottomMenuFragment.setListenerItem(new BottomMenuFragment.DialogListenerItem() {
            @Override
            public void listenerItem(String s) {


            }
        });


    }

    @JavascriptInterface
    public void onSum(String result) {
        ToastUtil.showNormalToast(this, result);
    }

    /*
    评论左边右边的监听
     */
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        //TODO 这个位置 改变 EditText文本方向 靠左靠右?
        switch (checkedId) {
            case R.id.comment_iv_left_rb:
                commentType = 0;


                break;
            case R.id.comment_iv_right_rb:
                commentType = 1;


                break;

            case R.id.rb_text_time:
                type = "all";
                getCommentIndo(true);
                break;


            case R.id.rb_text_redu:
                type = "hot";
                getCommentIndo(true);
                break;


        }

    }

    /*
    发送评论
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            String content = commentEdContent.getText().toString().trim();
            LogUtils.e(KEY.TAG + content);
            sendCommentContent(content);
            diglog.show();
        }
        return false;

    }

    private void sendCommentContent(String content) {
        HttpParams params = new HttpParams();
        params.put("is_first", 1);
        params.put("content_id", contentId);
        params.put("comment_direction", commentType);
        params.put("comment_content", content);
        params.put("content_publish_user_id", contentUserId);

        NetworkReuset.getInstance().PostReuset(RequestUrl.COMMENTCONTENT, params, new onCallBack<CommentContentBean>(this) {

            @Override
            public void onSucceed(CommentContentBean commentContentBean, Call call, String string) {
                    /*
                    评论 commentContentBean
                     */
                if (commentContentBean.getCode() == 200) {
                    /*
                    手动往里面添加一条评论
                     */
                    List<CommentInfoBean.DataBean> data = new ArrayList<CommentInfoBean.DataBean>();
                    CommentInfoBean.DataBean bean = new CommentInfoBean.DataBean();
                    CommentContentBean.DataBean commentData = commentContentBean.getData();
                    /*
                    赋值
                     */
                    bean.setComment_content(commentData.getComment_content());
                    bean.setComment_direction(commentData.getComment_direction());
                    bean.setComment_id(commentData.getComment_id());
                    bean.setComment_school(commentData.getComment_school());
                    bean.setComment_time(commentData.getComment_time());
                    bean.setComment_user(commentData.getComment_user());
                    bean.setComment_user_avatar(commentData.getComment_user_avatar());
                    bean.setComment_user_nick(commentData.getComment_user_nick());
                    bean.setHot_cnt(commentData.getHot_cnt());
                    bean.setLike_cnt(commentData.getLike_cnt());
                    bean.setComment_evaluation("");
                    data.add(bean);
                    commentItemAdapter.setData(data);
                    commentEdContent.setText("");
                }
                diglog.dismiss();
            }
        });
    }

    /*
    收藏接口
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        HttpParams params = new HttpParams();

        if (!isChecked) {
            acInformationCollect.setChecked(false);
            commentIvCollect.setChecked(false);
            params.clear();
            params.put("content_id", contentId);
            NetworkReuset.getInstance().cancelCollcetion(params, new onCallBack(this) {
                @Override
                public void onSucceed(Object o, Call call, String string) {
                }
            });
        } else {
            acInformationCollect.setChecked(true);
            commentIvCollect.setChecked(true);
            params.clear();
            params.put("action", "collect");
            NetworkReuset.getInstance().addCollcettion(String.valueOf(contentId), params, new onCallBack(this) {
                @Override
                public void onSucceed(Object o, Call call, String string) {
                }
            });
        }

    }

    @Override
    public void onIikeListener(List<ImageButton> buttons, int type, String commentId, String commentUserId, TextView tvNumber) {
        if (type == 0) {
            /*
            点赞
             */
            commentLike(buttons, commentId, "like", commentUserId, tvNumber);
        }
        if (type == 1) {
            /*
            踩
             */
            commentLike(buttons, commentId, "unlike", commentUserId, tvNumber);
        }
    }

    private void commentLike(final List<ImageButton> buttons, String commentID, final String type, String commentUserId, final TextView tvNumber) {
        HttpParams params = new HttpParams();
        params.put("action", "evaluation");
        params.put("type", type);
        params.put("content_id", contentId);
        params.put("comment_user_id", commentUserId);

        NetworkReuset.getInstance().commentLike(commentID, params, new onCallBack<LikeBean>(this) {
            @Override
            public void onSucceed(LikeBean codeBean, Call call, String string) {
                if (codeBean.getCode() == 200) {
                    // 点赞 踩赞 成功
                    new SuperHanDialog(InformationParticularsActivity.this, codeBean.getMsg()).show();
                    tvNumber.setText(String.valueOf(codeBean.getData().getLike_cnt()));
                    ImageButton imageButton = buttons.get(0);
                    ImageButton imageButton1 = buttons.get(1);
                    if (type.equals("like")) {
                        imageButton.setBackgroundResource(R.drawable.zan_h);
                        imageButton1.setBackgroundResource(R.drawable.cai_n);
                    } else {
                        imageButton.setBackgroundResource(R.drawable.zan_n);
                        imageButton1.setBackgroundResource(R.drawable.cai_h);
                    }
                } else {
                    new SuperHanDialog(InformationParticularsActivity.this, codeBean.getMsg()).show();
                }
            }
        });
    }


    //接口三 评论信息
    private void getCommentIndo(final boolean isRefresh) {
        HttpParams commentParams = new HttpParams();
        commentParams.put("type", type); // 参数["all", 'hot', 'relation']
        commentParams.put("limit", 10);  // 参数 limit: 10 # 取的条数
        commentParams.put("offset", offset);  //参数 offset: 10 # 从第几条开始取
        NetworkReuset.getInstance().getCommentData(contentId, commentParams, new onCallBack<CommentInfoBean>(this) {
            @Override
            public void onSucceed(CommentInfoBean commentInfoBean, Call call, String string) {
                List<CommentInfoBean.DataBean> data = commentInfoBean.getData();

                if (isRefresh) {

                    commentItemAdapter.setNewData(data);
                } else {
//
//                    LogUtils.e("------加载更多的");
//                    if (data.size()==0){
//                        offset = 1;
//                    }

                    commentItemAdapter.setLoadMoreData(data);
                    relationRefreshLayout.finishLoadmore();
                }

                acInformationTvCommentNumber.setText(String.valueOf(data.size()));

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (!emojiKeyboard.interceptBackPress()) {
            super.onBackPressed();
        }
    }


    private void setEmojiconFragment(boolean useSystemDefault) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ac_information_fragment, EmojiconsFragment.newInstance(useSystemDefault))
                .commit();

    }


    @Override
    public void onEmojiconClicked(Emojicon emojicon) {
        EmojiconsFragment.input(commentEdContent, emojicon);
    }


    @Override
    public void onEmojiconBackspaceClicked(View v) {
        EmojiconsFragment.backspace(commentEdContent);
    }


    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.e("页面不可见");
        acInformationWebview.loadUrl("javascript:(function () {" +
                "    var embeds = document.getElementsByTagName('iframe');" +
                "    var srcs = [];" +
                "        for (var i = 0; i < embeds.length; i++) {" +
                "            srcs.push(embeds[i].src);" +
                "            embeds[i].src = '';" +
                "        }" +
                "   window.androidShare.jsMethod(srcs.join(',') )})()");

    }


    private class JsToJava {

        //这里需要加@JavascriptInterface，4.2之后提供给javascript调用的函数必须带有@JavascriptInterface
        @JavascriptInterface
        public void jsMethod(String paramFromJS) {
            /*
            这里记住 js返回的字符串
             */

            LogUtils.e("视频地址"+paramFromJS);
            player = paramFromJS;
        }
    }


}

