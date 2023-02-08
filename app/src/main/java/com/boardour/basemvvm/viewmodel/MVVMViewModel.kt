package com.boardour.basemvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.base.jetpack.BaseMutableLiveData
import com.base.viewmodel.DialogStateViewModel
import com.boardour.basemvvm.http.DialogCallback
import com.retrofit.RetrofitPresenter

class MVVMViewModel : ViewModel() {
    var name: BaseMutableLiveData<String> = BaseMutableLiveData("点击请求网络")

    fun request(dialogState: DialogStateViewModel) {
        RetrofitPresenter.post(this, "post", object : DialogCallback<String>(dialogState) {
            override fun onLoadSucceed(data: String) {
                name.postValue(data)
            }
        })
    }
}