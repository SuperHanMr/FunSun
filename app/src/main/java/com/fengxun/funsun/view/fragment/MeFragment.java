package com.fengxun.funsun.view.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.activity.SttingActivity;
import com.fengxun.funsun.view.adapter.MeMessaegAdapter;
import com.fengxun.funsun.view.base.BaseFragment;
import com.fengxun.funsun.view.views.BlurBitmap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/3.
 * Holle Android
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
    @BindView(R.id.fragment_me_recycler)
    RecyclerView fragmentMeRecycler;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }


    @Override
    protected void initView() {
        MeMessaegAdapter adapter = new MeMessaegAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentMeRecycler.setLayoutManager(manager);
        fragmentMeRecycler.setAdapter(adapter);

        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.pinglun_message);
        fragmentMeLl.setBackground(new BitmapDrawable(BlurBitmap.blur(getContext(), bmp)));
    }

    /*
    这里设置个人中心
    网络请求 拿到图片 下载本地 处理图片模糊
     */

    @Override
    public void NetworkData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @OnClick({R.id.fragment_me_iv_stting, R.id.fragment_me_ll_quotations, R.id.fragment_me_ll_collect, R.id.fragment_me_ll_face})
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
        }
    }
}
