package com.lven.baseproject

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import cn.carhouse.titlebar.DefTitleBar
import com.lven.baseproject.comm.ShareActivity
import com.lven.baseproject.databinding.ActivityMainBinding

class MainActivity : ShareActivity<ActivityMainBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("主页面")
        // 不要返回按钮
        titleBar.clearBackImage()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        val mainViewModel = MainViewModel()
        mBinding.viewModel = mainViewModel
        mBinding.click = MainClick()
        // 这里是监听通知，在OtherActivity点击的时候发生改变
        shareViewModel.shareName.observe(this, Observer {
            Log.e("TAG", "change:$it")
            mainViewModel.name.value = it
        })

    }

    inner class MainClick {
        fun click() {
            startActivity(Intent(getAppActivity(), OtherActivity::class.java))
        }
    }

    /**
     * 不需要加载页面
     */
    override fun isNeedLoading(): Boolean {
        return false
    }
}