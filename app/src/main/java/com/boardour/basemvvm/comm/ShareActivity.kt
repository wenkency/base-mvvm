package com.boardour.basemvvm.comm

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.base.BindActivity

/**
 * 数据共享的Activity
 */
abstract class ShareActivity<M : ViewModel, T : ViewDataBinding> : BindActivity<M, T>() {
    lateinit var shareViewModel: ShareViewModel
    override fun initData() {
        // 这样创建出来只有一份
        shareViewModel = getShareViewModel(ShareViewModel::class.java)
    }
}