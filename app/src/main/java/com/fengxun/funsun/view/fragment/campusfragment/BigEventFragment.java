package com.fengxun.funsun.view.fragment.campusfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CamPusEventBean;

import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.eventbus.CamPusEventBus;
import com.fengxun.funsun.model.listener.NewItemListener;
import com.fengxun.funsun.model.listener.OnCamPusListenerRefresh;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.ACache;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.activity.VideoPlayerActivity;
import com.fengxun.funsun.view.adapter.CamPusEventRecyclerViewAdapter;
import com.fengxun.funsun.view.adapter.NewRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.EditTextDialog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/4.
 * Holle Android
 * 内容：校内大事件 TODO：还有专题
 */

public class BigEventFragment extends Fragment implements NewItemListener {


    @BindView(R.id.bigevent_recyclerview)
    RecyclerView bigeventRecyclerview;
    Unbinder unbinder;

    private int schollId = 5501;
    private List<CamPusEventBean.DataBean> list;

    private CamPusEventRecyclerViewAdapter adapter;
    private EditTextDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bigfragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    /*
    布局 创建完成
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }


    private void initView() {

        list = new ArrayList<>();


         /*
        读取上一次缓存数据
        现在用的是假数据
         */


        // 大事件 复用趣闻模块的条目
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        bigeventRecyclerview.setLayoutManager(manager);
        adapter = new CamPusEventRecyclerViewAdapter(getContext(),list,true);
        bigeventRecyclerview.setLayoutManager(manager);
        bigeventRecyclerview.addItemDecoration(new SpaceItemDecoration(10));
        bigeventRecyclerview.setAdapter(adapter);

        adapter.setItemListener(this);




    }


    /*
    网络请求
    创建布局 的先读的是上一次缓存
    然后 在校内Fragment用EventBus 发送刷新消息 然后 这个页面 收到 消息 开始刷新 请求网络
    set数据 刷新item
     */


    @Override
    public void onResume() {
        super.onResume();
        NetworkData();
    }


    /*
    #define _big_school_content(school_id,offset,order)
         [NSString stringWithFormat:@"%@school_content/v2/%@/big/?offset=%@&order=%@",server,(school_id),(offset),(order)]
     */
    private void NetworkData(){
        HttpParams params = new HttpParams();
        params.put("offset",1); // 请求页数
        params.put("order",0); // 按时间 0 按热度 1


        NetworkReuset.getInstance().getCamPusEventbus(String.valueOf(schollId), params, new onCallBack<CamPusEventBean>(this) {
            @Override
            public void onSucceed(CamPusEventBean headlinesBean, Call call, String string) {
                adapter.setData(headlinesBean.getData());
                listenerRefresh.camPuslistener();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                listenerRefresh.camPuslistener();
            }
        });


   }





   /*
   刷新完毕 接口回调
    */
    private OnCamPusListenerRefresh listenerRefresh;
    public void setListenerRefresh(OnCamPusListenerRefresh listenerRefresh){
        this.listenerRefresh = listenerRefresh;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTypeRefresh(CamPusEventBus bus){
        int type = bus.getType();
        if (type==0){
            NetworkData();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }



    // 视频
    @Override
    public void OnVideoInfoListener(VideoInfoBean bean) {
        LogUtils.e(KEY.TAG+"国外视频条目："+bean.toString());
        Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.VIDEOINFO,bean);
        intent.putExtras(mBundle);
        getContext().startActivity(intent);
    }

    //帖子
    @Override
    public void OnPostInfoListener(String postId, int type) {
        LogUtils.e(postId+","+type);
        ToastUtil.showNormalToast(getContext(),"帖子ID"+postId+",是否原生"+type);

    }

    // 评论
    @Override
    public void OnCommentContentListener(String contentId,String userId) {
        dialog = new EditTextDialog(contentId, userId, new EditTextDialog.SendBackListener() {
            @Override
            public void sendBack() {
                ToastUtil.showNormalToast(getContext(),"评论成功");
                dialog.dismiss();
            }
        });
        dialog.show(getFragmentManager(),"dialog");
    }

    @Override
    public void onRelationListener(String userId, String contentId, int type) {

    }
}
