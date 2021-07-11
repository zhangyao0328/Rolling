package com.rolling.act.main.frament.home.ritem

import android.os.Build
import androidx.annotation.RequiresApi
import butterknife.BindView
import com.hjq.permissions.Permission
import com.rolling.R
import com.rolling.aop.Permissions
import com.rolling.base.view.BaseActivity
import com.rolling.util.CineLog
import com.rolling.view.TestView
import jp.wasabeef.richeditor.RichEditor
import java.io.File

class PreViewActivity : BaseActivity() {

    @BindView(R.id.textViewIcon)
    lateinit var textViewIcon: RichEditor

    override fun getLayoutContextView(): Int {
        return R.layout.activity_pre_view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun init() {
        textViewIcon.setInputEnabled(false)
        textViewIcon.html = intent.getStringExtra(this.javaClass.name)

        var str = textViewIcon.html


        lambda("这个字符串是$str")

    }





    override fun succeed(t: Any?, tag: Int) {
    }

    override fun error(t: Any?, tag: Int) {
    }

    fun printLen(str: String = "hello"): String {
        return str
    }

    var lambda = {name : String ->
        CineLog.e(name)
        CineLog.e(name)
    }


}