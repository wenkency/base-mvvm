package com.lven.baseproject.http

import com.base.viewmodel.DialogStateViewModel
import com.retrofit.callback.BeanCallback
import com.retrofit.core.RestClient

/**
 * 这个是带Dialog的封装
 */
abstract class DialogCallback<T>(
    private val dialogState: DialogStateViewModel,
    private val isDestroy: Boolean = false
) : BeanCallback<T>() {

    override fun onBefore(client: RestClient) {
        dialogState.showDialog.value = true
    }

    override fun onSucceed(data: T, client: RestClient) {
        dismiss()
        onLoadSucceed(data)
    }

    abstract fun onLoadSucceed(data: T)

    final override fun onError(code: Int, message: String, client: RestClient) {
        dismiss()
        onError(code, message)
    }

    open fun onError(code: Int, message: String) {

    }

    private fun dismiss() {
        dialogState.isDestroy.value = isDestroy
        dialogState.dismissDialog.value = true
    }
}