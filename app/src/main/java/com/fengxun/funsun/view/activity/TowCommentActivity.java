package com.fengxun.funsun.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.AtextBean;
import com.fengxun.funsun.model.bean.CommentContentBean;
import com.fengxun.funsun.model.bean.CommentInfoBean;
import com.fengxun.funsun.model.bean.LikeBean;
import com.fengxun.funsun.model.bean.MeetTheManBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.eventbus.TowCommentEventBus;
import com.fengxun.funsun.model.listener.OnIikeListener;
import com.fengxun.funsun.model.listener.OnTowCommentBeanListener;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.utils.Util;
import com.fengxun.funsun.view.adapter.CommentItemAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.EmojiKeyboard;
import com.fengxun.funsun.view.views.RecyclerViewNoBugLinearLayoutManager;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;
import com.lzy.okgo.model.HttpParams;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
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
 * 创建日期：on 2017/12/28.
 * Holle Android
 */

public class TowCommentActivity extends BaseActivity implements EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener, TextView.OnEditorActionListener, RadioGroup.OnCheckedChangeListener, OnIikeListener, OnTowCommentBeanListener, CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener {


    @BindView(R.id.ac_tow_comment_recyclerview)
    RecyclerView acTowCommentRecyclerview; //评论RecyclerView

    @BindView(R.id.comment_ed_content)
    EmojiconEditText commentEdContent; // 评论EditText

    @BindView(R.id.comment_iv_emoji) //表情icon
            ImageView commentIvEmoji;

    @BindView(R.id.ac_towcomment_head)
    CircleImageView acTowcommentHead;
    @BindView(R.id.ac_tow_comment_name)
    TextView acTowCommentName;
    @BindView(R.id.ac_tow_comment_item_left_btn_zan)
    ImageButton acTowCommentItemLeftBtnZan;
    @BindView(R.id.ac_tow_comment_item_left_number)
    TextView acTowCommentItemLeftNumber;
    @BindView(R.id.ac_tow_comment_item_left_btn_cai)
    ImageButton commentItemLeftBtnCai;
    @BindView(R.id.ac_tow_tv_bconmm_content)
    TextView acTowTvBconmmContent;
    @BindView(R.id.ac_tow_tv_time)
    TextView acTowTvTime;

    @BindView(R.id.comment_iv_right_rb)
    RadioButton commentIvRightRb;
    @BindView(R.id.comment_rb)
    RadioGroup commentRb;
    @BindView(R.id.comment_iv_nomeet)
    ImageView commentIvNomeet;
    @BindView(R.id.comment_iv_meet_head)
    CircleImageView commentIvMeetHead;
    @BindView(R.id.comment_rl_meet)
    AutoRelativeLayout commentRlMeet;
    @BindView(R.id.tow_fragment)
    AutoFrameLayout acInformationFragment;

    @BindView(R.id.ac_tow_content_al)
    AutoLinearLayout acTowContentAl;
    @BindView(R.id.comment_iv_collect)
    CheckBox commentIvCollect;

    /*
    评论区控件
     */


    private CommentInfoBean.DataBean commentBean;
    private AtextBean.DataBean dataBean;
    private EmojiKeyboard emojiKeyboard;
    private SuperHanLoginDiglog diglog;


    private CustomShareListener mShareListener;
    private UMImage imageurl;
    private ShareAction mShareAction;


    private int commentType;
    private AtextBean.DataBean.ShareDataBean share_data;
    private CommentItemAdapter adapter;
    private List<ImageButton> buttons;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_tow_comment;
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
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        super.initView();
        setStatusBarTextColocr();
        setBarLeftIcon(true, R.drawable.dingbuback);
        diglog = new SuperHanLoginDiglog(this);
        commentRb.setOnCheckedChangeListener(this);
        commentIvRightRb.setChecked(true);

        List<CommentInfoBean.DataBean> list = new ArrayList();
        RecyclerViewNoBugLinearLayoutManager manager = new RecyclerViewNoBugLinearLayoutManager(this);

        manager.setOrientation(LinearLayoutManager.VERTICAL);
        acTowCommentRecyclerview.setLayoutManager(manager);
        adapter = new CommentItemAdapter(this, list, false);
        acTowCommentRecyclerview.setAdapter(adapter);

        adapter.setOnIikeListener(this);
        adapter.setCommentItem(this);

