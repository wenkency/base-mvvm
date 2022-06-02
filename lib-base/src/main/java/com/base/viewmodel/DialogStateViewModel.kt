package com.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 控制页面显示状态的ViewModel
 */
class DialogStateViewModel : ViewModel() {
    // 控制Dialog
    var showDialog = MutableLiveData(false)
    var dismissDialog = MutableLiveData(false)

    // 是否销毁公用的Dialog
    var isDestroy: MutableLiveData<Boolean> = MutableLiveData(false)
}