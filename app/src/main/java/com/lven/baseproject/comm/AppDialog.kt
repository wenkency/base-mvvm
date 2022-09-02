package com.lven.baseproject.comm

import android.app.Activity
import cn.carhouse.alert.QuickBuilder
import cn.carhouse.alert.QuickDialog
import cn.carhouse.titlebar.utils.TitleBarUtil
import com.base.IDialog
import com.lven.baseproject.R

/**
 * 公共的Dialog，网络加载调用
 */
class AppDialog : IDialog {
    private var dialog: QuickDialog? = null
    override fun showDialog(activity: Activity) {
        if (dialog == null) {
            dialog = QuickBuilder(activity)
                .setContentView(R.layout.loading_pager_loading)
                .isSetBackground(false)
                .isDimEnabled(true)
                .build()
            // 隐藏虚拟键盘
            TitleBarUtil.hideNav(dialog?.window)
        }
        dialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    override fun dismissDialog(isDestroy: Boolean) {
        dialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        // 在Activity或者Fragment销毁时调用
        if (isDestroy) {
            dialog = null
        }
    }
}