package com.lven.baseproject

import android.widget.Toast
import cn.carhouse.titlebar.DefTitleBar
import com.base.BindActivity
import com.base.jetpack.LiveDataBus
import com.lven.baseproject.databinding.ActivityMvvmBinding
import com.lven.baseproject.viewmodel.MVVMViewModel

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
    }

    override fun initViews() {
        // 订阅信息
        LiveDataBus.with("data", String::class.java)
            .observe(this) {
                Toast.makeText(getAppActivity(), it, Toast.LENGTH_SHORT).show()
            }
        // 订阅网络请求。
        viewModel.name.observe(this) {
            it?.let {
                Toast.makeText(getAppActivity(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }


    // 不清除 LiveDataBus 发送的数据
    override fun isClearLiveData(): Boolean {
        return false
    }
}