package com.lven.baseproject.http

import com.base.AppActivity
import com.retrofit.callback.BeanCallback
import com.retrofit.core.RestClient

/**
 * 这个是带页面加载状态的封装
 */
abstract class PageStateCallback<T>(
    var activity: AppActivity? = null,
    var isShowContent: Boolean = true
) : BeanCallback<T>() {

    override fun onBefore(client: RestClient) {
        activity?.showLoading(isShowContent)
    }

    override fun onSucceed(data: T, client: RestClient) {
        onLoadSucceed(data)
        activity?.showContent()
        activity = null
    }

    abstract fun onLoadSucceed(data: T)

    override fun onError(code: Int, message: String, client: RestClient) {
        activity?.showNetOrDataError()
        activity = null
    }
}