package com.rolling.act.location;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.rolling.R;
import com.rolling.act.location.adapter.CityListWithHeadersAdapter;
import com.rolling.base.view.BaseActivity;
import com.rolling.bean.location.CityBean;
import com.rolling.bean.location.CityBeanResponse;
import com.rolling.net.HttpConfig;
import com.rolling.util.CineLog;
import com.rolling.util.CineToast;
import com.rolling.view.RlRecyclerView;
import com.rolling.view.TextViewIcon;
import com.rolling.view.adapter.OnItemClickListener;
import com.rolling.view.search.SearchLayout;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LocationActivity extends BaseActivity implements AMapLocationListener, OnQuickSideBarTouchListener, OnItemClickListener {

    private final int NET_GET_CITY = 1001;

    @BindView(R.id.rlRecyclerView)
    RlRecyclerView recyclerView;

    @BindView(R.id.quickSideBarView)
    QuickSideBarView quickSideBarView;

    @BindView(R.id.quickSideBarTipsView)
    QuickSideBarTipsView quickSideBarTipsView;

    @BindView(R.id.tvGpsCity)
    TextViewIcon tvGpsCity;

    @BindView(R.id.searchLayout)
    SearchLayout searchLayout;

    AMapLocationClient aMapLocationClient;

    AMapLocationClientOption locationClient;

    HashMap<String, Integer> letters = new HashMap<>();

    CityListWithHeadersAdapter adapter;

    CityBeanResponse cityBeanResponse;

    String gpsCity;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_location;
    }

    @Override
    public void init() {
        initView();
        initSearch();
        initLocation();
        getCityList();
    }

    private void initView() {
        quickSideBarView.setOnQuickSideBarTouchListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new CityListWithHeadersAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void succeed(Object o, int tag) {
        switch (tag) {
            case NET_GET_CITY:
                cityBeanResponse = JSON.parseObject(o.toString(), CityBeanResponse.class);
                if (cityBeanResponse != null) {
                    buildLocation();
                }
                break;
        }
    }

    @Override
    public void error(Object o, int tag) {

    }

    private void initLocation() {

        aMapLocationClient = new AMapLocationClient(getApplicationContext());
        aMapLocationClient.setLocationListener(this);
        locationClient = getDefaultOption();

        startLocation();
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        locationClient = new AMapLocationClientOption();
        locationClient.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
//        locationClient.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        return locationClient;
    }

    @Override
    public void onLocationChanged(AMapLocation location) {

        if (null != location) {
            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
            if (location.getErrorCode() == 0) {
                //定位成功
//                tvAddress.setText(location.getCity());
                gpsCity = location.getCity();
                addGpsInfo();
            }
        }
        stopLocation();
        CineLog.e(location.getCity());
    }

    private void addGpsInfo() {
        if (!TextUtils.isEmpty(gpsCity)) {
            tvGpsCity.setText(gpsCity);
        }
    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != aMapLocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            aMapLocationClient.onDestroy();
            aMapLocationClient = null;
            aMapLocationClient = null;
        }
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数
        // 设置定位参数
        aMapLocationClient.setLocationOption(locationClient);
        // 启动定位
        aMapLocationClient.startLocation();
    }


    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    private void getCityList() {
        getLoad(HttpConfig.URL_API_LOCATION_CITY, new String[]{}, new String[]{}, NET_GET_CITY, true);
    }

    @Override
    public void onLetterChanged(String letter, int position, float y) {
        quickSideBarTipsView.setText(letter, position, y);
        //有此key则获取位置并滚动到该位置
        if (letters.containsKey(letter)) {
            setRecyclerViewScroll(letters.get(letter));
        } else {
            recyclerView.scrollToPosition(0);
        }
    }

    @Override
    public void onLetterTouching(boolean touching) {
        quickSideBarTipsView.setVisibility(touching ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 添加list的首字母header
     */
    private void addHeader() {
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);
    }

    private void buildLocation() {

        List<CityBean> hotList = new ArrayList<>();
        CityBean cityBean1 = new CityBean();
        cityBean1.setNameZh("北京");
        hotList.add(cityBean1);

        CityBean cityBean2 = new CityBean();
        cityBean2.setNameZh("上海");
        hotList.add(cityBean2);

        CityBean cityBean = new CityBean();
        cityBean.setViewType(adapter.TYPE_HOT);
        cityBean.setHotCity(hotList);


        cityBeanResponse.getData().add(0, cityBean);

        buildLetters(cityBeanResponse.getData());
        adapter.addAll(cityBeanResponse.getData());
        addHeader();


    }

    /**
     * 添加右侧字母
     */
    private void buildLetters(List<CityBean> locationBean) {
        ArrayList<String> customLetters = new ArrayList<>();
        int position = 0;
        for (CityBean city : locationBean) {
            if (!TextUtils.isEmpty(city.getName())) {
                String letter = city.getName().substring(0, 1);
                //如果没有这个key则加入并把位置也加入
                if (!TextUtils.isEmpty(letter)) {
                    if (!letters.containsKey(letter)) {
                        letters.put(letter, position);
                        customLetters.add(letter);
                    }
                }
            }
            position++;
        }
        //不自定义则默认26个字母
        customLetters.add(0, "热");
        quickSideBarView.setLetters(customLetters);
    }

    @Override
    public void onItemClick(View view, int position) {
        CineToast.showShort(adapter.getItem(position).getNameZh());
    }

    @OnClick({R.id.searchLayout})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.searchLayout:
                if (!TextUtils.isEmpty(gpsCity)) {
                    CineToast.showShort(gpsCity);
                }
                break;
        }
    }

    private void initSearch() {
        searchLayout.edSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    adapter.clear();
                    adapter.addAll(search(newText));
                    ;
                } else {
                    adapter.addAll(cityBeanResponse.getData());
                }

                return false;
            }
        });
    }

    private List<CityBean> search(String query) {
        List<CityBean> filterDatas = new ArrayList<>();
        for (CityBean cityBean : cityBeanResponse.getData()) {
            if (!TextUtils.isEmpty(cityBean.getName())
                    && !TextUtils.isEmpty(cityBean.getNameZh())
                    && !TextUtils.isEmpty(cityBean.getAbbreviation())) {
                if (cityBean.getName().substring(0, 1).contains(query)) {
                    filterDatas.add(cityBean);
                } else if (cityBean.getNameZh().contains(query)) {
                    filterDatas.add(cityBean);
                } else if (cityBean.getAbbreviation().contains(query.toUpperCase())) {
                    filterDatas.add(cityBean);
                }
            }
        }
        return filterDatas;
    }

    private void setRecyclerViewScroll(int pos) {
        recyclerView.scrollToPosition(pos);
        LinearLayoutManager mLayoutManager =
                (LinearLayoutManager) recyclerView.getLayoutManager();
        mLayoutManager.scrollToPositionWithOffset(pos, 0);
    }

}