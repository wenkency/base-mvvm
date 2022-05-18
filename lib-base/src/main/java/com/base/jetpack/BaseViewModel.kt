package com.base.jetpack

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * 数据共享用
 */
object BaseViewModel : ViewModelStoreOwner {


    private var viewModelStore: ViewModelStore? = null
    private var factory: ViewModelProvider.Factory? = null
    override fun getViewModelStore(): ViewModelStore {
        if (viewModelStore == null) {
            viewModelStore = ViewModelStore()
        }
        return viewModelStore!!
    }

    private fun getFactory(application: Application): ViewModelProvider.Factory {
        if (factory == null) {
            factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }
        return factory!!
    }

    private fun getViewModelProvider(activity: Activity): ViewModelProvider {
        return ViewModelProvider(this, getFactory(activity.application))
    }

    fun <T : ViewModel> getViewModel(activity: Activity, clazz: Class<T>): T {
        return getViewModelProvider(activity).get(clazz)
    }

    fun clear() {
        viewModelStore?.clear()
        factory = null
    }
}