package com.fengxun.funsun.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.CodeBean;
import com.fengxun.funsun.model.bean.RegistrationUserBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.views.SuperHanDialog;
import com.lzy.okgo.model.HttpParams;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/6.
 * Holle Android
 * 内容 填写基本信息
 * TODO：头像暂时不做 后面的话自定义拍照或者拍摄视频
 * TODO：残留问题 数据回显 如果当前Activity因内存不足销毁 该如何保存数据
 * 地理位置明天做 2017/11/8
 */

public class RegistrationActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.ac_registration_name)
    EditText acRegistrationName;
    @BindView(R.id.ac_registration_rb)
    RadioGroup acRegistrationRb;

    @BindView(R.id.ac_registration_time)
    TextView acRegistrationTime;
    @BindView(R.id.ac_registration_location)
    TextView acRegistrationLocation;
    @BindView(R.id.ac_registration_btn)
    Button acRegistrationBtn;
    @BindView(R.id.ac_registration_checkbox)
    CheckBox acRegistrationCheckbox;
    @BindView(R.id.ac_registration_rb1)
    RadioButton acRegistrationRb1;
    @BindView(R.id.ac_registration_rb2)
    RadioButton acRegistrationRb2;


    private boolean isChecked = true;
    // 基本信息
    private String year = "";
    private String sxt = "";
    private String nick = "";
    private String schoolid = "";
    public static final String USERINFO = "userinfo";
    private RegistrationUserBean bean;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_registration;
    }

    @Override
    protected int getBoolarColors() {
        return R.color.colorBooblar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initViews();
        isUserRegistration();
    }



    private void initViews() {
        setBarLeftIcon(true);

        // 数据回显
        if (bean != null) {
            acRegistrationName.setText(bean.nick);
            if (bean.sex.equals("1")){
                acRegistrationRb1.setChecked(true);
            }else {
                acRegistrationRb2.setChecked(true);
            }
            acRegistrationLocation.setText(bean.school);
            acRegistrationTime.setText(bean.admission);
            acRegistrationCheckbox.setChecked(true);
        }

        acRegistrationName.addTextChangedListener(new EditChangedListener());
        acRegistrationCheckbox.setChecked(isChecked);
        // 监听选择的性别
        acRegistrationRb.setOnCheckedChangeListener(this);

        acRegistrationCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RegistrationActivity.this.isChecked = isChecked;
                isUserRegistration();
            }
        });
    }

    /**
     * @param group     监听选中的性别
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.ac_registration_rb1:
                sxt = String.valueOf(1);
                break;
            case R.id.ac_registration_rb2:
                sxt = String.valueOf(1);
                break;
        }
        isUserRegistration();
    }

    /**
     * @param view
     */
    @OnClick({R.id.ac_registration_btn, R.id.ac_registration_paizaho, R.id.ac_registration_time, R.id.ac_registration_location,R.id.ac_registration_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_registration_btn:
                LogUtils.d("跳转手机号注册页面");

                inspectionName();

                break;
            case R.id.ac_registration_paizaho:
                LogUtils.d("拍照");
                break;
            case R.id.ac_registration_location:
                getLocation();
                LogUtils.d("选择学校");
                break;
            case R.id.ac_registration_time:
                LogUtils.d("选择入校时间");
                selectorTime();
                break;
            case R.id.ac_registration_protocol:
                openActivity(FunSunProtocol.class);
                break;
        }
    }

    private void inspectionName() {

        HttpParams params = new HttpParams();
        params.put("nick",nick);
        NetworkReuset.getInstance().PostReuset(RequestUrl.INSPECTION, params, new onCallBack<CodeBean>(this) {
            @Override
            public void onSucceed(CodeBean codeBean, Call call, String string) {
                if (codeBean.getCode()==200){
                    registrationPhone();
                }else {
                    new SuperHanDialog(RegistrationActivity.this,codeBean.getMsg()).show();
                }
            }
        });


    }


    // 对象进行序列化 传递给PhoneNumActivtiy
    private void registrationPhone() {
        bean = new RegistrationUserBean("", sxt, nick, schoolid, year);
        Intent intent = new Intent(this, PhoneNumActivtiy.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(USERINFO, bean);
        intent.putExtras(mBundle);
        startActivity(intent);
    }

    /**
     * 时间选择器
     */
    private void selectorTime() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        final Calendar endDate = Calendar.getInstance();
        //正确设置方式 原因：注意事项有说明
        selectedDate.set(2017, 0, 1);
        startDate.set(1970, 0, 1);
        endDate.set(2017, 1, 1);

        TimePickerView timePickeView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                endDate.setTime(date);
                //year 获取入学时间
                year = String.valueOf(endDate.get(Calendar.YEAR));
                acRegistrationTime.setText(year);
                isUserRegistration();
            }
        }).setType(new boolean[]{true, false, false, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(15)//滚轮文字大小
                .setTitleSize(15)//标题文字大小
                .setTitleText("选择入校时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.GRAY)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        timePickeView.show();
    }

    //
    private void getLocation() {
        Intent intent = new Intent(this, LocationActivity.class);
        // startActivityForResult跳转Activity 获得回传数据
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000&&resultCode==1001){
            String school = data.getStringExtra("school");
            String schoolId = data.getStringExtra("schoolId");
            this.schoolid = schoolId;
            acRegistrationLocation.setText(school);
        }
    }

    private void isUserRegistration() {
        LogUtils.d("学校：" + nick + "，性别：" + sxt + ",所在大学：" + schoolid + "入学时间：" + year + "，同意协议：" + isChecked + "");
        if (nick.equals("") || sxt.equals("") || schoolid.equals("") || year.equals("") || isChecked == false) {
            acRegistrationBtn.setBackgroundResource(R.drawable.shape_login_borde);
            acRegistrationBtn.setEnabled(false);
            return;
        }

        if (!nick.contains(" ")) {
            if (!nick.equals("") && !sxt.equals("") && !schoolid.equals("") && !year.equals("") && isChecked) {
                acRegistrationBtn.setBackgroundResource(R.drawable.shape_login_borde_n);
                acRegistrationBtn.setEnabled(true);
            }
        } else {
            acRegistrationBtn.setBackgroundResource(R.drawable.shape_login_borde);
            acRegistrationBtn.setEnabled(false);
        }

    }

    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            nick = acRegistrationName.getText().toString();
            isUserRegistration();
        }
    }


}
