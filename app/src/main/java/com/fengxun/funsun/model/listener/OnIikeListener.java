package com.fengxun.funsun.model.listener;

import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/21.
 * Holle Android
 */

public interface OnIikeListener {

    void onIikeListener(List<ImageButton> buttons, int type, String commentId, String commentUserID, TextView tvNumber);



}
