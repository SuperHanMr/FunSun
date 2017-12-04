package com.fengxun.funsun.view.views.barrage;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


/*
    动画管理类
 */

public class AnimationHelper {

    public static Animation createTranslateAnim(Context context, int fromX, int toX) {
        /*
        这个构造 两个Y坐标点不变 变的只有 X坐标  从起点fromX到toX坐标 达到一个平移效果

         */
        TranslateAnimation tlAnim = new TranslateAnimation(fromX, toX, 0, 0);
        long duration = (long) (Math.abs(toX - fromX) * 1.0f / BarrageTools.getScreenWidth(context) * 3000);
        tlAnim.setDuration(duration);
        tlAnim.setInterpolator(new DecelerateAccelerateInterpolator());
        tlAnim.setFillAfter(true);
        return tlAnim;
    }


}