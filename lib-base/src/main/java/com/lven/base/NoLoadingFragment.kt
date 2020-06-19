package com.lven.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

/**
 * 使用DataBinding开发的Fragment
 */
abstract class NoLoadingFragment<M : ViewModel,T : ViewDataBinding> : BindFragment<M,T>() {
    override fun isNeedLoading(): Boolean {
        return false
    }

}