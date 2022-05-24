package com.lven.baseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * 网络请求测试
 */
class NetViewModel : ViewModel() {
    var text = MutableLiveData("")
}