package com.lven.baseproject

import android.widget.Toast
import cn.carhouse.titlebar.DefTitleBar
import com.lven.baseproject.comm.ShareActivity
import com.lven.baseproject.databinding.ActivityOtherBinding

class OtherActivity : ShareActivity<OtherViewModel, ActivityOtherBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("Other")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_other
    }

    override fun bind(binding: ActivityOtherBinding, viewModel: OtherViewModel) {
        binding.viewModel = viewModel
        binding.click = OtherClick()
    }

    inner class OtherClick {
        fun click() {
            // 点击更改共享数据
            shareViewModel.shareName.value = "change text form other activity"
            viewModel.name.value = "change main text"
            Toast.makeText(getAppActivity(),"change",Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * 不需要加载页面，实际开发网络请求可能需要
     */
    override fun isNeedLoading(): Boolean {
        return false
    }
}