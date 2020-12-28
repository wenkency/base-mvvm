package com.lven.baseproject

import android.widget.Toast
import androidx.lifecycle.Observer
import cn.carhouse.titlebar.DefTitleBar
import com.lven.base.BindActivity
import com.lven.base.jetpack.LiveDataBus
import com.lven.baseproject.databinding.ActivityMvvmBinding

/**
 * MVVM用法
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
        LiveDataBus.instance.with("data", String::class.java)
            .observe(this, Observer {
                Toast.makeText(getAppActivity(), it, Toast.LENGTH_SHORT).show()
            })
        // 订阅网络请求。
        viewModel.name.observe(this) {

        }
    }

    override fun initNet() {
        viewModel.request()
    }
}