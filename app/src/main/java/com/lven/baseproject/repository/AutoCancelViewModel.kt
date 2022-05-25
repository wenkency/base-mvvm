package com.lven.baseproject.repository

import androidx.lifecycle.ViewModel
import com.retrofit.config.CancelNetUtils

open class AutoCancelViewModel : ViewModel() {


    /**
     * 自动取消网络
     */
    override fun onCleared() {
        CancelNetUtils.cancel(this)
    }
}