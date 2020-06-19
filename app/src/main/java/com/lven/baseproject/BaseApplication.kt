package com.lven.baseproject

import android.app.Application
import com.lven.base.TitleBarConfig
import com.lven.loading.LoadingManager

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 配置返回按钮，其它标题配置，可以参考TitleBarConfig注释
        // https://github.com/wenkency/titlebar
        TitleBarConfig.IC_TITLE_BACK = R.mipmap.ic_title_back

        // 配置加载页面，实际用自己UI设置的页面
        // https://github.com/wenkency/loading
        LoadingManager.BASE_LOADING_LAYOUT_ID = R.layout.loading_pager_empty
        LoadingManager.BASE_RETRY_LAYOUT_ID = R.layout.loading_pager_empty
        LoadingManager.BASE_DATA_ERROR_LAYOUT_ID = R.layout.loading_pager_empty
        LoadingManager.BASE_EMPTY_LAYOUT_ID = R.layout.loading_pager_empty
    }
}