        commentEdContent.setOnEditorActionListener(this);
        commentEdContent.setOnFocusChangeListener(this);
        commentIvCollect.setOnCheckedChangeListener(this);
        emojiKeyboard = new EmojiKeyboard(this, commentEdContent, acInformationFragment, commentIvEmoji, acTowContentAl);
        setEmojiconFragment(false);
        getMeetRen();

    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getInformationParticarsEventBus(TowCommentEventBus bus) {
        commentBean = bus.getCommentBean();
        dataBean = bus.getDataBean();
        setTvTitle(dataBean.getContent_title(), R.color.colorbBlack);
        Picasso.with(this).load(commentBean.getComment_user_avatar()).into(acTowcommentHead);
        acTowCommentName.setText(commentBean.getComment_user_nick());
        acTowTvBconmmContent.setText(commentBean.getComment_content());
        acTowTvTime.setText(TimeUtils.getTimeFormatText(String.valueOf((int) commentBean.getComment_time())));

        buttons = new ArrayList<>();
        if (commentBean.getComment_evaluation() != null) {
            if (commentBean.getComment_evaluation().equals("like")) {
                acTowCommentItemLeftBtnZan.setBackgroundResource(R.drawable.zan_h);
            } else if (commentBean.getComment_evaluation().equals("unlike")) {
                commentItemLeftBtnCai.setBackgroundResource(R.drawable.cai_h);
            }
            acTowCommentItemLeftNumber.setText(commentBean.getLike_cnt() + "");
        }


        if (dataBean.getIs_collection() == 0) {

            commentIvCollect.setChecked(false);
        } else {

            commentIvCollect.setChecked(true);
        }


        buttons.add(acTowCommentItemLeftBtnZan);
        buttons.add(commentItemLeftBtnCai);


        share_data = dataBean.getShare_data();
        if (share_data != null) {
            imageurl = new UMImage(this, share_data.getShare_img());
            imageurl.setThumb(new UMImage(this, share_data.getShare_img()));
            mShareListener = new CustomShareListener(this);
            mShareAction = new ShareAction(this).setDisplayList(
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                    SHARE_MEDIA.SINA)
                    .setShareboardclickCallback(new ShareBoardlistener() {
                        @Override
                        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                            if (share_media == SHARE_MEDIA.SINA) {
                                new ShareAction(TowCommentActivity.this).withText(Util.wbDynamic(share_data.getShare_title(), share_data.getShare_url()))
                                        .withMedia(imageurl)
                                        .setPlatform(share_media)
                                        .setCallback(mShareListener).share();
                            } else {
                                UMWeb web = new UMWeb(share_data.getShare_url());
                                web.setTitle(share_data.getShare_title());
                                web.setDescription(share_data.getShare_content());
                                web.setThumb(new UMImage(TowCommentActivity.this, share_data.getShare_img()));
                                new ShareAction(TowCommentActivity.this).withMedia(web)
                                        .setPlatform(share_media)
                                        .setCallback(mShareListener)
                                        .share();
                            }

                        }
                    });
        }

