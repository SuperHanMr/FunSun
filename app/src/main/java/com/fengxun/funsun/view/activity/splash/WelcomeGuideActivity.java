package com.fengxun.funsun.view.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.StatusBarUtil;
import com.fengxun.funsun.view.adapter.GuideViewPagerAdapter;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/30.
 * Holle Android
 */

public class WelcomeGuideActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.vp_guide)
    ViewPager vpGuide;

    @BindView(R.id.ac_welocmg_ll)
    AutoLinearLayout acWelocmgLl;
    private List<View> views;
    private Button startBtn;


    // 底部小点图片
    private ImageView[] dots;


    // 记录当前选中位置
    private int currentIndex;

    // 引导页图片资源
    private static final int[] pics = {R.layout.guid_view1,
            R.layout.guid_view2, R.layout.guid_view3};
    private GuideViewPagerAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this);
        setContentView(R.layout.actvity_welcomg);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        views = new ArrayList<View>();
        // 初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);
            if (i == pics.length - 1) {
                startBtn = (Button) view.findViewById(R.id.ac_welocmg_btn_enter);
                startBtn.setTag("enter");
                startBtn.setOnClickListener(this);
            }
            views.add(view);
        }
        adapter = new GuideViewPagerAdapter(views);
        vpGuide.setAdapter(adapter);
        vpGuide.setOnPageChangeListener(new PageChangeListener());
        initDots();


    }

    private void initDots() {
        dots = new ImageView[pics.length];

        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = (ImageView) acWelocmgLl.getChildAt(i);
            dots[i].setEnabled(false);// 都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(true); // 设置为白色，即选中状态
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 如果切换到后台，就设置下次不进入功能引导页
        SPUtils.putBoolean(KEY.FIRST_OPEN, true);
        finish();
    }


    /**
     * 设置当前view
     *
     * @param position
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vpGuide.setCurrentItem(position);
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")) {
            enterMainActivity();
            return;
        }

        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }

    private void enterMainActivity() {
        Intent intent =new Intent(this,SelectorInterest.class);
        startActivity(intent);
        finish();

    }

    @OnClick(R.id.ac_welocmg_tv_enter)
    public void onViewClicked() {
        LogUtils.e("兴趣选择");
        enterMainActivity();
    }


    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        // 当滑动状态改变时调用
        @Override
        public void onPageScrollStateChanged(int position) {
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。

        }

        // 当前页面被滑动时调用
        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
            // arg0 :当前页面，及你点击滑动的页面
            // arg1:当前页面偏移的百分比
            // arg2:当前页面偏移的像素位置

        }

        // 当新的页面被选中时调用
        @Override
        public void onPageSelected(int position) {
//             设置底部小点选中状态
            setCurDot(position);
        }

    }
}