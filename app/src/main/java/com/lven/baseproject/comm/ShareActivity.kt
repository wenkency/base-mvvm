package com.lven.baseproject.comm

import androidx.databinding.ViewDataBinding
import com.lven.base.BindActivity

/**
 * 数据共享的Activity
 */
abstract class ShareActivity<T : ViewDataBinding> : BindActivity<T>() {
    lateinit var shareViewModel: ShareViewModel
    override fun initData() {
        // 这样创建出来只有一份
        shareViewModel = getAppViewModel(ShareViewModel::class.java)
    }
}