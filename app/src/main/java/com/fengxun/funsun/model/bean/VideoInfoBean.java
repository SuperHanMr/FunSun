package com.fengxun.funsun.model.bean;

import java.io.Serializable;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/30.
 * Holle Android
 */

public class VideoInfoBean implements Serializable{

    public String videoURL;
    public String videoCover;
    public int videoID;

    public VideoInfoBean(String videoURL, String videoCover, int videoID) {
        this.videoURL = videoURL;
        this.videoCover = videoCover;
        this.videoID = videoID;
    }

    @Override
    public String toString() {
        return "VideoInfoBean{" +
                "videoURL='" + videoURL + '\'' +
                ", videoCover='" + videoCover + '\'' +
                ", videoID=" + videoID +
                '}';
    }
}
