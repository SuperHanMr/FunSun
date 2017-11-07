package com.fengxun.funsun.model.bean;

import java.io.Serializable;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/7.
 * Holle Android
 */

public class RegistrationUserBean implements Serializable{


    @Override
    public String toString() {
        return "RegistrationUserBean{" +
                "avatar='" + avatar + '\'' +
                ", sex='" + sex + '\'' +
                ", nick='" + nick + '\'' +
                ", school='" + school + '\'' +
                ", admission='" + admission + '\'' +
                '}';
    }

    public String avatar;
    public String sex;
    public String nick;
    public String school;
    public String admission;

    public RegistrationUserBean(String avatar, String sex, String nick, String school, String admission) {

        this.avatar = avatar;
        this.sex = sex;
        this.nick = nick;
        this.school = school;
        this.admission = admission;
    }



}
