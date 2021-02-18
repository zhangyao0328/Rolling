package com.rolling.act.fragment

import android.widget.RelativeLayout
import butterknife.BindView
import com.alibaba.fastjson.JSON
import com.aspsine.swipetoloadlayout.OnLoadMoreListener
import com.aspsine.swipetoloadlayout.OnRefreshListener
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout
import com.rolling.R
import com.rolling.act.main.frament.home.ritem.adapter.RItemAdapter
import com.rolling.base.view.BaseFragment
import com.rolling.bean.home.ActivitiesBean
import com.rolling.bean.tab.TopTabBean
import com.rolling.net.NetParameter
import com.rolling.view.RlRecyclerView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException

/**
 * @author zhangyao
 * @date 2020/11/3  12:05
 * @E-mail android_n@163.com
 */
class RecyclerViewFragment : BaseFragment(), OnRefreshListener, OnLoadMoreListener {
    private val NET_GET_IRS_DATA = 1001
    private val NET_GET_IRS_DATA_LOADMORE = 1002

    @BindView(R.id.swipeToLoadLayout)
    lateinit var swipeToLoadLayout: SwipeToLoadLayout

    @BindView(R.id.swipe_target)
    lateinit var recyclerView: RlRecyclerView

    lateinit var adapter: RItemAdapter
    var topTabBean: TopTabBean? = null


    override fun getLayoutContextView(): Int {
        return R.layout.swipe_to_load_layout_new
    }

    override fun init() {
        val bundle = arguments
        if (bundle != null) {
            topTabBean = bundle.getSerializable(this.javaClass.name) as TopTabBean?
        }

        if (recyclerView == null || swipeToLoadLayout == null) {
            return
        }

        recyclerView!!.initCineRecyclerViewNoDecoration(context)
        swipeToLoadLayout!!.setOnRefreshListener(this)
        swipeToLoadLayout!!.setOnLoadMoreListener(this)
        adapter = RItemAdapter(context!!)
        recyclerView!!.adapter = adapter
    }


    override fun onCreate() {}

    override fun firstLoadDate() {
        if (swipeToLoadLayout == null)
            return
        swipeToLoadLayout!!.isRefreshing = true
    }

    override fun succeed(o: Any, tag: Int) {
        when (tag) {
            NET_GET_IRS_DATA -> {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val homeDataBean = moshi.adapter(ActivitiesBean::class.java).fromJson(o.toString())
                if (adapter!!.itemCount > 0) {
                    adapter!!.clearItems()
                }
                if (homeDataBean != null) {
                    buildData(homeDataBean)
                }
            }
            NET_GET_IRS_DATA_LOADMORE -> {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val bean = moshi.adapter(ActivitiesBean::class.java).fromJson(o.toString())
                if (bean != null) {
                    buildData(bean)
                }
            }
        }
        closeRecycler(swipeToLoadLayout)
    }

    override fun error(o: Any, tag: Int) {

        closeRecycler(swipeToLoadLayout)
    }

    override fun onLoadMore() {
        getData(NET_GET_IRS_DATA_LOADMORE)
    }

    override fun onRefresh() {
        if (topTabBean != null) {
            getData(NET_GET_IRS_DATA)
        }
    }


    fun getData(tag: Int) {
        when (tag) {
            NET_GET_IRS_DATA -> {
                getLoad(topTabBean!!.link, null, null, tag, false)
            }
            NET_GET_IRS_DATA_LOADMORE -> {
                var key = arrayOf(NetParameter.LimitKey, NetParameter.PageKey)
                var value = arrayOf(NetParameter.LimitValue, pageInfo.nextPage.toString())
                getLoad(topTabBean!!.link, key, value, tag, false)
            }
        }

    }

    private fun buildData(activitiesBean: ActivitiesBean) {
        pageInfo = activitiesBean.data.pageInfo
        adapter!!.addItems(activitiesBean.data.items)

//        Item item = new Item();
//        item.(adapter.TYPE_FILTER);
//        adapter.addItem(userListBean, 3);
    }
}