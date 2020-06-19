package com.lven.baseproject

import cn.carhouse.titlebar.DefTitleBar
import com.lven.base.AppActivity

/**
 * 普通用法，没有DataBinding
 */
class NormalActivity : AppActivity() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("普通用法")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_normal
    }
}