package com.boardour.basemvvm.http

import com.base.viewmodel.PageStateViewModel
import com.retrofit.callback.BeanCallback
import com.retrofit.core.RestClient

/**
 * 这个是带页面加载状态的封装
 */
abstract class PageStateCallback<T>(
    private val pageState: PageStateViewModel,
    private val isShowContent: Boolean = true
) : BeanCallback<T>() {

    override fun onBefore(client: RestClient) {
        pageState.showLoadingContent.value = isShowContent
        pageState.showLoading.value = true
    }

    override fun onSucceed(data: T, client: RestClient) {
        pageState.showContent.value = true
        onLoadSucceed(data)
    }

    abstract fun onLoadSucceed(data: T)

    final override fun onError(code: Int, message: String, client: RestClient) {
        pageState.showNetOrDataError.value = true
        onError(code, message)
    }

    open fun onError(code: Int, message: String) {

    }
}