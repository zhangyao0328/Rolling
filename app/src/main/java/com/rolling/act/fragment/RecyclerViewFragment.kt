package com.rolling.act.fragment

import android.widget.RelativeLayout
import butterknife.BindView
import com.alibaba.fastjson.JSON
import com.aspsine.swipetoloadlayout.OnLoadMoreListener
import com.aspsine.swipetoloadlayout.OnRefreshListener
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout
import com.rolling.R
import com.rolling.base.view.BaseFragment
import com.rolling.bean.home.ActivitiesBean
import com.rolling.bean.tab.TopTabBean
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

    @JvmField
    @BindView(R.id.swipeToLoadLayout)
    var swipeToLoadLayout: SwipeToLoadLayout? = null

    @JvmField
    @BindView(R.id.swipe_target)
    var recyclerView: RlRecyclerView? = null

    @JvmField
    @BindView(R.id.layoutFilter)
    var layoutFilter: RelativeLayout? = null
    var adapter: RecyclerAdapter? = null
    var topTabBean: TopTabBean? = null
    private val mCurrentPosition = 0
    var mSuspensionHeight = 0
    override fun getLayoutContextView(): Int {
        return R.layout.fragment_recycler_view
    }

    override fun init() {
        val bundle = arguments
        if (bundle != null) {
            topTabBean = bundle.getSerializable(this.javaClass.name) as TopTabBean?
        }

        if(recyclerView == null || swipeToLoadLayout == null ){
            return
        }

        recyclerView!!.initCineRecyclerViewNoDecoration(context)
        swipeToLoadLayout!!.setOnRefreshListener(this)
        swipeToLoadLayout!!.setOnLoadMoreListener(this)
        adapter = RecyclerAdapter(context!!)
        recyclerView!!.adapter = adapter
    }

    private fun updateSuspensionBar() {

//        layoutFilter.setText("button " + mCurrentPosition);
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
                val homeDataBean = JSON.parseObject(o.toString(), ActivitiesBean::class.java)
                if (adapter!!.itemCount > 0) {
                    adapter!!.clearItems()
                }
                buildData(homeDataBean)
            }
            NET_GET_IRS_DATA_LOADMORE -> {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val bean = moshi.adapter(ActivitiesBean::class.java).fromJson(o.toString())
                if (bean != null) {
                    adapter!!.addItems(bean.data.items)
                }
            }
        }
        closeRecycler(swipeToLoadLayout)
    }

    override fun error(o: Any, tag: Int) {}
    override fun onLoadMore() {
        closeRecycler(swipeToLoadLayout)
    }

    override fun onRefresh() {
        if (topTabBean != null) {
            data
        }
    }

    private val data: Unit
        private get() {
            getLoad(topTabBean!!.link, null, null, NET_GET_IRS_DATA_LOADMORE, false)
        }

    private fun buildData(activitiesBean: ActivitiesBean) {
        adapter!!.addItems(activitiesBean.data.items)

//        Item item = new Item();
//        item.(adapter.TYPE_FILTER);
//        adapter.addItem(userListBean, 3);
    }
}