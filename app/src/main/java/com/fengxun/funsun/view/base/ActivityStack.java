package com.fengxun.funsun.view.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ActivityStack {

    private static final String LOG = ActivityStack.class.getSimpleName();

    public static List<Activity> mActStack = new ArrayList<Activity>();

    /**
     * 添加一个Activity实例
     *
     * @param act
     * @return
     */
    public static List<Activity> addAct(Activity act) {
        mActStack.add(act);
        Log.i(LOG, "add " + act.getClass().getName());
        return mActStack;
    }

    /**
     * 删除一个Activity实例
     *
     * @param act
     * @return
     */
    public static List<Activity> removeAct(Activity act) {
        mActStack.remove(act);
        Log.i(LOG, "remove " + act.getClass().getName());
        return mActStack;
    }

    /**
     * 退出应用程序
     */
    public static void finishAll() {
        for (Activity act : mActStack) {
            act.finish();
        }
    }

    /**
     * 获取TopActivity的实例
     *
     *
     * @return
     */
    public static Activity getTopActInstance() {

        if(mActStack.size()==0)
            return null;

        return mActStack.get(mActStack.size()-1);
    }

    public static Activity getAct(String actName) {
        if(mActStack.size()==0)
            return null;

        for (int i = mActStack.size()-1; i >=0; i--) {
            Activity act=mActStack.get(i);
            if (act.getClass().getName().equals(actName)) {
                return act;
            }
        }
        return null;
    }

    public static String getTopActivityName(Context c) {
        ActivityManager am = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> infos = am.getRunningTasks(1);
        ActivityManager.RunningTaskInfo info = infos.get(0);
        return info.topActivity.getClassName();
    }

}


