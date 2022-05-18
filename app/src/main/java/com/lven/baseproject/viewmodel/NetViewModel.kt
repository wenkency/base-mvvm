package com.lven.baseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lven.retrofit.RetrofitPresenter
import com.lven.retrofit.callback.BeanCallback

/**
 * 网络请求测试
 */
class NetViewModel : ViewModel() {
    var text = MutableLiveData("")

    /**
     * 点击事件
     */
    fun requestNet() {
        // 网络请求
        RetrofitPresenter.post(null, "post", object : BeanCallback<String>() {
            override fun onSucceed(t: String) {
                // 更新UI
                text.postValue(t)
            }
        })
    }
}