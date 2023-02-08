package com.boardour.basemvvm

import android.content.Intent
import android.view.View
import cn.carhouse.titlebar.DefTitleBar
import com.base.AppActivity
import com.base.jetpack.LiveDataBus

/**
 * 1. 普通用法，没有DataBinding
 * 2. 利用LiveDataBus发送数据
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
        // 发送一个信息,如果isStick false 处理粘性数据，ture 不处理粘性数据
        LiveDataBus.postValue("data", String::class.java, "Name", true)
        startActivity(Intent(this, MVVMActivity::class.java))
    }

    // 不清除 LiveDataBus 发送的数据
    override fun isClearLiveData(): Boolean {
        return false
    }
}