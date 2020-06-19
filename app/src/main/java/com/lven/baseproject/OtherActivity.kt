package com.lven.baseproject

import cn.carhouse.titlebar.DefTitleBar
import com.lven.baseproject.comm.ShareActivity
import com.lven.baseproject.databinding.ActivityOtherBinding

class OtherActivity : ShareActivity<ActivityOtherBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("Other")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_other
    }

    override fun initViews() {
        mBinding.viewModel = OtherViewModel()
        mBinding.click = OtherClick()
    }

    inner class OtherClick {
        fun click() {
            // 点击更改共享数据
            shareViewModel.shareName.value = "share"
        }
    }


    /**
     * 不需要加载页面，实际开发网络请求可能需要
     */
    override fun isNeedLoading(): Boolean {
        return false
    }
}