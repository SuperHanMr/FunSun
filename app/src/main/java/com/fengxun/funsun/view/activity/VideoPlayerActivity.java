package com.fengxun.funsun.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CommentContentBean;
import com.fengxun.funsun.model.bean.GetCommentContentBean;
import com.fengxun.funsun.model.bean.MeetTheManBean;
import com.fengxun.funsun.model.bean.RelationInfBean;
import com.fengxun.funsun.model.bean.RoostBean;
import com.fengxun.funsun.model.bean.VideoBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.listener.OnVideoPlayerTime;
import com.fengxun.funsun.model.listener.SampleListener;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.utils.Util;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.EmojiKeyboard;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;
import com.fengxun.funsun.view.views.barrage.Barrage;
import com.fengxun.funsun.view.views.barrage.BarrageView;
import com.fengxun.funsun.view.views.video.LandLayoutVideo;
import com.lzy.okgo.model.HttpParams;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
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
 * 创建日期：on 2017/11/300.
 * Holle Android
 * 内容：播放视频类 测试
 */

public class VideoPlayerActivity extends AppCompatActivity implements TextView.OnEditorActionListener, CompoundButton.OnCheckedChangeListener, OnVideoPlayerTime
,EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener {

    @BindView(R.id.post_detail_nested_scroll)
    NestedScrollView postDetailNestedScroll;

    @BindView(R.id.detail_player)
    LandLayoutVideo detailPlayer;
    @BindView(R.id.video_ll)
    AutoLinearLayout activityDetailPlayer;


    @BindView(R.id.video_user_head)
    CircleImageView videoUserHead;
    @BindView(R.id.video_user_tv_name)
    TextView videoUserTvName;
    @BindView(R.id.video_user_tv_title)
    TextView videoUserTvTitle;
    @BindView(R.id.video_user_tv_time)
    TextView videoUserTvTime;
    @BindView(R.id.roots1_head)


     /*
      学校溯源
        */
            CircleImageView roots1Head;
    @BindView(R.id.roots1_school)
    TextView rootsSchoolName;
    @BindView(R.id.new_roots1)
    AutoRelativeLayout newRoots1;

    /*
    兴趣溯源
     */
    @BindView(R.id.roots2_content)
    TextView roots2Content;
    @BindView(R.id.new_roots2)
    AutoRelativeLayout newRoots2;
    @BindView(R.id.video_user_barrage)
    BarrageView videoUserBarrage;

    /*
    =========================评论区控件========================
     */
    @BindView(R.id.comment_iv_emoji)
    ImageView commentIvEmoji; // emoji表情

    @BindView(R.id.comment_iv_collect)
    CheckBox commentIvCollect; // 收藏chebox

    @BindView(R.id.comment_iv_right_rb)
    RadioButton commentIvRightRb; // 右边的吐槽 一进来选中

    @BindView(R.id.comment_iv_nomeet)
    ImageView commentIvNomeet; // 如果就有相遇的 隐藏 默认显示

    @BindView(R.id.comment_iv_meet_head)
    CircleImageView commentIvMeetHead; //相遇的人头像

    @BindView(R.id.comment_rl_meet)
    AutoRelativeLayout commentRlMeet; // 相遇的人布局

    @BindView(R.id.comment_iv_sharing) //分享icon
     ImageView commentIvSharing;

    @BindView(R.id.comment_rb) //左边右边评论按钮
            RadioGroup commentRb;
    @BindView(R.id.comment_ed_content)
    EmojiconEditText commentEdContent;
    @BindView(R.id.ac_information_fragment)
    AutoFrameLayout acInformationFragment;


    private int commentType;
    private boolean isPlay;
    private boolean isPause;


    /*
    视频旋转管理类
     */


    private OrientationUtils orientationUtils;
    private List<Barrage> mBarrages = new ArrayList<>();
    private int videoId;
    private int content_publish_user_id;
    private SuperHanLoginDiglog diglog;
    private int collection;
    private VideoBean.DataBean videoBeanData;
    private TextView texZimu;
    private EmojiKeyboard emojiKeyboard;
    private CustomShareListener mShareListener;
    private UMImage imageurl;
    private ShareAction mShareAction;
    private VideoBean.DataBean.ShareDataBean share_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_player);
        ButterKnife.bind(this);

        /*
        =======================获取跳转过来的视频信息=======================
         */
        VideoInfoBean bean = (VideoInfoBean) getIntent().getSerializableExtra(BaseNewFragmnet.VIDEOINFO);
        String url = bean.videoURL;
        String videoCover = bean.videoCover;
        videoId = bean.videoID;

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(this).load(videoCover).into(imageView);
        //detailPlayer.setThumbImageView(imageView);
        resolveNormalVideoUI();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);






        /*
        =============================配置播放器==========================
         */

        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1)
                .setUrl(url)
                .setCacheWithPlay(false)
                .setStandardVideoAllCallBack(new SampleListener() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        Debuger.printfError("***** onPrepared **** " + objects[0]);
                        Debuger.printfError("***** onPrepared **** " + objects[1]);
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }


                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);

                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            //配合下方的onConfigurationChanged
                            orientationUtils.setEnable(!lock);
                        }
                    }
                }).build(detailPlayer);


        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(VideoPlayerActivity.this, true, true);
            }
        });

        /*
        关闭播放器
         */
        detailPlayer.findViewById(R.id.video_iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailPlayer.findViewById(R.id.video_iv_jvbao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)){
                    new SuperHanDialog(VideoPlayerActivity.this, "请登录").show();
                    return;
                }

                ToastUtil.showNormalToast(VideoPlayerActivity.this, "点击了举报");
            }
        });

        texZimu = (TextView) detailPlayer.findViewById(R.id.video_zimu_text);

        detailPlayer.setOnVideoPlayerTimeListener(this);

        detailPlayer.startPlayLogic();

        emojiKeyboard = new EmojiKeyboard(this, commentEdContent, acInformationFragment, commentIvEmoji, activityDetailPlayer);
        setEmojiconFragment(false);

        /*
        这里 联网请求视频详细信息
        title 发布视频用户头像和名字  发布时间 溯源的展示 以及评论弹幕
         */
        NetworkReuset.getInstance().getVideoData(String.valueOf(videoId), new onCallBack<VideoBean>(this) {
            @Override
            public void onSucceed(VideoBean videoBean, Call call, String string) {
                videoBeanData = videoBean.getData();
                content_publish_user_id = videoBeanData.getContent_publish_user_id();
                initView(videoBeanData);
            }
        });

    }


    /**
     * @param view
     */
    /*
    屏幕的点击事件
     */
    @OnClick({R.id.ac_video_zimufanyi, R.id.ac_video_zimu,R.id.comment_iv_sharing,R.id.comment_rl_meet,R.id.new_roots1,R.id.new_roots2,R.id.video_user_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_video_zimufanyi:

                break;
            case R.id.ac_video_zimu:

                break;
            case R.id.comment_iv_sharing:
                if (mShareAction != null) {
                    mShareAction.open();
                } else {
                    new SuperHanDialog(this, "请登录").show();
                }
                break;

            case R.id.comment_rl_meet:
                startActivity(new Intent(this, MeetActivity.class).putExtra("contentID",videoId+""));
                break;

            case R.id.new_roots1:
                startActivity(new Intent(this,SchoolRootsActivity.class)
                        .putExtra(KEY.KEY_SCHOOLNAME,videoBeanData.getContent_root_tag())
                        .putExtra(KEY.KEY_SCHOOLID,videoBeanData.getContent_root_tag_id()+""));
                break;
            case R.id.new_roots2:
                startActivity(new Intent(this,InterestRootsActivity.class)
                        .putExtra(KEY.KEY_SCHOOLNAME,videoBeanData.getContent_root_tag())
                        .putExtra(KEY.KEY_SCHOOLID,videoBeanData.getContent_root_tag_id()+""));
                break;

            case R.id.video_user_head:
                RelationInfBean bean = new RelationInfBean(1,videoBeanData.getContent_publish_user_id()+"",videoBeanData.getContent_id()+"");
                Intent intent = new Intent(this, RelationCalorieActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(BaseNewFragmnet.RELATION, bean);
                intent.putExtras(mBundle);
                startActivity(intent);
                break;

        }
    }

       /*
    播放时间 实时监听
     */

    @Override
    public void onVideoPlayerTimeListener(int videoPlayerTime) {

        if (videoBeanData==null){
            return;
        }
        VideoBean.DataBean.VedioWordZhCnBean vedioWordZhCn =
                videoBeanData.getVedio_word_zh_cn();


        if (vedioWordZhCn == null || videoBeanData == null) {
            return;
        }

        List<Integer> beginTime = vedioWordZhCn.getBeginTime();
        List<Integer> endTime = vedioWordZhCn.getEndTime();
        List<String> text = vedioWordZhCn.getText();

        for (int i = 0; i < beginTime.size(); i++) {
            if (videoPlayerTime > beginTime.get(i) && videoPlayerTime < endTime.get(i)) {
                texZimu.setText(text.get(i));

                //TODO 这个位置 要做横屏字幕
//                TextView textView = (TextView) detailPlayer.findViewById(R.id.video_tv_hengxiang);
//                if (textView!=null){
//                    textView.setText(text.get(i));
//                    LogUtils.e(textView.getText().toString());
//                }
//                LogUtils.e(textView+"****");

            }
        }

    }


    /*
    进来开始播放视频 详情信息 在后面进行初始化
     */
    private void initView(final VideoBean.DataBean data) {

        diglog = new SuperHanLoginDiglog(this);





        /*
        视频的基本信息
         */
        Picasso.with(this).load(data.getContent_publish_user_avatar()).into(videoUserHead);
        videoUserTvTitle.setText(data.getContent_title());
        videoUserTvName.setText(data.getContent_publish_user_nick());
        int content_publish_time = (int) data.getContent_publish_time();
        videoUserTvTime.setText(TimeUtils.getTimeFormatText(String.valueOf(content_publish_time)));


        /*
        视频的溯源
         */

        if (data.getContent_root_cnt() == 0) {
            // 等于0说明是 兴趣溯源否则就是学校溯源
            newRoots1.setVisibility(View.GONE);
            newRoots2.setVisibility(View.VISIBLE);
            roots2Content.setText(data.getContent_root_tag());


        } else {
            newRoots1.setVisibility(View.VISIBLE);
            newRoots2.setVisibility(View.GONE);
            Picasso.with(this).load(data.getContent_root_tag_img()).into(roots1Head);
            rootsSchoolName.setText(data.getContent_root_tag());


        }

        commentIvRightRb.setChecked(true);
        commentRb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.comment_iv_left_rb:
                        commentType = 1;
                        break;
                    case R.id.comment_iv_right_rb:
                        commentType = 0;
                        break;
                }
            }
        });


        /*
        是否收藏
         */
        commentIvCollect.setOnCheckedChangeListener(this);
        collection = data.getIs_collection();
        if (collection == 0) {
            commentIvCollect.setChecked(false);
        } else {
            commentIvCollect.setChecked(true);
        }



        commentEdContent.setOnEditorActionListener(this);

        NetworkReuset.getInstance().getCommentContent(String.valueOf(videoId), new onCallBack<GetCommentContentBean>(this) {
            @Override
            public void onSucceed(GetCommentContentBean getCommentContentBean, Call call, String string) {
                List<GetCommentContentBean.DataBean> data1 = getCommentContentBean.getData();
                for (int i = 0; i < data1.size(); i++) {
                    String comment_content = data1.get(i).getComment_content();
                    int comment_direction = data1.get(i).getComment_direction();
                    mBarrages.add(new Barrage(comment_content, comment_direction));
                    videoUserBarrage.setBarrages(mBarrages);
                }
            }
        });



        /*
        获取相遇的人
         */
        NetworkReuset.getInstance().getMeetTheMan(String.valueOf(videoId), new onCallBack<MeetTheManBean>(this) {
            @Override
            public void onSucceed(MeetTheManBean meetTheManBean, Call call, String string) {
                List<MeetTheManBean.DataBean> data1 = meetTheManBean.getData();
                if (data1!=null){
                    if (data1.size() != 0) {
                        MeetTheManBean.DataBean dataBean = data1.get(0);
                        String user_avatar = dataBean.getUser_avatar();
                        commentIvNomeet.setVisibility(View.GONE);
                        commentRlMeet.setVisibility(View.VISIBLE);
                        Picasso.with(VideoPlayerActivity.this).load(user_avatar).into(commentIvMeetHead);
                        //TODO
                    } else {
                        commentIvNomeet.setVisibility(View.VISIBLE);
                        commentRlMeet.setVisibility(View.GONE);
                    }
                }

            }
        });


        /*
        分享
         */

        share_data = data.getShare_data();
        if (share_data!=null){
            imageurl = new UMImage(this, share_data.getShare_img());
            imageurl.setThumb(new UMImage(this, share_data.getShare_img()));
            mShareListener = new CustomShareListener(this);
            mShareAction = new ShareAction(this).setDisplayList(
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                    SHARE_MEDIA.SINA)
                    .setShareboardclickCallback(new ShareBoardlistener() {
                        @Override
                        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                            if (share_media==SHARE_MEDIA.SINA){
                                new ShareAction(VideoPlayerActivity.this).withText(Util.wbDynamic(share_data.getShare_title(), share_data.getShare_url()))
                                        .withMedia(imageurl)
                                        .setPlatform(share_media)
                                        .setCallback(mShareListener).share();
                            }else {
                                UMWeb web = new UMWeb(share_data.getShare_url());
                                web.setTitle(share_data.getShare_title());
                                web.setDescription(share_data.getShare_content());
                                web.setThumb(new UMImage(VideoPlayerActivity.this, share_data.getShare_img()));
                                new ShareAction(VideoPlayerActivity.this).withMedia(web)
                                        .setPlatform(share_media)
                                        .setCallback(mShareListener)
                                        .share();
                            }

                        }
                    });
        }

    }


    /*
       键盘发送弹幕按钮监听
        */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            if (!SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)){
                new SuperHanDialog(this,"请先登录").show();
                return false;
            }

            String content = commentEdContent.getText().toString().trim();
            sendCommentContent(content);
            diglog.show();

        }
        return false;
    }


    /*
    发送评论
     */
    private void sendCommentContent(final String content) {
        HttpParams params = new HttpParams();
        params.put("is_first", 1);
        params.put("content_id", videoId);
        params.put("comment_direction", commentType);
        params.put("comment_content", content);
        params.put("content_publish_user_id", content_publish_user_id);
        NetworkReuset.getInstance().PostReuset(RequestUrl.COMMENTCONTENT, params, new onCallBack<CommentContentBean>(this) {
            @Override
            public void onSucceed(CommentContentBean commentContentBean, Call call, String string) {
                /*
                评论 commentContentBean
                 */
                if (commentContentBean.getCode() == 200) {
                    videoUserBarrage.addBarrage(new Barrage(content, commentType));
                    commentEdContent.setText("");
                }
                diglog.dismiss();

            }
        });
    }

    /*
    评论区域的点击事件
     */


    /*
    ====================播放视频的生命周期的配=======================
     */

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }

        if (!emojiKeyboard.interceptBackPress()) {
            super.onBackPressed();
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
        isPause = false;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoUserBarrage.destroy();
        if (isPlay) {
            getCurPlay().release();
        }
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(this, newConfig, orientationUtils);
        }
    }

    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);
    }

    private GSYVideoPlayer getCurPlay() {
        if (detailPlayer.getFullWindowPlayer() != null) {
            return detailPlayer.getFullWindowPlayer();
        }
        return detailPlayer;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)){
            new SuperHanDialog(VideoPlayerActivity.this, "请登录").show();
            commentIvCollect.setChecked(false);
            return;
        }
        HttpParams params = new HttpParams();
        LogUtils.e(KEY.TAG + isChecked);
        if (!isChecked) {
            params.clear();
            params.put("content_id", videoId);
            NetworkReuset.getInstance().cancelCollcetion(params, new onCallBack(this) {
                @Override
                public void onSucceed(Object o, Call call, String string) {
                }
            });

        } else {
            params.clear();
            params.put("action", "collect");
            NetworkReuset.getInstance().addCollcettion(String.valueOf(videoId), params, new onCallBack(this) {
                @Override
                public void onSucceed(Object o, Call call, String string) {
                }
            });
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




    private static class CustomShareListener implements UMShareListener {

        private WeakReference<VideoPlayerActivity> mActivity;

        private CustomShareListener(VideoPlayerActivity activity) {
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


