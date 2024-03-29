package com.base.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.base.jetpack.ShareViewModelProvider

/**
 * 创建ViewModel工具类
 */
object ViewModelUtils {
    /**
     * 子类用
     */
    open fun <T : ViewModel> getViewModel(owner: ViewModelStoreOwner, clazz: Class<T>): T {
        return ViewModelProvider(owner).get(clazz)
    }

    /**
     * 子类用
     */
    open fun <T : ViewModel> getAndroidViewModel(
        owner: ViewModelStoreOwner,
        application: Application,
        clazz: Class<T>
    ): T {
        return ViewModelProvider(
            owner,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(clazz)
    }

    /**
     * 只有一份，公共用的，数据共享
     */
    open fun <T : ViewModel> getShareViewModel(application: Application, clazz: Class<T>): T {
        return ShareViewModelProvider.getViewModel(application, clazz)
    }
}