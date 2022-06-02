package com.base.jetpack

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * 提供创建共享数据的ViewModel
 */
object ShareViewModelProvider : ViewModelStoreOwner {


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

    private fun getViewModelProvider(application: Application): ViewModelProvider {
        return ViewModelProvider(this, getFactory(application))
    }

    fun <T : ViewModel> getViewModel(application: Application, clazz: Class<T>): T {
        return getViewModelProvider(application).get(clazz)
    }

    fun clear() {
        viewModelStore?.clear()
        factory = null
    }
}