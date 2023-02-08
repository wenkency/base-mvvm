package com.boardour.basemvvm

import android.widget.Toast
import cn.carhouse.titlebar.DefTitleBar
import com.boardour.basemvvm.databinding.ActivityOtherBinding
import com.boardour.basemvvm.comm.ShareActivity
import com.boardour.basemvvm.viewmodel.OtherViewModel

/**
 * Android ViewModel演示
 */
class OtherActivity : ShareActivity<OtherViewModel, ActivityOtherBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("Other")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_other
    }

    override fun isAndroidViewModel(): Boolean {
        return true
    }

    override fun onBind(binding: ActivityOtherBinding, viewModel: OtherViewModel) {
        binding.viewModel = viewModel
        binding.click = OtherClick()
    }

    inner class OtherClick {
        fun click() {
            // 点击更改共享数据
            shareViewModel.shareName.value = "change text form other activity"
            // 更改自己的显示数据
            viewModel.name.value = "change main text"
            Toast.makeText(getAppActivity(), "change", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * 不需要加载页面，实际开发网络请求可能需要
     */
    override fun isNeedLoading(): Boolean {
        return false
    }
}