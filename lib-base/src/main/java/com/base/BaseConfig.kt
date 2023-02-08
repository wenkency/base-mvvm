package com.base

import android.app.Activity
import android.graphics.Color
import com.boardour.libmvvm.R

object BaseConfig {
    /**
     * 返回按钮，用户可以在Application更改
     */
    var IC_TITLE_BACK: Int = R.mipmap.ic_title_back

    /**
     * 返回按钮图片过滤颜色
     */
    var IC_TITLE_BACK_FILTER_COLOR = Color.WHITE

    /**
     * 内容背景颜色
     */
    var CONTENT_COLOR = Color.WHITE

    /**
     * 标题颜色
     */
    var TITLE_TEXT_COLOR = Color.WHITE

    /**
     * 标题右边文本颜色
     */
    var TITLE_RIGHT_TEXT_COLOR = Color.WHITE

    /**
     * 标题栏的颜色
     */
    var TITLE_CONTENT_COLOR = Color.parseColor("#6200EE")

    /**
     * 标题字体是否黑色
     */
    var TITLE_DARK = false

    /**
     * true 时fitSystem 为false
     */
    var TITLE_TRANS = true

    /**
     * 虚拟键盘透明
     */
    var navigationBarTrans = true

    /**
     * 隐藏虚拟键盘
     */
    var hideNavigationBar = true

    // ===================Dialog统一配置=================
    var dialog: IDialog? = null

    fun showDialog(activity: Activity) {
        dialog?.showDialog(activity)
    }

    fun dismissDialog(isDestroy: Boolean = false) {
        dialog?.dismissDialog(isDestroy)
    }
    // ===================Dialog统一配置=================
}