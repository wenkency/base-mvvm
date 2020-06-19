package com.lven.base

import androidx.databinding.ViewDataBinding
import cn.carhouse.titlebar.DefTitleBar

/**
 * 使用DataBinding开发的Activity
 */
abstract class NoLoadingTitleActivity<T : ViewDataBinding> : BindActivity<T>() {
    override fun isNeedLoading(): Boolean {
        return false
    }

    override fun isNeedTitle(): Boolean {
        return false
    }

    override fun initTitle(titleBar: DefTitleBar) {

    }
}