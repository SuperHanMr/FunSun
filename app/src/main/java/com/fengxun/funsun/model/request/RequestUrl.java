package com.fengxun.funsun.model.request;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/4.
 * Holle Android
 * 内容: 请求接口
 */

public class RequestUrl {


    //  测试接口接口
    public static final String TEST = "http://testapi.funsun.cn";

    //  请求接口
    public static final String HTTP = "http://api.funsun.cn";

    //  登录
    public static final String LOGIN = HTTP + "/auth/v1/login/";

    //  获取验证码
    public static final String CODE = HTTP + "/get_ver_code/v1/";

    //  忘记密码
    public static final String FOGETPASSWORD = HTTP + "/forget_password/v1/";

    //  注册账号
    public static final String REGISTRATION = HTTP + "/auth/v1/register/";

    //  检验名字
    public static final String INSPECTION = HTTP + "/check_user_info/v1/";

    //  查询所有学校
    public static final String QUEYSCHOOL = TEST + "/load_school/v2/?scope=only";

    //  资讯
    public static final String zixun = HTTP + "/content/v1/?content_type=global";

    //我的消息列表
    public static final String MESSAGELIST =HTTP+ "/user_info_message_list/v1/";


    //我的消息-评论提示列表
    public static final String COMMENTPROMTING = HTTP+"/user_message_comment_list/v1/";

    //我的消息-评论提示列表-点进去明细
    public static final String COMMENTDATAIL = HTTP+"/user_message_comment_detail_list/v1/";


    //我的消息-查看提示列表
    public static final String TOVIEWPROMTING = HTTP+"/user_message_visit_list/v1/";

    //我的消息-查看提示列表-明细
    public static final String TOVIEWDATAIL = HTTP+"/user_message_visit_detail/v1/";


    //设置-修改个人信息
    public static final String XIUGAIUERINFO = HTTP+"/user_info_set_avatar/v1/";

    //设置-修改密码
    public static final String WANGJIPASSWORD = HTTP+"/account/v1/password/change/";


    //设置-黑名单
    public static final String HEIMINGDAN = HTTP+"/black_user/v1/";


    // 头条资讯
    public static final String HEADLINES = HTTP + "/content/v1/";



}

