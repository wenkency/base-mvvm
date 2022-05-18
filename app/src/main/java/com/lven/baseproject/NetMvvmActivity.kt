package com.lven.baseproject

import cn.carhouse.titlebar.DefTitleBar
import com.base.BindActivity
import com.lven.baseproject.databinding.ActivityNetBinding
import com.lven.baseproject.viewmodel.NetViewModel
import com.lven.retrofit.RetrofitPresenter
import com.lven.retrofit.callback.BeanCallback
import com.lven.retrofit.core.RestClient

/**
 * 网络测试
 * 结合了MVVM MVP写法
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

    override fun afterInitLoading() {
        showLoading(true)
    }

    // 网络请求业务，可以看作是P层
    override fun initNet() {
        // 网络请求
        RetrofitPresenter.post(this, "post", object : BeanCallback<String>() {
            override fun onSucceed(t: String) {
                showContent()
                // 更新UI
                viewModel.text.postValue(t)
            }

            override fun onError(code: Int, message: String, client: RestClient) {
                showNetOrDataError()
            }
        })
    }

}