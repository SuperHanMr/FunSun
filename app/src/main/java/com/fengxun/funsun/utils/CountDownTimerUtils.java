package com.fengxun.funsun.utils;

import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/7.
 * Holle Android
 */

public class CountDownTimerUtils extends CountDownTimer {

    private Button btn;

    /**
     * @param btn     The TextView
     *
     *
     * @param millisInFuture  The number of millis in the future from the call
     *             to {@link #start()} until the countdown is done and {@link #onFinish()}
     *             is called.
     * @param countDownInterval The interval along the way to receiver
     *             {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(Button btn, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
    }

    /**
     * 倒计时期间会调用
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setClickable(false); //设置不可点击
        btn.setText(millisUntilFinished / 1000 + "秒"); //设置倒计时时间


        SpannableString spannableString = new SpannableString(btn.getText().toString()); //获取按钮上的文字
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */

        btn.setText(spannableString);
    }

    /**
     * 倒计时完成后调用
     */
    @Override
    public void onFinish() {
        btn.setText("重新验证");
        btn.setClickable(true);//重新获得点击
    }
}
