package com.fengxun.funsun.view.views.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;


import com.fengxun.funsun.R;

import com.fengxun.funsun.model.listener.OnVideoPlayerTime;
import com.fengxun.funsun.model.listener.OnVideoTextViewListener;

import com.fengxun.funsun.utils.LogUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;



/**
 * Created by shuyu on 2016/12/23.
 * CustomGSYVideoPlayer是试验中，建议使用的时候使用StandardGSYVideoPlayer
 */
public class LandLayoutVideo extends StandardGSYVideoPlayer {



    public boolean isQuanPing;

    /**
     * 1.5.0开始加入，如果需要不同布局区分功能，需要重载
     */
    public LandLayoutVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public LandLayoutVideo(Context context) {
        super(context);
    }

    public LandLayoutVideo(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    protected void setTextAndProgress(int secProgress) {
        super.setTextAndProgress(secProgress);
        int timeMs = getCurrentPositionWhenPlaying();
        int totalSeconds = timeMs / 1000;
        if (onVideoPlayerTime!=null){
            onVideoPlayerTime.onVideoPlayerTimeListener(totalSeconds);
        }

    }




    /*
    监听播放时间
     */

    private OnVideoPlayerTime onVideoPlayerTime;
    public void setOnVideoPlayerTimeListener(OnVideoPlayerTime onVideoPlayerTime){
        this.onVideoPlayerTime = onVideoPlayerTime;
    }




    private OnVideoTextViewListener onVideoTextViewListener;

    public void setOnVideoTextViewListener (OnVideoTextViewListener onVideoTextViewListener){
        this.onVideoTextViewListener = onVideoTextViewListener;
    }





    //这个必须配置最上面的构造才能生效
    @Override
    public int getLayoutId() {
        if (mIfCurrentIsFullscreen) {
            LogUtils.e("重新载入布局载入横向布局");
            return R.layout.sample_video_land;
        }

        return R.layout.sample_video_normal;
    }

    @Override
    public ImageView getBackButton() {
        return super.getBackButton();
    }





    @Override
    protected void updateStartImage() {
        if (mIfCurrentIsFullscreen) {
            if(mStartButton instanceof ImageView) {
                ImageView imageView = (ImageView) mStartButton;
                if (mCurrentState == CURRENT_STATE_PLAYING) {
                    imageView.setImageResource(R.drawable.video_click_pause_selector);
                } else if (mCurrentState == CURRENT_STATE_ERROR) {
                    imageView.setImageResource(R.drawable.video_click_play_selector);
                } else {
                    imageView.setImageResource(R.drawable.video_click_play_selector);
                }
            }


        } else {
            super.updateStartImage();
        }
    }



}
