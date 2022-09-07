package com.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 控制页面显示状态的ViewModel
 */
class DialogStateViewModel : ViewModel() {
    // 显示Dialog
    var showDialog = MutableLiveData(false)

    // 关闭Dialog
    var dismissDialog = MutableLiveData(false)

    // 是否销毁公用的Dialog
    var isDestroy: MutableLiveData<Boolean> = MutableLiveData(false)
}