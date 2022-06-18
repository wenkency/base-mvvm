package com.lven.baseproject.viewmodel

import androidx.lifecycle.ViewModel
import com.base.jetpack.BaseMutableLiveData
import com.retrofit.RetrofitPresenter
import com.retrofit.callback.BeanCallback
import com.retrofit.core.RestClient

class MVVMViewModel : ViewModel() {
    var name: BaseMutableLiveData<String> = BaseMutableLiveData("点击请求网络")

    fun request() {
        RetrofitPresenter.post(this, "post", object : BeanCallback<String>() {
            override fun onSucceed(t: String, client: RestClient) {
                name.postValue(t)
            }
        })
    }
}