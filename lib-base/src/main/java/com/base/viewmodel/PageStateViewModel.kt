package com.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 控制页面显示状态的ViewModel
 */
class PageStateViewModel : ViewModel() {
    // 默认显示
    var showContent = MutableLiveData(true)
    var showEmpty = MutableLiveData(false)
    var showLoading = MutableLiveData(false)
    var showNetOrDataError = MutableLiveData(false)
    var showRetry = MutableLiveData(false)

    // 如果加载中要显示Content，就设置showLoadingContent为true
    var showLoadingContent: MutableLiveData<Boolean> = MutableLiveData(false)

}