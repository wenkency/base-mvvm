package com.lven.base

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lven.base.jetpack.BaseViewModel

/**
 * 使用DataBinding开发的Fragment
 */
abstract class BindFragment<T : ViewDataBinding> : AppFragment() {


    lateinit var mBinding: T

    /**
     * 3. 初始化ContentView
     */
    override fun getRootView(container: ViewGroup?): View {
        mBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    // ===提供一些通用的方法子类用======================================================
    /**
     * 子类用
     */
    fun <T : ViewModel> getViewModel(clazz: Class<T>): T {
        return ViewModelProvider(this).get(clazz)
    }

    /**
     * 只有一份，公共用的，数据共享
     */
    fun <T : ViewModel> getAppViewModel(clazz: Class<T>): T {
        return BaseViewModel.instance.getViewModel(getAppActivity(), clazz)
    }

}