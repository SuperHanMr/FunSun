package com.fengxun.funsun.model.request;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.FunSunResponseBean;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by BM-WGX on 2017/3/20.
 */

public abstract class JsonCallback<T> extends com.lzy.okgo.callback.AbsCallback<T> {
    Gson gson = new Gson();
    private String string;

    /**
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public T convertSuccess(Response response) throws Exception {
        LogUtils.d("--------aaaaaaa---------响应了");
        //以下代码是通过泛型解析实际参数,泛型必须传

        //com.lzy.demo.callback.DialogCallback<com.lzy.demo.model.Login> 得到类的泛型，包括了泛型参数
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //我们的示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        //com.lzy.demo.model.Login
        Type type = params[0];

        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
//        JsonReader jsonReader = new JsonReader(response.body().charStream());
        string = response.body().string();
        LogUtils.d(string);
        T data = gson.fromJson(string, type);
        response.close();
        return data;
    }

    protected void onreSponse401() {

    }

    public void onFaild(String faild){

    }

    @Override
    public void onSuccess(T t, Call call, Response response) {
        onSucceed(t,call,string);
    }
    public abstract void onSucceed(T t, Call call, String string);

    @Override
    public void onAfter(T t, Exception e) {
        super.onAfter(t, e);
        LogUtils.d("-------------onAfter");
        string = null;
    }


}
