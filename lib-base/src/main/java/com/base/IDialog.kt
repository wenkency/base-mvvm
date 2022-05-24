package com.base

import android.app.Activity

/**
 * 这个是要子类实现，并且在BaseConfig配置dialog
 */
interface IDialog {
    /**
     * 显示Dialog
     */
    fun showDialog(activity: Activity)

    /**
     * 关闭Dialog
     */
    fun dismissDialog(isDestroy: Boolean = false)
}