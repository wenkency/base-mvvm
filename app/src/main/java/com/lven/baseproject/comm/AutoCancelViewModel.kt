package com.lven.baseproject.comm

import androidx.lifecycle.ViewModel
import com.retrofit.config.CancelNetUtils

open class AutoCancelViewModel : ViewModel() {
    override fun onCleared() {
        CancelNetUtils.cancel(this)
    }
}