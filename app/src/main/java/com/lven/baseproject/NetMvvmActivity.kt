package com.lven.baseproject

import cn.carhouse.titlebar.DefTitleBar
import com.base.BindActivity
import com.lven.baseproject.databinding.ActivityNetBinding
import com.lven.baseproject.repository.NetPresenterViewModel
import com.lven.baseproject.viewmodel.NetTestViewModel

/**
 * 网络测试
 * 结合多个ViewModel的写法
 * 1.一个ViewModel负责UI显示
 * 2.一个ViewModel负责数据请求
 */
class NetMvvmActivity : BindActivity<NetTestViewModel, ActivityNetBinding>() {
    // 数据请求的ViewModel
    private lateinit var presenter: NetPresenterViewModel

    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("网络测试")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_net
    }

    override fun onBind(binding: ActivityNetBinding, viewModel: NetTestViewModel) {
        binding.vm = viewModel
        // 点击事件
        binding.click = Click()
        // 初始化网络请求
        presenter = getViewModel(NetPresenterViewModel::class.java)
    }

    override fun initViews() {
        // 网络请求数据结果
        presenter.result.observe(this) {
            viewModel.text.value = it
        }
    }

    // 网络请求业务，可以看作是P层
    override fun initNet() {
        // 网络请求
        presenter.requestNet(this)
    }

    inner class Click {
        fun requestNet() {
            presenter.requestNetShowDialog(this@NetMvvmActivity)
        }
    }

}