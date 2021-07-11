package com.rolling.act.main.frament.home.ritem


import android.content.Intent
import android.view.View
import android.widget.Button
import butterknife.BindView
import butterknife.OnClick
import com.rolling.R
import com.rolling.act.main.frament.home.ritem.holder.EditDetailActivity
import com.rolling.base.view.BaseActivity
import com.rolling.util.CineLog
import com.rolling.util.OpenAcitivtyUtils
import com.rolling.util.isView
import com.rolling.util.pri
import me.nereo.multi_image_selector.MultiImageSelector
import me.nereo.multi_image_selector.MultiImageSelectorActivity


/**
 * 发布活动
 */
class CreateRItemActivity : BaseActivity() {

    private val REQUEST_IMAGE = 100


    @BindView(R.id.btInsertImg)
    lateinit var btInsertImg: Button

    override fun succeed(t: Any?, tag: Int) {
    }

    override fun error(t: Any?, tag: Int) {
    }

    override fun getLayoutContextView(): Int {
        return R.layout.activity_put_r_item
    }

    override fun init() {

        s()
        val a = arrayOf(1, 3, 5, 7)
        a?.let {
            CineLog.e("let${it.javaClass}")
        }

        a?.run {
            CineLog.e("run${this.javaClass}")
        }

        a?.also {
            CineLog.e("also${this.javaClass}")

        }

        a?.takeIf { it.size > 3 }.also { CineLog.e("takeif${a.size}") }


    }

    @OnClick(R.id.btInsertImg, R.id.btSaveDrafts, R.id.btSubmit, R.id.edDetail)
    fun onClicks(view: View) {
        when (view.id) {
            R.id.btInsertImg -> {
                MultiImageSelector.create()
                        .single()
                        .showCamera(false)
                        .start(this, REQUEST_IMAGE)
            }
            R.id.btSaveDrafts -> {
            }
            R.id.btSubmit -> {
//                mEditor.html?.let {
//                    var bundle = Bundle()
//                    bundle.putString(PreViewActivity::class.java.name, mEditor.html
//                    + "<br><img src=http://qiniu.postrock.com.cn/tmp/wx6c9fc6f6d77239c7.o6zAJs7vhhLHSt4zxHfb8GKG3F0M.9idwAE3lxmIK6d1be9cf11fd6a09297fd97ea2d82c3c.JPG  width=\"100\"><br>"
//                    )
//                    OpenAcitivtyUtils.openAct(this, PreViewActivity::class.java, bundle)
//                }
            }
            R.id.edDetail -> {
                OpenAcitivtyUtils.openAct(this, EditDetailActivity::class.java)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE) {
            when (resultCode) {
                RESULT_OK -> {
                    val path: List<String>? = data!!.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)
                    if (path != null) {
                        if (path.size > 0) {
//                            mEditor.insertImage(path.get(0), path.get(0) + "\" style=\"max-width:100%")
                        }
                    }
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun s() {
        var list1 = arrayListOf(1, 2, 3, 4, 6, 8, 9)

        var l = a(list1)
        CineLog.e("*********$l")


    }

    fun a(list1: List<Int>): List<Int> {
        var list = mutableListOf<Int>()
        for (item in list1) {
            if (item % 3 == 0) {
                list.add(item)
            }
        }
        return list
    }


}