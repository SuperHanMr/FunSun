/*
 * AUTHOR：Yan Zhenjie
 *
 * DESCRIPTION：create the File, and add the content.
 *
 * Copyright © ZhiMore. All Rights Reserved
 *
 */
package com.fengxun.funsun.view.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.hanyonghui.refreshlibrary.PtrFrameLayout;
import com.example.hanyonghui.refreshlibrary.PtrUIHandler;
import com.example.hanyonghui.refreshlibrary.indicator.PtrIndicator;
import com.fengxun.funsun.R;



/**
 * Created by Yan Zhenjie on 2016/11/21.
 */
public class ParallaxHeader extends FrameLayout implements PtrUIHandler {

    ImageView mIvBack1;
    ImageView mIvBack2;
    ImageView mIvIcon;

    private Animation mBackAnim1;
    private Animation mBackAnim2;
    private AnimationDrawable mAnimationDrawable;
    private boolean isRunAnimation = false;

    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.refresh_parallax, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorBooblar));


        mIvBack1 = (ImageView) findViewById(R.id.iv_background_1);
        mIvBack2 = (ImageView) findViewById(R.id.iv_background_2);
        mIvIcon = (ImageView) findViewById(R.id.iv_refresh_icon);

        mAnimationDrawable = (AnimationDrawable) mIvIcon.getDrawable();
        mBackAnim1 = AnimationUtils.loadAnimation(getContext(), R.anim.refresh_down_background_1);
        mBackAnim2 = AnimationUtils.loadAnimation(getContext(), R.anim.refresh_down_background_2);
    }

    public ParallaxHeader(Context context) {
        this(context, null, 0);
    }

    public ParallaxHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallaxHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    /**
     * 开始刷新动画。
     */
    private void startAnimation() {
        if (!isRunAnimation) {
            isRunAnimation = true;
            mIvBack1.startAnimation(mBackAnim1);
            mIvBack2.startAnimation(mBackAnim2);
            mIvIcon.setImageDrawable(mAnimationDrawable);
            mAnimationDrawable.start();
        }
    }

    /**
     * 停止刷新动画。
     */
    private void stopAnimation() {
        if (isRunAnimation) {
            isRunAnimation = false;
            mIvBack1.clearAnimation();
            mIvBack2.clearAnimation();
            mAnimationDrawable.stop();
        }
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        stopAnimation();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        stopAnimation();
        startAnimation();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        stopAnimation();
    }

    /*
    在这里是刷新动画
     */
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }

}
