package com.fengxun.funsun.view.views.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.ColorInt;
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
import android.widget.LinearLayout;

import com.fengxun.funsun.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;


/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/15.
 * Holle Android
 */

public class PullRefreshHeader extends FrameLayout implements RefreshHeader {


    private   ImageView mIvBack1;
    private   ImageView mIvBack2;
    private   ImageView mIvIcon;


    private Animation mBackAnim1;
    private Animation mBackAnim2;
    private AnimationDrawable mAnimationDrawable;


    public PullRefreshHeader(Context context) {
        super(context);
        initView(context);

    }

    public PullRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public PullRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }


    private void initView(Context context){
        LayoutInflater.from(getContext()).inflate(R.layout.refresh_parallax, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorBooblar));
        mIvBack1 = (ImageView) findViewById(R.id.iv_background_1);
        mIvBack2 = (ImageView) findViewById(R.id.iv_background_2);
        mIvIcon = (ImageView) findViewById(R.id.iv_refresh_icon);
        mAnimationDrawable = (AnimationDrawable) mIvIcon.getDrawable();
        mBackAnim1 = AnimationUtils.loadAnimation(getContext(), R.anim.refresh_down_background_1);
        mBackAnim2 = AnimationUtils.loadAnimation(getContext(), R.anim.refresh_down_background_2);
    }



    /*
    开启动画
     */
    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
        mIvBack1.startAnimation(mBackAnim1);
        mIvBack2.startAnimation(mBackAnim2);
        mIvIcon.setImageDrawable(mAnimationDrawable);
        mAnimationDrawable.start();
    }



    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        mIvBack1.clearAnimation();
        mIvBack2.clearAnimation();
        mAnimationDrawable.stop();
        return 500;
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return  SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(@ColorInt int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }





    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }
}
