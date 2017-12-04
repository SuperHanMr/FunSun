package com.fengxun.funsun.view.views.barrage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import static com.fengxun.funsun.view.views.barrage.BarrageTools.getScreenWidth;


/**
 * Created by shiwei on 2017/8/2.
 */

public class BarrageView extends RelativeLayout {
    private static final String TAG = "===========LOG===========:";
    private Set<Integer> existMarginValues = new HashSet<>();
    private int linesCount; // 行数

    private int validHeightSpace;//有效高度空间
    private int INTERVAL = 500; // 时间间隔

    private Random random = new Random(System.currentTimeMillis()); // 随机时间
    private int maxBarrageSize; //接二连三的弹幕大小
    private int maxTextSize; // 文本大小
    private int minTextSize; // 分钟文本大小
    private int lineHeight; // 行高
    private int borderColor; // 边框颜色
    private boolean random_color; // 随机颜色开启
    private boolean allow_repeat; // 允许重复

    /*
    一些默认值
     */
    private final int DEFAULT_BARRAGESIZE = 10;
    private final int DEFAULT_MAXTEXTSIZE = 20;
    private final int DEFAULT_MINTEXTSIZE = 14;
    private final int DEFAULT_LINEHEIGHT = 24;
    private final int DEFAULT_BORDERCOLOR = 0xff000000;
    private final boolean DEFAULT_RANDOMCOLOR = false;
    private final boolean DEFAULT_ALLOWREPEAT = false;




    private List<Barrage> barrages = new ArrayList<>();
    private List<Barrage> cache = new ArrayList<>();
    private int screenWidth;
    private int leftMargin;

    public BarrageView(Context context) {
        this(context, null);
    }

    public BarrageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BarrageView, 0, 0);
        try {
            /*
            在xml文件里面 添加属性
             */
            maxBarrageSize = typedArray.getInt(R.styleable.BarrageView_size, DEFAULT_BARRAGESIZE);
            maxTextSize = typedArray.getInt(R.styleable.BarrageView_max_text_size, DEFAULT_MAXTEXTSIZE);
            minTextSize = typedArray.getInt(R.styleable.BarrageView_min_text_size, DEFAULT_MINTEXTSIZE);
            lineHeight = typedArray.getDimensionPixelSize(R.styleable.BarrageView_line_height, BarrageTools.dp2px(context, DEFAULT_LINEHEIGHT));
            borderColor = typedArray.getColor(R.styleable.BarrageView_border_color, DEFAULT_BORDERCOLOR);
            random_color = typedArray.getBoolean(R.styleable.BarrageView_random_color, DEFAULT_RANDOMCOLOR);
            allow_repeat = typedArray.getBoolean(R.styleable.BarrageView_allow_repeat, DEFAULT_ALLOWREPEAT);

            if (BarrageTools.px2sp(context, lineHeight) < maxTextSize)
                maxTextSize =BarrageTools.px2sp(context, lineHeight) ;
        } finally {
            typedArray.recycle();
        }
    }

    public void setBarrages(List<Barrage> list) {
        if (!list.isEmpty()) {
            barrages.clear();
            barrages.addAll(list);
            mHandler.sendEmptyMessageDelayed(0, INTERVAL);
        }
    }

    public void addBarrage(Barrage tb) {
        barrages.add(tb);
        if (allow_repeat) {
            cache.add(tb);
        }
        showBarrage(tb);
        if (!mHandler.hasMessages(0)) {
            mHandler.sendEmptyMessageDelayed(0, INTERVAL);
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            checkBarrage();
            sendEmptyMessageDelayed(0, INTERVAL);
        }
    };


    public void checkBarrage() {
        int index = (int) (Math.random() * barrages.size());
        Barrage barrage = barrages.get(index);
        if (allow_repeat) {
            if (cache.contains(barrage))
                return;
            cache.add(barrage);
        }
        showBarrage(barrage);
    }

    private void showBarrage(final Barrage tb) {
        if (linesCount != 0 && getChildCount() >= linesCount)
            return;
        if (getChildCount() >= maxBarrageSize)
            return;

        //final TextView textView = tb.isShowBorder() ? new BorderTextView(getContext(), borderColor) : new TextView(getContext()); // 创建是否有边框的弹幕
        //创建 没有边框的
        final TextView textView = new TextView(getContext());
        textView.setTextColor(Color.WHITE);
        textView.setText(tb.getContent());


        /*
        =================这里处理弹幕的播放方向========================
        弹幕方向是根据Type去判断的 0和1去判断
        存一个集合里面
         */

        switch (tb.getType()){
            case 1:
                leftMargin = 0;
                screenWidth = getScreenWidth(getContext());
                break;
            case 0:
                leftMargin =   getRight()-getLeft()-getPaddingLeft(); //往右往左
                screenWidth = -getScreenWidth(getContext());

                break;
        }





        int verticalMargin = getRandomTopMargin(); // 垂直边缘
        textView.setTag(verticalMargin);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.topMargin = verticalMargin;
        textView.setLayoutParams(params);

        // 这个位置 改变 播放动画方向 重要的两个参数
        /*
        leftMargin TextView的起始坐标点
        -getScreenWidth(getContext()) 结束X坐标点 获取屏幕的宽 为- 就是最左边
         */
        Animation anim = AnimationHelper.createTranslateAnim(getContext(), leftMargin, screenWidth);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("111111", "onAnimationEnd: ");
                if (allow_repeat)
                    cache.remove(tb);
                removeView(textView);
                int verticalMargin = (int) textView.getTag();
                existMarginValues.remove(verticalMargin);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        textView.startAnimation(anim);
        addView(textView);
           /*
            =================END======================
         */
    }

    private int getRandomTopMargin() {
        if (validHeightSpace == 0) {
            validHeightSpace = getBottom() - getTop() - getPaddingTop() - getPaddingBottom();
        }
        if (linesCount == 0) {
            linesCount = validHeightSpace / lineHeight;
            if (linesCount == 0) {
                throw new RuntimeException("Not enough space to show text.");
            }
        }
        while (true) {
            int randomIndex = (int) (Math.random() * linesCount);
            int marginValue = randomIndex * (validHeightSpace / linesCount);
            if (!existMarginValues.contains(marginValue)) {
                existMarginValues.add(marginValue);
                return marginValue;
            }
        }
    }

    public void destroy() {
        if (mHandler.hasMessages(0))
            mHandler.removeMessages(0);
        barrages.clear();
        cache.clear();
    }

}
