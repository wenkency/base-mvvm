package com.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 控制页面显示状态的ViewModel
 */
class PageStateViewModel : ViewModel() {
    // 默认显示：内容页面
    var showContent = MutableLiveData(true)

    // 空页面
    var showEmpty = MutableLiveData(false)

    // 加载页面
    var showLoading = MutableLiveData(false)

    // 网络请求失败，或者数据解析错误页面
    var showNetOrDataError = MutableLiveData(false)

    // 没有网络显示页面
    var showRetry = MutableLiveData(false)

    // 如果加载中要显示Content，就设置showLoadingContent为true
    var showLoadingContent: MutableLiveData<Boolean> = MutableLiveData(false)

}