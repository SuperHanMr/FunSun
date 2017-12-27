package com.fengxun.funsun.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.StreetNumber;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.SchoolListBean;
import com.fengxun.funsun.model.listener.SpaceItemDecoration;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.fengxun.funsun.utils.ToastUtil;
import com.fengxun.funsun.view.adapter.LocationSchoolListAdapter;
import com.fengxun.funsun.view.adapter.SearchSchoolRecyclerViewAdapter;
import com.fengxun.funsun.view.base.BaseActivity;
import com.fengxun.funsun.view.views.SuperHanDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/23.
 * Holle Android
 */

public class SearchSchoolActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener, TextView.OnEditorActionListener {


    @BindView(R.id.ac_ed_search_school)
    EditText acEdSearchSchool;
    @BindView(R.id.ac_search_recyclerview)
    RecyclerView acSearchRecyclerview;


    /*
    ==============定位==========================
     */
    private int WRITE_COARSE_LOCATION_REQUEST_CODE = 1;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private SearchSchoolRecyclerViewAdapter adapter;
    private List<SchoolListBean.DataBean> schoolListBeanData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_school;

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
        NetworkData();
    }


    private void NetworkData() {
        diaLogin(this).show();
        //请求网络 获取大学列表 进行匹配 相同则传入adapter
        NetworkReuset.getInstance().GetReuset(RequestUrl.QUEYSCHOOL, new onCallBack<SchoolListBean>(this) {
            @Override
            public void onSucceed(SchoolListBean schoolListBean, Call call, String string) {
                diaLogin(SearchSchoolActivity.this).dismiss();
                schoolListBeanData = schoolListBean.getData();
                LogUtils.d(schoolListBeanData.size()+"------------>");
                adapter.setmList(schoolListBeanData);
            }
        });

    }



    // 在这里 Activity可见可交互的时候 开启权限
    @Override
    protected void onResume() {
        super.onResume();
        unlockingLocation();
    }

    private void initViews() {

        setBarLeftIcon(true);
        setBarRightIcon(true);
        acEdSearchSchool.setOnEditorActionListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        acSearchRecyclerview.setLayoutManager(manager);
        adapter = new SearchSchoolRecyclerViewAdapter(this);
        acSearchRecyclerview.setAdapter(adapter);
        acSearchRecyclerview.addItemDecoration(new SpaceItemDecoration(10));

        adapter.setOnItemSchoolName(new SearchSchoolRecyclerViewAdapter.OnItemSchoolName() {
            @Override
            public void onItemSchoolName(String schoolName, String id) {
                acEdSearchSchool.setText(schoolName);
                Intent intent = new Intent(SearchSchoolActivity.this,SchoolRootsActivity.class);
                intent.putExtra(KEY.KEY_SCHOOLID,id);
                intent.putExtra(KEY.KEY_SCHOOLNAME,schoolName);
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.tooblar_right_icon)
    public void onViewClicked() {
        location();
    }


    //初始化定位
    private void location() {
        diaLogin(this).show();
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
//        mLocationOption.setMockEnable(true);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    LogUtils.d("纬度: " + aMapLocation.getLatitude() + "/n" + "经度：" + aMapLocation.getLongitude());
                    searchSchool(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    LogUtils.e("location Error, ErrCode:" + aMapLocation.getErrorCode() + ", " +
                            "errInfo:" + aMapLocation.getErrorInfo());
                    failCode(aMapLocation.getErrorCode());
                }
            }
        }
    };

    private void searchSchool(double latitude, double longitude) {
        int currentPage = 0;
        PoiSearch.Query query = new PoiSearch.Query("大学", "高等院校", "北京市");
        query.setPageSize(500);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        LatLonPoint lp = new LatLonPoint(latitude, longitude);
        if (lp != null) {
            PoiSearch poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 3000, true));//
            poiSearch.searchPOIAsyn();// 异步搜索
        }

    }


    // 处理定位失败的结果
    private void failCode(int code) {
        switch (code){
            case 5:
                new SuperHanDialog(this,"网络太差劲了").show();
                break;
            case 12:
                new SuperHanDialog(this,"请打开定位权限").show();
                break;
        }
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        final ArrayList<PoiItem> pois = poiResult.getPois();
        // pois 每个item的基本信息 adapter
        LogUtils.e( pois.get(0).getTitle());
        LogUtils.d("---->"+pois.size());
        final List<SchoolListBean.DataBean> list = new ArrayList();

        //请求网络 获取大学列表 进行匹配 相同则传入adapter
        NetworkReuset.getInstance().GetReuset(RequestUrl.QUEYSCHOOL, new onCallBack<SchoolListBean>(this) {
            /**
             * @param schoolListBean
             * @param call
             * @param string
             */
            @Override
            public void onSucceed(SchoolListBean schoolListBean, Call call, String string) {
                diaLogin(SearchSchoolActivity.this).dismiss();
                List<SchoolListBean.DataBean> data = schoolListBean.getData();
                for (int i = 0; i < pois.size(); i++) {
                    String schooleName = pois.get(i).getTitle();
                    LogUtils.d("------>"+schooleName);
                    for (int i1 = 0; i1 < data.size(); i1++) {
                        if (data.get(i1).getName().equals(schooleName)){
                            SchoolListBean.DataBean dataBean = data.get(i1);
                            list.add(dataBean);
                        }
                    }
                }
                adapter.setmList(list);
            }
        });


    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }


    /**
     * 申请定位权限
     */
    private void unlockingLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
        }
    }

    /**
     * 用户是否开启权限的回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁定位客户端，同时销毁本地定位服务。
        if (mLocationClient!=null){
            mLocationClient.onDestroy();
        }

    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            String content = acEdSearchSchool.getText().toString().trim();
            sendSearch(content);
        }
        return false;
    }


    private void sendSearch(String content) {
         List<SchoolListBean.DataBean> list = new ArrayList();
        for (int i = 0; i < schoolListBeanData.size(); i++) {
            if (schoolListBeanData.get(i).getName().contains(content)){
                SchoolListBean.DataBean dataBean = schoolListBeanData.get(i);
                list.add(dataBean);
            }
        }

        if (list.size()==0){
            ToastUtil.showNormalToast(this,"暂无学校");
        }

        adapter.setmList(list);

    }
}