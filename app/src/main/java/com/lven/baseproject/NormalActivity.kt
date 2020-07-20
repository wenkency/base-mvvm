package com.lven.baseproject

import android.content.Intent
import android.view.View
import cn.carhouse.titlebar.DefTitleBar
import com.lven.base.AppActivity
import com.lven.base.jetpack.LiveDataBus

/**
 * 普通用法，没有DataBinding
 */
class NormalActivity : AppActivity() {
    override fun isNeedLoading(): Boolean {
        return false
    }
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("普通用法")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_normal
    }

    fun start(view: View) {
        // 发送一个信息
        LiveDataBus.instance.post("data", String::class.java,"Name")
        startActivity(Intent(this, MVVMActivity::class.java))

    }
}