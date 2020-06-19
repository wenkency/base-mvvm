package com.lven.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lven.base.jetpack.BaseViewModel

/**
 * 使用DataBinding开发的Activity
 */
abstract class BindActivity<T : ViewDataBinding> : AppActivity() {


    lateinit var mBinding: T


    override fun initContentView() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        rootView = mBinding.root
        mBinding.lifecycleOwner = this
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