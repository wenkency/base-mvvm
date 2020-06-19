package com.lven.base

import androidx.databinding.ViewDataBinding

/**
 * 使用DataBinding开发的Fragment
 */
abstract class NoLoadingFragment<T : ViewDataBinding> : BindFragment<T>() {
    override fun isNeedLoading(): Boolean {
        return false
    }

}