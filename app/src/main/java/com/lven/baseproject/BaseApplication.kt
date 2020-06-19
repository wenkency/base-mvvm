package com.lven.baseproject

import android.app.Application
import com.lven.base.TitleBarConfig

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 配置返回按钮
        TitleBarConfig.IC_TITLE_BACK = R.mipmap.ic_title_back
    }
}