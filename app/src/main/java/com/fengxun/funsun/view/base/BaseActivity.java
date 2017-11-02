package com.fengxun.funsun.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.fengxun.funsun.R;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 程序员：韩永辉
 * 时间：2017/11/2
 * 联系方式：17710558669
 * Hello Android！
 * 内容 ：抽取父类的Activity
 * 1. 标题栏和状态栏一制（沉浸式标题栏）
 * 2.抽取toolbar
 * 3.网络请求
 *
 *
 */

public abstract class BaseActivity extends AutoLayoutActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载布局
        setContentView(getLayoutId());
        //设置状态栏和标题栏颜色一致
        SteBoolarUtil.setWindowStatusBarColor(this,getBoolarColors());
    }



    // 子类实现这个方法 返回布局ID
    protected abstract int getLayoutId();

    // 子类实现这个方法 返回颜色ID
    protected abstract int getBoolarColors();





    // 子类 可以直接跳转Activity
    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    // Toast
    public void showShart(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

}
