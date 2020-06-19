package com.lven.baseproject

import cn.carhouse.titlebar.DefTitleBar
import com.lven.base.BindActivity
import com.lven.baseproject.databinding.ActivityMvvmBinding

/**
 * MVVM用法
 */
class MVVMActivity : BindActivity<ActivityMvvmBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("MVVM用法")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_mvvm
    }

    override fun initViews() {
        // 绑定ViewModel
        mBinding.vm = MVVMViewModel()
    }
}