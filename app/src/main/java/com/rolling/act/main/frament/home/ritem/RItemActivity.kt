package com.rolling.act.main.frament.home.ritem

import android.os.Bundle
import android.view.View
import android.widget.Button
import butterknife.BindView
import butterknife.OnClick
import com.rolling.base.view.BaseActivity
import com.rolling.R
import com.rolling.app.MyApplication
import com.rolling.bean.BaseDataBean
import com.rolling.bean.home.Item
import com.rolling.bean.home.RItemInfoBean
import com.rolling.net.HttpConfig
import com.rolling.util.OpenAcitivtyUtils
import com.rolling.view.FrescoImage
import com.rolling.view.TextViewIcon
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class RItemActivity : BaseActivity() {

    val NET_TAG_USER_STATUS: Int = 1001

    val NET_TAG_USERS: Int = 1002

    val NET_TAG_INFO: Int = 1003

    val NET_TAG_ADD: Int = 1004

    @BindView(R.id.tvTitle)
    lateinit var tvTitle: TextViewIcon

    @BindView(R.id.frescoCover)
    lateinit var frescoCover: FrescoImage

    @BindView(R.id.btAdd)
    lateinit var btAdd: Button


    var itemData: Item? = null

    override fun getLayoutContextView(): Int {
        return R.layout.activity_r_item
    }

    override fun init() {
        itemData = intent.getSerializableExtra(this.javaClass.name) as Item
        if (itemData != null) {

            getUserStatus()
            getUserList()
//            getRItemInfo()
            refresh()
        }
    }

    override fun succeed(t: Any?, tag: Int) {
        when (tag) {
            NET_TAG_USER_STATUS -> {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val bean = moshi.adapter(BaseDataBean::class.java).fromJson(t.toString())
                if (bean != null) {
                    if (bean.code == 20402) {
                        btAdd.setText("加入")
                        btAdd.visibility = View.VISIBLE
                    } else {
                        btAdd.visibility = View.GONE
                    }
                }
            }
            NET_TAG_INFO -> {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val bean = moshi.adapter(RItemInfoBean::class.java).fromJson(t.toString())
                if (bean != null) {
                    itemData = bean.data
                }
            }
            NET_TAG_ADD -> {
                getUserStatus()
            }
        }
    }

    override fun error(t: Any?, tag: Int) {
    }

    private fun refresh() {
        tvTitle.text = itemData?.Name
        frescoCover.setImageURL(itemData?.Cover)
    }

    @OnClick(R.id.btAdd, R.id.btCreate)
    fun onClicks(view: View) {
        when (view.id) {
            R.id.btAdd -> {
                addRItem()
            }
            R.id.btCreate -> {
                OpenAcitivtyUtils.openAct(MyApplication.getInstance(), CreateRItemActivity::class.java)
            }
        }
    }

    private fun getUserStatus() {
        var key: Array<String> = arrayOf("userId", "punchId")
        var value: Array<String> = arrayOf(MyApplication.getUserLoginBean()?.data?.id.toString(), itemData?.Id.toString())
        getLoad(HttpConfig.URL_API_PUNCH_USER_STATUS, key, value, NET_TAG_USER_STATUS, true)
    }

    private fun getUserList() {
        var key: Array<String> = arrayOf("punch_id")
        var value: Array<String> = arrayOf(itemData!!.Id.toString())
        getLoad(HttpConfig.URL_API_PUNCH_USERS, key, value, NET_TAG_USERS, true)
    }

    private fun getRItemInfo() {
        var key: Array<String> = arrayOf("id")
        var value: Array<String> = arrayOf(itemData!!.Id.toString())
        getLoad(HttpConfig.URL_API_PUNCH, key, value, NET_TAG_INFO, true)
    }

    /**
     * 加入活动
     */
    private fun addRItem() {
        val map = mapOf("userId" to MyApplication.getUserLoginBean().data.id.toString(), "punchId" to itemData!!.Id.toString())
        postLoad(HttpConfig.URL_API_PUNCH_ADD, map, NET_TAG_ADD, true, null)
    }


}