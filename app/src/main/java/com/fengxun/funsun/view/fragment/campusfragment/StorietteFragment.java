package com.fengxun.funsun.view.fragment.campusfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CamPusStorietteBean;
import com.fengxun.funsun.model.bean.VideoInfoBean;
import com.fengxun.funsun.model.eventbus.CamPusEventBus;
import com.fengxun.funsun.model.listener.NewItemListener;
import com.fengxun.funsun.model.listener.OnCamPusListenerRefresh;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.activity.VideoPlayerActivity;
import com.fengxun.funsun.view.adapter.StorietteRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseNewFragmnet;
import com.fengxun.funsun.view.views.EditTextDialog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
 */

public class StorietteFragment extends Fragment implements NewItemListener {

    @BindView(R.id.storiette_recyclerview)
    RecyclerView storietteRecyclerview;
    Unbinder unbinder;
    private StorietteRecyclerViewAdapter adapter;
    private EditTextDialog dialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.storiette_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        NetworkData();
    }



    private void initView() {
        storietteRecyclerview.setLayoutManager(new
                StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new StorietteRecyclerViewAdapter(getActivity(),true);
        storietteRecyclerview.addItemDecoration(new SpaceItemDecoration(10));
        storietteRecyclerview.setAdapter(adapter);

        adapter.setItemListener(this);
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTypeRefresh(CamPusEventBus bus){
        int type = bus.getType();
        if (type==1){
            NetworkData();
        }
    }


    private void NetworkData() {
        String school = String.valueOf(5501);
        HttpParams params = new HttpParams();
        params.put("offset",1);

        /*
         这个位置
         */
        String URL = RequestUrl.HTTP+"/school_content/v2/{school_id}/small/";
        String replace = URL.replace("{school_id}", school);
        OkGo.get(replace)
                .headers(SPUtils.getBoolean(KEY.KEY_ISLOGIN,false)?"X-Fo-Access-Token":"X-User-Anonymous",SPUtils.getString(KEY.KEY_USERTOKEN))
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson =new Gson();
                        String json = s.replace("\"vedio_word\": \"{}\"", "\"vedio_word\": null");
                        CamPusStorietteBean camPusStorietteBean = gson.fromJson(json, CamPusStorietteBean.class);
                        List<CamPusStorietteBean.DataBean> beanList = camPusStorietteBean.getData();
                        adapter.setData(beanList);
                        listenerRefresh.camPuslistener();
                    }
                });
    }


    private OnCamPusListenerRefresh listenerRefresh;

    public void setListenerRefresh(OnCamPusListenerRefresh listenerRefresh){
        this.listenerRefresh = listenerRefresh;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void OnVideoInfoListener(VideoInfoBean bean) {
        Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(BaseNewFragmnet.VIDEOINFO,bean);
        intent.putExtras(mBundle);
        getContext().startActivity(intent);
    }

    @Override
    public void OnPostInfoListener(String postId, int type) {
        LogUtils.e(postId+","+type);
        ToastUtil.showNormalToast(getContext(),"帖子ID"+postId+",是否原生");

    }

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
