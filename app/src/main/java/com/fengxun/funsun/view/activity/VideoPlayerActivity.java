package com.fengxun.funsun.view.activity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CommentContentBean;
import com.fengxun.funsun.model.bean.GetCommentContentBean;
import com.fengxun.funsun.model.bean.MeetTheManBean;
import com.fengxun.funsun.model.bean.VideoBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.listener.SampleListener;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
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
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/300.
 * Holle Android
 * 内容：播放视频类 测试
 */

public class VideoPlayerActivity extends AppCompatActivity implements TextView.OnEditorActionListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.post_detail_nested_scroll)
    NestedScrollView postDetailNestedScroll;

    @BindView(R.id.detail_player)
    LandLayoutVideo detailPlayer;
    @BindView(R.id.activity_detail_player)
    RelativeLayout activityDetailPlayer;


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
    ImageView commentIvNomeet; // 如果有相遇的 就隐藏 默认显示

    @BindView(R.id.comment_iv_meet_head)
    CircleImageView commentIvMeetHead; //相遇的人头像

    @BindView(R.id.comment_rl_meet)
    AutoRelativeLayout commentRlMeet; // 相遇的人布局

    @BindView(R.id.comment_iv_sharing) //分享icon
            ImageView commentIvSharing;

    @BindView(R.id.comment_rb) //左边右边评论按钮
            RadioGroup commentRb;
    @BindView(R.id.comment_ed_content)
    EditText commentEdContent;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_player);
        ButterKnife.bind(this);
        //断网自动重新链接，url前接上ijkhttphook:
        //String url = "ijkhttphook:http://baobab.wdjcdn.com/14564977406580.mp4";
        //如果视频帧数太高导致卡画面不同步
        //VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 5);
        //如果视频seek之后从头播放
        //VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
        //List<VideoOptionModel> list = new ArrayList<>();
        //list.add(videoOptionModel);
        //GSYVideoManager.instance().setOptionModelList(list);
        //GSYVideoManager.instance().setTimeOut(4000, true);

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
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        Debuger.printfError("***** onEnterFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onEnterFullscreen **** " + objects[1]);//当前全屏player
                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                    }

                    @Override
                    public void onClickStartError(String url, Object... objects) {
                        super.onClickStartError(url, objects);
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[1]);//当前非全屏player
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

        detailPlayer.startPlayLogic();


        /*
        这里 联网请求视频详细信息
        title 发布视频用户头像和名字  发布时间 溯源的展示 以及评论弹幕
         */
        NetworkReuset.getInstance().getVideoData(String.valueOf(videoId), new onCallBack<VideoBean>(this) {
            @Override
            public void onSucceed(VideoBean videoBean, Call call, String string) {
                VideoBean.DataBean data = videoBean.getData();
                content_publish_user_id = data.getContent_publish_user_id();
                initView(data);
            }
        });





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
                        LogUtils.e(KEY.TAG+"");
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
        if (collection ==0){
            commentIvCollect.setChecked(false);
        }else {
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
                if (data1.size()!=0){
                    MeetTheManBean.DataBean dataBean = data1.get(0);
                    String user_avatar = dataBean.getUser_avatar();
                    commentIvNomeet.setVisibility(View.GONE);
                    commentRlMeet.setVisibility(View.VISIBLE);
                    Picasso.with(VideoPlayerActivity.this).load(user_avatar).into(commentIvMeetHead);
                    //TODO

                }else {

                    commentIvNomeet.setVisibility(View.VISIBLE);
                    commentRlMeet.setVisibility(View.GONE);
                }
            }
        });




    }



    /*
       键盘发送弹幕按钮监听
        */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId== EditorInfo.IME_ACTION_SEND){
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
        params.put("is_first",1);
        params.put("content_id",videoId);
        params.put("comment_direction",commentType);
        LogUtils.e(KEY.TAG+commentType);
        params.put("comment_content",content);
        params.put("content_publish_user_id",content_publish_user_id);
        NetworkReuset.getInstance().PostReuset(RequestUrl.COMMENTCONTENT, params, new onCallBack<CommentContentBean>(this) {
            @Override
            public void onSucceed(CommentContentBean commentContentBean, Call call, String string) {
                /*
                评论 commentContentBean
                 */
                if (commentContentBean.getCode()==200){
                    videoUserBarrage.addBarrage(new Barrage(content,commentType));
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

        HttpParams params = new HttpParams();
        LogUtils.e(KEY.TAG+isChecked);
            if (!isChecked){
                params.clear();
                params.put("content_id",videoId);
                NetworkReuset.getInstance().cancelCollcetion(params, new onCallBack(this) {
                    @Override
                    public void onSucceed(Object o, Call call, String string) {
                    }
                });


            }else {
                params.clear();
                params.put("action","collect");
                NetworkReuset.getInstance().addCollcettion(String.valueOf(videoId), params, new onCallBack(this) {
                    @Override
                    public void onSucceed(Object o, Call call, String string) {
                    }
                });


            }
    }
}


