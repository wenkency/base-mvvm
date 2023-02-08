package com.boardour.basemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * 网络请求测试
 */
class NetTestViewModel : ViewModel() {
    var text = MutableLiveData("")
}