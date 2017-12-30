package com.fengxun.funsun.utils;

import com.fengxun.funsun.model.request.RequestUrl;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/23.
 * Holle Android
 * 内容：常用的AIP
 */

public class SuperHanUtils {
    /*
                文本只能显示一行的工具的Text属性
                android:singleLine="true"
                android:lines="1"



                /// 兴趣溯源
#define _content_source(source_tag_id,sort,offset) getNSUserDefault(@"X-Fo-Access-Token") == nil ?
[NSString stringWithFormat:@"%@not_login_content_source/v1/?source_tag_id=%@&sort=%@&offset=%@",server,(source_tag_id),(sort),(offset)] :                                                                                                                                   [NSString stringWithFormat:@"%@content_source/v1/?source_tag_id=%@&sort=%@&offset=%@",server,(source_tag_id),(sort),(offset)]

       地址：http://api.funsun.cn/not_login_content_source/v1/{source_tag_id}
         参数
              sort = sort
              offset= offset





     */


    /*
兴趣Demo接口

    Map<String,List> map = new HashMap<>();
    List<String> list = new ArrayList<>();
        list.add(5012+"");
        list.add(5000+"");
        list.add(5004+"");
        list.add(5021+"");
    Gson gson = new Gson();
        map.put("tags",list);
    String s1 = gson.toJson(map);
        LogUtils.e(s1);
    OkGo.<String>post(RequestUrl.SELECTINITTAG)
                .tag(this)
                .upJson(s1)
                .execute(new StringCallback() {
        @Override
        public void onSuccess(String s, Call call, Response response) {
        }
    });



    */
}
