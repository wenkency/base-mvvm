package com.boardour.basemvvm

import cn.carhouse.titlebar.DefTitleBar
import com.base.BindActivity
import com.boardour.basemvvm.databinding.ActivityNetBinding
import com.boardour.basemvvm.repository.NetPresenterViewModel
import com.boardour.basemvvm.viewmodel.NetTestViewModel

/**
 * 网络测试
 * 结合多个ViewModel的写法
 * 1.一个ViewModel负责UI显示
 * 2.一个ViewModel负责数据请求
 * 3. 还添加网络请求、加载页面、统一Dialog显示
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
        // 网络请求,可以控制页面
        presenter.requestNet(pageState)
    }

    inner class Click {
        fun requestNet() {
            // 网络请求，可以控制Dialog
            presenter.requestNetShowDialog(dialogState)
        }
    }

}