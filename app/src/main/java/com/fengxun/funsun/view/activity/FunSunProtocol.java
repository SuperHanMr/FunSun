package com.fengxun.funsun.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.base.BaseActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/8.
 * Holle Android
 */

public class FunSunProtocol extends BaseActivity {
    @BindView(R.id.ac_protocol)
    TextView acProtocol;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_protocol;
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
        setBarLeftIcon(true);
        InputStream inputStream = getResources().openRawResource(R.raw.funsun);
        acProtocol.setText(getString(inputStream));
        setTvTitle("用户协议和隐私条款");
    }

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
