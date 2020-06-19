package com.lven.baseproject.comm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 项目数据共享定义的类
 */
class ShareViewModel : ViewModel() {
    var shareName: MutableLiveData<String> = MutableLiveData()
}