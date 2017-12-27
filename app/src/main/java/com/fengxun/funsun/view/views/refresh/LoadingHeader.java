package com.fengxun.funsun.view.views.refresh;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.LogUtils;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/20.
 * Holle Android
 */

public class LoadingHeader extends FrameLayout implements PtrUIHandler{


    ImageView mIvBack1;
    ImageView mIvBack2;
    ImageView mIvIcon;

    private Animation mBackAnim1;
    private Animation mBackAnim2;
    private AnimationDrawable mAnimationDrawable;

    private boolean isRunAnimation = false;

    public LoadingHeader(@NonNull Context context) {
        this(context,null,0);
    }


    public LoadingHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingHeader(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    // 初始化 下拉刷新
    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.refresh_parallax, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorBooblar));

        mIvBack1 = (ImageView) findViewById(R.id.iv_background_1);
        mIvBack2 = (ImageView) findViewById(R.id.iv_background_2);
        mIvIcon = (ImageView) findViewById(R.id.iv_refresh_icon);


        mAnimationDrawable = (AnimationDrawable) mIvIcon.getDrawable();
        mBackAnim1 = AnimationUtils.loadAnimation(getContext(), R.anim.refresh_down_background_1);
        mBackAnim2 = AnimationUtils.loadAnimation(getContext(), R.anim.refresh_down_background_2);

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

    }


    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();
        if (currentPos < mOffsetToRefresh) {
            //未到达刷新线
            if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
            }
        } else if (currentPos > mOffsetToRefresh) {
            //到达或超过刷新线
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                LogUtils.e("释放刷新刷新");
            }
        }
    }




}
