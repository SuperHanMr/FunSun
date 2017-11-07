package com.fengxun.funsun.model.request;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/4.
 * Holle Android
 * 内容: 请求接口
 */

public class RequestUrl {

//  请求接口
    public static final String HTTP = "http://api.funsun.cn";

//  登录
    public static final String LOGIN = HTTP+"/auth/v1/login/";

//  获取验证码
    public static final String CODE = HTTP+"/get_ver_code/v1/";

//  忘记密码
    public static final String FOGETPASSWORD = HTTP+ "/forget_password/v1/";

//  注册账号
    public static final String REGISTRATION = HTTP+"/auth/v1/register/";

//  检验名字
    public static final String INSPECTION = HTTP+"/check_user_info/v1/";

}
