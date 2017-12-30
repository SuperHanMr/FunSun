package com.fengxun.funsun.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hanyonghui.mylibrary.BottomMenuFragment;
import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.eventbus.MainActivityEventBus;
import com.fengxun.funsun.model.eventbus.SttingActivityEventBus;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.ACache;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.utils.SteBoolarUtil;
import com.fengxun.funsun.view.activity.BlackListActivity;
import com.fengxun.funsun.view.activity.ChangeNameActivity;
import com.fengxun.funsun.view.activity.ChangeNumActivity;
import com.fengxun.funsun.view.activity.ChangePasswordActivity;
import com.fengxun.funsun.view.base.ActivityStack;
import com.fengxun.funsun.view.base.BaseFragment;
import com.lzy.okgo.model.HttpParams;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/30.
 * Holle Android
 */

public class MeSttingFragment extends BaseFragment {


    @BindView(R.id.status_bar_fix)
    View statusBarFix;
    Unbinder unbinder;

    @BindView(R.id.ac_stting_iv_head)
    CircleImageView acSttingIvHead;
    @BindView(R.id.ac_stting_tv_name)
    TextView acSttingTvName;
    @BindView(R.id.ac_stting_tv_xingbie)
    TextView acSttingTvXingbie;
    @BindView(R.id.ac_stting_tv_school)
    TextView acSttingTvSchool;
    @BindView(R.id.ac_stting_iv_school)
    CircleImageView acSttingIvSchool;
    @BindView(R.id.ac_stting_tv_funsun)
    TextView acSttingTvFunsun;
    @BindView(R.id.ac_stting_iv_funsun_back)
    ImageView acSttingIvFunsunBack;
    @BindView(R.id.ac_stting_rl_num)
    RelativeLayout acSttingRlNum;


    @Override
    protected int getLayoutId() {
        return R.layout.me_stting_fragment;
    }

    @Override
    protected void initView() {
        setBarLeftTv("我");

        Glide.with(getContext()).load(SPUtils.getString(KEY.KEY_UNIVERSITY)).into(acSttingIvSchool);
        acSttingTvSchool.setText(SPUtils.getString(KEY.KEY_USERSCHOOL));
        Glide.with(getContext()).load(SPUtils.getString(KEY.KEY_USERHEAD)).into(acSttingIvHead);
        acSttingTvName.setText(SPUtils.getString(KEY.KEY_USERNAME));
        acSttingTvXingbie.setText(SPUtils.getInt(KEY.KEY_USERGENDER) == 1 ? "男" : "女");
        LogUtils.d(SPUtils.getString(KEY.KEY_USERFUNSUNNUM)+"----->");
        if (!SPUtils.getString(KEY.KEY_USERFUNSUNNUM).equals("")) {
            acSttingIvFunsunBack.setVisibility(View.GONE);
            acSttingRlNum.setEnabled(false);
            acSttingTvFunsun.setText(SPUtils.getString(KEY.KEY_USERFUNSUNNUM));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        statusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SteBoolarUtil.getStateBarHeight(getActivity())));//填充状态栏
        EventBus.getDefault().register(this);
        return rootView;
    }




    @OnClick({R.id.ac_stting_rl_head, R.id.ac_stting_rl_name, R.id.ac_stting_rl_xingbie, R.id.ac_stting_rl_num, R.id.ac_stting_rl_password, R.id.ac_stting_rl_heimingdan, R.id.ac_stting_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_stting_rl_head:
                break;
            case R.id.ac_stting_rl_name:
                openActivity(ChangeNameActivity.class);
                break;
            case R.id.ac_stting_rl_xingbie:
//                modificationXingBie();
                break;
            case R.id.ac_stting_rl_num:
                openActivity(ChangeNumActivity.class);
                break;
            case R.id.ac_stting_rl_password:
                openActivity(ChangePasswordActivity.class);
                break;
            case R.id.ac_stting_btn:
                pullOutLogin();
                break;
            case R.id.ac_stting_rl_heimingdan:
                openActivity(BlackListActivity.class);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBusRefresh(SttingActivityEventBus bus){
        int sttingActivityEventBus = bus.getSttingActivityEventBus();
        switch (sttingActivityEventBus){
            case 1 :
                //TODO 这个地方 读取头像URL 设置头像
                break;
            case 2 :
                // 获取姓名
                acSttingTvName.setText(SPUtils.getString(KEY.KEY_USERNAME));
                break;
            case 3 :
                acSttingIvFunsunBack.setVisibility(View.GONE);
                acSttingRlNum.setEnabled(false);
                acSttingTvFunsun.setText(SPUtils.getString(KEY.KEY_USERFUNSUNNUM));
                break;
        }
    }





    /*
    退出登录
     */
    private void pullOutLogin() {
        SPUtils.clear();
        ACache aCache = ACache.get(getContext());
        aCache.clear();
        ActivityStack.finishAll();
        EventBus.getDefault().post(new MainActivityEventBus(2));


    }


    /*
   选择性别 只能改一次
   思路 是我保存还是服务器保存
    */
    private void modificationXingBie() {


    }


    public void setGender(final int gender) {
        HttpParams params = new HttpParams();
        params.put("set_type", 3);
        params.put("set_value", gender);
        NetworkReuset.getInstance().GetReuset(RequestUrl.XIUGAIUERINFO, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean codeBean, Call call, String string) {
                if (codeBean.getCode() == 200) {
                    DialogPromting("修改成功");
                    SPUtils.putInt(KEY.KEY_USERGENDER,gender);
                    acSttingTvXingbie.setText(gender== 1 ? "男" : "女");
                } else {
                    DialogPromting("性别只能修改一次，您已修改过");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }
}
