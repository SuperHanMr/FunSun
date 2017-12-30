package com.fengxun.funsun.view.activity.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.bean.RoostBean;
import com.fengxun.funsun.model.listener.OnSelectorInterestListener;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.activity.MainActivity;
import com.fengxun.funsun.view.adapter.SelectorInterestAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/30.
 * Holle Android
 */

public class SelectorInterest extends BaseActivity implements OnSelectorInterestListener {


    private String[] interestName = {"生活", "创就", "二次元", "搞笑", "科技", "见闻",
            "留学", "美食", "时尚", "体育", "校园",
            "学习", "音乐", "影视", "游记",
            "游戏", "运动", "追星"};


    private String[] interestId = {"5012", "5007", "5020", "5002", "5000", "5011",
            "5008", "5004", "5016", "5006", "5005", "5021",
            "5010", "5001", "5003", "5017", "5015", "5009"};

    private List<String> idList;

    @BindView(R.id.ac_selector_gridview)
    GridView acSelectorGridview;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_selector_interest;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorbWhite;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setStatusBarTextColocr();
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        super.initView();
        setTvTitle("您关注哪些分类？", R.color.colorbBlack);
        setBarRightTv("确定",R.color.colorBooblar);
        idList = new ArrayList<>();
        List<RoostBean> list = new ArrayList<>();
        for (int i = 0; i < interestName.length; i++) {
            RoostBean bean = new RoostBean(interestName[i], interestId[i]);
            list.add(bean);
        }
        SelectorInterestAdapter adapter = new SelectorInterestAdapter(this, list);
        acSelectorGridview.setAdapter(adapter);
        adapter.setSelectorIntereestListener(this);
    }

    @OnClick(R.id.tooblar_right_text)
    public void onViewClicked() {

        if (idList.size()<3){
            new SuperHanDialog(this,"至少选择3个兴趣").show();
            return;
        }


        diaLogin(this).show();
        Map<String,List> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("tags",idList);
        String s1 = gson.toJson(map);
        LogUtils.e(s1);
        OkGo.<String>post(RequestUrl.SELECTINITTAG)
                .tag(this)
                .upJson(s1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        CodeBean codeBean = gson.fromJson(s, CodeBean.class);
                        if (codeBean.getCode()==200){
                            startActivity(new Intent(SelectorInterest.this, MainActivity.class));
                            finish();
                            diaLogin(SelectorInterest.this).dismiss();
                        }
                    }
                });

    }


    @Override
    public void addInterestIdListener(String id) {
        idList.add(id);
    }

    @Override
    public void deldeteInterestListenr(String id) {
        LogUtils.e(id);
        idList.remove(id);


    }

}
