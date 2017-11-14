package com.fengxun.funsun.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import com.fengxun.funsun.view.activity.CommentariesPromtingActivity;
import com.fengxun.funsun.view.activity.SttingActivity;
import com.fengxun.funsun.view.activity.ToViewPromtingActivity;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.views.BlurBitmap;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.HttpParams;

import java.text.DecimalFormat;
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
    LinearLayout fragmentMeLl;
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


    @Override
    protected void initView() {

        NetworkReuset.getInstance().GetBitmap(SPUtils.getString(KEY.KEY_USERHEAD), new BitmapCallback() {
            @Override
            public void onSuccess(Bitmap bitmap, Call call, Response response) {
                fragmentMeIvHead.setImageBitmap(bitmap);
                Bitmap blur = BlurBitmap.blur(getContext(), bitmap);
                fragmentMeLl.setBackground(new BitmapDrawable(blur));
            }
        });
        fragmentMeTvName.setText(SPUtils.getString(KEY.KEY_USERNAME));
        fragmentMeIvAge.setImageResource(SPUtils.getInt(KEY.KEY_USERGENDER) == 1 ? R.drawable.male_h : R.drawable.female_h);
        fragmentMeTvSchllo.setText(SPUtils.getString(KEY.KEY_USERSCHOOL));



    }


    /*
    这里设置个人中心
    拿到图片 下载本地 处理图片模糊
     */
    @Override
    public void NetworkData() {
        HttpParams params = new HttpParams();
        params.put("friend_id_list", "");
        NetworkReuset.getInstance().GetReuset(RequestUrl.MESSAGELIST, params, new onCallBack<MeCenterMessageBean>(this) {

            @Override
            public void onSucceed(MeCenterMessageBean meCenterMessageBean, Call call, String string) {
                List<MeCenterMessageBean.DataBean> data = meCenterMessageBean.getData();
                /*
                TextView 小红点没有写 需要根据返回的值 去判断小红点是否显示
                 */
                systemMeassageItem(data.get(0).getSystem_data());
                commentMeassageItem(data.get(0).getComment_data());
                vistiMeassageItem(data.get(0).getVisit_data());
            }
        });

    }


    private void vistiMeassageItem(MeCenterMessageBean.DataBean.VisitDataBean visit_data) {
        if (!visit_data.getContent().equals("")){
            int timestamp = (int) visit_data.getTimestamp(); // 时间戳 转换成 刚刚几分钟前
            itemToviewMessage.setText(visit_data.getContent());
            itemToviewMessageTime.setText(InspectionPhoneUtils.getStandardDate(timestamp+""));
        }
    }

    private void commentMeassageItem(MeCenterMessageBean.DataBean.CommentDataBean comment_data) {
        if (!comment_data.getContent().equals("")){
            int timestamp = (int) comment_data.getTimestamp();
            itemPinglunMessage.setText(comment_data.getContent());
            itemPinglunMessageTime.setText(InspectionPhoneUtils.getStandardDate(String.valueOf(timestamp)));
        }

    }

    private void systemMeassageItem( MeCenterMessageBean.DataBean.SystemDataBean system_data) {
        if (!system_data.getContent().equals("")){
            String timestamp = system_data.getTimestamp();
            itemSystemMessage.setText(system_data.getContent());
//            itemSystemMessageTime.setText(timestamp);
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
