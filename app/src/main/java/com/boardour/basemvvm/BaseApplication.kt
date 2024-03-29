package com.boardour.basemvvm

import android.app.Application
import com.base.BaseConfig
import com.boardour.basemvvm.comm.AppDialog
import com.lven.loading.LoadingManager
import com.retrofit.config.RestConfig

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 配置返回按钮，参考TitleBarConfig，项目：https://github.com/wenkency/titlebar
        // BaseConfig.IC_TITLE_BACK = R.mipmap.ic_title_back

        // 配置加载页面，实际用自己UI设置的页面：https://github.com/wenkency/loading
        LoadingManager.BASE_LOADING_LAYOUT_ID = R.layout.loading_pager_loading
        LoadingManager.BASE_RETRY_LAYOUT_ID = R.layout.loading_pager_error
        LoadingManager.BASE_DATA_ERROR_LAYOUT_ID = R.layout.loading_pager_data_error
        LoadingManager.BASE_EMPTY_LAYOUT_ID = R.layout.loading_pager_empty

        // 网络加载弹窗统一的Dialog
        BaseConfig.dialog = AppDialog()

        // 网络配置
        RestConfig
            .baseUrl("http://httpbin.org/")
            .debugUrl("http://httpbin.org/")
            .debug(true)
            //.commHeaders(HashMap()) // 添加公共请求头，根据项目自己添加
            //.commParams(HashMap()) // 添加公共请求参数，根据项目自己添加
            .register(this)
    }
}