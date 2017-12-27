package com.fengxun.funsun.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.MeCenterMessageBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.InspectionPhoneUtils;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.TimeUtils;
import com.fengxun.funsun.view.activity.CommentariesPromtingActivity;
import com.fengxun.funsun.view.activity.SttingActivity;
import com.fengxun.funsun.view.activity.SystemPromtingActivity;
import com.fengxun.funsun.view.activity.ToViewPromtingActivity;
import com.fengxun.funsun.view.adapter.SystemPormtingAdapter;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.views.BlurBitmap;
import com.fengxun.funsun.view.views.BlurTransformation;
import com.fengxun.funsun.view.views.SuperHanLoginDiglog;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.HttpParams;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/3.
 * Holle Android
 * 内容 ：写逻辑 填充头像以及姓名
 * 获取提示信息列表
 */

public class MeFragment extends BaseFragment {


    @BindView(R.id.fragment_me_iv_head)
    CircleImageView fragmentMeIvHead;
    @BindView(R.id.fragment_me_ll)
    AutoRelativeLayout fragmentMeLl;
    @BindView(R.id.fragment_me_tv_name)
    TextView fragmentMeTvName;
    @BindView(R.id.fragment_me_iv_age)
    ImageView fragmentMeIvAge;
    @BindView(R.id.fragment_me_tv_schllo)
    TextView fragmentMeTvSchllo;
    Unbinder unbinder;



    @BindView(R.id.item_system_message_tishi)
    ImageView itemSystemMessageTishi;
    @BindView(R.id.item_pinglun_message_tishi)
    ImageView itemPinglunMessageTishi;
    @BindView(R.id.item_toview_message_tishi)
    ImageView itemToviewMessageTishi;
    @BindView(R.id.item_system_message_time)
    TextView itemSystemMessageTime;
    @BindView(R.id.item_system_message)
    TextView itemSystemMessage;
    @BindView(R.id.item_pinglun_message_time)
    TextView itemPinglunMessageTime;
    @BindView(R.id.item_pinglun_message)
    TextView itemPinglunMessage;
    @BindView(R.id.item_toview_message_time)
    TextView itemToviewMessageTime;
    @BindView(R.id.item_toview_message)
    TextView itemToviewMessage;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }


    /*
      拿到图片 下载本地 处理图片模糊
     */
    @Override
    protected void initView() {
        Picasso.with(getContext()).load(SPUtils.getString(KEY.KEY_USERHEAD)).into(mTarget);
        Picasso.with(getContext()).load(SPUtils.getString(KEY.KEY_USERHEAD)).into(fragmentMeIvHead);
        fragmentMeTvName.setText(SPUtils.getString(KEY.KEY_USERNAME));
        fragmentMeIvAge.setImageResource(SPUtils.getInt(KEY.KEY_USERGENDER) == 1 ? R.drawable.male_h : R.drawable.female_h);
        fragmentMeTvSchllo.setText(SPUtils.getString(KEY.KEY_USERSCHOOL));

    }

    private Target mTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Bitmap blur = BlurBitmap.blur(getContext(), bitmap);
            fragmentMeLl.setBackground(new BitmapDrawable(blur));
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    public void NetworkData() {
        super.NetworkData();
        /*
        加载进度条
         */
        HttpParams params = new HttpParams();
        params.put("friend_id_list", "");
        NetworkReuset.getInstance().getMeReuset(RequestUrl.MESSAGELIST, params, new onCallBack<MeCenterMessageBean>(this) {
            @Override
            public void onSucceed(MeCenterMessageBean meCenterMessageBean, Call call, String string) {
                List<MeCenterMessageBean.DataBean> data = meCenterMessageBean.getData();
                systemMeassageItem(data.get(0).getSystem_data());
                commentMeassageItem(data.get(0).getComment_data());
                vistiMeassageItem(data.get(0).getVisit_data());
                LogUtils.e("有网啦，正常网络");
            }

            /*
            如果没有网络 会走缓存回调
             */

            @Override
            public void onCacheSuccess(MeCenterMessageBean meCenterMessageBean, Call call) {
                super.onCacheSuccess(meCenterMessageBean, call);
                List<MeCenterMessageBean.DataBean> data = meCenterMessageBean.getData();
                systemMeassageItem(data.get(0).getSystem_data());
                commentMeassageItem(data.get(0).getComment_data());
                vistiMeassageItem(data.get(0).getVisit_data());
                LogUtils.e("没网啦，走本地缓存");

            }
        });

    }


    private void vistiMeassageItem(MeCenterMessageBean.DataBean.VisitDataBean visit_data) {
        if (visit_data.getIs_update().equals("1")){
            itemToviewMessageTishi.setVisibility(View.VISIBLE);
        }

        if (!visit_data.getContent().equals("")){
            itemToviewMessage.setText(visit_data.getContent());
            String timestamp = visit_data.getTimestamp();
            String intNumber = timestamp.substring(0,timestamp.indexOf("."));
            itemToviewMessageTime.setText(TimeUtils.getTimeFormatText(intNumber));

        }
    }

    private void commentMeassageItem(MeCenterMessageBean.DataBean.CommentDataBean comment_data) {
        if (comment_data.getIs_update().equals("1")){
            itemPinglunMessageTishi.setVisibility(View.VISIBLE);
        }



        if (!comment_data.getContent().equals("")){
            itemPinglunMessage.setText(comment_data.getContent());
            String timestamp = comment_data.getTimestamp();
            String intNumber = timestamp.substring(0,timestamp.indexOf("."));
            itemPinglunMessageTime.setText(TimeUtils.getTimeFormatText(String.valueOf(intNumber)));
        }

    }

    private void systemMeassageItem( MeCenterMessageBean.DataBean.SystemDataBean system_data) {

        if (system_data.getIs_update().equals("1")){
            itemSystemMessageTishi.setVisibility(View.VISIBLE);
        }

        if (!system_data.getContent().equals("")){
            String timestamp = system_data.getTimestamp();
            itemSystemMessage.setText(system_data.getContent());
            String intNumber = timestamp.substring(0,timestamp.indexOf("."));
            itemPinglunMessageTime.setText(TimeUtils.getTimeFormatText(String.valueOf(intNumber)));
        }
    }


    @OnClick({R.id.fragment_me_iv_stting, R.id.fragment_me_ll_quotations, R.id.fragment_me_ll_collect, R.id.fragment_me_ll_face
            , R.id.fragment_me_rl_system, R.id.fragment_me_rl_pinglun, R.id.fragment_me_rl_toview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_me_iv_stting:
                startActivity(new Intent(getActivity(), SttingActivity.class));
                break;
            case R.id.fragment_me_ll_quotations:
                break;
            case R.id.fragment_me_ll_collect:
                break;
            case R.id.fragment_me_ll_face:
                break;

            case R.id.fragment_me_rl_system:
                getContext().startActivity(new Intent(getContext(), SystemPromtingActivity.class));
                break;

            case R.id.fragment_me_rl_pinglun:
                getContext().startActivity(new Intent(getContext(), CommentariesPromtingActivity.class));
                break;
            case R.id.fragment_me_rl_toview:
                getContext().startActivity(new Intent(getContext(), ToViewPromtingActivity.class));
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

}
