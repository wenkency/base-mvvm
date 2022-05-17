package com.lven.baseproject

import cn.carhouse.titlebar.DefTitleBar
import com.lven.base.BindActivity
import com.lven.baseproject.databinding.ActivityNetBinding
import com.lven.retrofit.RetrofitPresenter
import com.lven.retrofit.callback.BeanCallback

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
        binding.click = Click()
    }

    inner class Click {
        fun requestNet() {
            // 网络请求
            RetrofitPresenter.post(this@NetMvvmActivity, "post", object : BeanCallback<String>() {
                override fun onSucceed(t: String) {
                    // 更新UI
                    viewModel.text.postValue(t)
                }
            })
        }
    }

}