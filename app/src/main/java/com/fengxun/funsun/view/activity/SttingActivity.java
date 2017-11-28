package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanyonghui.mylibrary.BottomMenuFragment;
import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.eventbus.MainActivityEventBus;
import com.fengxun.funsun.model.eventbus.SttingActivityEventBus;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.SPUtils;
import com.fengxun.funsun.view.base.ActivityStack;
import com.fengxun.funsun.view.base.BaseActivity;

import com.lzy.okgo.model.HttpParams;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/10.
 * Holle Android
 */

public class SttingActivity extends BaseActivity {
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
    @BindView(R.id.ac_stting_tv_huancun)
    TextView acSttingTvHuancun;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stting;

    }

    /**
     * @return
     */
    @Override
    protected int getBoolarColors() {
        return R.color.colorBooblar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        setBarLeftIcon(true);
        setTvTitle("设置");
        initViews();
    }

    private void initViews() {

        Picasso.with(this).load(SPUtils.getString(KEY.KEY_UNIVERSITY)).into(acSttingIvSchool);
        acSttingTvSchool.setText(SPUtils.getString(KEY.KEY_USERSCHOOL));
        Picasso.with(this).load(SPUtils.getString(KEY.KEY_USERHEAD)).into(acSttingIvHead);
        acSttingTvName.setText(SPUtils.getString(KEY.KEY_USERNAME));
        acSttingTvXingbie.setText(SPUtils.getInt(KEY.KEY_USERGENDER) == 1 ? "男" : "女");
        LogUtils.d(SPUtils.getString(KEY.KEY_USERFUNSUNNUM)+"----->");
        if (!SPUtils.getString(KEY.KEY_USERFUNSUNNUM).equals("")) {
            acSttingIvFunsunBack.setVisibility(View.GONE);
            acSttingRlNum.setEnabled(false);
            acSttingTvFunsun.setText(SPUtils.getString(KEY.KEY_USERFUNSUNNUM));
        }

    }


    @OnClick({R.id.ac_stting_rl_head, R.id.ac_stting_rl_name, R.id.ac_stting_rl_xingbie, R.id.ac_stting_rl_num, R.id.ac_stting_rl_password, R.id.ac_stting_rl_heimingdan,R.id.ac_stting_rl_huancun, R.id.ac_stting_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_stting_rl_head:
                break;
            case R.id.ac_stting_rl_name:
                openActivity(ChangeNameActivity.class);
                break;
            case R.id.ac_stting_rl_xingbie:
                modificationXingBie();
                break;
            case R.id.ac_stting_rl_num:
                openActivity(ChangeNumActivity.class);
                break;
            case R.id.ac_stting_rl_password:
                openActivity(ChangePasswordActivity.class);
                break;
            case R.id.ac_stting_rl_huancun:
                // TODO 清理缓存  acSttingTvHuancun

             

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
        EventBus.getDefault().post(new MainActivityEventBus(2));
        ActivityStack.finishAll();
    }




    /*
    选择性别 只能改一次
    思路 是我保存还是服务器保存
     */
    private void modificationXingBie() {

        String[] items = {"性别只能选择一次哟", "男", "女"};
        final BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        bottomMenuFragment.setItems(items);
        bottomMenuFragment.show(getFragmentManager(), "BottomMenuFragment");
        bottomMenuFragment.setListenerItem(new BottomMenuFragment.DialogListenerItem() {
            @Override
            public void listenerItem(String s) {
                LogUtils.e("SttingActivity" + s);
                if (s.equals("男")) {
                    setGender(1);
                } else {
                    setGender(0);
                }
                bottomMenuFragment.dismiss();
            }
        });
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
