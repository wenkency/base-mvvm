package com.lven.baseproject

import cn.carhouse.titlebar.DefTitleBar
import com.lven.base.BindActivity
import com.lven.baseproject.databinding.ActivityNetBinding

/**
 * 网络测试
 */
class NetMvvmActivity : BindActivity<NetViewModel, ActivityNetBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("网络测试")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_net
    }

    override fun onBind(binding: ActivityNetBinding, viewModel: NetViewModel) {
        binding.vm = viewModel
    }

}