        NetworkData();
    }

    /*
    现在做 详情页的二级评论 然后 进来查询
     */

    private void NetworkData() {
        HttpParams pparams = new HttpParams();
        NetworkReuset.getInstance().getTowCommentItem(commentBean.getComment_id() + "", pparams, new onCallBack<CommentInfoBean>(this) {
            @Override
            public void onSucceed(CommentInfoBean towCommentSuccessfulBean, Call call, String string) {
                List<CommentInfoBean.DataBean> data = towCommentSuccessfulBean.getData();

                LogUtils.d("二级评论" + data.size());
                adapter.setData(data);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.comment_rl_meet, R.id.comment_iv_sharing, R.id.ac_tow_comment_item_left_btn_zan, R.id.ac_tow_comment_item_left_btn_cai,R.id.ac_towcomment_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.comment_rl_meet:
                /*
                点击相遇的人

                 */
                startActivity(new Intent(this, MeetActivity.class).putExtra("contentID", dataBean.getContent_id()));
                break;
            case R.id.comment_iv_sharing:
                /*
                点击分享 is登录
                 */
                if (mShareAction != null) {
                    mShareAction.open();
                } else {
                    new SuperHanDialog(this, "请登录").show();
                }

                break;

            case R.id.ac_tow_comment_item_left_btn_zan:
                /*
                点赞
                 */
                commentLike(buttons, commentBean.getComment_id() + "", "like", commentBean.getComment_user() + "", acTowCommentItemLeftNumber);

                break;

            case R.id.ac_tow_comment_item_left_btn_cai:
                /*
                踩赞
                 */
                commentLike(buttons, commentBean.getComment_id() + "", "unlike", commentBean.getComment_user() + "", acTowCommentItemLeftNumber);

                break;

            case R.id.ac_towcomment_head:
                RelationInfBean bean = new RelationInfBean(1, commentBean.getComment_user()+"", dataBean.getContent_id() + "");
                Intent intent = new Intent(this, RelationCalorieActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
                intent.putExtras(mBundle);
                startActivity(intent);
                break;




        }
    }


    public void setEmojiconFragment(boolean useSystemDefault) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.tow_fragment, EmojiconsFragment.newInstance(useSystemDefault))
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
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            String content = commentEdContent.getText().toString().trim();
            LogUtils.e(KEY.TAG + content);
            sendCommentContent(content);
            diglog.show();
        }
        return false;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.comment_iv_left_rb:
                commentType = 0;

                break;
            case R.id.comment_iv_right_rb:
                commentType = 1;
                break;
        }
    }

    private void sendCommentContent(String content) {
        HttpParams params = new HttpParams();
        params.put("is_first", 0);
        params.put("content_id", dataBean.getContent_id());
        params.put("comment_direction", commentType);
        params.put("comment_content", content);
        params.put("content_publish_user_id", dataBean.getContent_publish_user_id() + "");
        params.put("parent_comment_id", commentBean.getComment_id());
        params.put("parent_comment_content", commentBean.getComment_content());
        params.put("parent_comment_user_id", commentBean.getComment_user() + "");
        NetworkReuset.getInstance().PostReuset(RequestUrl.COMMENTCONTENT, params, new onCallBack<CommentContentBean>(this) {
            @Override
            public void onSucceed(CommentContentBean commentContentBean, Call call, String string) {
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
                bean.setLatest_child_user_avatar("");
                data.add(bean);
                adapter.setData(data);
                commentEdContent.setText("");
                diglog.dismiss();
            }
        });
    }


    /*
    交互区
    一级评论点赞 踩
    二级评论的点赞 踩
    二级评论点击头像进入关系卡
    收藏帖子
    相遇的人
     */


    private void commentLike(final List<ImageButton> buttons, String commentID, final String type, String commentUserId, final TextView tvNumber) {
        if (!SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)){
            new SuperHanDialog(this,"请先登录").show();
            return;
        }

        HttpParams params = new HttpParams();
        params.put("action", "evaluation");
        params.put("type", type);
        params.put("content_id", dataBean.getContent_id());
        params.put("comment_user_id", commentUserId);

        NetworkReuset.getInstance().commentLike(commentID, params, new onCallBack<LikeBean>(this) {
            @Override
            public void onSucceed(LikeBean codeBean, Call call, String string) {
                if (codeBean.getCode() == 200) {
                    // 点赞 踩赞 成功
                    new SuperHanDialog(TowCommentActivity.this, codeBean.getMsg()).show();
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
                    new SuperHanDialog(TowCommentActivity.this, codeBean.getMsg()).show();
                }
            }
        });
    }

    @Override
    public void onIikeListener(List<ImageButton> buttons, int type, String commentId, String commentUserID, TextView tvNumber) {
        if (type == 0) {
            /*
            点赞
             */
            commentLike(buttons, commentId, "like", commentBean.getComment_user() + "", tvNumber);
        }
        if (type == 1) {
            /*
            踩
             */
            commentLike(buttons, commentId, "unlike", commentBean.getComment_user() + "", tvNumber);
        }
    }

    /*
    这是 一级评论进入二级评论的 回调 此页面不需要 空实现
     */
    @Override
    public void onTowCommentBeanListenr(CommentInfoBean.DataBean commentBean) {

    }

    /*
    点击头像进入关系卡
     */
    @Override
    public void onCommentRelationUserId(String userId) {
        RelationInfBean bean = new RelationInfBean(1, userId, dataBean.getContent_id() + "");
        Intent intent = new Intent(this, RelationCalorieActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
        intent.putExtras(mBundle);
        startActivity(intent);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)){
            new SuperHanDialog(this,"请先登录").show();
            commentIvCollect.setChecked(false);
            return;
        }
        HttpParams params = new HttpParams();
        if (!isChecked) {
            commentIvCollect.setChecked(false);
            params.clear();
            params.put("content_id", dataBean.getContent_id());
            NetworkReuset.getInstance().cancelCollcetion(params, new onCallBack(this) {
                @Override
                public void onSucceed(Object o, Call call, String string) {
                    ToastUtil.massageToast(TowCommentActivity.this,"取消收藏");
                }
            });
        }else {
            commentIvCollect.setChecked(true);
            params.clear();
            params.put("action", "collect");
            NetworkReuset.getInstance().addCollcettion(String.valueOf(dataBean.getContent_id()), params, new onCallBack(this) {
                @Override
                public void onSucceed(Object o, Call call, String string) {
                    ToastUtil.massageToast(TowCommentActivity.this,"收藏成功");
                }
            });
        }

    }



    private void getMeetRen(){
         /*
        获取相遇的人
         */
        NetworkReuset.getInstance().getMeetTheMan(String.valueOf(dataBean.getContent_id()), new onCallBack<MeetTheManBean>(this) {
            @Override
            public void onSucceed(MeetTheManBean meetTheManBean, Call call, String string) {
                List<MeetTheManBean.DataBean> data1 = meetTheManBean.getData();
                if (data1==null){
                    return;
                }
                if (data1.size() != 0) {
                    MeetTheManBean.DataBean dataBean = data1.get(0);
                    String user_avatar = dataBean.getUser_avatar();
                    commentIvNomeet.setVisibility(View.GONE);
                    commentRlMeet.setVisibility(View.VISIBLE);
                    Picasso.with(TowCommentActivity.this).load(user_avatar).into(commentIvMeetHead);
                    //TODO 点击跳转 相遇人列表
                } else {
                    commentIvNomeet.setVisibility(View.VISIBLE);
                    commentRlMeet.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            if (!SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)){
                new SuperHanDialog(this,"请先登录").show();
                commentEdContent.clearFocus();
                return;
            }
        }
    }


    private static class CustomShareListener implements UMShareListener {

        private WeakReference<TowCommentActivity> mActivity;

        private CustomShareListener(TowCommentActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                if (t != null) {
                    Log.d("throw", "throw:" + t.getMessage());
                }
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }
}
