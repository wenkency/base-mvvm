package com.lven.baseproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lven.retrofit.RetrofitPresenter
import com.lven.retrofit.callback.BeanCallback

/**
 * 网络请求测试
 */
class NetViewModel : ViewModel() {
    var text = MutableLiveData("")


}