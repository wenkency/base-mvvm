package com.lven.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import cn.carhouse.titlebar.DefTitleBar

/**
 * 使用DataBinding开发的Activity
 */
abstract class NoLoadingTitleActivity<M : ViewModel,D : ViewDataBinding> : BindActivity<M,D>() {
    override fun isNeedLoading(): Boolean {
        return false
    }

    override fun isNeedTitle(): Boolean {
        return false
    }

    override fun initTitle(titleBar: DefTitleBar) {

    }
}