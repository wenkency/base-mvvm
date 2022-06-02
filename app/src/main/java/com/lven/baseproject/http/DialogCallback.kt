package com.lven.baseproject.http

import com.base.AppActivity
import com.retrofit.callback.BeanCallback
import com.retrofit.core.RestClient

/**
 * 这个是带Dialog的封装
 */
abstract class DialogCallback<T>(
    var activity: AppActivity? = null,
    var isDestroy: Boolean = false
) : BeanCallback<T>() {

    override fun onBefore(client: RestClient) {
        activity?.showDialog()
    }

    override fun onSucceed(data: T, client: RestClient) {
        onLoadSucceed(data)
        activity?.dismissDialog(isDestroy)
        activity = null
    }

    abstract fun onLoadSucceed(data: T)

    override fun onError(code: Int, message: String, client: RestClient) {
        activity?.dismissDialog(isDestroy)
        activity = null
    }
}