package com.fengxun.funsun.view.views.barrage;

import com.fengxun.funsun.R;

public class Barrage {

    private String content; // 内容
    private int color; // 内容颜色
    private boolean showBorder; //是否显示边框


    public int getType() {
        return type;
    }



    private int type;

    public Barrage(String content) {
        this(content, R.color.black, false,0);
    }

    public Barrage(String content, boolean showBorder) {
        this(content, R.color.black, showBorder,0);
    }


    public Barrage(String content, int type) {
        this(content, R.color.black, false,type);
    }



    public Barrage(String content, int color, boolean showBorder,int type) {
        this.content = content;
        this.color = color;
        this.showBorder = showBorder;
        this.type = type;
    }



    public boolean isShowBorder() {
        return showBorder;
    }

    public void setShowBorder(boolean showBorder) {
        this.showBorder = showBorder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
