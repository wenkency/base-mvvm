package com.boardour.basemvvm

import cn.carhouse.titlebar.DefTitleBar
import com.base.BindActivity
import com.base.jetpack.LiveDataBus
import com.boardour.basemvvm.databinding.ActivityMvvmBinding
import com.boardour.basemvvm.viewmodel.MVVMViewModel

/**
 * MVVM用法
 * 1.
 */
class MVVMActivity : BindActivity<MVVMViewModel, ActivityMvvmBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("MVVM用法")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_mvvm
    }

    override fun onBind(binding: ActivityMvvmBinding, viewModel: MVVMViewModel) {
        // 绑定ViewModel
        binding.vm = viewModel
        // 绑定点击事件
        binding.click = Click()
    }

    override fun initViews() {
        // 订阅信息
        LiveDataBus.with("data", String::class.java)
            .observe(this) {
                //Toast.makeText(getAppActivity(), it, Toast.LENGTH_SHORT).show()
            }
        // 订阅网络请求。
        viewModel.name.observe(this) {
            it?.let {
                // Toast.makeText(getAppActivity(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class Click {
        fun requestNet() {
            // 网络请求，可以控制Dialog
            viewModel.request(dialogState)
        }
    }

    // 不清除 LiveDataBus 发送的数据
    override fun isClearLiveData(): Boolean {
        return false
    }
}